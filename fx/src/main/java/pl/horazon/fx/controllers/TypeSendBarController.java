package pl.horazon.fx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lombok.Data;
import pl.horazon.fx.service.BarrelEventBus;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

@Data
public class TypeSendBarController implements Initializable {

    public TextField fxTxtMsg;

    private Consumer<String> consumer;

    public void onClickSend(ActionEvent actionEvent) {
        consumer.accept(fxTxtMsg.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BarrelEventBus.register(this);
    }
}
