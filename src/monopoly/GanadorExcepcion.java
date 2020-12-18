package monopoly;

//Excepcion para cuando gane 1 jugador
public class GanadorExcepcion extends Exception  {
    public GanadorExcepcion(String mensaje){
        super(mensaje);
    }
}
