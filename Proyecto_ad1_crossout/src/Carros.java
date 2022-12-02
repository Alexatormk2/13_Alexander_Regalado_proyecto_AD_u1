import java.util.ArrayList;
import java.util.List;

public class Carros {


    private List<vehiculos> carro = new ArrayList<vehiculos>();

    public Carros() {
    }

    public void add(vehiculos carr) {
        carro.add(carr);
    }

    public List<vehiculos> getListaCarro() {
        return carro;
    }

}
