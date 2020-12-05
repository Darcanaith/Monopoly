package monopoly;

public class Jugador {
    private String Nombre;
    private String Color;
    private Integer Posicion;
    private Integer Dinero = 1000;
    private Integer Casillas_adquiridas;

    public Jugador(String nombre, String color, Integer posicion,  Integer casillas){
        this.Nombre = nombre;
        this.Color = color;
        this.Posicion = posicion;
        this.Casillas_adquiridas = casillas;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
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

