package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import motosave.DAO.ClienteDAO;
import motosave.Modelos.Cliente;
import motosave.Modelos.Motocicleta;

import java.util.ArrayList;
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
    public Cliente buscarCliente(EntityManager em, int id_cliente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            Cliente cliente = em.find(Cliente.class, id_cliente);
            transaction.commit();
            if (cliente != null){
                return cliente;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cliente buscarClienteCorreo(EntityManager em, String correo) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            List<Cliente> clientes = null;
            Cliente cliente = null;
            String jpql = "SELECT c FROM Cliente c WHERE c.correo = :correo";
            clientes = (em.createQuery(jpql, Cliente.class)
                    .setParameter("correo", correo)
                    .getResultList());
            transaction.commit();
            cliente = clientes.getFirst();
            if (cliente != null){
                return cliente;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
