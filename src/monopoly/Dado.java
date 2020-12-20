package monopoly;

public class Dado {
    //Devuelve num sacado
    static public byte tirar(Jugador jugador)  {
        String duenyo = jugador.getNombre();
        //Obtenemos un número al azar entre 1 y 6
        byte numero = (byte) Caja.getNumero(1,6);
        Vista.limpiar();
        //Mostramos al jugador el número que ha sacado
        Vista.print("\n"+duenyo+", has sacado un "+numero+".");
        return numero;
    }

}

