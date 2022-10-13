import com.thoughtworks.xstream.XStream;

import java.io.*;

public class xream2 {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ListaEmpleados listaper = new ListaEmpleados();
        RandomAccessFile fichero = new RandomAccessFile(".//src//empleados.dat", "rw");
        char[] apellido = new char[10];

        int i;
        double d;
        String n;
        int b;
        int pos = 0;
        while (true) {


            fichero.seek(pos);
            i = fichero.readInt();

            for (int g = 0; g < apellido.length; g++) {
                apellido[g] = fichero.readChar();


            }
            n = new String(apellido);

            b = fichero.readInt();

            d = fichero.readDouble();


            Ejer9_emple empleado = new Ejer9_emple(i, n, b, d); //leer una Persona
            listaper.add(empleado);
            pos = pos + 36;


            System.out.println("Comienza el proceso de creación del fichero a XML ...");
//Creamos un objeto Lista de Personas


            if (fichero.getFilePointer() == fichero.length()) {
                break;


            }

        }
//conecta el flujo de bytes al flujo de datos

            //cerrar stream de entrada
            try {
                XStream xstream = new XStream();
//cambiar de nombre a las etiquetas XML
                xstream.alias("ListaEmpleados", ListaEmpleados.class);
                xstream.alias("DatosEmpleados", Ejer9_emple.class);
//quitar etiqueta lista (atributo de la clase ListaPersonas)
                xstream.addImplicitCollection(ListaEmpleados.class, "lista");
//Insrtar los objetos en el XML
                xstream.toXML(listaper, new FileOutputStream("Empleados2.xml"));
                System.out.println("Creado fichero XML....");
            } catch (Exception e) {
                e.printStackTrace();
            }
            fichero.close();
        }// fin
    }

