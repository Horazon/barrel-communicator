module fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.common;
    requires client;
    requires common;
    requires lombok;


    opens pl.horazon.fx to javafx.fxml;

    exports pl.horazon.fx;
    exports pl.horazon.fx.controllers;
    opens pl.horazon.fx.controllers to javafx.fxml;
}