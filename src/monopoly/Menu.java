package monopoly;
import java.util.ArrayList;

public class Menu {
    //Las distintas opciones que se presentaran en el menú
    private ArrayList <String> opciones;
    //Título que tendrá el menú
    private String title;

    //Constructor por defecto
    public Menu() {
        this("");
    }

    /* Constructor que pasamos el titulo y las distintas
     * opciones que formaran parte del menú
     */
    public Menu(String titulo, String... opciones) {
        this.opciones = new ArrayList<String>();
        this.title = titulo;

        //Si se han añadido opciones las añadimos.
        if(opciones.length > 0) {
            for(int i=0; i<opciones.length; i++) {
                addOption(opciones[i]);
            }
        }
    }

    //Añadimos una opción al menú
    public void addOption(String opcion) {
        opciones.add(opcion);
    }

    //Visualizamos el menú
    public int ver() {
        //La opción mínima
        int min = 1;
        //La opción máxima
        int max = opciones.size();
        //Por defecto no hay opción seleccionada
        int selec = -1;

        do {
            //Si hay un título de menú, lo mostramos
            if(!this.title.equals("")) {
                Vista.print(title+"\n\n");
            }

            // Vamos mostrando las distintas opciones
            for(int i = min; i<=max; i++) {
                Vista.print(" "+i+"."+opciones.get(i-1)+"\n");
            }
            //Comprobar que ha escogido opción valida
            if(selec != -1) {
                Vista.print("\n Opción no válida. \n Vuelva a seleccionar una opción correcta.\n");
            }
            try {
                //Obtenemos la opción elegida pasándola a entero
                selec = Integer.parseInt(Vista.input("\nSeleccione una opción("+min+"-"+max+"):"));
            }catch(NumberFormatException e) {
                //Si se selecciono algo no numérico consideramos que escribió un 0
                selec = 0;
            }
            //Seguimos mientras que no se haya escogido una opción de las disponible
        }while(selec<min  || selec>max);

        //Devolvemos el número de la opción elegida
        return selec;
    }
}