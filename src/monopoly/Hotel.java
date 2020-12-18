package monopoly;

public class Hotel  extends Servicio_Privado  {
    //Constructor por defecto
    public Hotel() {
        super();
        label="Hotel";
        //Obtenemos el precio de la casilla
        precio = Caja.getNumero(5000, 10000);
    }
}