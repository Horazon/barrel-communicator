package pl.horazon.fx.controllers;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import pl.horazon.barrel.common.pojo.domain.GroupChatMsg;
import pl.horazon.fx.service.BarrelEventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class MsgPanelController implements Initializable {

    public ListView fxListMsg;
    public Tab fxMainChatTab;
    public ChatTabController fxMainChatTabController;

    private ObservableList<GroupChatMsg> studentObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        BarrelEventBus.register(this);
    }

    @Subscribe
    public void stringEvent(GroupChatMsg msg) {
        Platform.runLater(() -> fxMainChatTabController.newMsg(msg));
    }
}
