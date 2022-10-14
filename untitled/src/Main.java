import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //listas--------------
    static item[] disponiblesPiezas = new item[20];
    static survivor[] listaPlayers = new survivor[20];
    static vehiculos[] listaCarros = new vehiculos[25];
    static partida[] matchs = new partida[30];


    static survivor playerActual;
    static historial_mision log = new historial_mision();


    public static void inicializador() {


    }

    public static void main(String[] args) throws IOException {
        inicializador();


        int opcion, opcion2, opcionMenu, playerChoose, mercado;
        opcionMenu = 0;
        System.out.println("Hola antes de empezar mira si tu cuenta es una de estas o sino  crea una");
        System.out.println("1.Revisar Cuentas y seleccionar");
        System.out.println("2.Nuevo usuario");
        System.out.println("3.Salir :c");
        //opcion 1 la cual es para crear y loguearse
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

        do {
            opcion = 0;

            opcion2 = 0;


            opcionMenu = Integer.parseInt(br.readLine());


            System.out.println("Menu dle juego");
            System.out.println("1.Batalla");
            System.out.println("2.Mercado");

            opcion = Integer.parseInt(br.readLine());


            switch (opcion) {
                case 1:
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
                    break;
                case 2:
                    System.out.println("Queres comprar o vender");
                    System.out.println("1.comprar");
                    System.out.println("2.vender");
                    mercado = Integer.parseInt(br.readLine());
                    switch (mercado) {

                        case 1:
                            comprar();
                            break;
                        case 2:
                            vender();
                            break;
                        default:
                            System.out.println("Saliendo del menu de mercado");
                            break;

                    }
                    break;

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
        System.out.println("USUARIO:" + playerActual.getNomnbre());


    }

    //funcion que le da item a jugador
    public static void comprar() throws IOException {
        for (int q = 0; q < disponiblesPiezas.length; q++) {
            System.out.println(q + " . Nombre  " + disponiblesPiezas[q].getNombre() + "  " + "Precio:" + disponiblesPiezas[q].getPrecio());
        }
        int selector = Integer.parseInt(br.readLine());
        double plata = playerActual.getGold() - disponiblesPiezas[selector].getPrecio();
        if (plata >= 0) {
            if (playerActual.almacen[selector] == disponiblesPiezas[selector]) {

                System.out.println("No se puede tener duplicados");
            } else {
                playerActual.almacen[selector] = disponiblesPiezas[selector];
                System.out.println("Compra realizada");

            }


        }


    }

    //funcio que resta item de jugador
    public static void vender() throws IOException {
        item[] paraMover = new item[20];
        if (playerActual.almacen.length == 0) {

            System.out.println("No posees ningun item lo siento vuelve cuando tengas algo para vender");
        } else {
            for (int d = 0; d < playerActual.almacen.length; d++) {
                System.out.println(d + " . Nombre  " + playerActual.almacen[d].getNombre() + "  " + "Precio:" + playerActual.almacen[d].getPrecio());
            }
            int selector = Integer.parseInt(br.readLine());
            double plata = playerActual.getGold() + playerActual.almacen[selector].getPrecio();
            //copar arrya pero sin el que a sido borrado
            for (int a = 0; a < playerActual.almacen.length; a++) {
                if (a == selector) {
                    continue;
                }

                paraMover[a] = playerActual.almacen[a];

            }

            playerActual.setGold(plata);
            for (int j = 0; j < paraMover.length; j++) {
                playerActual.almacen[j] = paraMover[j];

            }


        }
    }

}


