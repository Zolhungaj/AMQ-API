package tech.zolhungaj.amqapi.client.responses;

public record AuthenticationResponse(boolean verified, String html, Boolean alreadyOnline) {
}
