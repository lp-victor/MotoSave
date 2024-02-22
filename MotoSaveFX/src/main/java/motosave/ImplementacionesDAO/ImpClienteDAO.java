package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import motosave.DAO.ClienteDAO;
import motosave.Modelos.Cliente;

import java.util.List;

/**
 * @author MotoSave
 */
public class ImpClienteDAO implements ClienteDAO {

    /**
     * Añade un nuevo cliente a la base de datos.
     *
     * @param em           El EntityManager utilizado para realizar la operación.
     * @param nuevoCliente El cliente a añadir.
     */
    @Override
    public void anadirCliente(EntityManager em, Cliente nuevoCliente) {
        try {
            em.getTransaction().begin();
            em.persist(nuevoCliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todos los clientes almacenados en la base de datos.
     *
     * @param em El EntityManager utilizado para realizar la operación.
     * @return Una lista de objetos Cliente, o null si ocurre un error.
     */
    @Override
    public List<Cliente> listarClientes(EntityManager em) {
        try {
            List<Cliente> clientes = (em.createQuery("FROM Cliente", Cliente.class).getResultList());
            return clientes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
