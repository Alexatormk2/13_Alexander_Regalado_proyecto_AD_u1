import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Metodos_main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
    static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionCross"; //URI colección
    static String usu = "admin"; //Usuario
    static String usuPwd = "admin"; //Clave
    static Collection col;

    public static void inicializador() throws IOException {


        File ficheroCarro = new File(".//vehiculos.dat");

//Flujo de entrada
        FileInputStream fileDEntro = new FileInputStream(ficheroCarro);
//Conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataGET = new ObjectInputStream(fileDEntro);
        int aj = 0;
        try {
            while (true) {

                vehiculos carro = (vehiculos) dataGET.readObject();
                String nombre = carro.getNombre();
                String descripcion = carro.getDescripcion();
                int danio = carro.getDanio();
                vehiculos.Clascificacion clascificacion = carro.getClascificacion();
                int durabilidad = carro.getDurabilidad();
                String tipo = carro.clascificacion.getTipo();
                String categoria = carro.clascificacion.getCategoria();
                clascificacion = new vehiculos.Clascificacion(tipo, categoria);
                vehiculos a = new vehiculos(durabilidad, nombre, danio, descripcion, clascificacion);
                Main.listaCarros[aj] = a;
                aj++;

            }
        } catch (EOFException e) {
            System.out.println(" Fin de la carga de carros");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dataGET.close();


        //leer survivors
        File ficheroSurv = new File(".//survivor.dat");

//Flujo de entrada
        FileInputStream fileSur = new FileInputStream(ficheroSurv);
//Conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataSur = new ObjectInputStream(fileSur);
        int aw = 0;
        try {
            while (true) {

                survivor sur = (survivor) dataSur.readObject();
                String nomnbre = sur.getNomnbre();
                vehiculos carro = sur.getCarro();
                survivor a = new survivor(nomnbre, carro);
                Main.listaPlayers[aw] = a;
                aw++;

            }

        } catch (EOFException e) {
            System.out.println(" Fin de la carga de survivor");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dataSur.close();

        //leer bots
        File ficherobot = new File(".//bot.dat");

//Flujo de entrada
        FileInputStream filebot = new FileInputStream(ficherobot);
//Conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataBot = new ObjectInputStream(filebot);
        int at = 0;
        try {
            while (true) {

                BOT bot = (BOT) dataBot.readObject();
                String nombre2 = bot.getNombre();
                vehiculos carro = bot.getCarro();
                String frased = bot.fraseDerrota;
                String fraseV = bot.fraseVictoria;
                String descr = bot.descripcionBot;
                BOT a = new BOT(nombre2, carro, descr, fraseV, frased);
                Main.listaBots[at] = a;
                at++;

            }

        } catch (EOFException e) {
            System.out.println(" Fin de la carga de bot");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dataBot.close();


    }


    //Batalla ambas sus iguales solo cambiar el material
    public static void chatarra() {
        // carga de las variables para su uso posterior
        //survivor
        String player = Main.playerActual.getNomnbre();
        String carro = Main.playerActual.carro.getNombre();
        int danio = Main.playerActual.carro.getDanio();
        int health = Main.playerActual.carro.getDurabilidad();
        int danioTotal = 0;
        int vida = health;
        int contador = 0;
        //random que seleciona el bot de forma aleatoria
        for (contador = 0; contador < Main.listaBots.length; contador++) {
            if (Main.listaBots[contador] == null) {

                break;
            }

        }
        System.out.println("Este bot no posee un carro re selecionando");
        String bot;
        String Carrobot;
        int daniobot;
        int Healthbot;
        String victoria;
        String derrota;
        int vidaBot = 0;
        int danioTotalBot = 0;
        int numeroAleatorio = (int) (Math.random() * contador + 0);

        //bot/enemigo

        if (Main.listaBots[numeroAleatorio].carro == null) {
            numeroAleatorio = (int) (Math.random() * contador + 0);
            System.out.println("Este bot no posee un carro re selecionando");
            bot = Main.listaBots[numeroAleatorio].getNombre();
            Carrobot = Main.listaBots[numeroAleatorio].carro.getNombre();
            daniobot = Main.listaBots[numeroAleatorio].carro.getDanio();
            Healthbot = Main.listaBots[numeroAleatorio].carro.getDurabilidad();
            victoria = Main.listaBots[numeroAleatorio].getFraseVictoria();
            derrota = Main.listaBots[numeroAleatorio].getFraseDerrota();
            vidaBot = Healthbot;


        } else {
            bot = Main.listaBots[numeroAleatorio].getNombre();
            Carrobot = Main.listaBots[numeroAleatorio].carro.getNombre();
            daniobot = Main.listaBots[numeroAleatorio].carro.getDanio();
            Healthbot = Main.listaBots[numeroAleatorio].carro.getDurabilidad();
            victoria = Main.listaBots[numeroAleatorio].getFraseVictoria();
            derrota = Main.listaBots[numeroAleatorio].getFraseDerrota();
            vidaBot = Healthbot;
        }
        //for que lleva la batalla
        for (int round = 0; round < 10; round++) {
            //if que revisa la vida del bot y jugador
            if (vida <= 0 || vidaBot <= 0) {
                break;
            } else {
                //random para los turnos de la batalla
                int numeroAleatorio2 = (int) (Math.random() * 2 + 1);
                switch (numeroAleatorio2) {
                    case 1:
                        //turno jugador
                        System.out.println("+++++++++++++++++");
                        System.out.println("Survivor :" + player + " A dañado el vehiculo enemigo ");
                        danioTotal = Healthbot - danio;
                        vidaBot = vidaBot - danio;
                        System.out.println("+++++++++++++++++");
                        System.out.println(player + " A causado :" + danio + " de danio  al bot");
                        System.out.println("Durabildiad restante del jugador  " + (health - danioTotalBot));
                        System.out.println("+++++++++++++++++");
                        System.out.println("Durabilidad del bot restante " + vidaBot);
                        System.out.println("+++++++++++++++++");
                        //Condcion que revisa la vida restante del bot y si es zero se ejecuta para
                        if (vidaBot <= 0) {
                            System.out.println("----------Victoria--------");
                            System.out.println("Enemigo eliminado ganador survivor: " + player);
                            System.out.println("----" + bot + ": " + derrota);
                            //Asignar recursos al jugador
                            Main.playerActual.setScrap(Main.playerActual.getScrap() + 50);
                            Main.playerActual.setGold(Main.playerActual.getGold() + 30);
                            Main.listaBots[numeroAleatorio].setDeaths(Main.listaBots[numeroAleatorio].getDeaths() + 1);
                            Main.playerActual.setKills(Main.playerActual.getKills() + 1);
                            //Poner la condicion del for al maximo para saltar el loop
                            round = 11;
                        } else {
                            break;
                        }
                        break;
                    case 2:

                        //turno bot
                        System.out.println("-----------");
                        System.out.println(" Bot:" + bot + "A dañado el vehiculo del survivor rival");
                        danioTotalBot = health - daniobot;
                        vida = vida - daniobot;
                        System.out.println(bot + " A causado :" + daniobot + " de danio  al survivor");
                        System.out.println("-----------");
                        System.out.println("Durabildiad total perdida  del bot  " + (Healthbot - danioTotal));
                        System.out.println("-----------");
                        System.out.println("Durabilidad del jugador restante " + vida);
                        System.out.println("-----------");
                        //Condcion que revisa la vida restante del juguador
                        if (vida <= 0) {
                            System.out.println("---------Derrota------");
                            System.out.println("Has sido eliminado" + player);
                            System.out.println("----" + bot + ": " + victoria);
                            //Asignar recursos al jugador
                            Main.playerActual.setScrap(Main.playerActual.getScrap() + 25);
                            Main.playerActual.setGold(Main.playerActual.getGold() + 15);
                            Main.playerActual.setDeaths(Main.playerActual.getDeaths() + 1);
                            //asignar derrota
                            Main.playerActual.setDerrotas(Main.playerActual.getDerrotas() + 1);
                            //asignar kill bot
                            Main.listaBots[numeroAleatorio].setKill(Main.listaBots[numeroAleatorio].getKill() + 1);
                            //asigna victoria bot
                            Main.listaBots[numeroAleatorio].setVictorias(Main.listaBots[numeroAleatorio].getVictorias() + 1);
                            //Poner la condicion del for al maximo para saltar el loop
                            round = 11;
                        } else {
                            break;
                        }
                        break;
                }
            }


        }

    }

    public static void cobre() {


        String player = Main.playerActual.getNomnbre();
        String carro = Main.playerActual.carro.getNombre();
        int danio = Main.playerActual.carro.getDanio();
        int health = Main.playerActual.carro.getDurabilidad();
        int danioTotal = 0;
        int numeroAleatorio = (int) (Math.random() * 4 + 0);
        int vida = health;

        String bot = Main.listaBots[numeroAleatorio].getNombre();
        String Carrobot = Main.listaBots[numeroAleatorio].carro.getNombre();
        int daniobot = Main.listaBots[numeroAleatorio].carro.getDanio();
        int Healthbot = Main.listaBots[numeroAleatorio].carro.getDurabilidad();
        String victoria = Main.listaBots[numeroAleatorio].getFraseVictoria();
        String derrota = Main.listaBots[numeroAleatorio].getFraseDerrota();
        int vidaBot = Healthbot;
        int danioTotalBot = 0;

        for (int round = 0; round < 10; round++) {
            if (vida <= 0 || vidaBot <= 0) {
                break;
            } else {
                int numeroAleatorio2 = (int) (Math.random() * 2 + 1);
                switch (numeroAleatorio2) {
                    case 1:
                        //turno jugador
                        System.out.println("+++++++++++++++++");
                        System.out.println("Survivor :" + player + " A dañado el vehiculo enemigo ");
                        danioTotal = Healthbot - danio;
                        vidaBot = vidaBot - danio;
                        System.out.println("+++++++++++++++++");
                        System.out.println(player + " A causado :" + danio + " de danio  al bot");
                        System.out.println("Durabilidad restante del jugador  " + (health - danioTotalBot));
                        System.out.println("+++++++++++++++++");
                        System.out.println("Durabilidad restante  del bot  " + vidaBot);
                        System.out.println("+++++++++++++++++");
                        //Condcion que revisa la vida restante del bot
                        if (vidaBot <= 0) {
                            System.out.println("----------Victoria--------");
                            System.out.println("Enemigo eliminado ganador survivor: " + player);
                            System.out.println("----" + bot + ": " + derrota);
                            //Asignar recursos al jugador
                            Main.playerActual.setCopper(Main.playerActual.getCopper() + 50);
                            Main.playerActual.setGold(Main.playerActual.getGold() + 30);
                            //asignar muerte bot
                            Main.listaBots[numeroAleatorio].setDeaths(Main.listaBots[numeroAleatorio].getDeaths() + 1);
                            //asignar kill
                            Main.playerActual.setKills(Main.playerActual.getKills() + 1);
                            //asignar victoria
                            Main.playerActual.setVictorias(Main.playerActual.getVictorias() + 1);
                            //asignar kill bot
                            Main.listaBots[numeroAleatorio].setDeaths(Main.listaBots[numeroAleatorio].getDeaths() + 1);
                            //asigna victoria bot
                            Main.listaBots[numeroAleatorio].setDerrotas(Main.listaBots[numeroAleatorio].getDerrotas() + 1);
                            //Poner la condicion del for al maximo para saltar el loop
                            //Poner la condicion del for al maximo para saltar el loop
                            round = 11;
                        } else {
                            break;
                        }
                        break;
                    case 2:

                        //turno bot
                        System.out.println("-----------");
                        System.out.println(" Bot:" + bot + " A dañado el vehiculo del survivor rival");
                        danioTotalBot = health - daniobot;
                        vida = vida - daniobot;
                        System.out.println(bot + " A causado :" + daniobot + " de danio  al survivor");
                        System.out.println("-----------");
                        System.out.println("Durabildiad total perdida  del bot  " + (Healthbot - danioTotal));
                        System.out.println("-----------");
                        System.out.println("Durabilidad  restante del jugador " + vida);
                        System.out.println("-----------");
                        //Condcion que revisa la vida restante del juguador
                        if (vida <= 0) {
                            System.out.println("---------Derrota------");
                            System.out.println("Has sido eliminado" + player);
                            System.out.println("----" + bot + ": " + victoria);
                            //Asignar recursos al jugador
                            Main.playerActual.setCopper(Main.playerActual.getCopper() + 25);
                            Main.playerActual.setGold(Main.playerActual.getGold() + 15);
                            //asignar muerte
                            Main.playerActual.setDeaths(Main.playerActual.getDeaths() + 1);
                            //asignar derrota
                            Main.playerActual.setDerrotas(Main.playerActual.getDerrotas() + 1);
                            //asignar kill bot
                            Main.listaBots[numeroAleatorio].setKill(Main.listaBots[numeroAleatorio].getKill() + 1);
                            //asigna victoria bot
                            Main.listaBots[numeroAleatorio].setVictorias(Main.listaBots[numeroAleatorio].getVictorias() + 1);
                            //Poner la condicion del for al maximo para saltar el loop
                            round = 11;
                        } else {
                            break;
                        }
                        break;
                }
            }


        }


    }

    public static void cambiarCarro() throws IOException {

        //sirve para cambiar el crro actual del jugador
        System.out.println("Hola que carro quiere");
        for (int k = 0; k < Main.listaCarros.length; k++) {
            if (Main.listaCarros[k] == null) {


                break;
            } else if (Main.listaCarros[k] == Main.playerActual.getCarro()) {

                System.out.println(" Este es el actual--" + k + ". Nombre: " + Main.listaCarros[k].getNombre() + " Descripcion :" + Main.listaCarros[k].getDescripcion() + " Durabilida/hp " + Main.listaCarros[k].getDurabilidad() + " Daño: " + Main.listaCarros[k].getDanio());
            } else {
                System.out.println(k + ". Nombre: " + Main.listaCarros[k].getNombre() + " Descripcion :" + Main.listaCarros[k].getDescripcion() + " Durabilida/hp " + Main.listaCarros[k].getDurabilidad() + " Daño: " + Main.listaCarros[k].getDanio());


            }
        }

        int opcion = 0;
        //no puede selecionar el carro actual

        try {
            opcion = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Se esperaba un numero");
        } catch (IOException e) {
            System.out.println("Error inesperado");
        }

        if (Main.listaCarros[opcion] == Main.playerActual.getCarro()) {
            System.out.println("Este es el actual no se puede seleccionar");
        } else {
            System.out.println("Cambiandio el carro espera.....");
            Main.playerActual.setCarro(Main.listaCarros[opcion]);

        }


    }


    //crear nuevo survivor para usar
    public static void newUser() throws IOException {
        try {
            System.out.println("Nombre de la cuenta");
            String name = br.readLine();

            for (int o = 0; o < Main.listaPlayers.length; o++) {
                //en caso de que la posicion sea null la salta
                if (Main.listaPlayers[o] == null) {


                    break;

                } else if (Objects.equals(name, Main.listaPlayers[o].getNomnbre())) {
                    //revisa que el nombre no haiga sido usado o no exista y en caso de que exista se cierra el programa
                    System.out.println("Este usuario ya existe lo siento");
                    System.out.println("Cerrando programna :P");
                    System.exit(0);
                }


            }
            //lista los carros disponibles y el usuario debe selecionar uno
            System.out.println("Selecciona un carro:");
            for (int p = 0; p < Main.listaCarros.length; p++) {
                if (Main.listaCarros[p] == null) {
                    break;
                } else {

                    System.out.println(p + ". Nombre " + Main.listaCarros[p].getNombre());
                    System.out.println("Descripcion: " + Main.listaCarros[p].getDescripcion());
                }
            }

            System.out.println("Seleciona un carro");

            int k = Integer.parseInt(br.readLine());


            vehiculos carro = Main.listaCarros[k];

            Main.playerActual = new survivor(name, carro);
            System.out.println("USUARIO:" + Main.playerActual.getNomnbre());
        } catch (IOException e) {
            System.out.println("Error al meter los datos cerrando :P");
            System.exit(0);
        } catch (NumberFormatException e) {
            System.out.println("Error al meter los datos cerrando:P");
            System.exit(0);
        }


    }

    public static void BorrarUser() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (Main.playerActual.getNomnbre().equals("IVY_XO") || Main.playerActual.getNomnbre().equals("Foxy")) {
            for (int a = 0; a < Main.listaPlayers.length; a++) {
                if (Main.listaPlayers[a] == null) {
                    break;
                } else {
                    try {
                        //lista todos los usuarios y pone texto en los que son admins

                        if (Main.listaPlayers[a].getNomnbre().equals("IVY_XO") || Main.listaPlayers[a].getNomnbre().equals("Foxy")) {
                            System.out.println("Admin no se puede borrar");

                        } else {
                            System.out.println(a + ". Nombre :" + Main.listaPlayers[a].getNomnbre());
                        }
                    } catch (Exception e) {
                        System.out.println("Error datos vacios");
                    }
                }
            }
            System.out.println("--------------------");
            System.out.println("Cual queres borrar");
            int opcion = Integer.parseInt(br.readLine());
            //en caso de que se de la opcion de los admins estos no seran borrados
            if (Main.listaPlayers[opcion].getNomnbre().equals("IVY_XO") || Main.listaPlayers[opcion].getNomnbre().equals("Foxy")) {
                System.out.println("Admin no se puede borrar");
            } else {

                Main.listaPlayers[opcion] = null;
                System.out.println("Borrado con exito");
            }

        } else {

            System.out.println("No eres admin no podes borrar nada");
        }

    }

    public static void guardarDatos() throws IOException {
        //Coge los datos de las listas los vuelve a meter al dat
        File ficheroSurvivor = new File(".//survivor.dat");
        FileOutputStream escribirSurvi = new FileOutputStream(ficheroSurvivor);
        ObjectOutputStream itemSurvi = new ObjectOutputStream(escribirSurvi);
        survivor surv = Main.playerActual;
        itemSurvi.writeObject(Main.playerActual);
        for (int a = 0; a < Main.listaPlayers.length; a++) {

            if (Main.listaPlayers[a] == null) {
                break;
            } else if (Main.listaPlayers[a] == Main.playerActual) {

                try {
                    Main.listaPlayers[a] = Main.playerActual;
                } catch (Exception e) {
                    System.out.println("Error inesperado");
                }

            }
            Main.playerActual = Main.listaPlayers[a];
            if (Main.playerActual == surv) {
                continue;
            } else {
                itemSurvi.writeObject(Main.playerActual);
            }
        }


    }

    public static void guardarDatosBot() throws IOException {
        //Coge los datos de las listas los vuelve a meter al dat
        File ficheroBot = new File(".//BOT.dat");
        FileOutputStream escribirbot = new FileOutputStream(ficheroBot);
        ObjectOutputStream itemBot = new ObjectOutputStream(escribirbot);


        for (int a = 0; a < Main.listaBots.length; a++) {

            if (Main.listaBots[a] == null) {
                break;
            } else {

                try {
                    itemBot.writeObject(Main.listaBots[a]);

                } catch (Exception e) {
                    System.out.println("Error inesperado");
                }


            }

        }


    }

    public static void consultasExist() {

        int op = 0;
        do {
            try {
                System.out.println("............................................................\n"
                        + ".  1 Gestion carros. \n"
                        + ".  2 Gestion Bots .  \n"
                        + ".  3 SALIR.\n"
                        + "............................................................\n");
                op = Integer.parseInt(br.readLine());
                switch (op) {
                    case 1:
                        System.out.println("............................................................\n"
                                + ".  1 Listar carro. \n"
                                + ".  2 Insertar carro.  \n"
                                + ".  3 Borrar carro.  \n"
                                + ".  4 Modificar carro.  \n"
                                + ".  5 Buscar por nombre carro.  \n"
                                + ".  3 SALIR.\n"
                                + "............................................................\n");
                        int opCar = 0;
                        opCar = Integer.parseInt(br.readLine());
                        switch (opCar) {
                            case 1:
                                listarCarr();

                                break;
                            case 2:
                                System.out.println("Da le nombre del vbehiculo");
                                String nombre = br.readLine();
                                System.out.println("Da el daño del vehiculo");
                                int danio = Integer.parseInt(br.readLine());
                                System.out.println("Durabilidad del carro");
                                int durabilidad = Integer.parseInt(br.readLine());
                                System.out.println("Descripcion del carro");
                                String descripcion = br.readLine();
                                System.out.println("Tipo del carro");
                                String tipo = br.readLine();
                                System.out.println("Categoria del carro");
                                String categoria = br.readLine();

                                insertarCarr(danio, nombre, durabilidad, descripcion, tipo, categoria);
                                break;

                            case 3:
                                listarCarr();
                                System.out.println("Da el nombre del carro a borrar");
                                String nombreBorrar = br.readLine();
                                borrarregistroCarro(nombreBorrar);
                                break;
                            case 4:
                                modificarCarro();
                                break;
                            case 5:
                                buscarCarroNombre();

                                break;
                        }


                        break;


                }
            } catch (NumberFormatException | IOException as) {
                System.out.println("Se esperaba un numero por favor revise");
            }
        } while (op != 3);


    }

    public static Collection conectar() {

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.getDeclaredConstructor().newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            return col;
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
        return null;
    }

    public static void modificarCarro() throws IOException {
        System.out.println("Nombre carro a editar:");
        String nombre = br.readLine();
        if (comprobarCarro(nombre)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            if (conectar() != null) {
                listarCarr();


                System.out.println("Durabilidad:");
                int durabilidad = Integer.parseInt(br.readLine());
                System.out.println("Danio");
                int danio = Integer.parseInt(br.readLine());
                try {
                    System.out.printf("Actualizo el jugador: %s\n", nombre);
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    //Consulta para modificar/actualizar un valor --> update value
                    ResourceSet result = servicio.query("update value /jugadores/jugador[nombre = \"" + nombre + "\"]/durabilidad with " + durabilidad);
                    result = servicio.query("update value /jugadores/jugador[nombre = \"" + nombre + "\"]/danio with " + danio);
                    for (int k = 0; k < Main.listaCarros.length; k++) {
                        if (Main.listaCarros[k].getNombre() == nombre) {

                            Main.listaCarros[k].setDanio(danio);
                            Main.listaCarros[k].setDurabilidad(durabilidad);
                        }
                    }

                    col.close();
                    System.out.println("Jugador actualizado.");
                } catch (Exception e) {
                    System.out.println("Error al actualizar.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la conexión. Comprueba datos.");
            }
        } else {
            System.out.println("El carro no existe");
        }
    }

    ////metodos consultas exist
    private static void listarCarr() {
        if (conectar() != null) {
            try {
                XPathQueryService servicio;
                servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Preparamos la consulta
                ResourceSet result = servicio.query("/Carros/carro/vehiculos/concat(\"  Nombre:\",nombre ,\"   Durabilidad:\" ,durabilidad,\"   Dani:o\",danio, \"    Descripcion:\"  ,descripcion )");
                // recorrer los datos del recurso.
                ResourceIterator i;
                i = result.getIterator();
                if (!i.hasMoreResources()) {
                    System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");

                }
                while (i.hasMoreResources()) {
                    Resource r = i.nextResource();
                    System.out.println("--------------------------------------------");
                    System.out.println((String) r.getContent());
                }
                col.close();
            } catch (XMLDBException e) {
                System.out.println(" ERROR AL CONSULTAR DOCUMENTO.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }

    }

    private static void listarbot() {
        if (conectar() != null) {
            try {
                XPathQueryService servicio;
                servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Preparamos la consulta
                ResourceSet result = servicio.query("for $carro in //Carros return $carro");
                // recorrer los datos del recurso.
                ResourceIterator i;
                i = result.getIterator();
                if (!i.hasMoreResources()) {
                    System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");

                }
                while (i.hasMoreResources()) {
                    Resource r = i.nextResource();
                    System.out.println("--------------------------------------------");
                    System.out.println((String) r.getContent());
                }
                col.close();
            } catch (XMLDBException e) {
                System.out.println(" ERROR AL CONSULTAR DOCUMENTO.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }

    }

    private static void borrarregistroCarro(String nombre) {
        System.out.println(nombre + "  Nombre carro");
        if (comprobarCarro(nombre)) {
            if (conectar() != null) {
                try {
                    if (nombre == "Viper" || nombre == "starter") {
                        System.out.println("Este carro no se puede borrar");
                    } else {
                        System.out.printf("Borro el carro: %s\n", nombre);


                        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                        //Consulta para borrar un departamento --> update delete
                        ResourceSet result = servicio.query(
                                "update delete /Carros/carro/vehiculos[nombre=\"" + nombre + "\"]");
                        col.close();
                        for (int o = 0; o < Main.listaCarros.length; o++) {
                            if (Main.listaCarros[o].getNombre() == nombre) {
                                Main.listaCarros[o] = null;


                            }
                            if (Main.listaBots[o].carro.getNombre() == nombre) {
                                Main.listaBots[o].carro = null;
                            }
                            if (Main.listaPlayers[o].carro.getNombre() == nombre) {
                                Main.listaPlayers[o].carro = null;

                            }
                            if (Main.playerActual.carro.getNombre() == nombre) {
                                Main.playerActual.carro = null;

                            }
                        }

                        System.out.println("Carro borrado.");
                    }
                } catch (Exception e) {
                    System.out.println("Error al borrar.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la conexión. Comprueba datos.");
            }
        } else {
            System.out.println("El carro no existe");
        }

    }

    private static boolean comprobarCarro(String nombre) {
        //Devuelve true si el dep existe
        if (conectar() != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");


                //Consulta para consultar la información de un departamento
                ResourceSet result = servicio.query("/Carros/carro/vehiculos[nombre=\"" + nombre + "\"]");
                ResourceIterator i;
                i = result.getIterator();
                col.close();
                if (!i.hasMoreResources()) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Error al consultar.");
                // e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }

        return false;

    }

    private static void buscarCarroNombre() throws IOException {
        System.out.println("Da el nombre del carro a buscar");
        String nombre = br.readLine();
        if (conectar() != null) {
            try {
                XPathQueryService servicio;
                servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Preparamos la consulta
                ResourceSet result = servicio.query("/Carros/carro/vehiculos[nombre=\"" + nombre + "\"]");
                // recorrer los datos del recurso.
                ResourceIterator i;
                i = result.getIterator();
                if (!i.hasMoreResources()) {
                    System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");

                }
                while (i.hasMoreResources()) {
                    Resource r = i.nextResource();
                    System.out.println("--------------------------------------------");
                    System.out.println((String) r.getContent());
                }
                col.close();
            } catch (XMLDBException e) {
                System.out.println(" ERROR AL CONSULTAR DOCUMENTO.");
                e.printStackTrace();
            } catch (Exception e) {

                System.out.println("Error a la hora de recibir datos desde la consola");
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }


    }

    private static void insertarCarr(int danio, String nombre, int durabilidad, String descripcion, String
            tipo, String categoria) {

        //Caso concreto: sabemos cuáles son los nodos
        String carroNuevo = "<Carros><carro><vehiculos><clasificacion tipo= \"" + tipo + "\" ><categoria>" + categoria + "</categoria></clasificacion>"
                + "<durabilidad>" + durabilidad + "</durabilidad><nombre>" + nombre + "</nombre><danio>" + danio + "</danio><descripcion>" + descripcion + "</descripcion></vehiculos></carro></Carros>";

        if (conectar() != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                System.out.printf("Inserto: %s \n", carroNuevo);
                //Consulta para insertar --> update insert ... into
                ResourceSet result = servicio.query("update insert " + carroNuevo + " into /Carros");
                col.close(); //borramos
                System.out.println("CARRO insertado.");
            } catch (Exception e) {
                System.out.println("Error al insertar carro.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
    }
}

