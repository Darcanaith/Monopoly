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

     //Limpiar la pantalla(eso es lo que parece en pantalla jeje)
    public static void limpiar()  {
        for(byte i= 0; i<10; i++) {
            System.out.println("");
        }
    }

    //Para notificar error
    public static void error(String texto) {
        limpiar();
        print("ERROR:"+texto+"\n");
        System.exit(-1);
    }
}