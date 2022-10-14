import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void inicializador(){





    }
    public static void main(String[] args) throws IOException {
        inicializador();
        historial_mision log = new historial_mision();
        partida[] matchs = new partida[30];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        do {


            opcion = Integer.parseInt(br.readLine());

        }
        while (opcion != 6);
        System.out.println("Cerrando sesion");
       log.exportatHistorial(matchs);

    }




}