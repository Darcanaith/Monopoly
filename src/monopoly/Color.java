package monopoly;
import java.io.Serializable;
import java.util.ArrayList;

class Color implements Serializable{

    //Es el label del color seleccionado
    private String seleccionado;
    //Listado de colores disponibles a escoger

    private static ArrayList<String> colores = new ArrayList<String>() {{
        add("Rojo");
        add("Azul");
        add("Verde");
        add("Rosa");
        add("Amarillo");
    }};

    //inicialización la parte estática de la clase, pruebas
    public static void main(String[] args) {
        getColores();
    }

    //Constructor que nos permite inicializar el objeto
    public Color() {

        //Si no hay más colores
        if(colores.size() < 1) {
            throw new IllegalAccessError("No hay más colores para escoger");
        }
        //Pedimos el color que quiera y lo guardamos
        seleccionado = pedirColor();
    }

    //Constructor que recibe el color a ser usado
    public Color(String color) {
        //Al seleccionar un color hay que quitarlo del listado de colores disponible
        if (colores.contains(color) ) {
            seleccionado = color;
            colores.remove(color);
        }else {
            ///Sino está el color lo indicamos
            throw new IllegalArgumentException("No se ha encontrado color");
        }
    }

    public String get() { return seleccionado; }

    //Añadir colores

    public static void add(String... colores) {
        //Si no se ha inicializado la lista de colores, lo hacemos a partir de los colores pasados
        if(Color.colores.isEmpty() ) {
            Color.colores = new ArrayList<String>(colores.length);
        }

        ///Empezamos a añadir los colores que nos hayan pasado y que aún no estén
        for(int i= 0; i<colores.length;) {
            if(!Color.colores.contains(colores[i])) {
                Color.colores.add(i, colores[i]);
                i++;
            }
        }
    }


    //colores disponibles
    public static String[] getColores() {
        String[] base = colores.toArray(new String[0]);

        return base;
    }

    public String pedirColor() {
        int color_elegido = 0;
        Menu menu_colores = new Menu("\nSelección de color: ");

        //Cargamos el menú con los colores disponibles
        for(int i= 0; i<colores.size(); i++) {
            menu_colores.addOption(colores.get(i));
        }

        //Mostramos los distintos colores para que escojan uno
        color_elegido = menu_colores.ver();

        //Eliminamos el color escogido para que nadie más pueda cogerlo
        return colores.remove(color_elegido-1);
    }
}