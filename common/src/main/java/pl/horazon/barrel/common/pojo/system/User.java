package pl.horazon.barrel.common.pojo.system;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.horazon.barrel.common.pojo.BarrelMsg;

@Data
@AllArgsConstructor
public class User extends BarrelMsg {
    private final Long id;
    private final String login;
}
