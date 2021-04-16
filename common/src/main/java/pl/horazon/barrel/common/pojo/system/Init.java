package pl.horazon.barrel.common.pojo.system;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.horazon.barrel.common.pojo.BarrelMsg;

@Data
@AllArgsConstructor
public class Init extends BarrelMsg {
    private String login;
}
