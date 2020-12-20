package monopoly;
import java.util.*;

public class Carcel extends Especial {
    //Conjunto de jugadores atrapados en la cárcel a la espera de sacar un 5
    protected Set<Jugador> atrapados;
    public Carcel()  {
        super();
        label = "Cárcel";
        atrapados = new HashSet<Jugador>();
    }

    //Si el jugador ya no está atrapado en la cárcel sale
    public void sale(Jugador jugador) {
        if(!atrapados.contains(jugador)) {
            jugadores.remove(jugador);
            jugador.setPosicion(null);
            return;
        }

        //Mostramos al jugador el mensaje
        Vista.limpiar();
        Vista.print("\nAndas metido aún en el agujero, looser.");
        Vista.print("\nRecuerda, que si quieres salir el siguiente turno de aquí");
        Vista.print("\ntendrás que sacar un cinco");

        //Hacemos que el jugador tire el dado para ver si sale o no
        if ( 5 == Dado.tirar(jugador)  ) { //Si sale un 5 sale
            atrapados.remove(jugador); //y quitamos al jugador de jugadores atrapados
            Vista.print("\nEnhorabuena, sales libre el próximo turno");
        }else { //Sino saca un 5 le tocará seguir estando en la cárcel
            Vista.print("\nPor ahora, sigues en la cárcel.");
            Vista.print("Yo de tí no me agacharía en la ducha...\n");
        }
    }

    //Cae en la carcel
    public void cae(Jugador jugador) {
        //Añadimos el jugador a jugadores en esta casilla
        jugadores.add(jugador);
        //Marcamos la posicion actual del jugador
        jugador.setPosicion(this);
        //Guardamos al jugador en jugadores atrapados
        atrapados.add(jugador);
        //Le decimos al jugador que ha caido en la carcel
        Vista.print("\nVaya looser estas echo");
        Vista.print("\nHas entrado en la carcel ¿Tienes abogado? \n");
    }
}