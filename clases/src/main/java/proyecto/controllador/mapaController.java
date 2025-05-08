package proyecto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class mapaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ProgressBar barraDefensa;

    @FXML
    private ProgressBar barraSalud;

    @FXML
    void initialize() {
        assert barraDefensa != null : "fx:id=\"barraDefensa\" was not injected: check your FXML file 'mapa.fxml'.";
        assert barraSalud != null : "fx:id=\"barraSalud\" was not injected: check your FXML file 'mapa.fxml'.";

    }

}
