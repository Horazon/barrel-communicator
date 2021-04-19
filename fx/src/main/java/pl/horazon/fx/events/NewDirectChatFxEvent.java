package pl.horazon.fx.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewDirectChatFxEvent {
    private String login;
}
