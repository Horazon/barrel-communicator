package pl.horazon.fx.controllers;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import pl.horazon.barrel.common.pojo.system.UserList;
import pl.horazon.fx.events.NewDirectChatFxEvent;
import pl.horazon.fx.service.BarrelEventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class UserListController implements Initializable {
    public ListView<String> fxUserList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BarrelEventBus.register(this);

        fxUserList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    String login = fxUserList.getSelectionModel()
                            .getSelectedItem();

                    BarrelEventBus.post(new NewDirectChatFxEvent(login));
                    //use this to do whatever you want to. Open Link etc.
                }
            }
        });
    }

    @Subscribe
    public void usersList(UserList list) {
        Platform.runLater(() -> {
            list.getUsers().stream().forEach(user -> fxUserList.getItems().add(user.getLogin()));
        });
    }
}
