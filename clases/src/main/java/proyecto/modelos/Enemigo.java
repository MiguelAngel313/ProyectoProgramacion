package proyecto.modelos;

public class Enemigo extends Personaje {
    private TipoEnemigo tipoEnemigo;

    public Enemigo(String nombre, Posicion posicion, TipoEnemigo tipoEnemigo){
        super(nombre, posicion);
        this.tipoEnemigo=tipoEnemigo;

    }

    public TipoEnemigo getTipoEnemigo() {
        return this.tipoEnemigo;
    }

    public void setTipoEnemigo(TipoEnemigo tipoEnemigo) {
        this.tipoEnemigo = tipoEnemigo;
    }
   

    @Override
    public String toString() {
        return super.toString() + "{" +
            " tipoEnemigo='" + getTipoEnemigo() + "'" +
            "}";
    }

    public void moverHacia(Heroe heroe) {
        System.out.println("El enemigo se mueve hacia el héroe.");
    }

}

