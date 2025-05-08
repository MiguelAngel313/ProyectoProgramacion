package proyecto.modelos;

import java.util.List;
import java.util.Objects;

public class Personaje {

    private String nombre;
    private int id;
    private static int identificador =0;
    private int vidaMaxima;
    private int vidaActual;
    private int velocidad;
    private int ataque;
    private int defensa;
    private int nivel;
    private Posicion posicion;

    //Constructor
    public Personaje(String nombre, Posicion posicion){
        this.nombre=nombre;
        this.posicion=posicion;
        this.id = identificador++;
        this.vidaMaxima=100;
        this.vidaActual=this.vidaMaxima;
        this.velocidad=10;
        this.ataque=10;
        this.defensa=5;
        this.nivel=1;
    }

    //Getters y Setters
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return this.id;
    }

    public int getVidaMaxima() {
        return this.vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getVidaActual() {
        return this.vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }

    public int getVelocidad() {
        return this.velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getAtaque() {
        return this.ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return this.defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    //Mostrar los datos
    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", id='" + getId() + "'" +
            ", vidaMaxima='" + getVidaMaxima() + "'" +
            ", vidaActual='" + getVidaActual() + "'" +
            ", velocidad='" + getVelocidad() + "'" +
            ", ataque='" + getAtaque() + "'" +
            ", defensa='" + getDefensa() + "'" +
            ", nivel='" + getNivel() + "'" +
            "}";
    }

    //Métodos
    //1. Mover. 
    public void mover(Direccion direccion) {
        
        if (direccion == Direccion.ARRIBA) {
            this.posicion.setY(this.posicion.getY() - 1);
        } else if (direccion == Direccion.ABAJO) {
            this.posicion.setY(this.posicion.getY() + 1);
        } else if (direccion == Direccion.IZQUIERDA) {
            this.posicion.setX(this.posicion.getX() - 1);
        } else if (direccion == Direccion.DERECHA) {
            this.posicion.setX(this.posicion.getX() + 1);
        }
    }

    //2. Atacar. Se ejecuta un ataque
    public void atacar(Personaje objetivo) {
        int danio = Math.max(0, this.ataque - objetivo.getDefensa());
        objetivo.recibirDanio(danio);
    }

    //3. Recibir daño. Se reduce la vida
    public void recibirDanio(int danio) {
        this.vidaActual -= danio;
        if (this.vidaActual < 0) {
            this.vidaActual = 0;
        }
    }

    //4. Tiene vida, esta vivo. Comprueba si el personaje tiene vida.
    public boolean estaVivo() {
        return this.vidaActual > 0;
    }

    //5. Calcular la distancia, la posicion de los personajes
    public int calcularDistancia(Posicion otra) {
        return Math.abs(this.posicion.getX() - otra.getX()) + Math.abs(this.posicion.getY() - otra.getY());
    }


    //6. equals y hashcode.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personaje)) return false;
        Personaje personaje = (Personaje) o;
        return id == personaje.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    
    

}
