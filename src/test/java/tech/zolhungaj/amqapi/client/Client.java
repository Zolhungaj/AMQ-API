package tech.zolhungaj.amqapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.time.Duration;
import java.util.logging.Level;

public class Client implements AutoCloseable{

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private static final String BASE_URL ="https://animemusicquiz.com";
    private static final String SIGN_IN_URL = "/signIn";
    private static final String TOKEN_URL = "/socketToken";
    private static final String LOGOUT_URL = "/signout";
    private static final URI SOCKET_URL = URI.create("https://socket.animemusicquiz.com");

    private static final WebClient webClient = WebClient.builder()
            .baseUrl(BASE_URL)
            .defaultHeader("User-Agent", "AMQ-API Client")
            .build();

    private static final String WEB_CLIENT_LOG_CATEGORY = "Client";
    private static final Level WEB_CLIENT_LOG_LEVEL = Level.ALL;

    private String token = null;
    private int port = 0;

    private final Authentication authentication;


    public Client(String username, String password){
        this.authentication = new Authentication(username, password);
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
                .log(WEB_CLIENT_LOG_CATEGORY, WEB_CLIENT_LOG_LEVEL)
                .block(TIMEOUT);
        if(authenticationResponse == null){
            throw new AuthenticationFailedException("authentication response is null");
        }
        if(!authenticationResponse.verified()){
            throw new AuthenticationFailedException("verified is false");
        }
        LOG.info("Authentication successful!");
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
            throw new AuthenticationFailedException("token response is null");
        }
        if(tokenResponse.token().isBlank()){
            throw new AuthenticationFailedException("token is blank");
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
    public void close() throws Exception {

    }
}
