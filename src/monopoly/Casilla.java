package monopoly;

import java.io.Serializable;
import java.util.*;

//set, nos permite no tener elementos repetidos

//las otras heredan de casilla
abstract public class Casilla implements Serializable {
    protected String casilla;
    //jugadores que forman parte de la partida
    protected Set<Jugador> jugadores;
    //Posición que ocupa dentro del tablero
    protected int posicion;
    protected String label;


    //Posicion a 0
    public Casilla() { this(0);}

    //Se le pasa la posición que ocupa la casilla
    public Casilla(int posicion) {
        this.posicion = posicion;
        jugadores = new HashSet<Jugador>();
    }

    //Comprobación
    public void sale(Jugador jugador) {

        //Si el jugador no estaba en la casilla se lanza una excepción
        if(!jugadores.remove(jugador) ) {
            throw new IllegalArgumentException("Usuario no se encuentra en la casilla");
        }
        //Al salir el jugador ya no ocupa ninguna posición hasta que cae en otra casilla
        jugador.setPosicion(null);
    }


    //El jugador cae en la casilla. ExNoDinero, Se lanza cuando el jugador al caer a la casilla se queda sin dinero
    public void cae(Jugador jugador) throws SinDineroExcepcion {
        //Añadimos al jugador al listado de jugadores que hay en esta casilla
        jugadores.add(jugador);
        jugador.setPosicion(this);
    }

    //Getters y Setter
    public void setPosicion(int posicion){ this.posicion = posicion; }
    public String getLabel() { return label; }
    public int getPosicion() { return posicion; }


    //Devolvemos  los nombres de todos los jugadores que hay en la casilla
    public String getNombreJugadores() {
        StringBuilder nombres = new StringBuilder();

        Iterator<Jugador> it=jugadores.iterator();
        while(it.hasNext())
        {
            Jugador jugador=(Jugador)it.next();
            nombres.append(jugador.getNombre());
            if(it.hasNext()) nombres.append("-");
        }

        if(0 == nombres.length()) { nombres.append("No hay jugadores"); }

        return nombres.toString();
    }
}
