package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Cliente;

import java.util.List;

public interface ClienteDAO {

    void anadirCliente(EntityManager em, Cliente nuevoCliente);

    List<Cliente> listarClientes(EntityManager em);
}
