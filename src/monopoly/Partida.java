package monopoly;
import java.io.Serializable;
import java.util.*;


public class Partida implements Serializable{
    //Tablero de juego, no es más que un sucesión de casillas de distintos tipos
    private Tablero tablero;
    //Lista de jugadores(se usa lista para mantener el orden de los jugadores )
    private ArrayList<Jugador> jugadores;
    //Número del 0 al num_jugadores-1 que establece el jugador que tiene el turno
    private int turno;

    //Constructor de partida
    public Partida() throws SinDineroExcepcion {
        //el turno empieza en el jugador 0(del arraylist de jugadores)
        turno = 0;
        //Construimos/inicializamos los jugadores de la partida
        crearJugadores();
        //Construimos/inicializamos el tablero de partida
        tablero = new Tablero();

        //Ponemos a todos los jugadores en la casilla de salida
        for(int i=0; i<jugadores.size(); i++) {
            tablero.getCasilla(0).cae(jugadores.get(i));
        }
    }

    //Constructor de partida a partir del número de un tablero ya disponible y de un número de jugadores
    public Partida(Tablero tablero, ArrayList<Jugador> jugadores) {
        turno = 0;
        this.tablero = tablero;
        this.jugadores = jugadores;
    }

    //Empezamos a jugar

    public void jugar() throws GanadorExcepcion {
        menuJuego();
    }


    //Mostramos el menú de juego
    private void menuJuego() throws GanadorExcepcion {
        //Por defecto no hay opción elegida
        int opcion = 0;

        //La partida seguirá en principio hasta que no se le de a salir en el menú
        while(opcion != 3) {

            //Cargamos el título y las opciones del menú de juego
            Menu menu_juego = new Menu(getEstadoJugadores(),
                    "Tirar dado "+jugadores.get(turno).getNombre(),
                    "Ver tablero",
                    "Salir");

            ///Mostramos el menú y esperamos la elección
            opcion = menu_juego.ver();
            Jugador jugador_turno = jugadores.get(turno);

            //Según la opción elegida
            switch(opcion) {
                /* Tirar dado */
                case 1:
                    try {
                        jugador_turno.turno(tablero);
                        nextTurno(); //Pasamos al siguiente turno
                    } catch (SinDineroExcepcion e) {
                        //eliminamos al jugador eliminado de la lista de jugadores
                        jugadores.remove(turno);
                        //devolvemos las cosas del jugador
                        jugador_turno.eliminar();
                        //retrasamos un turno por el jugador que acaba de ser eliminado
                        turno--;
                        //Pasamos al siguiente turno
                        Vista.print("\nEl jugador " + jugador_turno.getNombre() + " se quedó sin dinero.");
                        Vista.print("\nPor tanto será expulsado de la partida y sus pertenencias irá a la banca.\n");
                        nextTurno(); //pasamos al siguiente turno
                    }
                    break;
                /* Ver tablero */
                case 2:
                    //Vemos el estado de los jugadores y del tablero
                    Vista.print("Jugadores:" + getEstadoJugadores() + "\n\n" +
                            "Tablero:" + tablero);
                    break;
                case 3:
                    Vista.print("Gracias por jugar al Monopoly. Hasta otra!");
                default:
                    //Salir
            }
        }

    }

    // Pasamos el turno al siguiente jugador que le toque
    private int nextTurno() throws GanadorExcepcion {
        //Comprobamos si ya ha terminado la partida o no
        if(1 == jugadores.size() ) {
            if (turno == -1){
                turno +=1;
            }
            ///Lanzamos la excepción de que ya hay un ganador.
            throw new GanadorExcepcion("\nEl jugador "+jugadores.get(turno).getNombre()+" ha ganado la partida!!!!\n");
        }
        //incrementamos para pasar de turno
        turno++;
        //Como el turno es rotario después del último pasa al primero
        if(turno> (jugadores.size() -1) ) //el turno empieza en 0, por eso el -1
            turno = 0;

        return turno;
    }

    //Devolvemos un string con el estado de la partida
    private String getEstadoJugadores() {
        String estado_jugadores = new String();

        //Metemos en el string la cabecera de nombre de jugador
        estado_jugadores += String.format("\n%-25s",  "Nombre");
        //Metemos en el string la cabecera de color de jugador
        estado_jugadores += String.format(";%-10s", "Color" );
        //Metemos en el string la cabecera de dinero de jugador
        estado_jugadores += String.format(";%s", 	"Dinero ");
        //Metemos también una linea para que quede más bonito
        estado_jugadores += "\n--------------------------------------------------------------------";

        //Metemos uno a uno la información de todos los jugadores
        for(int i=0; i<jugadores.size(); i++) {
            estado_jugadores += "\n"+jugadores.get(i);
        }

        return estado_jugadores;
    }

    //Pedimos el número de jugadores que participaran en la partida
    private int selectNumjugadores() {
        int num_jugadores = -1;
        int max_jugadores = Jugador.MAX_JUGADORES();

        //Seguir pidiendo el número de jugadores hasta que sea una cantidad válida [2..NUM_MAX]
        while(num_jugadores<2 || num_jugadores>max_jugadores) {
            Vista.limpiar();
            if(num_jugadores != -1) { //Sino se selecciono los jugadores permitidos, mostramos error y volvemos preguntar
                Vista.print("\nCantidad de jugadores incorrecta, bobo o que");
            }

            try {
                //preguntamos cuantos jugadores
                String total = Vista.input("\nPor favor, indique el número de jugadores(2-"+max_jugadores+"):");
                //Validamos el total devuelto y comprobamos que sea un numero
                num_jugadores = Integer.parseInt(total);
            }catch(NumberFormatException e){
                num_jugadores = 0;
            }
        }

        return num_jugadores;

    }

    //Realizamos la creación de los jugadores que formaran parte de la partida
    private void crearJugadores() {
        //Traemos el número de jugadores que participaran
        int num_jugadores = selectNumjugadores();
        //inicializamos jugadores;
        jugadores = new ArrayList<Jugador>(num_jugadores);
        //Jugador temporal para comprobaciones
        Jugador jugador;
        //Por cada uno de los jugadores vamos pidiendo todos sus datos relativos
        for(int i=0; i<num_jugadores; i++) {
            Vista.limpiar();
            Vista.print("Ahora se procederá a la creación del jugador "+(i+1));
            jugador = new Jugador();

            //Comprobamos si ya existe el usuario
            while(jugadores.contains(jugador)) {
                Vista.limpiar();
                Vista.print("El nombre elegido para su jugador ya existe. Especifique otro.");
                jugador.nuevoNombre();
            }

            //metemos al jugador en la lista de jugadores de la partida
            jugadores.add(jugadores.size(),jugador);
        }

        //ordenamos los jugadores por sus nombres
        Collections.sort(jugadores);
    }

}