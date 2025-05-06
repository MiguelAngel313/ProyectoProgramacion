package proyecto.modelos;

import java.util.HashMap;

public class Mapa {
    private Celda[][] celdas;
    private int ancho;
    private int alto;

    private Map<Posicion, Personaje> posicionesPersonajes;

    public Mapa(int ancho, int alto){
        this.ancho=ancho;
        this.alto=alto;
        this.celdas= new Celda[alto][ancho];
        this.posicionesPersonajes=new HashMap<>();

        for (int i = 0; i < alto ; i++) {
            for (int j = 0; j < ancho; j++) {
                celdas[i][j]= new Celda(true, TipoCelda.SUELO);
            }
        }
    }

}
