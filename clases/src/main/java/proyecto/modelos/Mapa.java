package proyecto.modelos;

import java.util.HashMap;
import java.util.Map;

public class Mapa {
    private Celda[][] celdas;
    private int ancho;
    private int alto;
    private Map<Posicion, Personaje> posicionesPersonajes;
    private Heroe heroe;
    private boolean partidaTerminada;

    public Mapa(int ancho, int alto){
        this.ancho=ancho;
        this.alto=alto;
        this.celdas= new Celda[alto][ancho];
        this.posicionesPersonajes=new HashMap<>();
        this.partidaTerminada = false;

    //Se inicia el mapa, con el suelo.
        for (int i = 0; i < alto ; i++) {
            for (int j = 0; j < ancho; j++) {
                celdas[i][j]= new Celda(true, TipoCelda.SUELO);
            }
        }
    }

    //Se añade un personaje al mapa
    public void agregarPersonaje(Personaje personaje) {
        if (personaje instanceof Heroe) {
            heroe = (Heroe) personaje;
        }

        //El personaje no esta fuera del mapa
        if (esPosicionValida(personaje.getPosicion())) {
            posicionesPersonajes.put(personaje.getPosicion(), personaje);
        } else{
            System.out.println("La posición no es correcta para el personaje.");
        }
    }


    //Se verifica si una posición está dentro del mapa
    private boolean esPosicionValida(Posicion posicion) {
        return posicion.getX() >= 0 && posicion.getX() < ancho &&
               posicion.getY() >= 0 && posicion.getY() < alto;
    }


    //Mover un personaje
    public void moverPersonaje(Personaje personaje, Direccion direccion) {
        Posicion nuevaPos = calcularNuevaPosicion(personaje.getPosicion(), direccion);

        //Ver si la nueva posicion, es correcta.
        if (esPosicionValida(nuevaPos) && celdas[nuevaPos.getY()][nuevaPos.getX()].isTransitable()) {
           
            if (posicionesPersonajes.containsKey(nuevaPos)) {
                Personaje otroPersonaje = posicionesPersonajes.get(nuevaPos);
                if (otroPersonaje instanceof Enemigo) {
                    realizarAtaque((Enemigo) otroPersonaje);
                    return; 
                }
            }

            //Mover a una nueva posición
            posicionesPersonajes.remove(personaje.getPosicion()); 
            personaje.setPosicion(nuevaPos); 
            posicionesPersonajes.put(nuevaPos, personaje); 
        } else {
            System.out.println("El movimiento no es correcto.");
        }
    }

    //Se calculara la nueva posición
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

    //Realizar ataque, cuando el personaje/heroe se mueven.
    private void realizarAtaque(Enemigo enemigo) {
        int daño = heroe.getAtaque() - enemigo.getDefensa();
        if (daño > 0) {
            enemigo.setVidaActual(enemigo.getVidaActual() - daño);
            System.out.println("¡Atacar! " + enemigo.getNombre() + "le digo un golpe" + daño + " puntos de daño.");
        } else {
            System.out.println("El golpe no hizo daño.");
        }

        //Comprueba si el enemigo ha muerto, lo elimina del mapa.
        if (enemigo.getVidaActual() <= 0) {
            System.out.println(enemigo.getNombre() + "ha fallecido, DEP!");
            posicionesPersonajes.remove(enemigo.getPosicion());
        }
    }

    //Se mueven los enemigos
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

    //Calcula la distancia entre un enemigo y el heroe
    private int distanciaAProtagonista(Posicion posicionEnemigo) {
        return Math.abs(heroe.getPosicion().getX() - posicionEnemigo.getX()) +
               Math.abs(heroe.getPosicion().getY() - posicionEnemigo.getY());
    }

    //Mover un enemigo aleatoriamente
    private void moverAleatorio(Enemigo enemigo) {
        Direccion direccionAleatoria = Direccion.values()[(int) (Math.random() * Direccion.values().length)];
        moverPersonaje(enemigo, direccionAleatoria);
    }

    //Mover un enemigo hacia el heroe
    private void moverHaciaHeroe(Enemigo enemigo) {
        int dx = heroe.getPosicion().getX() - enemigo.getPosicion().getX();
        int dy = heroe.getPosicion().getY() - enemigo.getPosicion().getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                moverPersonaje(enemigo, Direccion.DERECHA);
            } else {
                moverPersonaje(enemigo, Direccion.IZQUIERDA);
            }
        } else {
            if (dy > 0) {
                moverPersonaje(enemigo, Direccion.ABAJO);
            } else {
                moverPersonaje(enemigo, Direccion.ARRIBA);
            }
        }
    }

    //Se comprobara si la partida ha terminado
    public boolean isPartidaTerminada() {
        return partidaTerminada;
    }

    //Se Comprobara si el heroe ha muerto
    public boolean esHéroeMuerto() {
        return !heroe.estaVivo();
    }

    //Se Comprobara si todos los enemigos han sido derrotados
    public boolean todosLosEnemigosMuertos() {
        for (Personaje personaje : posicionesPersonajes.values()) {
            if (personaje instanceof Enemigo && personaje.estaVivo()) {
                return false;
            }
        }
        return true;
    }

    //Mostrar el mapa
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
}


