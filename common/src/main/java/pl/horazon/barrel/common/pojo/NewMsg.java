package pl.horazon.barrel.common.pojo;

import lombok.Data;

@Data
public class NewMsg extends BarrelMsg {

    private final String from;
    private final String to;
    private final String content;
}
