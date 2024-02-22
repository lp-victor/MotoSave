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
/**
 * Esta clase se encarga de escribir objetos Cliente en un archivo XML.
 *
 * @author MotoSave
 */
public class ClienteXMLWriter {

    public ClienteXMLWriter() {
    }

    /**
     * Escribe la lista de clientes en un archivo XML en la ruta especificada.
     *
     * @param clientes    La lista de clientes a escribir en el archivo XML.
     * @param rutaArchivo La ruta del archivo XML donde se escribirán los clientes.
     */
    public void escribirXML(List<Cliente> clientes, String rutaArchivo) {
        try {
            // Crear un nuevo documento XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Elemento raíz
            Element rootElement = doc.createElement("clientes");
            doc.appendChild(rootElement);

            // Crear un elemento por cada cliente y agregarlo al elemento raíz
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

    /**
     * Método privado para crear un nuevo elemento XML y agregarlo al padre con el valor especificado.
     *
     * @param doc    El documento XML.
     * @param parent El elemento padre al que se agregará el nuevo elemento.
     * @param nombre El nombre del nuevo elemento.
     * @param valor  El valor del nuevo elemento.
     */
    private void createElement(Document doc, Element parent, String nombre, String valor) {
        Element e = doc.createElement(nombre);
        e.appendChild(doc.createTextNode(valor));
        parent.appendChild(e);
    }

}
