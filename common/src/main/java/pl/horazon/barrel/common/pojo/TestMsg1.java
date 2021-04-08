package pl.horazon.barrel.common.pojo;

public class TestMsg1 extends Msg{
    private final String text;

    public TestMsg1(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
