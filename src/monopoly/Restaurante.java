package monopoly;

public class Restaurante  extends Servicio_Privado {
    //Constructor por defecto
    public Restaurante() {
        super();
        label="Restaurante";
        //Obtenemos el precio de la casilla
        precio = Caja.getNumero(500, 1000);
    }
}