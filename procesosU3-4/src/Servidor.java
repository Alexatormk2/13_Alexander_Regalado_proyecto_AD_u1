import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static OutputStream os;
    static InputStream is;

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(4000);
        Socket cliente;
        cliente = servidor.accept();

        System.out.println("Conexion completada");
        is = cliente.getInputStream();
        os = cliente.getOutputStream();
        int valor = is.read();
        System.out.println(valor);
        int total = valor * 2;
        os.write(total);
        is.close();
        os.close();
    }
}
