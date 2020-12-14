package monopoly;
import org.*;

import java.util.Random;

public class Dado {
    private Integer num_dado;

    public Dado() {
       Valor_dado();
    }

    public Integer getNum_dado() {//Obtiene el valor que tiene el dado
        return num_dado;
    }

    public void Valor_dado(){
        Random random = new Random();
        int numero_random = random.nextInt(6);
        if (numero_random == 0){
            numero_random = numero_random +1;
        }
        this.num_dado = numero_random;
    }
}
