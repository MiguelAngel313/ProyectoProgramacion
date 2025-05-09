package proyecto.modelos;

public class Celda {
    private boolean transitable;
    private TipoCelda tipoCelda;
    private Personaje personaje;

    public Celda(boolean transitable, TipoCelda tipoCelda){
        this.transitable=transitable;
        this.tipoCelda=tipoCelda;
    }

    //Getters and setters
    public boolean isTransitable() {
        return this.transitable;
    }

    public boolean getTransitable() {
        return this.transitable;
    }

    public void setTransitable(boolean transitable) {
        this.transitable = transitable;
    }
    
    public TipoCelda getTipoCelda() {
        return tipoCelda;
    }

    public void setTipoCelda(TipoCelda tipoCelda) {
        this.tipoCelda = tipoCelda;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public boolean estaOcupada() {
        return personaje != null;
    }

    //Mostrar
    @Override
    public String toString() {
        return "{" +
            " transitable='" + isTransitable() + "'" +
            "}";
    }

   

}
