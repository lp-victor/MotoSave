package motosave.Persistencia;

import motosave.Modelos.Cliente;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ClienteHandler extends DefaultHandler {

    private List<Cliente> clientes;
    private Cliente cliente;
    private StringBuilder data;

    public List<Cliente> getClientes() {
        return clientes;
    }

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

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}


