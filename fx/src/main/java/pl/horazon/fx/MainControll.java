package pl.horazon.fx;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.horazon.client.SocketClient;
import pl.horazon.barrel.common.pojo.TestMsg1;
import pl.horazon.barrel.common.pojo.UserList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainControll implements Initializable {

    public EventBus eventBus = new EventBus();
    public TextField fxTxtMsg;
    public ListView fxListUsers;
    public ListView fxListMsg;

    SocketClient client = new SocketClient();

    @Subscribe
    public void stringEvent(String event) {
        client.queue.add(new TestMsg1(fxTxtMsg.getText()));
    }

    @Subscribe
    public void stringEvent(TestMsg1 msg) {
        Platform.runLater(() -> fxListMsg.getItems().add(msg.getText()));
    }

    @Subscribe
    public void usersList(UserList list) {
        Platform.runLater(() -> {
            list.getUsers().stream().forEach(user -> fxListUsers.getItems().add(user.getLogin()));
        });
    }

    public void onClickSend(ActionEvent actionEvent) {
        eventBus.post(fxTxtMsg.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

        eventBus.register(this);

        try {
            client.startConnection("127.0.0.1", 6666);
            client.setMsgConsumer(eventBus::post);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
