
import java.util.ArrayList;
import java.util.List;
public class ListaPersonas {
    private List<ejer7_Persona> lista = new ArrayList<ejer7_Persona>();
    public ListaPersonas(){}
    public void add(ejer7_Persona per) {
        lista.add(per);
    }
    public List<ejer7_Persona> getListaPersonas() {
        return lista;
    }
}