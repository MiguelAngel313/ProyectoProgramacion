package proyecto.modelos;

public class Heroe extends Personaje {
    private int experiencia;
    private Direccion direccion;

    public Heroe(String nombre, Posicion posicion){
        super(nombre, posicion);
        this.experiencia=0;

    }
    //Getter y setters
    public int getExperiencia() {
        return this.experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    //Mostar datos
    @Override
    public String toString() {
        return super.toString()+ "{" +
            " experiencia='" + getExperiencia() + "'" +
            "}";
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    @Override
    public void realizarTurno(Mapa mapa){
        int nuevaX = this.getPosicion().getX();
        int nuevaY = this.getPosicion().getY();
        Posicion nuevaPosicion = new Posicion(nuevaX, nuevaY);

        switch (this.direccion) {
            case ARRIBA:
                nuevaY--;
                break;
            case ABAJO:
                nuevaY++;
                break;
            case IZQUIERDA:
                nuevaX--;
                break;
            case DERECHA:
                nuevaX++;
                break;
            default:
                return;
        }

        Personaje posibleEnemigo = mapa.getCelda(nuevaX, nuevaY).getPersonaje();
        if(posibleEnemigo != null){
            atacar(posibleEnemigo);
        }else{
            mapa.moverPersonaje(this, nuevaPosicion);
        }

        direccion = null; // Resetear la dirección después de realizar el turno
    }


}
