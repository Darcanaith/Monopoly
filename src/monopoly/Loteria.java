package monopoly;

public class Loteria extends Especial {
    public Loteria()  {
        super();
        label = "Lotería";
    }

    //jugador cae en loteria
    public void cae(Jugador jugador) throws SinDineroExcepcion {
        //Lo añadimos al conjunto de jugadores situados en la casilla
        jugadores.add(jugador);
        //Marcamos la casilla actual
        jugador.setPosicion(this);

        //Le mostramos la información sobre esta casilla
        Vista.print("\nSi sacas dos seis tu dinero se verá incrementado en un 10%.");
        Vista.print("\nEl coste por jugar son 100 euros");

        //Tirar el dado

        if(6 == Dado.tirar(jugador)) { //Si al tirar el primer dado saca un 6, continua
            Vista.print("Ya estás más cerca de ganar el euromillón.");
            Vista.print("Recuerda que sólo te falta sacar otro seis.");
            if(6 == Dado.tirar(jugador) ){ //Si saca dos seises ha conseguido el premio
                Vista.print("Has conseguido el premio. Ahora eres un 10% más rico que antes.");
                Vista.print("Menos 100 euros por el boleto del juego");
                //Calculamos el premio(10% del total de dinero que tiene)
                int premio = Math.round(jugador.getDinero()/10);
                //Se lo asignamos(quitándole 100 euros por caer en la casilla )
                jugador.setDinero(jugador.getDinero() + premio - 100);
                return;
            }
        }

        //Informamos al jugador de la mala suerte que tiene
        Vista.print("\nNo conseguiste el premio y perdiste 100 euros en el intento.\n");
        Vista.print("Manco :) \n");
        //Le quitamos 100 euros por caer en la casilla
        jugador.setDinero(jugador.getDinero() - 100);
        return;
    }
}