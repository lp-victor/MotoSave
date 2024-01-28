/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionesDAO;

import DAO.MotocicletaDAO;
import Modelos.Concesionario;
import Modelos.Motocicleta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
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

            // Obtener el concesionario de la motocicleta
            Concesionario concesionarioPersistente = moto.getConcesionario();

            // Persistir la motocicleta y actualizar el concesionario
            entityManager.persist(concesionarioPersistente); // Se persiste el concesionario si aún no está persistido
            entityManager.persist(moto);
            entityManager.getTransaction().commit();
            System.out.println("Transacción completada con éxito.");

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
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
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Motocicleta motocicleta = entityManager.find(Motocicleta.class, id_moto);
            transaction.commit();
            return motocicleta;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public ArrayList<Motocicleta> listarMotos(EntityManager entityManager) {
        try {
            return new ArrayList<>(entityManager.createQuery("SELECT m FROM Motocicleta m", Motocicleta.class).getResultList());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ArrayList<Motocicleta> listarMotosConcesionario(int id_concesionario, EntityManager entityManager) {
        try {
            // Consulta para obtener todas las motos asociadas a un concesionario específico
            String jpql = "SELECT m FROM Motocicleta m JOIN m.concesionario c WHERE c.id = :id_concesionario";
            return new ArrayList(entityManager.createQuery(jpql, Motocicleta.class)
                    .setParameter("id_concesionario", id_concesionario)
                    .getResultList());
        } finally {
            entityManager.close();
        }
    }

}
