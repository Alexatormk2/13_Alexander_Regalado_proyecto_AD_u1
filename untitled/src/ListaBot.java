import java.util.ArrayList;
import java.util.List;

public class ListaBot {


    private List<BOT> lista = new ArrayList<BOT>();

    public ListaBot() {
    }

    public void add(BOT bot) {
        lista.add(bot);
    }

    public List<BOT> getListaBOTs() {
        return lista;
    }


}
