package pl.horazon.fx.controllers;

import javafx.beans.NamedArg;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import lombok.Data;
import lombok.Getter;
import pl.horazon.barrel.common.pojo.domain.ChatMsg;
import pl.horazon.barrel.common.pojo.domain.DirectChatMsg;
import pl.horazon.barrel.common.pojo.domain.GroupChatMsg;
import pl.horazon.fx.events.NewDirectMsgFxEvent;
import pl.horazon.fx.events.NewMsgFxEvent;
import pl.horazon.fx.service.BarrelEventBus;
import pl.horazon.fx.util.FXMLUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatTabController extends Tab implements Initializable {
    public ListView fxListMsg;
    private ObservableList<ChatMsg> studentObservableList;

    public TypeSendBarController typeSendBarController;

    private boolean isMain;


    private String login;

    /*
    * https://stackoverflow.com/questions/41595035/set-custom-fxml-properties-as-parameters-for-custom-javafx-component
    * */
    public ChatTabController(@NamedArg("isMain") Boolean isMain) {
        this.isMain = isMain;
        FXMLUtils.loadFXML(this, "/fxml/chat-tab.fxml");
    }

    public void setLogin(String login) {
        this.login = login;
        setText(login);
    }

    public String getLogin() {
        return login;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        studentObservableList = FXCollections.observableArrayList();

        fxListMsg.setItems(studentObservableList);
        fxListMsg.setCellFactory(studentListView -> new StudentListViewCell());

        typeSendBarController.setConsumer(this::onEnter);
    }

    private void onEnter(String txt) {

        if(isMain)
            BarrelEventBus.post(new NewMsgFxEvent(txt));
        else
            BarrelEventBus.post(new NewDirectMsgFxEvent(txt, login));
    }

    public void newMsg(GroupChatMsg msg) {
        studentObservableList.add(msg);
    }

    public void newDirectChatMsg(DirectChatMsg msg) {
        studentObservableList.add(msg);
    }












    // POC

    private final BooleanProperty showPopupTriggerButton = new SimpleBooleanProperty(this, "showPopupTriggerButton", true);

    public final boolean getShowPopupTriggerButton() {
        return showPopupTriggerButton.get();
    }

    /**
     * Determines if the control will show a button for showing or hiding the
     * popup.
     *
     * @return true if the control will show a button for showing the popup
     */
    public final BooleanProperty showPopupTriggerButtonProperty() {
        return showPopupTriggerButton;
    }

    public final void setShowPopupTriggerButton(boolean showPopupTriggerButton) {
        this.showPopupTriggerButton.set(showPopupTriggerButton);
    }
}
