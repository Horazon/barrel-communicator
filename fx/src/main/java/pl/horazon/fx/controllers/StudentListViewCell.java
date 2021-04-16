package pl.horazon.fx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import pl.horazon.barrel.common.pojo.domain.GroupChatMsg;

import java.io.IOException;

/**
 * Created by Johannes on 23.05.16.
 *
 */

public class StudentListViewCell extends ListCell<GroupChatMsg> {

    @FXML
    private Label fxLabelUserLogin;

    @FXML
    private Label fxLabelUserMsg;


    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(GroupChatMsg student, boolean empty) {
        super.updateItem(student, empty);

        if(empty || student == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            fxLabelUserLogin.setText(student.getFrom());
            fxLabelUserMsg.setText(student.getContent());

            setText(null);
            setGraphic(gridPane);
        }

    }
}