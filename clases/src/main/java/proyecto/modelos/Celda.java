package proyecto.modelos;

public class Celda {
    private boolean transitable;
    private TipoCelda tipoCelda;

    public Celda(boolean transitable, TipoCelda tipoCelda){
        this.transitable=transitable;
        this.tipoCelda=tipoCelda;

    }

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

    @Override
    public String toString() {
        return "{" +
            " transitable='" + isTransitable() + "'" +
            "}";
    }

   

}
