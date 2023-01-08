package tech.zolhungaj.amqapi.client.handlers;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import tech.zolhungaj.amqapi.client.exceptions.*;
import tech.zolhungaj.amqapi.client.requests.AbortAuthentication;
import tech.zolhungaj.amqapi.client.requests.Authentication;
import tech.zolhungaj.amqapi.client.responses.AuthenticationResponse;
import tech.zolhungaj.amqapi.client.responses.TokenResponse;

import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.rmi.UnexpectedException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WebHandler implements Closeable {

    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    private static final Moshi MOSHI = new Moshi
            .Builder()
            .build();

    private static final URI BASE_URL = URI.create("https://animemusicquiz.com");
    private static final URI SIGN_IN_URL = BASE_URL.resolve("/signIn");
    private static final URI ABORT_SIGN_IN_URL = BASE_URL.resolve("/alreadyOnlineLogin");
    private static final URI TOKEN_URL = BASE_URL.resolve("/socketToken");
    private static final URI SIGN_OUT_URL = BASE_URL.resolve("/signout");
    private final HttpClient httpClient;
    private String token = null;
    private int port = 0;

    private final Authentication authentication;
    private final boolean forceConnect;

    public WebHandler(Authentication authentication, boolean forceConnect){
        this.authentication = authentication;
        this.forceConnect = forceConnect;
        this.httpClient = HttpClients.custom()
                .setConnectionTimeToLive(TIMEOUT.toMillis(), TimeUnit.MILLISECONDS)
                .setUserAgent("AMQ-API Client")
                .build();
        log.debug("WebHandler initialised");
    }

    public void connect(){
        log.info("Connecting for authentication...");
        try{
            loadWebpage();
            authenticate();
            fetchTokenAndPort();
        }catch (IOException e){
            throw new AuthenticationFailedException(e);
        }
        log.info("Authentication complete!");
    }

    /** Loads the login web page so that we have the required cookies to log in
     * @throws IOException if a transfer error occurs
     */
    private void loadWebpage() throws IOException {
        log.info("Loading webpage...");
        HttpGet get = new HttpGet(BASE_URL);
        try{
            HttpResponse response = this.httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            String reasonPhrase = response.getStatusLine().getReasonPhrase();
            String cause = statusCode + ": " + reasonPhrase;
            if(statusCode != HttpStatus.SC_OK){
                if(statusCode >= 500 && statusCode < 600){
                    throw new ServerUnavailableException(cause);
                }else{
                    throw new UnexpectedResponseException(cause);
                }
            }else{
                log.info("Webpage loaded! {}", reasonPhrase);
            }
        }finally {
            get.releaseConnection();
        }
    }

    private void authenticate() throws IOException{
        log.info("Starting authentication...");
        HttpPost post = new HttpPost(SIGN_IN_URL);
        post.addHeader(new BasicHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType()));
        String json = MOSHI.adapter(Authentication.class).toJson(this.authentication);
        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        post.setEntity(requestEntity);
        try{
            HttpResponse response = httpClient.execute(post);

            String responseReasonPhrase = response.getStatusLine().getReasonPhrase();
            int responseStatusCode = response.getStatusLine().getStatusCode();
            String fullReasonPhrase = responseStatusCode + ": " + responseReasonPhrase;
            AuthenticationResponse authenticationResponse = switch (responseStatusCode){
                case HttpStatus.SC_OK -> MOSHI.adapter(AuthenticationResponse.class).fromJson(new String(response.getEntity().getContent().readAllBytes()));
                case HttpStatus.SC_UNAUTHORIZED -> new AuthenticationResponse(false, null, false);
                    //endpoint returns just the string "Unauthorized" on incorrect password, so we construct our own response
                case HttpStatus.SC_INTERNAL_SERVER_ERROR,
                        HttpStatus.SC_BAD_GATEWAY,
                        HttpStatus.SC_SERVICE_UNAVAILABLE,
                        HttpStatus.SC_GATEWAY_TIMEOUT -> throw new ServerUnavailableException(fullReasonPhrase);
                case HttpStatus.SC_TOO_MANY_REQUESTS -> throw new TooManyRequestsException(fullReasonPhrase);
                default -> throw new UnexpectedException(fullReasonPhrase);
            };
            if(authenticationResponse == null){
                throw new AuthenticationFailedException("Authentication response is null");
            }
            if(!authenticationResponse.verified()){
                throw new AuthenticationFailedException("Verified is false");
            }
            if(!forceConnect && Boolean.TRUE.equals(authenticationResponse.alreadyOnline())){
                log.info("Account already logged in");
                abortAuthentication();
                throw new AuthenticationFailedException("Account already logged in");
            }
            log.info("Authentication successful!");
        }finally {
            post.releaseConnection();
        }
    }

    private void abortAuthentication() throws IOException{
        log.info("Aborting authentication");
        HttpPost post = new HttpPost(ABORT_SIGN_IN_URL);
        String json = MOSHI.adapter(AbortAuthentication.class).toJson(new AbortAuthentication(false));
        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        post.setEntity(requestEntity);
        try{
            HttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_GATEWAY_TIMEOUT){
                log.warn("Abort endpoint is still broken");
            }
        }finally {
            post.releaseConnection();
        }
        log.info("Authentication aborted!");
    }

    private void fetchTokenAndPort() throws IOException{
        log.info("Getting token and port...");
        HttpGet get = new HttpGet(TOKEN_URL);
        get.addHeader(new BasicHeader(HttpHeaders.ACCEPT, "*/*"));
        //workaround because the server returns "Content type 'text/html;charset=UTF-8'"
        try{

            HttpResponse response = httpClient.execute(get);
            log.info("{}", response);
            String json = new String(response.getEntity().getContent().readAllBytes());
            log.info(json);
            TokenResponse tokenResponse;
            try{
                tokenResponse = MOSHI.adapter(TokenResponse.class).fromJson(json);
            }catch(JsonDataException e){
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

            log.info("Token and port acquired!");
            log.debug("""
                            Token: {},
                            Port: {}
                        """, token, port);
        }finally {
            get.releaseConnection();
        }
    }

    public String getToken() {
        return token;
    }

    public int getPort() {
        return port;
    }

    @Override
    public void close() {
        try{
            signOut();
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    private void signOut() throws IOException {
        log.info("Signing out...");
        HttpGet get = new HttpGet(SIGN_OUT_URL);
        HttpResponse response = httpClient.execute(get);
        log.debug("Sign-out response: {}", response);
        this.token = null;
        this.port = 0;
        log.info("Signed out!");
    }
}
