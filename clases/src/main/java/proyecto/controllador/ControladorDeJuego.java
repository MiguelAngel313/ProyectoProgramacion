package proyecto.controllador;

import proyecto.modelos.TableroDeJuego;
import proyecto.modelos.Heroe;
import proyecto.modelos.Enemigo;
import proyecto.vista.VistaDeJuego;
import proyecto.vista.VistaDeEstadisticas;

import java.util.List;

public class ControladorDeJuego {
    private TableroDeJuego tableroDeJuego;
    private Heroe heroe; 
    private List<Enemigo> enemigos;
    private VistaDeJuego vistaDeJuego;
    private VistaDeEstadisticas vistaDeEstadisticas;

    public ControladorDeJuego(TableroDeJuego tableroDeJuego, Heroe heroe, List<Enemigo> enemigos, VistaDeJuego vistaDeJuego, VistaDeEstadisticas vistaDeEstadisticas) {
        this.tableroDeJuego = tableroDeJuego;
        this.heroe = heroe;
        this.enemigos = enemigos;
        this.vistaDeJuego = vistaDeJuego;
        this.vistaDeEstadisticas = vistaDeEstadisticas;
    }

    public void iniciarJuego() {
        try {
            vistaDeJuego.inicializarTablero(tableroDeJuego);
            vistaDeEstadisticas.actualizarEstadisticasJugador(heroe);
            bucleDeJuego();
        } catch (Exception e) {
            System.err.println("Error al iniciar el juego: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void bucleDeJuego() {
        while (heroe.estaVivo() && !enemigos.isEmpty()) {
            turnoDelJugador();
            turnoDeEnemigos();
            actualizarVistas();
        }
        finalizarJuego();
    }

    private void turnoDelJugador() {
        
    }

    private void turnoDeEnemigos() {
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo enemigo = enemigos.get(i);
            if (enemigo.estaVivo()) {
                enemigo.moverHacia(heroe);
            }
        }
    }

    private void actualizarVistas() {
        vistaDeJuego.actualizarTablero(tableroDeJuego);
        vistaDeEstadisticas.actualizarEstadisticasJugador(heroe);
        vistaDeEstadisticas.actualizarEstadisticasEnemigos(enemigos);
    }

    private void finalizarJuego() {
        if (!heroe.estaVivo()) {
            vistaDeJuego.mostrarGameOver();
        } else {
            vistaDeJuego.mostrarVictoria();
        }
    }
}
