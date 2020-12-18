package monopoly;

public class CentroComercial extends Servicio_Privado {
    //Constructor por defecto
    public CentroComercial(){
        super();
        label = "Centro Comercial";
        //Obtenemos el precio de la casilla
        precio = Caja.getNumero(10000, 20000);
    }
}