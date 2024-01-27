/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionesDAO;

import DAO.MotocicletaDAO;
import Modelos.Motocicleta;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import persistencia.HibernateConfig;

/**
 *
 * @author victo
 */
public class ImpMotocicletaDAO implements MotocicletaDAO {

    @Override
    public void guardarMoto(Motocicleta moto, EntityManager entityManager) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(moto);
            entityManager.getTransaction().commit();
            System.out.println("Transacción completada con éxito.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void actualizarMoto(Motocicleta moto, EntityManager entityManager) {

    }

    @Override
    public void eliminarMoto(Motocicleta moto, EntityManager entityManager) {

    }

    @Override
    public Motocicleta obtenerMotoId(int id_moto, EntityManager entityManager) {
        try {
            return entityManager.find(Motocicleta.class, id_moto);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ArrayList<Motocicleta> listarMotos(EntityManager entityManager) {
        try {
            return new ArrayList(entityManager.createQuery("FROM Moto", Motocicleta.class).getResultList());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ArrayList<Motocicleta> listarMotosConcesionario(int id_concesionario, EntityManager entityManager) {
        try {
            // Consulta para obtener todas las motos asociadas a un concesionario específico
            String jpql = "SELECT m FROM Moto m JOIN m.concesionario c WHERE c.id = :concesionarioId";
            return new ArrayList (entityManager.createQuery(jpql, Motocicleta.class)
                    .setParameter("concesionarioId", id_concesionario)
                    .getResultList());
        } finally {
            entityManager.close();
        }
    }

}
