package proyecto.modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.Observador;
import interfaces.Observable;

public class Mapa implements Observable {
    private Celda[][] celdas;
    private int ancho;
    private int alto;
    private Map<Posicion, Personaje> posicionesPersonajes;
    private Heroe heroe;
    private boolean partidaTerminada;
    public List<Observador> observadores;

    public Mapa(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.celdas = new Celda[alto][ancho];
        this.posicionesPersonajes = new HashMap<>();
        this.partidaTerminada = false;
        this.observadores = new ArrayList<>();

        // Se inicia el mapa, con el suelo.
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                celdas[i][j] = new Celda(true, TipoCelda.SUELO);
            }
        }
    }

    // Se añade un personaje al mapa
    public void agregarPersonaje(Personaje personaje) {
        if (personaje instanceof Heroe) {
            heroe = (Heroe) personaje;
        }

        // El personaje no esta fuera del mapa
        if (esPosicionValida(personaje.getPosicion())) {
            posicionesPersonajes.put(personaje.getPosicion(), personaje);
            notificarObservadores();
        } else {
            System.out.println("La posición no es correcta para el personaje.");
        }
    }

    // Se verifica si una posición está dentro del mapa
    private boolean esPosicionValida(Posicion posicion) {
        return posicion.getX() >= 0 && posicion.getX() < ancho &&
                posicion.getY() >= 0 && posicion.getY() < alto;
    }

    // Mover un personaje
    public boolean moverPersonaje(Personaje personaje, Posicion posicion) {
        Celda celdaDestino = getCelda(posicion.getX(), posicion.getY());
        if (celdaDestino == null && celdaDestino.isTransitable()) {
           return false;
        } 
        
       if(celdaDestino.estaOcupada()){
            return false;
       }

        int filaActual = personaje.getPosicion().getY();
        int columnaActual = personaje.getPosicion().getX();
        Celda celdaActual = getCelda(columnaActual, filaActual);
        if (celdaActual != null) {
            celdaActual.setPersonaje(null);
        }

        celdaDestino.setPersonaje(personaje);
        personaje.setPosicion(posicion);

        notificarObservadores();
        return true;
    }

    // Se calculara la nueva posición
    private Posicion calcularNuevaPosicion(Posicion posicion, Direccion direccion) {
        int x = posicion.getX();
        int y = posicion.getY();

        if (direccion == Direccion.ARRIBA) {
            y--;
        } else if (direccion == Direccion.ABAJO) {
            y++;
        } else if (direccion == Direccion.IZQUIERDA) {
            x--;
        } else if (direccion == Direccion.DERECHA) {
            x++;
        }

        return new Posicion(x, y);
    }

    // Realizar ataque, cuando el personaje/heroe se mueven.
    private void realizarAtaque(Enemigo enemigo) {
        int daño = heroe.getAtaque() - enemigo.getDefensa();
        if (daño > 0) {
            enemigo.setVidaActual(enemigo.getVidaActual() - daño);
            System.out.println("¡Atacar! " + enemigo.getNombre() + "le digo un golpe" + daño + " puntos de daño.");
        } else {
            System.out.println("El golpe no hizo daño.");
        }

        // Comprueba si el enemigo ha muerto, lo elimina del mapa.
        if (enemigo.getVidaActual() <= 0) {
            System.out.println(enemigo.getNombre() + "ha fallecido, DEP!");
            posicionesPersonajes.remove(enemigo.getPosicion());
        }
    }

    // Se mueven los enemigos
    public void moverEnemigos() {
        for (Personaje personaje : posicionesPersonajes.values()) {
            if (personaje instanceof Enemigo && personaje.estaVivo()) {
                Enemigo enemigo = (Enemigo) personaje;
                if (distanciaAProtagonista(enemigo.getPosicion()) <= 5) {
                    moverHaciaHeroe(enemigo);
                } else {
                    moverAleatorio(enemigo);
                }
            }
        }
    }

    // Calcula la distancia entre un enemigo y el heroe
    private int distanciaAProtagonista(Posicion posicionEnemigo) {
        return Math.abs(heroe.getPosicion().getX() - posicionEnemigo.getX()) +
                Math.abs(heroe.getPosicion().getY() - posicionEnemigo.getY());
    }

    // Mover un enemigo aleatoriamente
    private void moverAleatorio(Enemigo enemigo) {
        Direccion direccionAleatoria = Direccion.values()[(int) (Math.random() * Direccion.values().length)];
        moverPersonaje(enemigo, calcularNuevaPosicion(enemigo.getPosicion(), direccionAleatoria));
    }

    // Mover un enemigo hacia el heroe
    private void moverHaciaHeroe(Enemigo enemigo) {
        int dx = heroe.getPosicion().getX() - enemigo.getPosicion().getX();
        int dy = heroe.getPosicion().getY() - enemigo.getPosicion().getY();
        Posicion nuevaPosicion = calcularNuevaPosicion(enemigo.getPosicion(), Direccion.DERECHA);

        if (dx > 0) {
            nuevaPosicion = calcularNuevaPosicion(enemigo.getPosicion(), Direccion.DERECHA);
        } else if (dx < 0) {
            nuevaPosicion = calcularNuevaPosicion(enemigo.getPosicion(), Direccion.IZQUIERDA);
        }
        if (dy > 0) {
            nuevaPosicion = calcularNuevaPosicion(enemigo.getPosicion(), Direccion.ABAJO);
        } else if (dy < 0) {
            nuevaPosicion = calcularNuevaPosicion(enemigo.getPosicion(), Direccion.ARRIBA);
        }
    }

    // Se comprobara si la partida ha terminado
    public boolean isPartidaTerminada() {
        return partidaTerminada;
    }

    // Se Comprobara si el heroe ha muerto
    public boolean esHéroeMuerto() {
        return !heroe.estaVivo();
    }

    // Se Comprobara si todos los enemigos han sido derrotados
    public boolean todosLosEnemigosMuertos() {
        for (Personaje personaje : posicionesPersonajes.values()) {
            if (personaje instanceof Enemigo && personaje.estaVivo()) {
                return false;
            }
        }
        return true;
    }

    // Mostrar el mapa
    public void mostrarMapa() {
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                Celda celda = celdas[y][x];
                if (celda.getTipoCelda() == TipoCelda.SUELO) {
                    System.out.print(". ");
                } else if (celda.getTipoCelda() == TipoCelda.PARED) {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }

    public Celda getCelda(int x, int y) {
        if (x >= 0 && x < ancho && y >= 0 && y < alto) {
            return celdas[y][x];
        }
        return null;
    }

    public void setTipoCelda(int x, int y, TipoCelda tipo) {
        if (x >= 0 && x < ancho && y >= 0 && y < alto) {
            celdas[y][x].setTipoCelda(tipo);
            notificarObservadores();
        }
    }

    @Override
    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.actualizar();
        }
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public Map<Posicion, Personaje> getPosicionesPersonajes() {
        return posicionesPersonajes;
    }

    public Heroe getHeroe() {
        return heroe;
    }

    public void setHeroe(Heroe heroe) {
        this.heroe = heroe;
    }

    public void setPartidaTerminada(boolean partidaTerminada) {
        this.partidaTerminada = partidaTerminada;
    }

    public void setCeldas(Celda[][] celdas) {
        this.celdas = celdas;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setPosicionesPersonajes(Map<Posicion, Personaje> posicionesPersonajes) {
        this.posicionesPersonajes = posicionesPersonajes;
    }

    
}
