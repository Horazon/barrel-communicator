package pl.horazon.fx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import pl.horazon.barrel.common.pojo.domain.GroupChatMsg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatTabController extends VBox implements Initializable {
    public ListView fxListMsg;
    private ObservableList<GroupChatMsg> studentObservableList;

    public ChatTabController(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/chat-tab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        studentObservableList = FXCollections.observableArrayList();

        fxListMsg.setItems(studentObservableList);
        fxListMsg.setCellFactory(studentListView -> new StudentListViewCell());
    }

    public void newMsg(GroupChatMsg msg){
        studentObservableList.add(msg);
    }
}
