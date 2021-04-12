package pl.horazon.fx.controllers;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import pl.horazon.barrel.common.pojo.UserList;
import pl.horazon.fx.events.BarrelEventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class UserListController implements Initializable {
    public ListView fxUserList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BarrelEventBus.register(this);
    }

    @Subscribe
    public void usersList(UserList list) {
        Platform.runLater(() -> {
            list.getUsers().stream().forEach(user -> fxUserList.getItems().add(user.getLogin()));
        });
    }
}
