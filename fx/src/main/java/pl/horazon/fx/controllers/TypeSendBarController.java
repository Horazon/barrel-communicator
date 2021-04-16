package pl.horazon.fx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pl.horazon.fx.events.BarrelEventBus;
import pl.horazon.fx.events.NewTxtMsg;

import java.net.URL;
import java.util.ResourceBundle;

public class TypeSendBarController implements Initializable {

    public TextField fxTxtMsg;

    public void onClickSend(ActionEvent actionEvent) {
        BarrelEventBus.post(new NewTxtMsg(fxTxtMsg.getText()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BarrelEventBus.register(this);
    }
}
