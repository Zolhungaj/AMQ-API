package tech.zolhungaj.amqapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.HttpCookieStore;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import tech.zolhungaj.amqapi.client.exceptions.*;
import tech.zolhungaj.amqapi.client.requests.AbortAuthentication;
import tech.zolhungaj.amqapi.client.requests.Authentication;
import tech.zolhungaj.amqapi.client.responses.AuthenticationResponse;
import tech.zolhungaj.amqapi.client.responses.TokenResponse;

import java.net.URI;
import java.time.Duration;
import java.util.logging.Level;

public class Client implements AutoCloseable{

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);
    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    private static final String BASE_URL ="https://animemusicquiz.com";
    private static final String SIGN_IN_URL = "/signIn";
    private static final String ABORT_SIGN_IN_URL = "/alreadyOnlineLogin";
    private static final String TOKEN_URL = "/socketToken";
    private static final String SIGN_OUT_URL = "/signout";
    private static final URI SOCKET_URL = URI.create("https://socket.animemusicquiz.com");


    private static final String WEB_CLIENT_LOG_CATEGORY = "Client";
    private static final Level WEB_CLIENT_LOG_LEVEL = Level.ALL;

    private final WebClient webClient;
    private String token = null;
    private int port = 0;

    private final Authentication authentication;
    private final boolean forceConnect;

    private int sessionId;


    public Client(String username, String password, boolean forceConnect){

        var httpClient = new HttpClient(new SslContextFactory.Client.Client());
        httpClient.setCookieStore(new HttpCookieStore());

        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("User-Agent", "AMQ-API Client")
                .clientConnector(new JettyClientHttpConnector(httpClient))
                .exchangeStrategies(ExchangeStrategies
                        .builder()
                        .codecs(
                                clientCodecConfigurer ->
                                clientCodecConfigurer.defaultCodecs().maxInMemorySize(50_000_000))
                        .build())
                .build();
        this.authentication = new Authentication(username, password);
        this.forceConnect = forceConnect;
        this.connect();
    }

    private void connect(){
        loadWebpage();
        authenticate();
        getTokenAndPort();
        IO.Options options = new IO.Options();
        options.reconnection = true;
        options.reconnectionAttempts = 5;
        options.reconnectionDelay = 1000;
        options.reconnectionDelayMax = 3000;
        options.query = "{'token': '%s'}".formatted(this.token);
        Socket socket = IO.socket(SOCKET_URL.resolve("/:%d".formatted(this.port)), options);
        socket.on("sessionId", args -> {
            Object sessionId = args[0];
            LOG.info("Received sessionId {}", sessionId);
            if(sessionId instanceof Integer i){
                this.sessionId = i;
            }
        });
        socket.on("command", args -> {
            Object payload = args[0];//object with two entries: "command" and "data"
            LOG.info("Command: {}", payload);
        });
        socket.on(Socket.EVENT_CONNECT, args -> LOG.info("Connected!"));
        socket.on(Socket.EVENT_CONNECTING, args -> LOG.info("Connecting..."));
        socket.on(Socket.EVENT_CONNECT_TIMEOUT, args -> LOG.info("Connect timed out..."));
        socket.on(Socket.EVENT_CONNECT_ERROR, args -> {
            LOG.info("Connection error");
//            throw new SocketDisconnectedException("connect_error");
        });
        socket.on(Socket.EVENT_PING, args -> LOG.info("ping"));
        socket.on(Socket.EVENT_PONG, args -> LOG.info("pong"));

        socket.on(Socket.EVENT_MESSAGE, args -> LOG.info("message"));

        socket.on(Socket.EVENT_DISCONNECT, args -> LOG.info("Socket disconnected..."));
        socket.on(Socket.EVENT_RECONNECT_ERROR, args -> LOG.info("Reconnection error"));
        socket.on(Socket.EVENT_RECONNECT, args -> LOG.info("Successfully reconnected! {}", this.sessionId));
        socket.on(Socket.EVENT_RECONNECTING, args -> LOG.info("Reconnecting..."));
        socket.on(Socket.EVENT_RECONNECT_FAILED, args -> {
            throw new SocketDisconnectedException("Unable to reconnect to the server");
        });
        socket.on(Socket.EVENT_RECONNECT_ATTEMPT, args -> LOG.info("Attempting to reconnect: {}", this.sessionId));
        socket.on(Socket.EVENT_ERROR, args -> {
            throw new SocketDisconnectedException("error");
        });

        socket.connect();

    }

    private void loadWebpage(){
        LOG.info("Loading webpage...");
        webClient
                .get()
                .retrieve()
                .toBodilessEntity()
                .log(WEB_CLIENT_LOG_CATEGORY, WEB_CLIENT_LOG_LEVEL)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(
                                ex.getStatusCode().is5xxServerError()
                                        ?
                                        new ServerUnavailableException(ex)
                                        :
                                        new UnexpectedResponseException(ex)
                        )
                )
                .block(TIMEOUT);
        LOG.info("Webpage loaded!");
    }

    private void authenticate(){
        LOG.info("Starting authentication...");
        AuthenticationResponse authenticationResponse = webClient
                .post()
                .uri(SIGN_IN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(this.authentication)
                .retrieve()
                .bodyToMono(AuthenticationResponse.class)
                .onErrorResume(WebClientResponseException.class, Client::authenticationErrorHandling)
                .log(WEB_CLIENT_LOG_CATEGORY, WEB_CLIENT_LOG_LEVEL)
                .block(TIMEOUT);

        if(authenticationResponse == null){
            throw new AuthenticationFailedException("Authentication response is null");
        }
        if(!authenticationResponse.verified()){
            throw new AuthenticationFailedException("Verified is false");
        }
        LOG.info("Authentication successful!");
        if(!forceConnect && authenticationResponse.alreadyOnline()){
            LOG.info("Account already logged in");
            abortAuthentication();
        }
    }

    private static Mono<AuthenticationResponse> authenticationErrorHandling(WebClientResponseException exception){

        return switch(exception.getStatusCode()){
            //endpoint returns "Unauthorized" on incorrect password, so we construct our own response
            case UNAUTHORIZED -> Mono.just(new AuthenticationResponse(false, null, false));
            case
                    INTERNAL_SERVER_ERROR,
                    BAD_GATEWAY,
                    SERVICE_UNAVAILABLE,
                    GATEWAY_TIMEOUT -> Mono.error(new ServerUnavailableException(exception));
            case TOO_MANY_REQUESTS -> Mono.error(new TooManyRequestsException(exception));
            default -> Mono.error(new UnexpectedResponseException(exception));
        };
    }

    private void abortAuthentication(){
        LOG.info("Aborting authentication");
        webClient
                .post()
                .uri(ABORT_SIGN_IN_URL)
                .bodyValue(new AbortAuthentication(false))
                .retrieve()
                .toBodilessEntity()
                .onErrorResume(
                        WebClientResponseException.class,
                        ex -> ex.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT ? Mono.empty() : Mono.error(ex))
                        //handles error where server fails to handle abort.
                .log(WEB_CLIENT_LOG_CATEGORY, WEB_CLIENT_LOG_LEVEL)
                .block(TIMEOUT);
        throw new AuthenticationFailedException("Account already logged in");
    }

    private void getTokenAndPort() {
        LOG.info("Getting token and port...");
        String response = webClient
                .get()
                .uri(TOKEN_URL)
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(String.class)
                .log(WEB_CLIENT_LOG_CATEGORY, WEB_CLIENT_LOG_LEVEL)
                .block(TIMEOUT);
        //workaround because the server returns "Content type 'text/html;charset=UTF-8'"
        TokenResponse tokenResponse;
                try{
                    ObjectMapper mapper = new ObjectMapper();
                    tokenResponse = mapper.readValue(response, TokenResponse.class);
                }catch(JsonProcessingException e){
                    throw new UnexpectedResponseException(e);
                }
        if(tokenResponse == null){
            throw new AuthenticationFailedException("Token response is null");
        }
        if(tokenResponse.token().isBlank()){
            throw new AuthenticationFailedException("Token is blank");
        }
        this.token = tokenResponse.token();
        this.port = Integer.parseInt(tokenResponse.port());

        LOG.info("""
                    Token and port acquired!
                        Token: {},
                        Port: {}
                    """, token, port);
    }


    @Override
    public void close() {
        signOut();
    }
    private void signOut(){
        LOG.info("Signing out...");
        webClient
                .get()
                .uri(SIGN_OUT_URL)
                .retrieve()
                .toBodilessEntity()
                .log(WEB_CLIENT_LOG_CATEGORY, WEB_CLIENT_LOG_LEVEL)
                .block(TIMEOUT);
        this.token = null;
        this.port = 0;
        LOG.info("Signed out!");
    }
}
