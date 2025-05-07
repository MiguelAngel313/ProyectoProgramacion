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
   
    //Mostrar datos
    @Override
    public String toString() {
        return super.toString() + "{" +
            " tipoEnemigo='" + getTipoEnemigo() + "'" +
            "}";
    }
   


}
