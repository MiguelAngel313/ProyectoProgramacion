package proyecto.modelos;

public class Enemigo extends Personaje {
    private TipoEnemigo tipoEnemigo;
    private int percepcion; // Distancia de percepción del enemigo

    public Enemigo(String nombre, Posicion posicion, TipoEnemigo tipoEnemigo, int percepcion) {
        super(nombre, posicion);
        this.tipoEnemigo=tipoEnemigo;
        this.percepcion=percepcion;

    }

    public TipoEnemigo getTipoEnemigo() {
        return this.tipoEnemigo;
    }

    public void setTipoEnemigo(TipoEnemigo tipoEnemigo) {
        this.tipoEnemigo = tipoEnemigo;
    }

    public int getPercepcion() {
        return this.percepcion;
    }

    public void setPercepcion(int percepcion) {
        this.percepcion = percepcion;
    }
   
    //Mostrar datos
    @Override
    public String toString() {
        return super.toString() + "{" +
            " tipoEnemigo='" + getTipoEnemigo() + "'" +
            "}";
    }

    public void moverHacia(Heroe heroe) {
        System.out.println("El enemigo se mueve hacia el héroe.");
    }

    @Override
    public void realizarTurno(Mapa mapa){
        Heroe heroe = null;
        for (int index = 0; index < mapa.getAlto(); index++) {
            for (int j = 0; j < mapa.getAncho(); j++) {
                if(mapa.getCelda(index, j).getPersonaje() instanceof Heroe){
                    heroe = (Heroe) mapa.getCelda(index, j).getPersonaje();
                    break;
                }
            }
            if (heroe != null) {
                break;
            }
        }

        if (heroe == null) return;

        int distancia = this.calcularDistancia(heroe);

        int nuevaFila = this.getPosicion().getX();
        int nuevaColumna = this.getPosicion().getY();
        Posicion nuevaPosicion = new Posicion(nuevaFila, nuevaColumna);

        if(distancia < percepcion){
            if(Math.abs(this.getPosicion().getX() - heroe.getPosicion().getX()) > Math.abs(this.getPosicion().getY() - heroe.getPosicion().getY())){
                if(this.getPosicion().getX() < heroe.getPosicion().getX()){
                    nuevaFila++;
                }else{
                    nuevaFila--;
                }
            }else{
                if(this.getPosicion().getY() < heroe.getPosicion().getY()){
                    nuevaColumna++;
                }else{
                    nuevaColumna--;
                }
            }
        }else{
            // Si el héroe no está en la distancia de percepción, el enemigo se mueve aleatoriamente
            int direccion = (int) (Math.random() * 4);
            switch (direccion) {
                case 0: // Arriba
                    nuevaFila--;
                    break;
                case 1: // Abajo
                    nuevaFila++;
                    break;
                case 2: // Izquierda
                    nuevaColumna--;
                    break;
                case 3: // Derecha
                    nuevaColumna++;
                    break;
            }
        }

        Personaje posibleHeroe = mapa.getCelda(nuevaFila, nuevaColumna).getPersonaje();
        if (posibleHeroe instanceof Heroe){
            atacar(posibleHeroe);
        }else{
            mapa.moverPersonaje(this, nuevaPosicion);
        }
    }


    // SistemaCombate -- Encarga de ver que pasa cuando se atacan un heroe y un enemigo
    // CargarDatos -- Carga el escenario desde el txt, y los personajes de json
    // JuegoMazmorras -- Coordinador del juego, encargado de iniciar el juego, y de la logica del mismo
}

