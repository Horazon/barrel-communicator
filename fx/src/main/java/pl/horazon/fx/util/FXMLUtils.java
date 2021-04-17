package pl.horazon.fx.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pl.horazon.fx.controllers.ChatTabController;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLUtils {
    public static <T extends Parent> void loadFXML(ChatTabController component, String s) {
        FXMLLoader loader = new FXMLLoader(component.getClass().getResource(s));
        loader.setRoot(component);
        loader.setController(component);
        //loader.setControllerFactory(theClass -> component);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.MyBundle", new Locale("en", "EN"));

        loader.setResources(resourceBundle);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
