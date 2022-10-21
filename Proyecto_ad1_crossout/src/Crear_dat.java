import java.io.*;

public class Crear_dat {

    //fichero para crear los dat base
    public static void main(String[] args) throws IOException {

        //cerar dat de vehiculos
        File fichero = new File("vehiculos.dat");
        FileOutputStream escribirCarro = new FileOutputStream(fichero);
        ObjectOutputStream itemCarro = new ObjectOutputStream(escribirCarro);

        vehiculos carro1 = new vehiculos(575, "Viper", 420, "Un pequeño buggy armado con dos escopetas");
        vehiculos carro2 = new vehiculos(1575, "Bombarder", 420, "Un camion infectado por un parasito ravager ,seguro que quiere usarlo? ");
        vehiculos carro3 = new vehiculos(600, "Equilibrium", 220, "Rapido y resistente poco, de quien fue la ide a de poner mecheros es como arma ?");
        vehiculos carro4 = new vehiculos(400, "starter", 360, "Lo mas simple y con lo que se empieza");
        vehiculos carro11 = new vehiculos(1001, "Song of hell", 520, "Cuano escuche una cancion en las tierras de wasteland no es el camion de los helaods es solo alquien que viene a por tu vida");
        vehiculos carro12 = new vehiculos(500, "Fire koopa", 520, "Rapido y letal para quemar a quienes se ponganen tu camino");
        vehiculos carro13 = new vehiculos(1200, "Death_Machine", 320, "Un vehiculo que usa drones robados para luchar");
        itemCarro.writeObject(carro1);
        itemCarro.writeObject(carro2);
        itemCarro.writeObject(carro3);
        itemCarro.writeObject(carro4);
        itemCarro.writeObject(carro11);
        itemCarro.writeObject(carro12);
        itemCarro.writeObject(carro13);
        itemCarro.close();

        File ficheroSurvivor = new File("survivor.dat");
        FileOutputStream escribirSurvi = new FileOutputStream(ficheroSurvivor);
        ObjectOutputStream itemSurvi = new ObjectOutputStream(escribirSurvi);
        survivor survi1 = new survivor("IVX", 2, 2, carro1, 2141, 214, 2100, 999);
        survivor survi2 = new survivor("Foxy", 3, 3, carro4, 41, 21, 100, 2);

        itemSurvi.writeObject(survi1);
        itemSurvi.writeObject(survi2);
        itemSurvi.close();


        File ficheroBot = new File("BOT.dat");
        FileOutputStream escribirBot = new FileOutputStream(ficheroBot);
        ObjectOutputStream itemBot = new ObjectOutputStream(escribirBot);

        BOT bot0 = new BOT("PlaceHolder", 0, 0, carro1, "placeholder", "placeholder", "placeholder");
        BOT bot1 = new BOT("SoulTaker", 12414, 241, carro13, "Usando uno de los vehiculos de los jinetes de apocalipsis da casa a cualquier cosa que se mueva", "La muerte es algo con lo que te as encontrado y no has podido escapar", ".................");
        BOT bot2 = new BOT("Bowser", 124, 3141, carro12, "Creado por un fanatico de super mario este bot usa el fuego para acabar con todo", "Yo el gran bowser e aplastado a un insecto", "Noooooooooooooo");
        BOT bot3 = new BOT("Miku", 1123, 13, carro12, "", "Me diverti espero que no estes enfadado conmigo :D", "Fue creada para cantar no para luchar, pero estoy feliz de sentir emociones distintas");
        itemBot.writeObject(bot0);
        itemBot.writeObject(bot1);
        itemBot.writeObject(bot2);
        itemBot.writeObject(bot3);
        itemBot.close();


    }


}
