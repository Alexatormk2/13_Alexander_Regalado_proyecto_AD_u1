import java.io.Serializable;

public class vehiculos implements Serializable {

     Clascificacion clascificacion;
    int durabilidad;
    String nombre;
    int danio;
    String descripcion;



    public vehiculos(int danio,String nombre,int durabilidad,      String descripcion, Clascificacion clascificacion) {
        this.clascificacion = clascificacion;
        this.durabilidad = durabilidad;
        this.nombre = nombre;
        this.danio = danio;
        this.descripcion = descripcion;
    }

    public Clascificacion getClascificacion() {
        return clascificacion;
    }

    public void setClascificacion(Clascificacion clascificacion) {
        this.clascificacion = clascificacion;
    }

    static class Clascificacion implements  Serializable{
        String tipo;
        String categoria;

        public Clascificacion(String tipo, String categoria) {
            this.tipo = tipo;
            this.categoria = categoria;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
    }



    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDurabilidad() {
        return durabilidad;
    }

    public void setDurabilidad(int durabilidad) {
        this.durabilidad = durabilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDanio() {
        return danio;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }
}
