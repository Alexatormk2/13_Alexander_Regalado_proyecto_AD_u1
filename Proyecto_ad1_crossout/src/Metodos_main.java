import java.io.*;
import java.util.Objects;

public class Metodos_main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void inicializador() throws IOException {

//leer piezas


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
                int durabilidad = carro.getDurabilidad();


                vehiculos a = new vehiculos(durabilidad, nombre, danio, descripcion);
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

    //funcio que resta item de jugador


    public static void chatarra() {
        String player = Main.playerActual.getNomnbre();
        String carro = Main.playerActual.carro.getNombre();
        int danio = Main.playerActual.carro.getDanio();
        int health = Main.playerActual.carro.getDurabilidad();
        int danioTotal = 0;
        int numeroAleatorio = (int) (Math.random() * 2 + 0);

        String bot = Main.listaBots[numeroAleatorio].getNombre();
        String Carrobot = Main.listaBots[numeroAleatorio].carro.getNombre();
        int daniobot = Main.listaBots[numeroAleatorio].carro.getDanio();
        int Healthbot = Main.listaBots[numeroAleatorio].carro.getDurabilidad();
        String victoria = Main.listaBots[numeroAleatorio].getFraseVictoria();
        String derrota = Main.listaBots[numeroAleatorio].getFraseDerrota();
        int danioTotalBot = 0;

        for (int round = 0; round < 5; round++) {
            if (Healthbot == 0) {
                System.out.println("----------Victoria--------");
                System.out.println("Enemigo eliminado ganador survivor:" + player);
                System.out.println("----" + bot + ": " + derrota);
                int scraper = Main.playerActual.getScrap();
                scraper = scraper + 40;
                double gold = Main.playerActual.getGold();
                gold = gold + 30;
                Main.playerActual.setScrap(scraper);
                Main.playerActual.setGold(gold);

            } else if (health == 0) {
                System.out.println("---------Derrota------");
                System.out.println("Has sido eliminado" + player);
                System.out.println("----" + bot + ": " + victoria);
                int scraper = Main.playerActual.getScrap();
                scraper = scraper + 20;
                Main.playerActual.setScrap(scraper);
                double gold = Main.playerActual.getGold();
                gold = gold + 15;
                Main.playerActual.setGold(gold);
            } else {
                int numeroAleatorio2 = (int) (Math.random() * 2 + 1);
                switch (numeroAleatorio2) {
                    case 1:
                        //turno jugador
                        System.out.println("+++++++++++++++++");
                        System.out.println("Survivor :" + player + " A dañado el vehiculo enemigo ");
                        danioTotal = Healthbot - danio;
                        System.out.println("+++++++++++++++++");
                        System.out.println(player + " A causado :" + danio + " de danio total al bot");
                        System.out.println("+++++++++++++++++");
                        System.out.println("Durabildiad total perdida  del jugador  " + danioTotalBot);
                        break;
                    case 2:

                        //turno bot
                        System.out.println("-----------");
                        System.out.println(" Bot:" + bot + "A dañado el vehiculo del survivor rival");
                        danioTotalBot = health - daniobot;

                        System.out.println(player + " A causado :" + daniobot + " de danio total al bot");
                        System.out.println("-----------");
                        System.out.println("Durabildiad total perdida  del bot  " + danioTotal);

                        break;

                }


            }


        }


    }

    public static void cambiarCarro() throws IOException {


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
        opcion = Integer.parseInt(br.readLine());
        if (Main.listaCarros[opcion] == Main.playerActual.getCarro()) {
            System.out.println("Este es el actual no se puede seleccionar");
        } else {
            System.out.println("Cambio el carro espera.....");
            Main.playerActual.setCarro(Main.listaCarros[opcion]);

        }


    }

    public static void cobre() {


        String player = Main.playerActual.getNomnbre();
        String carro = Main.playerActual.carro.getNombre();
        int danio = Main.playerActual.carro.getDanio();
        int health = Main.playerActual.carro.getDurabilidad();
        int danioTotal = 0;
        int numeroAleatorio = (int) (Math.random() * 3 + 0);
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
                continue;
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
                        System.out.println("Durabildiad restante del jugador  " + (health - danioTotalBot));
                        System.out.println("+++++++++++++++++");


                        if (vidaBot - danioTotal <= 0) {
                            System.out.println("----------Victoria--------");
                            System.out.println("Enemigo eliminado ganador survivor:" + player);
                            System.out.println("----" + bot + ": " + derrota);


                            int copper = Main.playerActual.getCopper();
                            copper = copper + 40;
                            Main.playerActual.setCopper(copper);

                            double gold = Main.playerActual.getGold();
                            gold = gold + 30;

                            Main.playerActual.setGold(gold);

                            continue;
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

                        if (vida - danioTotalBot <= 0) {
                            System.out.println("---------Derrota------");
                            System.out.println("Has sido eliminado" + player);
                            System.out.println("----" + bot + ": " + victoria);
                            int copper = Main.playerActual.getCopper();
                            copper = copper + 20;
                            Main.playerActual.setScrap(copper);
                            double gold = Main.playerActual.getGold();
                            gold = gold + 15;
                            Main.playerActual.setGold(gold);

                            continue;

                        }
                        break;


                }
            }

            /////


        }


    }


    //crear nuevo survivor para usar
    public static void newUser() throws IOException {
        try {
            System.out.println("Nombre de la cuanta");
            String name = br.readLine();
//revisa que no exista
            for (int o = 0; o < Main.listaPlayers.length; o++) {
                if (Main.listaPlayers[o] == null) {


                    break;

                } else if (Objects.equals(name, Main.listaPlayers[o].getNomnbre())) {

                    System.out.println("Este usuario ya existe lo siento");
                    System.out.println("Cerrando programna :P");
                    System.exit(0);
                }


            }

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
            System.out.println("Error al meter los datos cerrando :P");
            System.exit(0);
        }


    }


    public static void guardarDatos() throws IOException {

        File ficheroSurvivor = new File(".//survivor.dat");
        FileOutputStream escribirSurvi = new FileOutputStream(ficheroSurvivor);
        ObjectOutputStream itemSurvi = new ObjectOutputStream(escribirSurvi);

        for (int a = 0; a < Main.listaPlayers.length; a++) {

            if (Main.listaPlayers[a] == null) {
                break;
            } else if (Main.listaPlayers[a] == Main.playerActual) {

                Main.listaPlayers[a] = Main.playerActual;

            }
            Main.playerActual = Main.listaPlayers[a];

            itemSurvi.writeObject(Main.playerActual);

        }
        ;

    }
}

