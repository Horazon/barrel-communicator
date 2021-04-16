package pl.horazon.fx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pl.horazon.fx.service.BarrelEventBus;
import pl.horazon.fx.events.NewMsgFxEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TypeSendBarController implements Initializable {

    public TextField fxTxtMsg;

    public void onClickSend(ActionEvent actionEvent) {
        BarrelEventBus.post(new NewMsgFxEvent(fxTxtMsg.getText()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BarrelEventBus.register(this);
    }
}
