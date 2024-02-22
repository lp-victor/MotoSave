package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import motosave.DAO.ClienteDAO;
import motosave.Modelos.Cliente;

import java.util.List;

public class ImpClienteDAO implements ClienteDAO {


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
