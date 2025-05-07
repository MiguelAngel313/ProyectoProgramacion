package proyecto.modelos;

public class Heroe extends Personaje {
    private int experiencia;

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

    @Override
    public String toString() {
        return super.toString()+ "{" +
            " experiencia='" + getExperiencia() + "'" +
            "}";
    }



}
