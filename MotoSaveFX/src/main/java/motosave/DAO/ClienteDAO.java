package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Cliente;

public interface ClienteDAO {

    public abstract void anadirCliente(EntityManager em, Cliente nuevoCliente);

    public abstract Cliente buscarCliente(EntityManager em, int id_cliente);

    public abstract Cliente buscarClienteCorreo(EntityManager em, String correo);

}
