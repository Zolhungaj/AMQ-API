package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import lombok.*;
import tech.zolhungaj.amqapi.clientcommands.AbstractClientCommand;
import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;

@Getter
@Setter
@ToString
public final class ExpandLibrarySubmit extends AbstractClientCommand {
    public ExpandLibrarySubmit(){
        super(ClientCommandType.SUBMIT_TO_EXPAND_LIBRARY);
    }
    private Integer annId;
    private Integer annSongId;
    private String url;
    private Integer resolution;

    @Builder
    public ExpandLibrarySubmit(Integer annId, Integer annSongId, String url, Integer resolution) {
        this();
        this.annId = annId;
        this.annSongId = annSongId;
        this.url = url;
        this.resolution = resolution;
    }
}
