package monopoly;

public class Casa_unifamiliar extends Alojamiento {
    public Casa_unifamiliar() {
        super();
        label = "Casa Unifamiliar";
        //Obtenemos el precio de la casilla
        precio = Caja.getNumero(500, 1000);
    }
}