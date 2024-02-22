/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import motosave.DAO.ConcesionarioDAO;
import motosave.Modelos.Concesionario;

import java.util.List;

/**
 * @author MotoSave
 */
public class ImpConcesionarioDAO implements ConcesionarioDAO {

    /**
     * Agrega un nuevo concesionario a la base de datos.
     *
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

    /**
     * Lista todos los concesionarios disponibles.
     *
     * @param em El EntityManager utilizado para realizar la operación.
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
