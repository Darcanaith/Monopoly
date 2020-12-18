package monopoly;
import java.io.Serializable;
import java.util.ArrayList;

class Color implements Serializable{

    //Es el label del color selecionado
    private String seleccionado;
    //Listado de colores disponibles a escoger
    private static ArrayList<String> colores;

    //inicialización la parte estática de la clase
    public static void main(String[] args) {
        ArrayList<String> colores = new ArrayList<>();
        colores.add("Rojo");
        colores.add("Azul");
        colores.add("Verde");
        colores.add("Rosa");
        colores.add("Amarillo");
    }
    //Constructor que nos permite inicializar el objeto
    public Color() {
        //Si no hay más colores
        if(colores.size() < 1) {
            throw new IllegalAccessError("No hay más colores para escoger");
        }
        //Pedimos el color que se quiera y lo guardamos
        seleccionado = pedirColor();
    }

    //Constructor que recibe el color a ser usado
    public Color(String color) {
        //Al selecionar un color hay que quitarlo del listado de colores disponible
        if (colores.contains(color) ) {
            seleccionado = color;
            colores.remove(color);
        }else {
            ///Sino está el color lo indicamos
            throw new IllegalArgumentException("No se ha encontrado color");
        }
    }

    public String get() { return seleccionado; }
    public String toString() { return this.get(); }

    //Añadir colores
    public static void add(String... colores) {
        //Si no se ha inicializado la lista de colores, lo hacemos a partir de los colores pasados
        if(null == Color.colores ) {
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
        String[] base = new String[1];
        return (String[]) colores.toArray(base);
    }

    public String pedirColor() {
        int color_elegido = 0;
        Menu menu_colores = new Menu("Selección de color");

        //Cargamos el menú con los colores disponibles
        for(int i= 0; i<colores.size(); i++) {
            menu_colores.addOption(colores.get(i));
        }

        //MOstramos los distintos colores para que escojan uno
        color_elegido = menu_colores.ver();

        //Elinamos el color escogido para que nadie más pueda cogerlo
        return colores.remove(color_elegido-1);
    }
}
