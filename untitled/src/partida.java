import java.util.Date;

public class partida {

   Date fecha;
    survivor player;
    BOT bot;
    int scrapGanado;
    int copperGanado;
    String resultadoDuelo;


    public partida(survivor player, BOT bot, int scrapGanado, int copperGanado, String resultadoDuelo) {
        this.player = player;
        this.bot = bot;
        this.scrapGanado = scrapGanado;
        this.copperGanado = copperGanado;
        this.resultadoDuelo = resultadoDuelo;
    }

    public partida() {

    }

    public survivor getPlayer() {
        return player;
    }

    public void setPlayer(survivor player) {
        this.player = player;
    }

    public BOT getBot() {
        return bot;
    }

    public void setBot(BOT bot) {
        this.bot = bot;
    }

    public int getScrapGanado() {
        return scrapGanado;
    }

    public void setScrapGanado(int scrapGanado) {
        this.scrapGanado = scrapGanado;
    }

    public int getCopperGanado() {
        return copperGanado;
    }

    public void setCopperGanado(int copperGanado) {
        this.copperGanado = copperGanado;
    }

    public String getResultadoDuelo() {
        return resultadoDuelo;
    }

    public void setResultadoDuelo(String resultadoDuelo) {
        this.resultadoDuelo = resultadoDuelo;
    }
}


