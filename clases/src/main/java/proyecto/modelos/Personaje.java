package proyecto.modelos;

public class Personaje {

    private String nombre;
    private int id;
    private int identificador;
    private int vidaMaxima;
    private int vidaActual;
    private int velocidad;
    private int ataque;
    private int defensa;
    private int nivel;
    private Posicion posicion;
    private int fuerza; 

    public Personaje(String nombre, Posicion posicion){
        this.nombre=nombre;
        this.posicion=posicion;
        this.id = identificador++;
        this.vidaMaxima=100;
        this.vidaActual=this.vidaMaxima;
        this.velocidad=10;
        this.ataque=10;
        this.nivel=1;
        this.fuerza=10; 
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

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

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

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerFuerza() {
        return fuerza;
    }

    public int obtenerDefensa() {
        return defensa;
    }

    public int obtenerVelocidad() {
        return velocidad;
    }

    public void recibirDano(int dano) {
        this.vidaActual -= dano;
        if (this.vidaActual < 0) {
            this.vidaActual = 0; 
        }
    }

    public boolean estaMuerto() {
        return this.vidaActual <= 0;
    }

    public boolean estaVivo() {
        return this.vidaActual > 0;
    }

    public void atacar(Personaje objetivo) {
        int dano = this.ataque - objetivo.defensa;
        if (dano > 0) {
            objetivo.recibirDano(dano);
        } else {
            System.out.println("El ataque no hace daño debido a la defensa del objetivo.");
        }
    }

    public void moverse(int x, int y) {
        this.posicion.setX(x);
        this.posicion.setY(y);
    }

    public void moverse(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }

}
