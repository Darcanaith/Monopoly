package monopoly;

//Solo se utiliza para la salida, luego queda inutilizada

public class Salida extends Casilla{
    public Salida() {
        super();
        label="Salida";
    }

    // Devolvemos la información de la casilla
    public String toString() {
        if(jugadores.isEmpty()) {
            return "";
        }else{
            return super.toString();
        }
    }
}