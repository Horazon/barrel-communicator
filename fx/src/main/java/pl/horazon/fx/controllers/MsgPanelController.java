package pl.horazon.fx.controllers;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import pl.horazon.barrel.common.pojo.NewMsg;
import pl.horazon.fx.events.BarrelEventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class MsgPanelController implements Initializable {
    public ListView fxListMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BarrelEventBus.register(this);
    }

    @Subscribe
    public void stringEvent(NewMsg msg) {
        Platform.runLater(() -> fxListMsg.getItems().add(msg.getContent()));
    }
}
