package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import motosave.DAO.ClienteDAO;
import motosave.Modelos.Cliente;
import motosave.Modelos.Comercial;

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
     * Busca un cliente en la base de datos dado su id.
     *
     * @param em           El EntityManager utilizado para realizar la operación.
     * @param id_cliente   El id del cliente a buscar.
     */
    @Override
    public Cliente buscarCliente(EntityManager em, int id_cliente) {
        try {
            String jpql = "SELECT c FROM Cliente c WHERE c.id_cliente = :id_cliente";
            Query query = em.createQuery(jpql)
                    .setParameter("id_cliente", id_cliente);
            Cliente cliente = (Cliente) query.getSingleResult();
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
