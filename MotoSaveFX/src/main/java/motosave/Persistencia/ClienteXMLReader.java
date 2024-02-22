package motosave.Persistencia;

import motosave.Modelos.Cliente;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;
/**
 * Esta clase se encarga de leer un archivo XML que contiene información de clientes utilizando SAX (Simple API for XML).
 *
 * @author MotoSave
 */
public class ClienteXMLReader {

    public ClienteXMLReader() {
    }

    /**
     * Lee un archivo XML que contiene información de clientes y devuelve una lista de clientes.
     *
     * @param rutaArchivo La ruta del archivo XML a leer.
     * @return Una lista de objetos Cliente leída del archivo XML.
     */
    public List<Cliente> leerXML(String rutaArchivo) {
        try {
            // Crear una instancia de SAXParserFactory y un objeto SAXParser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            // Crear un objeto ClienteHandler para manejar los eventos SAX
            ClienteHandler handler = new ClienteHandler();
            // Parsear el archivo XML utilizando el handler personalizado
            saxParser.parse(rutaArchivo, handler);
            // Obtener la lista de clientes procesados por el handler y devolverla
            return handler.getClientes();
        } catch (Exception e) {
            // Manejar cualquier excepción e imprimir el rastreo de la pila
            e.printStackTrace();
            return null;
        }
    }
}
