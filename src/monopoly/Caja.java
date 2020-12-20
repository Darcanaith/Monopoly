package monopoly;
import java.util.ArrayList;

//Aquí se generan todos los random
public class Caja {
    //Contiene todos los números que luego se irán sacando al azar
    private ArrayList<Integer> numeros;

    //Constructor por defecto de Caja, por defecto 100 numeros aleatorios
    public Caja() {
        this(100);
    }

    //Añadir los random
    public Caja(int max) {
        numeros = new ArrayList<Integer>(max);
        for(int i=0; i<=max; i++) {
            numeros.add(i, i);
        }
    }

    public int getNumero() {
        int pos = getNumero(0, numeros.size());
        return getNumero(pos);
    }

    public int getNumero(int pos) {
        return numeros.remove(pos);
    }


    //Sacamos num random
    static public int getNumero(int desde, int hasta) {
        return (int)(Math.random()*hasta)+desde;
    }



    public boolean vacio() {
        if(numeros.size() > 0)
            return false;
        else
            return true;
    }

    //Para poder depurar bien
    public String toString() {
        return numeros.toString();
    }
}
