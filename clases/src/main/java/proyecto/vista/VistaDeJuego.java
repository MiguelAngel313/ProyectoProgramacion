package proyecto.vista;

import proyecto.modelos.TableroDeJuego;

public class VistaDeJuego {

     public void inicializarTablero(TableroDeJuego tableroDeJuego) {
        
        System.out.println("Tablero de juego inicializado.");
     }

    public void actualizarTablero(TableroDeJuego tableroDeJuego) {
            
        System.out.println("Tablero de juego actualizado.");
    }

    public void mostrarGameOver() {
        System.out.println("Game Over! El héroe ha sido derrotado.");
    }

    public void mostrarVictoria() {
        System.out.println("¡Victoria! El héroe ha derrotado a todos los enemigos.");
    }
    
}
