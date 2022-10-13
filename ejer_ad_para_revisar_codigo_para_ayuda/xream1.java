import com.thoughtworks.xstream.XStream;

import java.io.*;

public class xream1 {

    public static void main(String[] args) throws IOException , ClassNotFoundException {
        File fichero = new File(".//src//persona.dat");
        FileInputStream filein = new FileInputStream(fichero);//crea el flujo de entrada
//conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataIS = new ObjectInputStream(filein);
        System.out.println("Comienza el proceso de creación del fichero a XML ...");
//Creamos un objeto Lista de Personas
        ListaPersonas listaper = new ListaPersonas();
        try {
            while (true) { //lectura del fichero
                ejer7_Persona persona= (ejer7_Persona) dataIS.readObject(); //leer una Persona
                listaper.add(persona); //añaadir persona a la lista
            }
        }catch (EOFException eo) {} catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataIS.close(); //cerrar stream de entrada
        try {
            XStream xstream = new XStream();
//cambiar de nombre a las etiquetas XML
            xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
            xstream.alias("DatosPersona", ejer7_Persona.class);
//quitar etiqueta lista (atributo de la clase ListaPersonas)
            xstream.addImplicitCollection(ListaPersonas.class, "lista");
//Insrtar los objetos en el XML
            xstream.toXML(listaper, new FileOutputStream("Personas.xml"));
            System.out.println("Creado fichero XML....");
        }catch (Exception e)
        {e.printStackTrace();}
    } // fin
}
