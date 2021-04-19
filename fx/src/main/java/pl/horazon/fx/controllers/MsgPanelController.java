package pl.horazon.fx.controllers;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import pl.horazon.barrel.common.pojo.domain.DirectChatMsg;
import pl.horazon.barrel.common.pojo.domain.GroupChatMsg;
import pl.horazon.fx.events.NewDirectChatFxEvent;
import pl.horazon.fx.service.BarrelEventBus;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MsgPanelController implements Initializable {

    public ListView fxListMsg;
    public Tab fxMainChatTab;
    public ChatTabController fxMainChatTabController;
    public TabPane tabPane;

    private ObservableList<GroupChatMsg> studentObservableList;

    private List<ChatTabController> chats;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        chats = new ArrayList<>();

        BarrelEventBus.register(this);
    }

    @Subscribe
    public void stringEvent(GroupChatMsg msg) {
        Platform.runLater(() -> fxMainChatTabController.newMsg(msg));
    }

    @Subscribe
    public void directChatMsg(DirectChatMsg msg) {

        // FIXME: ugly as hell
        Platform.runLater(() -> ((ChatTabController)(tabPane.getTabs().get(1).getContent())).newDirectChatMsg(msg));
    }

    @Subscribe
    public void newDirectChatFxEvent(NewDirectChatFxEvent msg) {
        ChatTabController newChat = new ChatTabController(false);

        Tab tab1 = new Tab("Planes", newChat);

        chats.add(newChat);
        tabPane.getTabs().add(tab1);
    }
}
