module proyecto {
    requires javafx.controls;
    requires javafx.fxml;

    opens proyecto to javafx.fxml;
    exports proyecto.controllador to javafx.fxml;
    opens proyecto.controllador to javafx.fxml;
    exports proyecto;
}
