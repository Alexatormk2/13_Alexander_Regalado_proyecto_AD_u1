import com.sun.source.tree.WhileLoopTree;

import java.io.*;

public class ejer7_read {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File fichero = new File(".//src//persona.dat");

//Flujo de entrada
        FileInputStream filein = new FileInputStream(fichero);
//Conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try {
            while (true) {

                ejer7_Persona p21 = (ejer7_Persona) dataIS.readObject();
                System.out.println(p21.getNombre());
                System.out.println(p21.getEdad());
            }
        } catch (EOFException e) {
            System.out.println(" Fin de la lectura");
        }


    }


}
