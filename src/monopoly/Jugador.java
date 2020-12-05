package monopoly;

import java.awt.*;
import java.util.ArrayList;

public class Jugador {
    private String Nombre;
    private enum Color{};
    private Integer Posicion;
    private Integer Dinero = 10000;
    private ArrayList<String> Casillas_adquiridas;

    //Creamos contructor de clase Jugador
    public Jugador(String nombre, String color, Integer posicion,  Integer casillas){
        this.Nombre = nombre;
        this.Posicion = posicion;
    }

    //Eliminar casillas jugador
    public void eliminar_jugador(){
        this.Casillas_adquiridas.clear();//Por hacer
    }


    //Indicar si esta encarcelado o no
    public boolean encarcelado(){
        if(this.Posicion == null){
            return false;}
        else{
            return true;
    }}













    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Integer getPosicion() {
        return Posicion;
    }

    public void setPosicion(Integer posicion) {
        Posicion = posicion;
    }

    public Integer getDinero() {
        return Dinero;
    }

    public void setDinero(Integer dinero) {
        Dinero = dinero;
    }


}