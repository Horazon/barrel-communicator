package pl.horazon.barrel.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User extends Msg{
    private Long id;
    private String login;
}
