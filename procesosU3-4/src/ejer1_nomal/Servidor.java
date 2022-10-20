package ejer1_nomal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    static OutputStream os;
    static InputStream is;

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(4999);
        Socket cliente;
        cliente = servidor.accept();

        System.out.println("Conexion completada");
        is = cliente.getInputStream();
        os = cliente.getOutputStream();
        int valor = 0;
        do {
            valor = is.read();
            System.out.println("Cliente numero :" + valor);
        }
        while (valor !=5);

        System.out.println("Ejecion acabada");

            is.close();
            os.close();





    }
}
