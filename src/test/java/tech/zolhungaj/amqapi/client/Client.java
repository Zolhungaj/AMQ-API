package tech.zolhungaj.amqapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

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

    private static final WebClient webClient = WebClient.builder()
            .baseUrl(BASE_URL)
            .defaultHeader("User-Agent", "AMQ-API Client")
            .build();

    private static final String WEB_CLIENT_LOG_CATEGORY = "Client";
    private static final Level WEB_CLIENT_LOG_LEVEL = Level.ALL;

    private static final boolean DEFAULT_FORCE_CONNECT = false;

    private String token = null;
    private int port = 0;

    private final Authentication authentication;
    private final boolean forceConnect;


    public Client(String username, String password){
        this(username, password, DEFAULT_FORCE_CONNECT);
    }

    public Client(String username, String password, boolean forceConnect){
        this.authentication = new Authentication(username, password);
        this.forceConnect = forceConnect;
        this.connect();
    }

    private void connect(){
        loadWebpage();
        authenticate();
        getTokenAndPort();
    }

    private void loadWebpage(){
        LOG.info("Loading webpage...");
        webClient
                .get()
                .retrieve()
                .toBodilessEntity()
                .log(WEB_CLIENT_LOG_CATEGORY, WEB_CLIENT_LOG_LEVEL)
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

    private void getTokenAndPort(){
        LOG.info("Getting token and port...");
        TokenResponse tokenResponse = webClient
                .get()
                .uri(TOKEN_URL)
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .log(WEB_CLIENT_LOG_CATEGORY, WEB_CLIENT_LOG_LEVEL)
                .block(TIMEOUT);
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
