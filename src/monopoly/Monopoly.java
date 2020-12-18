package monopoly;

public class Monopoly {
    public static void main(String[] args){

        try {

            //Instanciamos la clase Monopoly
            Monopoly juego = new Monopoly();
            //Llamamos al menú principal del juego
            Partida partida = juego.menuPrincipal();
            //Comenzamos la partida
            partida.jugar();
        }catch(GanadorExcepcion | SinDineroExcepcion e) {//Un jugador ha conseguido ganar la partida
            Vista.print(e.getMessage()+"\nLa partida ha finalizado, hay ganador.\n");
        }/*catch(Exception e) {	//Si hubiera algún error, lo notificamos como es debido.
            Vista.error("\n Error 404: adios...\n");
        }*/
    }

    ///Mostramos el menu del juego principal
    public Partida menuPrincipal() throws SinDineroExcepcion {
        Partida partida = null;
        do { //El menú seguirá mostrandose hasta que se escoja partida o se quiera acabar el juego

            //Cargamos el menú y las opciones del juego
            Menu menu_principal = new Menu("Bienvenido a Monopoly\n By:\n - Moustafa Mahyou\n - Daniel Carvajal\n\nQue quieres hacer?",
                    "Empezar nueva partida",
                    "Salir");
            int opcion = menu_principal.ver(); //Mostramos el menú y esperamos una elección


            //Empezamos una nueva partida
            //Se selecionó acabar así que nos despedimos
            switch (opcion) {
                case 1 -> partida = new Partida();
                case 2 -> {
                    Vista.print("\nGracias por jugar al Monopoly. Hasta pronto");
                    System.exit(0);
                }
            }
        }while(partida == null);

        //Devolvemos partida
        return partida;
    }

}