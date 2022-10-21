import java.io.Serializable;

public class BOT implements Serializable {


    String nombre;
    int kill = 0;
    int deaths = 0;
    String descripcionBot;
    vehiculos carro;
    String fraseVictoria;
    String fraseDerrota;

    public BOT(String nombre, vehiculos carro,String DescriptionBot, String fraseVictoria, String fraseDerrota) {
        this.nombre = nombre;
        this.carro = carro;
        this.descripcionBot = DescriptionBot;
        this.fraseDerrota = fraseDerrota;
        this.fraseVictoria = fraseVictoria;
    }


    public BOT(String nombre, int kill, int deaths, vehiculos carro, String DescriptionBot, String fraseVictoria, String fraseDerrota) {
        this.nombre = nombre;
        this.kill = kill;
        this.deaths = deaths;
        this.carro = carro;
        this.descripcionBot = DescriptionBot;
        this.fraseDerrota = fraseDerrota;
        this.fraseVictoria = fraseVictoria;
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

    public String getDescripcionBot() {
        return descripcionBot;
    }

    public void setDescripcionBot(String descripcionBot) {
        this.descripcionBot = descripcionBot;
    }

    public vehiculos getCarro() {
        return carro;
    }

    public void setCarro(vehiculos carro) {
        this.carro = carro;
    }

    public String getFraseVictoria() {
        return fraseVictoria;
    }

    public void setFraseVictoria(String fraseVictoria) {
        this.fraseVictoria = fraseVictoria;
    }

    public String getFraseDerrota() {
        return fraseDerrota;
    }

    public void setFraseDerrota(String fraseDerrota) {
        this.fraseDerrota = fraseDerrota;
    }
}
