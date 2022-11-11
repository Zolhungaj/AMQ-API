package tech.zolhungaj.amqapi.servercommands.objects;

public record Donation(
    String avatarName,
    double amount,
    String username
) {
}