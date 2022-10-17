public class item {

    double precio;
    String nombre;
    String rareza;
    String categoria;


    public item(double precio, String nombre,String categoria) {
        this.precio = precio;
        this.nombre = nombre;

        this.categoria = categoria;
    }

    public item() {
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }



    public String getCategoria() {
        return categoria;
    }
}
