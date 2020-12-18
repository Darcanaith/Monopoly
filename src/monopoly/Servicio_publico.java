package monopoly;

public  class Servicio_publico extends Normal {
    //Constructor por defecto
    public Servicio_publico() {
        super();
        label="Servicio público";
        //Obtenemos el precio de la casilla
        precio = Caja.getNumero(100, 500);
    }
}
