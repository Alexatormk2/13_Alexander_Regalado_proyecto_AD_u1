import java.io.*;

public class Metodos_main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void inicializador() throws IOException {

//leer piezas

        File fichero = new File(".//src//piezas.dat");

//Flujo de entrada
        FileInputStream filein = new FileInputStream(fichero);
//Conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataIS = new ObjectInputStream(filein);
        int ad = 0;
        try {
            while (true) {

                item p21 = (item) dataIS.readObject();
                String nombre = p21.getNombre();
                String categoria = p21.getCategoria();

                double precio = p21.getPrecio();
                item a = new item(precio, nombre, categoria);
                Main.disponiblesPiezas[ad] = a;
                ad++;

            }
        } catch (EOFException e) {
            System.out.println(" Fin de la carga de piezas");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //leer vehiculos


        File ficheroCarro = new File(".//src//vehiculos.dat");

//Flujo de entrada
        FileInputStream fileDEntro = new FileInputStream(ficheroCarro);
//Conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataGET = new ObjectInputStream(fileDEntro);
        int aj = 0;
        try {
            while (true) {

                vehiculos carro = (vehiculos) dataIS.readObject();
                String nombre = carro.getNombre();
                String descripcion = carro.getDescripcion();
                int danio = carro.getDanio();
                int durabilidad = carro.getDurabilidad();


                vehiculos a = new vehiculos(durabilidad, nombre, danio, descripcion);
                Main.listaCarros[aj] = a;
                ad++;

            }
        } catch (EOFException e) {
            System.out.println(" Fin de la carga de carros");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        //leer survivors
        File ficheroSurv = new File(".//src//vehiculos.dat");

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
                ad++;

            }

        } catch (EOFException e) {
            System.out.println(" Fin de la carga de carros");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //leer bots


    }

    //funcio que resta item de jugador
    public static void vender() throws IOException {
        item[] paraMover = new item[20];
        if (Main.playerActual.almacen.length == 0) {

            System.out.println("No posees ningun item lo siento vuelve cuando tengas algo para vender");
        } else {
            for (int d = 0; d < Main.playerActual.almacen.length; d++) {
                System.out.println(d + " . Nombre  " + Main.playerActual.almacen[d].getNombre() + "  " + "Precio:" + Main.playerActual.almacen[d].getPrecio());
            }
            int selector = Integer.parseInt(br.readLine());
            double plata = Main.playerActual.getGold() + Main.playerActual.almacen[selector].getPrecio();
            //copar arrya pero sin el que a sido borrado
            for (int a = 0; a < Main.playerActual.almacen.length; a++) {
                if (a == selector) {
                    continue;
                }

                paraMover[a] = Main.playerActual.almacen[a];

            }

            Main.playerActual.setGold(plata);
            for (int j = 0; j < paraMover.length; j++) {
                Main.playerActual.almacen[j] = paraMover[j];

            }


        }
    }

    //funcion que le da item a jugador despudes de comprarlo aparte de revisar si ya lo tiene en tal caso no le deja comprar
    public static void comprar() throws IOException {
        for (int q = 0; q < Main.disponiblesPiezas.length; q++) {
            System.out.println(q + " . Nombre  " + Main.disponiblesPiezas[q].getNombre() + "  " + "Precio:" + Main.disponiblesPiezas[q].getPrecio());
        }
        int selector = Integer.parseInt(br.readLine());
        double plata = Main.playerActual.getGold() - Main.disponiblesPiezas[selector].getPrecio();
        if (plata >= 0) {
            if (Main.playerActual.almacen[selector] == Main.disponiblesPiezas[selector]) {

                System.out.println("No se puede tener duplicados");
            } else {
                Main.playerActual.almacen[selector] = Main.disponiblesPiezas[selector];
                System.out.println("Compra realizada");

            }


        }


    }


}

