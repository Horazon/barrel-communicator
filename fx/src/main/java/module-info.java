module fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.common;
    requires client;
    requires common;
    requires lombok;
    requires org.apache.commons.lang3;
    requires org.tinylog.api;


    opens pl.horazon.fx to javafx.fxml;

    exports pl.horazon.fx;
    exports pl.horazon.fx.controllers;
    opens pl.horazon.fx.controllers to javafx.fxml;
    exports pl.horazon.fx.service;
    opens pl.horazon.fx.service to javafx.fxml;
}