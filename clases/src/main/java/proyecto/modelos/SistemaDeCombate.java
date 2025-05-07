package proyecto.modelos;

import java.util.List;

public class SistemaDeCombate {

    public void atacar(Personaje atacante, Personaje defensor) {
        int dano = calcularDano(atacante, defensor);
        defensor.recibirDano(dano);
        if (defensor.estaMuerto()) {
            System.out.println(defensor.obtenerNombre() + " ha sido derrotado!");
        } else {
            System.out.println(atacante.obtenerNombre() + " ataca a " + defensor.obtenerNombre() + " causando " + dano + " de daño.");
        }
    }

    private int calcularDano(Personaje atacante, Personaje defensor) {
        int danoBase = atacante.obtenerFuerza();
        int defensa = defensor.obtenerDefensa();
        int dano = danoBase - defensa;
        return Math.max(dano, 0); 
    }

    public void gestionarOrdenDeTurnos(List<Personaje> personajes) {
        personajes.sort((p1, p2) -> Integer.compare(p2.obtenerVelocidad(), p1.obtenerVelocidad()));
    }
}
