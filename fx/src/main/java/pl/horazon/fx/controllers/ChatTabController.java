package pl.horazon.fx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import pl.horazon.barrel.common.pojo.domain.GroupChatMsg;
import pl.horazon.fx.events.NewMsgFxEvent;
import pl.horazon.fx.service.BarrelEventBus;
import pl.horazon.fx.util.FXMLUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatTabController extends VBox implements Initializable {
    public ListView fxListMsg;
    private ObservableList<GroupChatMsg> studentObservableList;

    public TypeSendBarController typeSendBarController;

    public ChatTabController() {
        FXMLUtils.loadFXML(this, "/fxml/chat-tab.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        studentObservableList = FXCollections.observableArrayList();

        fxListMsg.setItems(studentObservableList);
        fxListMsg.setCellFactory(studentListView -> new StudentListViewCell());

        typeSendBarController.setConsumer(this::onEnter);
    }

    private void onEnter(String txt) {
        BarrelEventBus.post(new NewMsgFxEvent(txt));
    }

    public void newMsg(GroupChatMsg msg) {
        studentObservableList.add(msg);
    }
}
