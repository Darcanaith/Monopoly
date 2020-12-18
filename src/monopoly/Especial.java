package monopoly;

abstract public class Especial extends Casilla{
    protected Especial() {
        super();
    }

    //Devuelve los distintos tipos de casillas especiales que hay
    static public String[] getTipos() {//??
        return new String[]{"Carcel", "Loteria"};
    }



    //Factory de casillas especiales
    static public Casilla getCasilla(String tipo) {

        try {
            return (Casilla) Class.forName("monopoly.Carcel"+"monopoly.Loteria"+"monopoly.Salida" +tipo.trim()).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Tipo de casilla especial no encontrado");
        }

    }

}

