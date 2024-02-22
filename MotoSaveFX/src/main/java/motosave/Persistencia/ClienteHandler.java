package motosave.Persistencia;

import motosave.Modelos.Cliente;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
/**
 * Manejador de eventos SAX para procesar archivos XML que contienen información de clientes.
 *
 * @author MotoSave
 */
public class ClienteHandler extends DefaultHandler {

    private List<Cliente> clientes;
    private Cliente cliente;
    private StringBuilder data;

    /**
     * Obtiene la lista de clientes procesados.
     *
     * @return Lista de clientes procesados.
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Se llama cuando se encuentra un nuevo elemento XML.
     *
     * @param uri         El URI del espacio de nombres del elemento.
     * @param localName   El nombre local (sin prefijo) del elemento.
     * @param qName       El nombre cualificado (con prefijo) del elemento.
     * @param attributes  Los atributos adjuntos al elemento.
     * @throws SAXException Si ocurre un error durante el procesamiento SAX.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("cliente")) {
            cliente = new Cliente();
            if (clientes == null) {
                clientes = new ArrayList<>();
            }
        }
        data = new StringBuilder();
    }

    /**
     * Se llama cuando se alcanza el final de un elemento XML.
     *
     * @param uri       El URI del espacio de nombres del elemento.
     * @param localName El nombre local (sin prefijo) del elemento.
     * @param qName     El nombre cualificado (con prefijo) del elemento.
     * @throws SAXException Si ocurre un error durante el procesamiento SAX.
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "cliente":
                clientes.add(cliente);
                break;
            case "id_cliente":
                cliente.setId_cliente(Integer.parseInt(data.toString()));
                break;
            case "nombre":
                cliente.setNombre(data.toString());
                break;
            case "correo":
                cliente.setCorreo(data.toString());
                break;
            case "telefono":
                cliente.setTelefono(Integer.parseInt(data.toString()));
                break;
            case "direccion":
                cliente.setDireccion(data.toString());
                break;
        }
    }

    /**
     * Se llama cuando se encuentran caracteres dentro de un elemento XML.
     *
     * @param ch     El arreglo de caracteres.
     * @param start  La posición inicial en el arreglo de caracteres.
     * @param length La longitud de los caracteres a leer desde el arreglo.
     * @throws SAXException Si ocurre un error durante el procesamiento SAX.
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}


