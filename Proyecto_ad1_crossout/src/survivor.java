import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

public class survivor implements Serializable {


    String nomnbre;
    int kills;
    int deaths;

    int derrotas;


    int victorias;
    vehiculos carro;
    int scrap;
    int copper;
    double gold = 100;

    public survivor(String nomnbre, int kills, int deaths,  vehiculos carro, int scrap, int copper, double gold, int victorias, int derrotas) {
        this.nomnbre = nomnbre;
        this.kills = kills;
        this.deaths = deaths;

        this.carro = carro;
        this.scrap = scrap;
        this.copper = copper;
        this.gold = gold;
        this.victorias = victorias;
        this.derrotas = derrotas;
    }

    public survivor(String nomnbre, vehiculos carro) {
        this.nomnbre = nomnbre;
        this.carro = carro;
    }

    public survivor() {
    }


    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public String getNomnbre() {
        return nomnbre;
    }

    public void setNomnbre(String nomnbre) {
        this.nomnbre = nomnbre;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
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

    public int getScrap() {
        return scrap;
    }

    public void setScrap(int scrap) {
        this.scrap = scrap;
    }

    public int getCopper() {
        return copper;
    }

    public void setCopper(int copper) {
        this.copper = copper;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }


    //metodos


}
