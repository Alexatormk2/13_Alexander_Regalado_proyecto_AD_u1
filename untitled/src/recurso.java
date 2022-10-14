public class recurso {


    String nombre;
    double precio;

    public recurso(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }


    public recurso() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
