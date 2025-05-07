package proyecto.modelos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TableroDeJuego {
    private char[][] tablero;
    private int filas;
    private int columnas;

    public TableroDeJuego(String rutaArchivo) {
        cargarTablero(rutaArchivo);
    }

    private void cargarTablero(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            filas = 0;
            while ((linea = br.readLine()) != null) {
                columnas = linea.length();
                filas++;
            }
            tablero = new char[filas][columnas];

            try (BufferedReader br2 = new BufferedReader(new FileReader(rutaArchivo))) {
                int filaActual = 0;
                while ((linea = br2.readLine()) != null) {
                    tablero[filaActual] = linea.toCharArray();
                    filaActual++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public char obtenerCelda(int fila, int columna) {
        return tablero[fila][columna];
    }

    public void establecerCelda(int fila, int columna, char valor) {
        tablero[fila][columna] = valor;
    }

    public int obtenerFilas() {
        return filas;
    }

    public int obtenerColumnas() {
        return columnas;
    }
}
