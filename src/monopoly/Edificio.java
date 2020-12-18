package monopoly;

public class Edificio extends Alojamiento {
    public Edificio() {
        super();
        label = "Edificio";
        //Obtenemos el precio de la casilla
        precio = Caja.getNumero(5000, 10000);
    }
}