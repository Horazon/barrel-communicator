package pl.horazon.fx.events;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewMsgFxEvent {
    private String msg;
}
