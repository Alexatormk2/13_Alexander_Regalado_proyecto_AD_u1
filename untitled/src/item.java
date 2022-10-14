public class item {

    double precio;
    String nombre;
    String rareza;
    String categoria;


    public item(double precio, String nombre, String rareza, String categoria) {
        this.precio = precio;
        this.nombre = nombre;
        this.rareza = rareza;
        this.categoria = categoria;
    }

    public item() {
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRareza() {
        return rareza;
    }

    public String getCategoria() {
        return categoria;
    }
}
