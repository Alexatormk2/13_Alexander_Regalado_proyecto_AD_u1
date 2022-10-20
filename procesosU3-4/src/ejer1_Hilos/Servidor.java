package ejer1_Hilos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    static OutputStream os;
    static InputStream is;

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(4999);
        Socket cliente;
        int valor2 = 1;

        System.out.println("Conexion completada");

        int valor = 0;


        while (valor != 5) {
            cliente = servidor.accept();
            hiloservidor hilo = new hiloservidor(cliente, valor2);
            hilo.start();
            valor++;
            valor2++;
        }

        System.out.println("Ejecucion acabada");

        servidor.close();


    }
}

class hiloservidor extends Thread {
    Socket cliente;
    Writer salida;
    BufferedReader entrada;
    int valor2;

    public hiloservidor(Socket cliente, int valor2) throws IOException {
        this.valor2 = valor2;
        this.cliente = cliente;
        salida = new PrintWriter(cliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

    }

    @Override
    public void run() {
        try {
            mayusculas();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mayusculas() throws IOException {


        if (valor2 < 6) {
            System.out.println("Hola cliente numero:" + valor2);

        } else {
            System.out.println("Ejecucion acabada");
        }

    }
}
