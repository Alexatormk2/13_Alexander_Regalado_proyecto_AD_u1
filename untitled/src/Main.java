import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        do {


            opcion = Integer.parseInt(br.readLine());

        }
        while (opcion != 6);


    }
}