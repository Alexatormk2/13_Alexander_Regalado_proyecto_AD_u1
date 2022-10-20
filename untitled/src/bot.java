import java.io.Serializable;

public class BOT implements Serializable {


    String nombre;
    int kill = 0;
    int deaths = 0;
    vehiculos carro;

    public BOT(String nombre, vehiculos carro) {
        this.nombre = nombre;
        this.carro = carro;
    }


    public BOT(String nombre, int kill, int deaths, vehiculos carro) {
        this.nombre = nombre;
        this.kill = kill;
        this.deaths = deaths;
        this.carro = carro;
    }

    public BOT() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public vehiculos getCarro() {
        return carro;
    }

    public void setCarro(vehiculos carro) {
        this.carro = carro;
    }
}
