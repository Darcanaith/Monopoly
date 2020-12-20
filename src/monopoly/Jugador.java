package monopoly;

import java.io.Serializable;
import java.util.*;
import java.text.NumberFormat;

public class Jugador implements Comparable<Object>,Serializable {
    private String nombre;
    private Color color;
    private Casilla posicion;
    private Integer dinero = 10000;
    private Set<Casilla> casillas; //casillas que tiene el jugador

    //Creamos constructor de clase Jugador
    public Jugador() {
        //Asigna(y dar a escoger) el color a un jugador
        nuevoColor();
        // Asigna(y dar a escoger) el nombre a un jugador
        nuevoNombre();
        // Al principio no estamos en ninguna casilla hasta tirar dado
        posicion = null;
        //Al principio nadie tiene casillas
        casillas = new HashSet<Casilla>();

    }


    //Indicar si esta encarcelado o no
    public boolean encarcelado() {
        return null != posicion && posicion instanceof Carcel;
    }

    //Eliminar jugador
    public void eliminar() {
        // Devolvemos el color que habíamos escogido
        if(null != color) {
            Color.add(color.get());
        }

        // Las casillas que compro el jugador vuelven a estar libre
        if( !casillas.isEmpty() ) {

            for (Casilla casilla : casillas) {
                Normal tcasilla = (Normal) casilla;
                tcasilla.setDuenyo(null);
            }
        }
    }

    //Getter y Setter
    public String getNombre() { return nombre; }
    public Casilla getPosicion() { return posicion; }
    public int getDinero() { return dinero; }
    //El número de jugadores se establece según el número de jugadores que haya
    public static int MAX_JUGADORES() { return Color.getColores().length; }
    public Casilla setPosicion(Casilla casilla) { return posicion = casilla; }
    public int setDinero(int dinero) throws SinDineroExcepcion {
        this.dinero = dinero;
        //Si no le queda dinero, lanzamos una excepción
        if(this.dinero <= 0) {
            throw new SinDineroExcepcion("Jugador "+getNombre()+" sin dinero");
        }

        return this.dinero;
    }



    //Nos permite comparar jugadores con respecto al nombre(para ordenarlos, por ejemplo).
    public int compareTo(Object o){
        if (!(o instanceof Jugador)) {
            throw new IllegalArgumentException("Se ha intentando comparar un jugador con otra elemento distinto");
        }

        Jugador otroJugador = (Jugador) o;
        //le pasamos la tarea a string
        return nombre.compareTo(otroJugador.getNombre());
    }


    //Para visualizar la información del jugador
    public String toString() {
        // Esto es para que aparezcan los miles..... ej 1.000.000
        Locale local = new Locale("ES", "ES");
        NumberFormat f = NumberFormat.getNumberInstance(local);
        String string = new String();
        //Mostramos información del nombre
        string += String.format("%-25s", nombre);
        //Mostramos la información del color
        string += String.format(";%-10s", color.get());
        //Mostramos la información del dinero restante del jugador
        string += String.format(";%s", f.format(dinero)+" euros");

        return string;
    }


    public void turno(Tablero tablero) throws SinDineroExcepcion {
        Casilla casilla_inicio = posicion;

        //Intentamos salir de la casilla actual
        casilla_inicio.sale(this);

        //Si no estamos ya en ninguna casilla es que hemos salido correctamente
        if (null != posicion) {
            return;
        }

        //Tiramos el dado para mover el jugador
        byte numero = Dado.tirar(this);
        //averiguamos la casilla después del movimiento
        Casilla casilla_destino = tablero.moverFicha(casilla_inicio, numero);
        //Informamos al jugador de la nueva casilla en la que se movió
        Vista.print("\n Has caído en la casilla número " + casilla_destino.getPosicion() + ":" + casilla_destino.getLabel());
        //movemos al jugador hasta su casilla destino
        casilla_destino.cae(this);
    }
    //Devolvemos si el jugador compró una casilla y se la añadimos a sus posesiones
    public boolean addCasilla(Casilla casilla) {
        return this.casillas.add(casilla);
    }

    //Borrar casilla de un jugador
    public boolean delCasilla(Casilla casilla) {
        return this.casillas.remove(casilla);
    }

    //inicialización
    public void nuevoNombre() {

        //Cogemos la longitud minima y maxima que puede tener el nombre del jugador
        int tamanyo_min_nombre = 2;
        int tamanyo_max_nombre = 15;

        do {
            //Obtenemos el nombre del jugador
            nombre = Vista.input("\n\nPor favor, jugador " + color.get() + ", escriba su nombre:").trim();

            //Validamos que el nombre tenga número de caracteres válidos
            if(nombre.length()< tamanyo_min_nombre) {
                Vista.print("\nNombre demasiado corto(min " + tamanyo_min_nombre + " letras).");
            }else	if(nombre.length() > tamanyo_max_nombre) {
                Vista.print("\nNombre demasiado largo(max " + tamanyo_max_nombre + " letras).");
            }

            //El bucle seguirá y seguirá hasta que se determine un nombre correcto

        }while(nombre.length() < tamanyo_min_nombre || nombre.length() > tamanyo_max_nombre);
    }

    //Devolvemos el color que habíamos escogido
    public void nuevoColor() {
        if(null != color) {
            Color.add(color.get());
        }
        color = new Color();
    }
}