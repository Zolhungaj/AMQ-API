package tech.zolhungaj.amqapi.clientcommands.social;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReportPlayer(
        String target,
        @JsonProperty("reportReason")
        String reason,
        int reportType
) implements SocialCommand{
    public enum ReportType {
        NONE(0),
        VERBAL_ABUSE(1),
        OFFENSIVE_NAME(2),
        NEGATIVE_ATTITUDE(3),
        CHEATING(4),
        OTHER(5);
        private final int value;
        ReportType(int value){
            this.value = value;
        }
    }
    public static ReportPlayer of(String target, String reason, ReportType reportType){
        return new ReportPlayer(target, reason, reportType.value);
    }
    @Override
    public String command() {
        return "report player";
    }
}
