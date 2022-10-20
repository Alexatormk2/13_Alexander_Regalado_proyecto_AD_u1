import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.Objects;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //listas--------------
    static item[] disponiblesPiezas = new item[20];
    static survivor[] listaPlayers = new survivor[30];
    static BOT[] listaBots = new BOT[30];
    static vehiculos[] listaCarros = new vehiculos[25];


    static survivor playerActual;


    //Carga los datos en la ejecucion desde los .dat los cuales la primera ves se cargan dese otro fichero

    public static void main(String[] args) throws IOException {
        Metodos_main.inicializador();


        int opcion, opcion2, opcionMenu, playerChoose, mercado;
        opcionMenu = 0;
        //menu el cual se encarga de dar acceso al programa esta fuera del do while por si se falla para que se cierre
        System.out.println("Hola antes de empezar mira si tu cuenta es una de estas o sino  crea una");
        System.out.println("1.Revisar Cuentas y seleccionar");
        System.out.println("2.Nuevo usuario");
        System.out.println("3.Salir :c");
        //opcion 1 la cual es para crear y loguearse
        opcionMenu = Integer.parseInt(br.readLine());
        switch (opcionMenu) {
            case 1:
                for (int o = 0; o < listaPlayers.length; o++) {
                    System.out.println(o + ". Nombre de jugador: " + listaPlayers[o].getNomnbre());
                }
                System.out.println("Selecciona tu nick o pon el numero 400 para salir");


                playerChoose = Integer.parseInt(br.readLine());

                if (playerChoose == 400) {

                    System.exit(0);

                } else {

                    playerActual = listaPlayers[playerChoose];
                }

                break;

            case 2:
                Metodos_main.newUser();
                break;
            case 3:
                System.exit(0);
                break;

        }

        do {


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
                            Metodos_main.comprar();
                            break;
                        case 2:
                            Metodos_main.vender();
                            break;
                        default:
                            System.out.println("Saliendo del menu de mercado");
                            break;

                    }
                    break;

            }

        }
        while (opcion != 6);

        exportarPlayer();
        System.out.println("Cerrando sesion");


    }

    //Expotar a xml tanto jugadores como bots:
    //exportar jugadores a xml
    public static void exportarPlayer() throws IOException {


        File fichero = new File(".//src//survivor.dat");
        FileInputStream filein = new FileInputStream(fichero);//crea el flujo de entrada
//conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataIS = new ObjectInputStream(filein);
        System.out.println("Comienza el proceso de creación del fichero a XML ...");
//Creamos un objeto Lista de Personas
        ListaSurvivor listasurv = new ListaSurvivor();
        try {
            while (true) { //lectura del fichero
                survivor survivors = (survivor) dataIS.readObject(); //leer una Persona
                listasurv.add(survivors); //añaadir persona a la lista
            }
        } catch (EOFException eo) {
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dataIS.close(); //cerrar stream de entrada
        try {
            XStream xstream = new XStream();
//cambiar de nombre a las etiquetas XML
            xstream.alias("Lista_Registro_survivors", ListaSurvivor.class);
            xstream.alias("Datos_Survivor", survivor.class);
//quitar etiqueta lista (atributo de la clase ListaPersonas)
            xstream.addImplicitCollection(ListaSurvivor.class, "lista");
//Insrtar los objetos en el XML
            xstream.toXML(listasurv, new FileOutputStream("survivor.xml"));
            System.out.println("Creado fichero XML....");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // fin


    }

    //exportar xml bot
    public void exportarBot(){




    }


}


