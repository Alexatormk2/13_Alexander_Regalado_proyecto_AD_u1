import com.thoughtworks.xstream.XStream;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class xml2 {
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
                listaper.add(persona); //a√±adir persona a la lista
            }
        }catch (EOFException eo) {}
        dataIS.close(); //cerrar stream de entrada
        try {
            XStream xstream = new XStream();
//cambiar de nombre a las etiquetas XML
            xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
            xstream.alias("DatosPersona", Persona.class);
//quitar etiwueta lista (atributo de la clase ListaPersonas)
            xstream.addImplicitCollection(ListaPersonas.class, "lista");
//Insrtar los objetos en el XML
            xstream.toXML(listaper, new FileOutputStream("Personas.xml"));
            System.out.println("Creado fichero XML....");
        }catch (Exception e)
        {e.printStackTrace();}
    } // fin main
}
