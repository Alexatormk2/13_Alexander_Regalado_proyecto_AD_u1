package ejer2_hilos;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream os;
        DataInputStream is;
        try {
            Socket peticion = new Socket("localhost", 5000);

 os = peticion.getOutputStream();

            System.out.println("Escribe algo para mandar al servidor");
            String valor = br.readLine();
            os.writeUTF(valor);

            is.close();
            os.close();
            peticion.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
