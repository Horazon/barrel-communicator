package pl.horazon.fx.controllers;

import javafx.fxml.Initializable;
import pl.horazon.fx.ClientProxy;
import pl.horazon.fx.UserContext;

import java.net.URL;
import java.util.ResourceBundle;

public class MainControll implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ClientProxy.getInstance().start();
        ClientProxy.getInstance().init(UserContext.login);
    }
}
