import java.util.ArrayList;
import java.util.List;

public class ListaCarro {


    private List<vehiculos> lista = new ArrayList<vehiculos>();

    public ListaCarro() {
    }

    public void add(vehiculos carr) {
        lista.add(carr);
    }

    public List<vehiculos> getListaCarro() {
        return lista;
    }

}
