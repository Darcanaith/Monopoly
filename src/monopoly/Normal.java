package monopoly;
import jdk.dynalink.beans.StaticClass;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

abstract public class Normal extends Casilla{
    //Precio de la casilla
    protected int precio = 0;
    //Dueño de la casilla
    protected Jugador duenyo = null;

    //Constructor por defecto
    protected Normal() {
        super();
    }

    //Jugador cae en casilla
    public void cae(Jugador jugador)  throws SinDineroExcepcion {
        super.cae(jugador);
        //si no tiene dueño le ofrecemos al jugador la posibilidad de comprarla
        if(duenyo == null) {
            //Si el jugador tiene dinero, seguimos adelante
            if(precio < jugador.getDinero() ) {
                String respuesta;
                //pregutnamos al jugador si quiere comprarla
                do {
                    respuesta = Vista.input("\n¿Te gustaría comprar "+label+" por "+getPrecioFormated()+" (s/n)?");

                    //si se decide a comprarla...
                    if((respuesta.equals("S") || respuesta.equals("s")) ){
                        //Le quitamos el dinero de lo que cuesta
                        jugador.setDinero(jugador.getDinero() - precio);
                        //Le asignamos como dueño
                        setDuenyo(jugador);
                        //Le mostramos claramente que casilla ha comprado
                        Vista.print("Felicidades, has comprado la casilla "+label);
                        //Añadimos la casilla al listado de casillas del jugador
                        jugador.addCasilla(this);
                        break; //Saltamos el bucle y ahorramos comprobacionesçççç
                    }
                    //El bucle seguirá hasta que no se escoga entre s/n
                }while(!respuesta.equals("n") && !respuesta.equals("N"));
            }else {
                //El jugador no tiene dinero para comprar la casilla y se lo decimos
                Vista.print("\nEsta casilla no tiene dueño, pero tampoco tienes dinero para comprarla.");
                Vista.print("\nConsigue "+getPrecioFormated()+" euros para la próxima vez");
            }
        }else if ( duenyo != jugador) {
            //Si el jugador no es el dueño, malas noticias para el
            //Avisamos de quien es el dueño
            Vista.print("\nEsta casilla pertenece a "+duenyo.getNombre());
            //si el dueño está en la cárcel no tiene porque pagarle el jugador
            if( !jugador.encarcelado()) {
                //le informamos de lo que tiene que pagar el jugador
                Vista.print("Por ello tienes que pagarle "+getCosteFormated());
                jugador.setDinero(jugador.getDinero() - getCoste());
            }else {
                //Informamos que se libra de pagar al dueño porque el dueño está en la carcel.
                Vista.print("Pero como está en la cárcel no le tienes que pagar.");
            }
        }else {
            //No hacemos nada la casilla ya es del jugador
            Vista.print("\nEsta casilla es de tu propiedad");
        }
    }




    static public String[] getTipos() {
        return new String[]{"Casa_unifamiliar","Edificio","CentroComercial","Hotel","Restaurante","Agua","Gas","Luz","Telefono"};
    }





    //Factory casillas
    static public Casilla getCasilla(String tipo) {
        try {
            //Obtenemos el objeto de la clase pedida
            if (tipo.equals("Casa_unifamiliar")){
                return new Casa_unifamiliar();
            }
            else if (tipo.equals("Edificio")){
                return new Edificio();
            }
            else if(tipo.equals("CentroComercial")){
                return new CentroComercial();
            }
            else if(tipo.equals("Hotel")){
                return new Hotel();
            }
            else if(tipo.equals("Restaurante")){
                return new Restaurante();
            }
            else if(tipo.equals("Agua")){
                return new Agua();
            }
            else if(tipo.equals("Gas")){
                return new Gas();
            }
            else if(tipo.equals("Luz")){
                return new Luz();
            }
            else{
                return new Telefono();
            }

            //return (Casilla) Class.forName("Monopoly.Casa_unifamiliar"+"Edificio"+"CentroComercial"+"Hotel"+"Restaurante"+"Agua"+"Gas"+"Luz"+"Telefono"+tipo.trim()).newInstance();
        } catch (Exception e) {
            //Si no es un tipo válido, le mandamos una excepción.
            throw new IllegalArgumentException("Tipo de casilla no especial no encontrado");
        }
    }


    //Getter y Setter

    //Precio de la casilla
    public int getPrecio() { return precio; }
    //Precio de la casila en euros
    public String getPrecioFormated() {
        Locale local = new Locale("ES", "ES");

        NumberFormat f = NumberFormat.getNumberInstance(local);
        return f.format(precio)+" euros";
    }
    //Coste de la casilla
    public int getCoste() { return Math.round(getPrecio()/10); }
    //Coste de la casilla en euros
    public String getCosteFormated() {
        Locale local = new Locale("ES", "ES");

        NumberFormat f = NumberFormat.getNumberInstance(local);
        return f.format(getCoste())+" euros";
    }
    //Dueño de la casilla
    public String getDuenyo() { if(duenyo != null) return duenyo.getNombre(); else return "sin dueño"; }
    //asignamos un nuevo dueño a la casilla
    public void setDuenyo(Jugador jugador) { duenyo = jugador; }

    //Devolvemos un string con información sobre la casilla
    public String toString()  {
        String cadena = new String();
        cadena += String.format("\n%3d:", posicion);
        cadena += String.format("%-22s",getLabel()			 ); //Tipo de Casilla
        cadena += String.format(";%-18s",getPrecioFormated());//Precio de compra casilla ( getPrecio() )
        cadena += String.format(";%-18s", getCosteFormated()); //Coste por caer ( getCoste() )
        cadena += String.format(";%-15s",getDuenyo()); //Dueño ( getDuenyo() )
        cadena += String.format(";%s",getNombreJugadores()); //Dueño ( getNombreJugadores() )

        return cadena;
    }
}