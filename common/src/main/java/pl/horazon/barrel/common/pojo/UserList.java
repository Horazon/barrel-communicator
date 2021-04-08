package pl.horazon.barrel.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserList extends Msg{
    private List<User> users;
}
