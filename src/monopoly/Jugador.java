package monopoly;

import java.io.Serializable;
import java.util.*;

public class Jugador implements Comparable<Object>,Serializable {
    private String nombre;
    private enum color{ROJO, AZUL, VERDE}
    private color color_jugador;
    private Casilla posicion;
    private Integer dinero = 10000;
    private Set<Casilla> casillas; //casillas que tiene el jugador

    //Creamos contructor de clase Jugador
    public Jugador() {
        //Asigna(y dar a escoger) el color a un jugador
        nuevoColor();//por hacer
        // Asigna(y dar a escoger) el nombre a un jugador
        nuevoNombre();// por hacer
        // Al principio no estamos en ninguna casilla hasta tirar dado
        posicion = null;
        //Al principio nadie tiene casillas
        casillas = new HashSet<Casilla>();

    }



    //Indicar si esta encarcelado o no
    public boolean encarcelado(){
        if(this.posicion == null){
            return false;}
        else{
            return true;
    }}

    //Getter y Setter
    public String getNombre() { return nombre; }
    public Casilla getPosicion() { return posicion; }
    public int getDinero() { return dinero; }
    //El número de jugadores se establece según el número de jugadores que haya
    //public static int MAX_JUGADORES() { return Color.getColores().length; } descomentar cuando se hayan arreglado los errores****************
    public Casilla setPosicion(Casilla casilla) { return posicion = casilla; }



    /*public int setDinero(int dinero) throws ExNoDinero {
        this.dinero = dinero;
        //Si no le queda dinero, lanzamos una excepción
        if(this.dinero <= 0) {
            throw new ExNoDinero("Jugador " + getNombre() + " sin dinero");
        }

        return this.dinero;
    }*/




    //Nos permite comparar jugadores con respecto al nombre para ordenarlos
    public int compareTo(Object o){
        if (!(o instanceof Jugador)) {
            throw new IllegalArgumentException("Se ha intentando comparar un jugador con otro elemento distinto");
        }

        Jugador otroJugador = (Jugador) o;
        //le pasamos la tarea a string
        return nombre.compareTo(otroJugador.getNombre());
    }

    public void nuevoColor(color color_escogido){ //El jugador escoge y se le asigna un color
        switch (color_escogido){
            case ROJO:
                System.out.println("Has escogido el color rojo");
                this.color_jugador = color_escogido;
                break;

            case AZUL:
                System.out.println("Has escogido el color azul");
                this.color_jugador = color_escogido;
                break;

            case VERDE:
                System.out.println("Has escogido el color verde");
                this.color_jugador = color_escogido;
                break;
        }
    }

    public void nuevoNombre(String nombre){ //El jugador escribe su nombre
        this.nombre = nombre;
    }






}