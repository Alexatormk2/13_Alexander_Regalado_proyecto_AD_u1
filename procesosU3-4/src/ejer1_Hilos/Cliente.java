package ejer1_Hilos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws IOException {
        OutputStream os;
        InputStream is;
        try {
            Socket peticion = new Socket("localhost", 4999);

            os = peticion.getOutputStream();
            is = peticion.getInputStream();



            is.close();
            os.close();
            peticion.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}