package monopoly;

import java.io.Serializable;
import java.util.*;

//set, nos permite no tener elementos repetidos

//las demas heredan de casilla
abstract public class Casilla implements Serializable {
    protected String casilla;
    //jugadores que forman parte de la partida
    protected Set<Jugador> jugadores;
    //Posición que ocupa dentro del tablero
    protected int posicion;


     //Constructor básico de una casilla, pone posicion a 0
    public Casilla() { this(0);}

     //Constructor que se le pasa la posición que ocupa la casilla
    public Casilla(int posicion) {
        this.posicion = posicion;
        jugadores = new HashSet<Jugador>();
    }


    public void sale(Jugador jugador) {
        //Al mover el jugador ya no ocupa ninguna posición hasta que cae en otra casilla
        jugador.setPosicion(null);
    }


    //El jugador cae en la casilla. ExNoDinero, Se lanza cuando el jugador al caer a la casilla se queda sin dinero
    public void cae(Jugador jugador) throws ExNoDinero  {
        //Añadimos al jugador al listado de jugadores que hay en esta casilla
        jugadores.add(jugador);
        jugador.setPosicion(this);
    }

    /** ************************** Getter/Setter ********************************** */
    public void setPosicion(int posicion){ this.posicion = posicion; }
    public String getcasilla() { return casilla; }
    public int getPosicion() { return posicion; }


    //Devolvemos  los nombres de todos los jugadores que hay en la casilla

    public String getNombreJugadores() {
        String nombres = "";

        Iterator<Jugador> it=jugadores.iterator();
        while(it.hasNext())
        {
            Jugador jugador=(Jugador)it.next();
            nombres += jugador.getNombre();
            if(it.hasNext()) nombres += "-";
        }

        if(0 == nombres.length()) { nombres += "no hay jugadores"; }

        return nombres;
    }


}
