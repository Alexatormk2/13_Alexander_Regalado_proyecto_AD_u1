package ejer2_hilos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {


    static OutputStream os;
    static InputStream is;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5000);
        Socket cliente;
        cliente = server.accept();
        PrintWriter fsalida = new PrintWriter(cliente.getOutputStream());
        PrintWriter fentrada = new PrintWriter(cliente.getInputStream().toString());



        System.out.println("Conexion completada");
while ((cadena = fentrada.re)){

            is = cliente.getInputStream();
    if (Objects.equals(is.toString(), "Fin")){
        System.out.println("Ejecucion acabada");
    }
    else{
        cliente = server.accept();
        hiloServidor hilo = new hiloServidor(cliente);
        hilo.start();
    }
    System.exit(0);

}


    }


}

class hiloServidor extends  Thread {
    BufferedReader fentrada;
    Writer fsalida;

    Socket sock  = null;
    public hiloServidor(Socket sock) throws IOException {
        this.sock = sock;
 fsalida  = new PrintWriter(sock.getOutputStream(),true);
 fentrada = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    }
    public void mayusculas(String valor) throws IOException {

     fsalida.write(valor.toUpperCase());
        System.out.println("Valor recibido convertido a :" + valor);

    }

    @Override
    public void run() {
        try {
            mayusculas(String.valueOf(fentrada.read()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
