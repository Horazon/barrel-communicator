module fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.common;
    requires client;
    requires common;

    opens pl.horazon.fx to javafx.fxml;

    exports pl.horazon.fx;
}