package pl.horazon.fx.events;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewDirectMsgFxEvent {
    private String msg;
}
