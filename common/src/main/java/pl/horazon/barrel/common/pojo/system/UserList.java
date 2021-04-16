package pl.horazon.barrel.common.pojo.system;

import lombok.*;
import pl.horazon.barrel.common.pojo.BarrelMsg;

import java.util.List;

@Data
@AllArgsConstructor
public class UserList extends BarrelMsg {
    private List<User> users;
}
