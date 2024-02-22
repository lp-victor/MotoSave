package motosave.Persistencia;

import motosave.Modelos.Cliente;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class ClienteXMLWriter {

    public ClienteXMLWriter() {
    }

    public void escribirXML(List<Cliente> clientes, String rutaArchivo) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Elemento raíz
            Element rootElement = doc.createElement("clientes");
            doc.appendChild(rootElement);

            // Crear elemento por cada cliente
            for (Cliente cliente : clientes) {
                Element clienteElement = doc.createElement("cliente");

                // Añadir elementos hijo al elemento cliente
                createElement(doc, clienteElement, "id_cliente", String.valueOf(cliente.getId_cliente()));
                createElement(doc, clienteElement, "nombre", cliente.getNombre());
                createElement(doc, clienteElement, "correo", cliente.getCorreo());
                createElement(doc, clienteElement, "telefono", String.valueOf(cliente.getTelefono()));
                createElement(doc, clienteElement, "direccion", cliente.getDireccion());

                rootElement.appendChild(clienteElement);
            }

            // Escribir el contenido en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaArchivo));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createElement(Document doc, Element parent, String nombre, String valor) {
        Element e = doc.createElement(nombre);
        e.appendChild(doc.createTextNode(valor));
        parent.appendChild(e);
    }

}
