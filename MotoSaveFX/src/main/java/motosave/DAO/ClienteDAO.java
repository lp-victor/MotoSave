package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Cliente;

import java.util.List;
/**
 * @author MotoSave
 */
public interface ClienteDAO {

    void anadirCliente(EntityManager em, Cliente nuevoCliente);
    Cliente buscarCliente (EntityManager em, int id_cliente);
    List<Cliente> listarClientes(EntityManager em);
}
