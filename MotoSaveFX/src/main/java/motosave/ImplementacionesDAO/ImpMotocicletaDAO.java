/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import motosave.DAO.MotocicletaDAO;
import motosave.Modelos.Motocicleta;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz MotocicletaDAO.
 * Proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * en la base de datos para la entidad Motocicleta.
 */
public class ImpMotocicletaDAO implements MotocicletaDAO {

    /**
     * Guarda una motocicleta en la base de datos.
     *
     * @param moto          La motocicleta a guardar.
     * @param entityManager El EntityManager utilizado para realizar la operación.
     */
    @Override
    public void guardarMoto(Motocicleta moto, EntityManager entityManager) { // Crear motocicleta (para el admin va a ser comprar moto)
        try {
            entityManager.getTransaction().begin();
            // Persistir la motocicleta y actualizar el concesionario
            entityManager.persist(moto);
            entityManager.getTransaction().commit();
            System.out.println("Transacción completada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la moto que entra por parametro en la base de datos,
     * @param moto
     * @param entityManager
     */
    @Override
    public void actualizarMoto(Motocicleta moto, EntityManager entityManager) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.merge(moto);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina una motocicleta de la base de datos.
     *
     * @param moto          La motocicleta a eliminar.
     * @param entityManager El EntityManager utilizado para realizar la operación.
     */
    @Override
    public void eliminarMoto(Motocicleta moto, EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // Asegura que la instancia de la motocicleta  este administrada y asociada.
            moto = entityManager.merge(moto);
            entityManager.remove(moto);
            transaction.commit();
            System.out.println("Motocicleta eliminada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene una motocicleta de la base de datos por su ID.
     *
     * @param id_moto       El ID de la motocicleta a obtener.
     * @param entityManager El EntityManager utilizado para realizar la operación.
     * @return La motocicleta encontrada o null si no se encuentra.
     */
    @Override
    public Motocicleta obtenerMotoPorId(int id_moto, EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Motocicleta motocicleta = entityManager.find(Motocicleta.class, id_moto);
            transaction.commit();
            return motocicleta;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Lista todas las motocicletas almacenadas en la base de datos.
     *
     * @param entityManager El EntityManager utilizado para realizar la operación.
     * @return Una lista de todas las motocicletas almacenadas.
     */
    @Override
    public List<Motocicleta> listarMotos(EntityManager entityManager) {
        try {
            String jpql = "SELECT m FROM Motocicleta m WHERE m.id_moto NOT IN (SELECT v.moto.id_moto FROM Venta v)";
            List<Motocicleta> motos = entityManager.createQuery(jpql, Motocicleta.class).getResultList();
            return motos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lista todas las motocicletas asociadas a un concesionario específico.
     *
     * @param id_concesionario_e El ID del concesionario del cual se desean obtener las motocicletas.
     * @param entityManager      El EntityManager utilizado para realizar la operación.
     * @return Una lista de motocicletas asociadas al concesionario especificado.
     */
    @Override
    public List<Motocicleta> listarMotosConcesionario(int id_concesionario_e, EntityManager entityManager) {
        try {
            // Consulta para obtener todas las motos asociadas a un concesionario específico
            String jpql = "SELECT m FROM Motocicleta m WHERE m.concesionario.id_concesionario = :id_concesionario AND m.id_moto NOT IN (SELECT v.moto.id_moto FROM Venta v)";
            List<Motocicleta> motos = entityManager.createQuery(jpql, Motocicleta.class)
                    .setParameter("id_concesionario", id_concesionario_e)
                    .getResultList();
            return motos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        /*
        try {
        // Consulta para obtener todas las motos asociadas a un concesionario específico
            String jpql = "SELECT m FROM Motocicleta m JOIN m.concesionario c WHERE c.id = :id_concesionario";

           // Utilizacion de 'TypedQuery' para obtener un tipo de consulta específico. Esto proporciona más seguridad
           // en tiempo de compilación y ayuda a evitar posibles advertencias de tipo.
            TypedQuery<Motocicleta> query = entityManager.createQuery(jpql, Motocicleta.class);
            query.setParameter("id_concesionario", id_concesionario);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
    }

    @Override
    public List<Motocicleta> listarMotocicletasVendidas(EntityManager em) {
        try {
            String jpql = "SELECT v.moto FROM Venta v";
            List<Motocicleta> motosVendidas = em.createQuery(jpql, Motocicleta.class).getResultList();
            return motosVendidas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
