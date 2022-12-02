import java.io.*;

public class Crear_dat {

    //fichero para crear los dat base
    public static void main(String[] args) throws IOException {

        //cerar dat de vehiculos
        File fichero = new File("vehiculos.dat");
        FileOutputStream escribirCarro = new FileOutputStream(fichero);
        ObjectOutputStream itemCarro = new ObjectOutputStream(escribirCarro);

        vehiculos.Clascificacion clasiViper = new vehiculos.Clascificacion("buggy","Ligero");
        vehiculos.Clascificacion clasiBom = new vehiculos.Clascificacion("Camion de artilleria Ravager","Pesado");
        vehiculos.Clascificacion clasiEqui = new vehiculos.Clascificacion("Deportivo","Ligero");
        vehiculos.Clascificacion clasiStart = new vehiculos.Clascificacion("Camioneta","Medio");
        vehiculos.Clascificacion clasiMega = new vehiculos.Clascificacion("Tanque","Pesado");
        vehiculos.Clascificacion clasiSong = new vehiculos.Clascificacion("Deportivo Ravager","Ligero");
        vehiculos.Clascificacion clasiKoopa = new vehiculos.Clascificacion("Carro blindado","ligero");
        vehiculos.Clascificacion clasideath = new vehiculos.Clascificacion("Funebre","Medio");
        vehiculos.Clascificacion clasifamine = new vehiculos.Clascificacion("Maquina hambrienta","Medio");
        vehiculos.Clascificacion clasipestilence = new vehiculos.Clascificacion("Maquina anti plagas","ligero");
        vehiculos.Clascificacion clasiwar = new vehiculos.Clascificacion("Blindado de guerra","Pesado");
        vehiculos carro1 = new vehiculos(575, "Viper", 420, "Un pequeño buggy armado con 3 escopetas",clasiViper );
        vehiculos carro2 = new vehiculos(1575, "Bombarder", 420, "Un camion armado con artilleria infectado por un parasito ravager  ,seguro que quiere usarlo? ", clasiBom);
        vehiculos carro3 = new vehiculos(600, "Equilibrium", 220, "Rapido y resistente poco, de quien fue la ide a de poner mecheros es como arma ?", clasiEqui);
        vehiculos carro4 = new vehiculos(400, "starter", 360, "Lo mas simple y con lo que se empieza", clasiStart);
        vehiculos carro10 = new vehiculos(1500, "Mega-TANQUE", 420, "Un tanque de color gris que se recuerda a un lider cybertroniano cruel y tirano", clasiMega);
        vehiculos carro11 = new vehiculos(1001, "Song of hell", 420, "Cuadno escuchas una cancion en las tierras de wasteland no es el camion de los helados es solo alquien que viene a por tu vida", clasiSong);
        vehiculos carro12 = new vehiculos(500, "Fire koopa", 480, "Rapido y letal para quemar a quienes se ponganen tu camino", clasiKoopa);
        vehiculos carro13 = new vehiculos(1200, "Death_Machine", 320, "Un vehiculo que usa drones robados para luchar", clasideath);
        vehiculos carro9 = new vehiculos(900, "Famine_Machine", 420, "Un vehiculo   hambriento el cual desea devorar tanto carros como survivors", clasifamine);
        vehiculos carro8 = new vehiculos(800, "Pestilence", 500, "Un carro creado para acabar con las plagas que se encuentran por le valle, con fuego", clasipestilence);
        vehiculos carro7 = new vehiculos(1250, "War_Machine", 350, "Un gran vehiculo armado con una ballesta y lanzacohetes, listo para la querra", clasiwar);
        itemCarro.writeObject(carro1);
        itemCarro.writeObject(carro2);
        itemCarro.writeObject(carro3);
        itemCarro.writeObject(carro4);
        itemCarro.writeObject(carro8);
        itemCarro.writeObject(carro7);
        itemCarro.writeObject(carro9);
        itemCarro.writeObject(carro11);
        itemCarro.writeObject(carro12);
        itemCarro.writeObject(carro13);
        itemCarro.close();

        File ficheroSurvivor = new File("survivor.dat");
        FileOutputStream escribirSurvi = new FileOutputStream(ficheroSurvivor);
        ObjectOutputStream itemSurvi = new ObjectOutputStream(escribirSurvi);
        survivor survi1 = new survivor("IVY_XO", 0, 0, carro1, 0, 0, 100, 0, 0);
        survivor survi2 = new survivor("Foxy", 0, 0, carro4, 0, 0, 100, 0, 0);

        itemSurvi.writeObject(survi1);
        itemSurvi.writeObject(survi2);
        itemSurvi.close();


        File ficheroBot = new File("BOT.dat");
        FileOutputStream escribirBot = new FileOutputStream(ficheroBot);
        ObjectOutputStream itemBot = new ObjectOutputStream(escribirBot);

        BOT bot0 = new BOT("Megatron", 0, 0, carro10, "Un agresivo bot con la memoria de un bot alienigena que se transformaba en un tanque", "Muere,survivor muere", "....... Todavia sigo funcionando", 0, 0);
        BOT bot1 = new BOT("Muerte", 0, 0, carro13, "Uno de los lideres de los jinetes de apocalipsis que da caza a cualquier idiota que se le cruze", "La muerte es algo con lo que te as encontrado y no has podido escapar", ".................", 0, 0);
        BOT bot2 = new BOT("Bowser", 0, 0, carro12, "Creado por un fanatico de super mario este bot usa el fuego para acabar con todo", "Yo el gran bowser e aplastado a un insecto", "Noooooooooooooo", 0, 0);
        BOT bot3 = new BOT("Miku", 0, 0, carro12, "Una Cantante virtual del viejo mundo, algun idiota le hizo un cuerpo usando un par de cadaveres y una unidad ravager y la dejo libre por el valle", "Me diverti bastante espero que no estes enfadado conmigo :D", "Fui creada para cantar no para luchar, pero estoy feliz de probar cosas distintas", 0, 0);
        BOT bot4 = new BOT("Hambre", 0, 0, carro9, "Uno de los lideres de los jinetes del apocalipsis, su hambre no tiene comparacion y tampoco nada lo sacia", "Comsumire todo a mi paso", "Esto no es lo planeado joder", 0, 0);
        BOT bot5 = new BOT("Peste", 0, 0, carro8, "Una de las lideres de los jinetes del apocalipsis, carbonizada a cualquiera que se cruze en su ruta o tenga alguna peste", "Las llamas purificaran tu carne", ".... No estas emfermo seguro ?.........Podria darte una muerte rapida con mis llamas", 0, 0);
        BOT bot6 = new BOT("Guerra", 0, 0, carro7, "Uno de los lideres de los jinetes del apocalipsis, luchara para destrozar a cualquiera que lo desafie", "Tu final a llegado", ".... La guerra nunca cambia no lo olvides", 0, 0);
        itemBot.writeObject(bot0);
        itemBot.writeObject(bot1);
        itemBot.writeObject(bot2);
        itemBot.writeObject(bot3);
        itemBot.writeObject(bot4);
        itemBot.writeObject(bot5);
        itemBot.writeObject(bot6);
        itemBot.close();


    }


}
