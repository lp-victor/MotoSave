/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import motosave.DAO.ConcesionarioDAO;
import motosave.Modelos.Concesionario;
import jakarta.persistence.EntityManager;
import motosave.Persistencia.miEntityManager;

import java.util.List;

/**
 *
 * @author victo
 */
public class ImpConcesionarioDAO implements ConcesionarioDAO {

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

}
