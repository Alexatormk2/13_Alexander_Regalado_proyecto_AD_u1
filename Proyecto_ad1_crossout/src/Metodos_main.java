import javax.sound.midi.Soundbank;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Objects;

public class Metodos_main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
                int durabilidad = carro.getDurabilidad();
                String tipo = carro.getTipo();

                vehiculos a = new vehiculos(durabilidad, nombre, danio, descripcion, tipo);
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
        //random que seleciona el bot de forma aleatoria
        int numeroAleatorio = (int) (Math.random() * 6 + 0);
        //bot/enemigo
        String bot = Main.listaBots[numeroAleatorio].getNombre();
        String Carrobot = Main.listaBots[numeroAleatorio].carro.getNombre();
        int daniobot = Main.listaBots[numeroAleatorio].carro.getDanio();
        int Healthbot = Main.listaBots[numeroAleatorio].carro.getDurabilidad();
        String victoria = Main.listaBots[numeroAleatorio].getFraseVictoria();
        String derrota = Main.listaBots[numeroAleatorio].getFraseDerrota();
        int vidaBot = Healthbot;
        int danioTotalBot = 0;
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
}

