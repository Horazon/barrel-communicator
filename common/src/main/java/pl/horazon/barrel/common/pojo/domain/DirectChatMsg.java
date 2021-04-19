package pl.horazon.barrel.common.pojo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.horazon.barrel.common.pojo.enums.MsgDest;
import pl.horazon.barrel.common.pojo.enums.MsgSource;

@Getter
@Setter
@EqualsAndHashCode
public class DirectChatMsg extends ChatMsg{
    public DirectChatMsg(String from, String to, String content) {
        super(from, to, MsgSource.USER, MsgDest.USER, content);
    }
}
