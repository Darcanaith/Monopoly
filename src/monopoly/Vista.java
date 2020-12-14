package monopoly;

import java.util.Scanner;

public class Vista {

    public static void print(String texto) {
        System.out.print(texto);
    }

    //pedir info
    public static String input(String texto) {
        Vista.print(texto);
        Scanner Input = new Scanner(System.in);
        return Input.nextLine();
    }

     //Limpiar la pantalla
    public static void limpiar()  {
        for(byte i= 0; i<20; i++) {
            System.out.println("");
        }
    }
}

