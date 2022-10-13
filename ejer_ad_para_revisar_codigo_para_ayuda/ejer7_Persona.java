import java.io.Serializable;

public class ejer7_Persona implements Serializable {


    private String nombre;
    private int edad;

    public ejer7_Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public ejer7_Persona() {
        this.nombre = null;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    //devuelve   nombre
    public int getEdad() {
        return edad;
    } //devuelve edad




}
