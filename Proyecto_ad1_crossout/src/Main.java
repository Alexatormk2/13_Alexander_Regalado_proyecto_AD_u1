import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.sound.midi.Soundbank;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //listas--------------
    static String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
    static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionCross"; //URI colección
    static String usu = "admin"; //Usuario
    static String usuPwd = "admin"; //Clave
    static Collection col;
    static survivor[] listaPlayers = new survivor[30];
    static BOT[] listaBots = new BOT[30];
    static vehiculos[] listaCarros = new vehiculos[25];


    static survivor playerActual;


    //Carga los datos en la ejecucion desde los .dat los cuales la primera ves se cargan dese otro fichero

    public static void main(String[] args) throws IOException {
        Metodos_main.inicializador();


        int opcion =0, opcion2 = 0, opcionMenu, playerChoose, mercado;
        opcionMenu = 0;
        //menu el cual se encarga de dar acceso al programa esta fuera del do while por si se falla para que se cierre
        System.out.println("Hola antes de empezar mira si tu cuenta es una de estas o sino  crea una");
        System.out.println("1.Revisar Cuentas y seleccionar");
        System.out.println("2.Nuevo usuario");
        System.out.println("3.Salir :c");
        //opcion 1 la cual es para crear y loguearse

        try {
            opcionMenu = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Error de valor revise, de un numero");
        } catch (IOException e) {
            System.out.println("Error de valor revise");
            ;
        }

        switch (opcionMenu) {
            //printea la lista de usuarios disponible y luego el usuario tiene que dar un numero para seleccionar el perfil
            case 1:
                for (int o = 0; o < listaPlayers.length; o++) {
                    if (listaPlayers[o] == null) {

                        break;

                    } else {
                        System.out.println(o + ". Nombre de jugador: " + listaPlayers[o].getNomnbre());
                    }
                }
                System.out.println("Selecciona tu nick o pon el numero 400 para salir");


                try {
                    playerChoose = Integer.parseInt(br.readLine());
String revisar = String.valueOf(playerChoose);
                    if ( playerChoose==400 ||listaPlayers[playerChoose]==null|| revisar.equals("")) {
                        System.out.println("Usuario no existente");
                        System.exit(0);

                    } else {

                        playerActual = listaPlayers[playerChoose];
                        if(playerActual ==null){
                            System.out.println("Usuario no existente");
                            System.exit(0);
                        }
                    }
                } catch (NumberFormatException e) {

                    System.out.println("Error de valor inesperado cerrando...");
                    System.exit(0);
                } catch (IOException e) {
                    System.out.println("Error de valor revise");
                    ;
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Error no existe ese usuario");
                }

                break;
//llama al metodo newUser que sirve para crear un usuario nuevo
            case 2:
                Metodos_main.newUser();
                break;

            case 3:
                System.exit(0);
                break;

        }

        try {
            do {

                if (playerActual ==null){
                    System.out.println("Se entro sin usuario cerrando");
                    System.exit(0);
                }else {
                System.out.println("Menu del juego");
                System.out.println("1.Batalla");
                System.out.println("2.Cambiar Carro");
                System.out.println("3.Borrar usuario :D");
                System.out.println("4.Administracion exist");
                System.out.println("5.Salir");

                opcion = Integer.parseInt(br.readLine());


    switch (opcion) {
        case 1:
            //Menu para seleccionar la batalla que se luchara
            System.out.println("1. A por chatarra");
            System.out.println("2. A por cobre");
            System.out.println("3. Volver a atras");

            try {
                opcion2 = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error de un numero por favor");
            } catch (IOException e) {
                System.out.println("Error inesperado");
            }

            switch (opcion2) {
                //Segun la opcion dada llamada a uno de los 2 metodos
                case 1:
                    Metodos_main.chatarra();

                    break;
                case 2:
                    Metodos_main.cobre();

                    break;

                case 3:
                    System.out.println("Volviendo a menu principal");


                    break;

            }
            break;
        case 2:
            Metodos_main.cambiarCarro();
            break;
        case 3:
            Metodos_main.BorrarUser();
            break;
        case 4:

            if ((Main.playerActual.getNomnbre().equals("IVY_XO") || Main.playerActual.getNomnbre().equals("Foxy"))) {
                Metodos_main.consultasExist();
            } else {

                System.out.println("Error no se poseen datos de admin");
            }
            break;

        case 5:

            break;
    }
}

            } while (opcion != 5);
            Metodos_main.guardarDatos();
            Metodos_main.guardarDatosBot();
            exportarPlayer();
            exportarBot();
            exportarCarro();
            conectar();
            exportarExisSurvi();
            exportarExisBot();
            exportarExisCarro();

            System.out.println("export completa por favor recarga exist");
            System.out.println("Cerrando sesion");
        } catch (NumberFormatException e) {
            System.out.println("Error de valor revise de un  numero");

        } catch (IOException e) {
            System.out.println("error");
        }
    }

    //Expotar a xml tanto jugadores como bots:
    //exportar jugadores a xml
    public static void exportarPlayer() throws IOException {


        File fichero = new File("survivor.dat");
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
            System.out.println("Exportando a exist");


        } catch (Exception e) {
            e.printStackTrace();
        }
        // fin


    }

    //exportar xml bot
    public static void exportarBot() throws IOException {


        File ficherobot = new File("bot.dat");
        FileInputStream filebot = new FileInputStream(ficherobot);//crea el flujo de entrada
//conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataIS = new ObjectInputStream(filebot);
        System.out.println("Comienza el proceso de creación del fichero a XML ...");
//Creamos un objeto Lista de Personas
        ListaBot listaBo = new ListaBot();
        try {
            while (true) { //lectura del fichero
                BOT bot = (BOT) dataIS.readObject(); //leer una Persona
                listaBo.add(bot); //añaadir persona a la lista
            }
        } catch (EOFException eo) {
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dataIS.close(); //cerrar stream de entrada
        try {
            XStream xstream = new XStream();
//cambiar de nombre a las etiquetas XML
            xstream.alias("Lista_Registro_bot", ListaBot.class);
            xstream.alias("Datos_BOT", BOT.class);
//quitar etiqueta lista (atributo de la clase ListaPersonas)
            xstream.addImplicitCollection(ListaBot.class, "lista");
//Insrtar los objetos en el XML
            xstream.toXML(listaBo, new FileOutputStream("bot.xml"));
            System.out.println("Creado fichero XML....");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // fin
    }

    public static void exportarExisSurvi() {


        try {

            XMLResource resource;
            resource = (XMLResource) col.createResource("survivor.xml", "XMLResource");

            File file = new File("survivor.xml");
            resource.setContent(file);
            col.storeResource(resource);
            for (String s : col.listResources()) {
                System.out.println(s);
            }
            col.close();

        } catch (Exception e) {
            System.out.println("Error al consultar colección: " + e.getLocalizedMessage());

        }
    }

    public static void exportarExisCarro() {


        try {

            XMLResource resource;
            resource = (XMLResource) col.createResource("vehiculos.xml", "XMLResource");

            File file = new File("vehiculos.xml");
            resource.setContent(file);
            col.storeResource(resource);
            for (String s : col.listResources()) {
                System.out.println(s);
            }
            col.close();

        } catch (Exception e) {
            System.out.println("Error al consultar colección: " + e.getLocalizedMessage());

        }
    }

    public static void exportarExisBot() {


        try {

            XMLResource resource;
            resource = (XMLResource) col.createResource("bot.xml", "XMLResource");

            File file = new File("bot.xml");
            resource.setContent(file);
            col.storeResource(resource);
            for (String s : col.listResources()) {
                System.out.println(s);
            }
            col.close();

        } catch (Exception e) {
            System.out.println("Error al consultar colección: " + e.getLocalizedMessage());

        }
    }


    public static void exportarCarro() throws IOException {


        File ficherobot = new File("vehiculos.dat");
        FileInputStream fileCarro = new FileInputStream(ficherobot);//crea el flujo de entrada
//conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataIS = new ObjectInputStream(fileCarro);
        System.out.println("Comienza el proceso de creación del fichero a XML ...");
//Creamos un objeto Lista de Personas
        Carros listaCa = new Carros();
        try {
            while (true) { //lectura del fichero
                vehiculos vehiculos = (vehiculos) dataIS.readObject(); //leer una Persona
                listaCa.add(vehiculos); //añaadir persona a la lista
            }
        } catch (EOFException eo) {
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dataIS.close(); //cerrar stream de entrada
        try {
            XStream xstream = new XStream();
//cambiar de nombre a las etiquetas XML


//quitar etiqueta lista (atributo de la clase ListaPersonas)

            xstream.useAttributeFor(vehiculos.Clascificacion.class, "tipo");
            xstream.aliasField("Tipo", vehiculos.Clascificacion.class, "tipo");
//Insrtar los objetos en el XML
            xstream.toXML(listaCa, new FileOutputStream("Vehiculos.xml"));
            System.out.println("Creado fichero XML....");
        } catch (Exception e) {
            e.printStackTrace();
        }


        // fin
    }

    public static void conectar() {

        try {
            Class<Database> cl = (Class<Database>) Class.forName(driver); //Cargar del driver
            Database database = cl.getDeclaredConstructor().newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
        } catch (XMLDBException e) {
            System.out.println("Error al inicializar la BD eXist.");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error en el driver.");
            //e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("Error al instanciar la BD.");
            //e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Error al instanciar la BD.");
            //e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}


