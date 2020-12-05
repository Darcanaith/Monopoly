package monopoly;

public class Jugador {
    private String Nombre;
    private enum Color{};
    private Integer Posicion;
    private Integer Dinero = 10000;
    private Integer Casillas_adquiridas;

    //Creamos contructor de clase Jugador
    public Jugador(String nombre, String color, Integer posicion,  Integer casillas){
        this.Nombre = nombre;
        this.Posicion = posicion;
        this.Casillas_adquiridas = casillas;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Integer getPosicion() {
        return Posicion;
    }

    public void setPosicion(Integer posicion) {
        Posicion = posicion;
    }

    public Integer getDinero() {
        return Dinero;
    }

    public void setDinero(Integer dinero) {
        Dinero = dinero;
    }

    public Integer getCasillas() {
        return Casillas_adquiridas;
    }

    public void setCasillas(Integer casillas) {
        Casillas_adquiridas = casillas;
    }

}