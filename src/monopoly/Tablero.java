package monopoly;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.HashMap;

public class Tablero implements Serializable {

    //Tamaño maximo y minimo
    private int Tamanyo() {
        int tamanyo = 0;

        int tam_min = 20;
        int tam_max = 100;


        Vista.clear();
        //Mientras que no nos de un valor adecuado de tablero seguimos preguntando [min..max] y divisible por 10
        while(tamanyo < tam_min || tamanyo > tam_max  || 0 != (tamanyo%10) ) {
            try {
                tamanyo = Integer.parseInt(Vista.input("\n\nPor favor, escoja tamaño del tablero("+tam_min+"-"+tam_max+"):"));
            }catch(NumberFormatException e) {
                //Si nos han querer poner algo que no sea un número se lo advertimos y pedimos de nuevo
                Vista.print("\nEspecifique un tamaño de tablero entendible.");
                continue;
            }
            //Mensaje según el error haya cometido al introducir los tamaños
            if(tamanyo < tam_min)
                Vista.print("\nTamaño del tablero demasiado pequeño(mínimo: "+tam_min+").");
            else if(tamanyo < tam_max)
                Vista.print("\nTamaño del tablero demasiado grande(máximo: "+tam_max+").");
            else if(0 != (tamanyo%10) ) {
                Vista.print("\nTamaño del tablero tiene que ser disible entre 10.");
            }

        }

        return tamanyo;
    }



}
