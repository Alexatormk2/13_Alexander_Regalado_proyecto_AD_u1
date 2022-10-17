import java.io.*;

public class Crear_dat {

    //fichero para crear los dat base
    public static void main(String[] args) throws IOException {

        //cerar dat de vehiculos
        File fichero = new File(".//src//vehiculos.dat");
        FileOutputStream escribirCarro = new FileOutputStream(fichero);
        ObjectOutputStream itemCarro = new ObjectOutputStream(escribirCarro);

        vehiculos carro1 = new vehiculos(575, "Viper", 120);
        vehiculos carro2 = new vehiculos(1575, "Bombarder", 420);
        vehiculos carro3 = new vehiculos(600, "Equilibrium", 220);
        vehiculos carro4 = new vehiculos(300, "starter", 120);
        itemCarro.writeObject(carro1);
        itemCarro.writeObject(carro2);
        itemCarro.writeObject(carro3);
        itemCarro.writeObject(carro4);


        File ficheroSurvivor = new File(".//src//survivor.dat");
        FileOutputStream escribirSurvi = new FileOutputStream(ficheroSurvivor);
        ObjectOutputStream itemSurvi = new ObjectOutputStream(escribirSurvi);
        survivor survi1 = new survivor("IVX", 2, 2, 12441, 214141, carro1, 2141, 214, 2100);

        File ficheroPiezas = new File(".//src//piezas.dat");
        FileOutputStream esribirPiezas = new FileOutputStream(ficheroPiezas);
        ObjectOutputStream itemPiezas = new ObjectOutputStream(esribirPiezas);
        item piezas1 = new item(30, "spirnter", "Cabina");
        

    }


}
