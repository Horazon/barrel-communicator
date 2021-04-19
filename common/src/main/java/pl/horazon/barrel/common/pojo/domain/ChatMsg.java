package pl.horazon.barrel.common.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.horazon.barrel.common.pojo.BarrelMsg;
import pl.horazon.barrel.common.pojo.enums.MsgDest;
import pl.horazon.barrel.common.pojo.enums.MsgSource;

@Data
@AllArgsConstructor
public abstract class ChatMsg extends BarrelMsg {

    private final String from;
    private final String to;
    private final MsgSource source;
    private final MsgDest dest;
    private final String content;

}
