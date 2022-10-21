import java.util.ArrayList;
import java.util.List;

public class ListaSurvivor {


    private List<survivor> lista = new ArrayList<survivor>();

    public ListaSurvivor() {
    }

    public void add(survivor surv) {
        lista.add(surv);
    }

    public List<survivor> getListaPersonas() {
        return lista;
    }


}
