package tech.zolhungaj.amqapi.client;

public record AuthenticationResponse(boolean verified, String html, boolean alreadyOnline) {
}
