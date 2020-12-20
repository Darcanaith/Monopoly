package monopoly;

import java.util.ArrayList;

abstract public class Especial extends Casilla{
    protected Especial() {
        super();
    }

    //Devuelve los distintos tipos de casillas especiales que hay
    static public String[] getTipos() {//??
        return new String[]{"Carcel", "Loteria"};
    }



    //Factory de casillas especiales
    static public Casilla getCasilla(String tipo) {
        try {
            if (tipo.equals("Carcel")){
                return new Carcel();
            }
            else{
                return new Loteria();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Tipo de casilla especial no encontrado");
        }
    }

    public String toString()  {
        String cadena = new String();
        cadena += String.format("\n%3d:", posicion);
        cadena += String.format("%-22s",getLabel()			 ); //Tipo de Casilla
        cadena += String.format(";%-18s","-----");//Precio de compra casilla ( getPrecio() )
        cadena += String.format(";%-18s", "-----"); //Coste por caer ( getCoste() )
        cadena += String.format(";%-15s","-----"); //Dueño ( getDuenyo() )
        cadena += String.format(";%s",getNombreJugadores()); //Dueño ( getNombreJugadores() )

        return cadena;
    }


}

