package monopoly;

import java.io.Serializable;


public class Tablero implements Serializable{

    //Listado de casillas que forman el tablero
    private Casilla[] casillas;

    public Tablero() {
        int tamanyo = pedirTamanyo();
        //A partir del tamaño del tablero creamos este
        //El +1 es para añadir la casilla de salida
        casillas = new Casilla[tamanyo+1];

        //Creamos una caja de números del tamaño seleccionado
        Caja caja = new Caja(tamanyo);
        //Colocamos la casilla de salida
        setSalida(caja);
        //Colocamos las casillas especiales
        setEspeciales(tamanyo/10, caja);
        //Colocamos las casillas no especiales
        setNormales(caja);

    }

    public Tablero(Casilla[] casillas) {
        this.casillas = casillas;
    }

    /*
     Movemos una ficha/jugador por el tablero
     inicio casilla a partir desde que se va a mover la ficha/jugador
     movimientos Número de casillas que se moverá
     casilla, retorna la casilla final
     */
    public Casilla moverFicha(Casilla inicio, byte movimientos) {
        int pos_inicial = inicio.getPosicion();
        int pos_final = pos_inicial + movimientos;
        //Si se ha llegado al final del tablero, toca recomenzar desde casilla 1
        if(pos_final >= casillas.length) {
            pos_final = pos_final - casillas.length + 1 /* la salida no cuenta */;
        }

        return casillas[pos_final];
    }


    //Pedir num casillas
    private int pedirTamanyo() {
        int tamanyo = 0;
        //Cantidad mínima que puede tener el tablero
        int tam_min = 20;
        //Cantidad máxima que puede tener el tablero
        int tam_max = 100;


        Vista.limpiar();
        //Mientras que no nos de un valor adecuado de tablero seguimos preguntando [min..max] y divisible por 10
        while(tamanyo < tam_min || tamanyo > tam_max  || 0 != (tamanyo%10) ) {
            try {
                tamanyo = Integer.parseInt(Vista.input("\n\nPor favor, escoja tamaño del tablero, entre ("+tam_min+"-"+tam_max+"):"));
            }catch(NumberFormatException e) {
                //Si introducen algo que no sea num
                Vista.print("\nEspecifique un tamaño de tablero entendible. Por ejemplo, 40");
                continue;
            }
            //Mensajes personalizados según el error haya cometido al introducir los tamaños
            if(tamanyo < tam_min)
                Vista.print("\n El tamaño del tablero es demasiado pequeño(mínimo "+tam_min+").");
            else if(tamanyo > tam_max)
                Vista.print("\n El tamaño del tablero es demasiado grande(máximo "+tam_max+").");
            else if(0 != (tamanyo%10) ) {
                Vista.print("\n El tamaño del tablero tiene que ser divisible entre 10.");
            }

        }

        return tamanyo;
    }



    //Añadimos a la casilla de salida,todos los jugadores
    private void setSalida(Caja caja) {
        casillas[0] = new Salida();
        caja.getNumero(0); //quitamos por tanto, de la caja, la casilla de salida
    }

    /*
     * Situamos la casillas especiales
     * num_especiales el número de casillas especiales que se colocaran
     * caja, caja con las distintas posiciones disponibles que hay para colocar casillas
     */
    private void setEspeciales(int num_especiales, Caja caja) {

        //Obtenemos todos los tipos de casillas especiales
        String[] casillas_especiales = Especial.getTipos();

        //Para cada tipo de casilla especial y mientras la caja no esté vacía
        // Vamos sacando casillas disponibles del tablero y a esa le asignamos el tipo
        for(int i=0; i<casillas_especiales.length && !caja.vacio(); i++) {
            int cantidad = num_especiales;
            while(cantidad > 0 && !caja.vacio()){
                int num_casilla = caja.getNumero();
                casillas[num_casilla] = Especial.getCasilla(casillas_especiales[i]);
                casillas[num_casilla].setPosicion(num_casilla);
                cantidad--;
            }
        }

    }

    private void setNormales(Caja caja) {
        String[] casillas_no_especiales = Normal.getTipos();
        for(int i=0; !caja.vacio(); i++) {
            int num_casilla = caja.getNumero();
            casillas[num_casilla] = Normal.getCasilla(casillas_no_especiales[i]);
            casillas[num_casilla].setPosicion(num_casilla);
            if(i == casillas_no_especiales.length-1) {
                i=0;
            }
        }
    }

    //Getters y Setters
    public Casilla getCasilla(int pos){
        if(pos<0 || pos>casillas.length) {
            throw new IllegalArgumentException("Acceso a casilla fuera de rango");
        }

        return casillas[pos];
    }

    //Devuelve un string con toda la información del tablero en formato CSV
    public String toString() {
        String tablero = new String("");
        //Cabecera del número de casilla
        tablero += String.format("\n%3s:",  "Num"          );
        //Cabecera de tipo
        tablero += String.format("%-22s",   "Tipo"         );
        //Cabecera del precio de compra
        tablero += String.format(";%-18s",  "Precio Compra");
        //Cabecera del coste que se tiene al caer
        tablero += String.format(";%-18s",  "Coste caer"	  );
        //Cabecera del dueño de casilla
        tablero += String.format(";%-15s",  "Dueño casilla");
        //Cabecera para jugadores que hay en una casilla
        tablero += String.format(";%s",	"Jugadores en casilla");

        //Línea de separación de cabecera y datos casilla
        tablero += "\n"; for(int i=0; i<150; i++) tablero += "-";

        //Ahora por cada casilla, vamos devolviendo sus datos
        for(int i=0; i<casillas.length; i++) {
            if (!casillas[i].getLabel().equals("Salida")){
                tablero += casillas[i];
            }
        }
        tablero += "\n";

        //Devolvemos toda la información del tablero
        return tablero;
    }
}