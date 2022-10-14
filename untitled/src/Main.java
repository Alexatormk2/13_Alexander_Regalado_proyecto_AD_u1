import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static survivor[] listaPlayers = new survivor[20];
    static vehiculos[] listaCarros = new vehiculos[25];
    static survivor playerActual;
    static historial_mision log = new historial_mision();
    static partida[] matchs = new partida[30];

    public static void inicializador() {


    }

    public static void main(String[] args) throws IOException {
        inicializador();


        int opcion, opcion2, opcionMenu, playerChoose;
        do {
            opcion = 0;
            opcionMenu = 0;
            opcion2 = 0;

            System.out.println("Hola antes de empezar mira si tu cuenta es una de estas o sino  crea una");
            System.out.println("1.Revisar Cuentas y seleccionar");
            System.out.println("2.Nuevo usuario");
            System.out.println("3.Salir :c");

            switch (opcionMenu) {
                case 1:
                    for (int o = 0; o < listaPlayers.length; o++) {
                        System.out.println(o + ". Nombre de jugador: " + listaPlayers[o].getNomnbre());
                    }
                    System.out.println("Selecciona tu nick o po en numero 400 para salir");
                    playerChoose = Integer.parseInt(br.readLine());
                    if (playerChoose == 400) {
                        System.exit(0);

                    } else {

                        playerActual = listaPlayers[playerChoose];
                    }

                    break;

                case 2:
                    newUser();
                    break;
                case 3:
                    System.exit(0);
                    break;

            }
            opcionMenu = Integer.parseInt(br.readLine());


            opcion = 0;
            System.out.println("Menu dle juego");
            System.out.println("1.Batalla");

            opcion = Integer.parseInt(br.readLine());

            if (opcion == 1) {
                System.out.println("1. a por chatarra");
                System.out.println("2. a por cobre");
                System.out.println("3. volver a atras");

                opcion2 = Integer.parseInt(br.readLine());
                switch (opcion2) {

                    case 1:


                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("Volviendo a menu principal");


                        break;

                }

            }

        }
        while (opcion != 6 || opcionMenu != 3);
        System.out.println("Cerrando sesion");
        log.exportatHistorial(matchs);

    }

    public static void newUser() throws IOException {
        System.out.println("Nombre de la cuanta");
        String name = br.readLine();


        for (int p = 0; p < listaCarros.length; p++) {

            System.out.println(p + ". Nombre " + listaCarros[p].getNombre());
            System.out.println("Descripcion: " + listaCarros[p].getDescripcion());
        }

        System.out.println("Seleciona un carro");

        int k = Integer.parseInt(br.readLine());
        vehiculos carro = listaCarros[k];

        playerActual = new survivor(name, carro);


    }


}