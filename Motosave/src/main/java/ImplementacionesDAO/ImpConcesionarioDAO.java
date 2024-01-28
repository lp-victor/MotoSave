/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionesDAO;

import DAO.ConcesionarioDAO;
import Modelos.Concesionario;
import jakarta.persistence.EntityManager;

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
