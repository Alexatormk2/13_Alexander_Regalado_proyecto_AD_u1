
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class xml1 {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        int id = 0;
        String apellidos = null;
        int dep = 0;
        double salario = 0;

        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "Empleados", null);
        document.setXmlVersion("1.0");


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
            id = i;
            System.out.println(i);
            for (int g = 0; g < apellido.length; g++) {
                apellido[g] = fichero.readChar();


            }
            n = new String(apellido);
            apellidos = n;
            System.out.println(n);
            b = fichero.readInt();
            dep = b;
            System.out.println(b);

            d = fichero.readDouble();
            salario = d;
            System.out.println(d);
            pos = pos + 36;


            Element raiz = document.createElement("empleado"); //nodo empleado
            document.getDocumentElement().appendChild(raiz); //lo añade a la raíz del documento
//se añaden los hijos al nodo raiz
            CrearElemento("id", String.valueOf(id), raiz, document);
            CrearElemento("apellido", apellidos, raiz, document);
            CrearElemento("dep", String.valueOf(dep), raiz, document);
            CrearElemento("salario", String.valueOf(salario), raiz, document);

            //  Se crea la fuente XML a partir del documento
            Source source = new DOMSource(document);
            //  Se crea el resultado en el fichero Empleados.xml
            Result result = new StreamResult(new java.io.File("Empleados.xml"));
            // Se obtiene un TransformerFactory
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //  Se realiza la transformación de documento a fichero
            transformer.transform(source, result);


            if (fichero.getFilePointer() == fichero.length()) {
                break;
            }
        }
        fichero.close();


    }

    static void CrearElemento(String datoEmple, String valor,
                              Element raiz, Document document) {
        Element elem = document.createElement(datoEmple); //creamos hijo
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor
    }
}
