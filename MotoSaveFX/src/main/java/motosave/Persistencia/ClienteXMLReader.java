package motosave.Persistencia;

import motosave.Modelos.Cliente;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

public class ClienteXMLReader {

    public ClienteXMLReader() {
    }

    public List<Cliente> leerXML(String rutaArchivo) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ClienteHandler handler = new ClienteHandler();
            saxParser.parse(rutaArchivo, handler);
            return handler.getClientes();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
