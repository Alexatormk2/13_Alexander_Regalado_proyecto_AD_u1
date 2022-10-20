import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws IOException {
        OutputStream os;
        InputStream is;
        try {
            Socket peticion = new Socket("localhost", 4000);

            os = peticion.getOutputStream();
            is = peticion.getInputStream();
            os.write(69);

            int valor = is.read();
            System.out.println(valor + "  Esto llego desde el server");
            is.close();
            os.close();
            peticion.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
