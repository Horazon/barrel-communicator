package pl.horazon.fx.controllers;

import com.google.common.eventbus.Subscribe;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pl.horazon.barrel.common.pojo.NewMsg;
import pl.horazon.fx.ClientProxy;
import pl.horazon.fx.events.BarrelEventBus;
import pl.horazon.fx.events.NewTxtMsg;

import java.net.URL;
import java.util.ResourceBundle;

public class TypeSendBarController implements Initializable {

    public TextField fxTxtMsg;

    @Subscribe
    public void stringEvent(NewTxtMsg event) {
        ClientProxy.getInstance().send(new NewMsg("me", "you", fxTxtMsg.getText()));
    }

    public void onClickSend(ActionEvent actionEvent) {
        BarrelEventBus.post(new NewTxtMsg());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BarrelEventBus.register(this);
    }
}
