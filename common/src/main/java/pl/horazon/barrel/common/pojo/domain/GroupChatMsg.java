package pl.horazon.barrel.common.pojo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.horazon.barrel.common.pojo.enums.MsgDest;
import pl.horazon.barrel.common.pojo.enums.MsgSource;

@Getter
@Setter
@EqualsAndHashCode
public class GroupChatMsg extends ChatMsg {

    private final String content;

    public GroupChatMsg(String from, String content) {
        super(from, null, MsgSource.USER, MsgDest.ALL);
        this.content = content;
    }
}
