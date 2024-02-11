/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import motosave.DAO.ConcesionarioDAO;
import motosave.Modelos.Concesionario;
import motosave.Modelos.Motocicleta;

import java.util.List;

/**
 * Implementación de la interfaz ConcesionarioDAO.
 * Proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * en la base de datos para la entidad Concesionario.
 */
public class ImpConcesionarioDAO implements ConcesionarioDAO {

    /**
     * Agrega un nuevo concesionario a la base de datos.
     * @param conc          El concesionario que se va a agregar.
     * @param entityManager El EntityManager utilizado para realizar la operación.
     */
    @Override
    public void agregarConcesionario(Concesionario conc, EntityManager entityManager) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(conc);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarConcesionario(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Lista los nombres de todos los concesionarios almacenados en la base de datos.
     * @param em El EntityManager utilizado para realizar la operación.
     * @return Una lista de los nombres de los concesionarios almacenados.
     */
    @Override
    public List<String> listarNombreConcesionarios(EntityManager em) {
        try {
            // Consulta JPQL para obtener los nombres de los concesionarios
            List<String> nombresConcesionarios = em.createQuery("SELECT c.ubicacion FROM Concesionario c", String.class)
                    .getResultList();

            return nombresConcesionarios;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Buscar un Concesionario por el id_Concesionario
     * @param em
     * @param id_concesionario
     * @return Concesionario
     */
    @Override
    public Concesionario buscarConcesionario(EntityManager em, int id_concesionario) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            Concesionario concesionario = em.find(Concesionario.class, id_concesionario);
            transaction.commit();
            return concesionario;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Lista todos los concesionarios disponibles.
     * @param em
     * @return Lista de Concesionarios
     */
    public List<Concesionario> listarConcesionarios(EntityManager em) {
        try {
            // Consulta JPQL para obtener los nombres de los concesionarios
            List<Concesionario> concesionarios = em.createQuery("SELECT c FROM Concesionario c", Concesionario.class)
                    .getResultList();

            return concesionarios;
        } catch (Exception e) {
            return null;
        }
    }

}
