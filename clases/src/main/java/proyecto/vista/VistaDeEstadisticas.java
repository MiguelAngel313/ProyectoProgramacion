package proyecto.vista;

import java.util.List;

import proyecto.modelos.Enemigo;
import proyecto.modelos.Heroe;

public class VistaDeEstadisticas {

    public void actualizarEstadisticasJugador(Heroe heroe) {

        System.out.println("Actualizando estadísticas del jugador: " + heroe.getNombre());
    }

    public void mostrarEstadisticas() {
        System.out.println("Mostrando estadísticas del jugador.");
    }

    public void actualizarEstadisticasEnemigos(List<Enemigo> enemigos) {
        for (Enemigo enemigo : enemigos) {
            System.out.println("Actualizando estadísticas del enemigo: " + enemigo.getNombre() + " de tipo "
                    + enemigo.getTipoEnemigo());
        }
    }

}
