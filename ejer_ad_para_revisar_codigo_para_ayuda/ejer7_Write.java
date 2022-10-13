import java.io.*;

public class ejer7_Write {
    public static void main(String[] args) throws IOException {

        File fichero = new File(".//src//persona.dat");
        FileOutputStream escribir = new FileOutputStream(fichero);
        ObjectOutputStream item = new ObjectOutputStream(escribir);
        ejer7_Persona p1 = new ejer7_Persona("Tomas",12);
        ejer7_Persona p2 = new ejer7_Persona("Myers",22);
        ejer7_Persona p3 = new ejer7_Persona("Paco",12);
        ejer7_Persona p4 = new ejer7_Persona("Carl",12);


        item.writeObject(p1);
        item.writeObject(p2);
        item.writeObject(p3);
        item.writeObject(p4);
    }

}
