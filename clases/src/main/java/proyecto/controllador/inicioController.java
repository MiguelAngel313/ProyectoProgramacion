package proyecto.controllador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import proyecto.App;

public class inicioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button aceptar;

    @FXML
    private TextField atack;

    @FXML
    private TextField defense;

    @FXML
    private TextField name;

    @FXML
    void cargarEstadisticasyMapa(ActionEvent event) throws IOException {
        
        String nombre = name.getText();
        int ataque = Integer.parseInt(atack.getText());
        int defensa = Integer.parseInt(defense.getText());
        App.setRoot("mapa");
    }

    @FXML
    void initialize() {
        assert aceptar != null : "fx:id=\"aceptar\" was not injected: check your FXML file 'inicio.fxml'.";
        assert atack != null : "fx:id=\"atack\" was not injected: check your FXML file 'inicio.fxml'.";
        assert defense != null : "fx:id=\"defense\" was not injected: check your FXML file 'inicio.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'inicio.fxml'.";

    }

}
