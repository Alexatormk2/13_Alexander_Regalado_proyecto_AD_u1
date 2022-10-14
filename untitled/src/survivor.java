import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class survivor {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String nomnbre;
    int kills;
    int deaths;
    int soldItems;
    item[] almacen;
    int bougthItems;
    vehiculos carro;
    int scrap;
    int copper;
    double gold = 100;

    public survivor(String nomnbre, int kills, int deaths, int soldItems, int bougthItems, vehiculos carro, int scrap, int copper, double gold) {
        this.nomnbre = nomnbre;
        this.kills = kills;
        this.deaths = deaths;
        this.soldItems = soldItems;
        this.bougthItems = bougthItems;
        this.carro = carro;
        this.scrap = scrap;
        this.copper = copper;
        this.gold = gold;
    }

    public survivor(String nomnbre,  vehiculos carro) {
        this.nomnbre = nomnbre;

        this.carro = carro;

    }

    public survivor() {
    }

    public item[] getAlmacen() {
        return almacen;
    }

    public void setAlmacen(item[] almacen) {
        this.almacen = almacen;
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

    public int getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(int soldItems) {
        this.soldItems = soldItems;
    }

    public int getBougthItems() {
        return bougthItems;
    }

    public void setBougthItems(int bougthItems) {
        this.bougthItems = bougthItems;
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
