/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import motosave.DAO.VentaDAO;
import motosave.Modelos.Venta;

import java.util.List;

/**
 * @author MotoSave
 */
public class ImpVentaDAO implements VentaDAO {

    /**
     * Realiza una venta de motocicleta, persistiendo la venta en la base de datos.
     *
     * @param em    El EntityManager utilizado para realizar la operación.
     * @param venta La venta de motocicleta que se va a realizar y persistir.
     */
    @Override
    public void realizarVenta(EntityManager em, Venta venta) {
        try {
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todas las ventas de motocicletas realizadas.
     *
     * @param em El EntityManager utilizado para realizar la operación.
     * @return Una lista de todas las ventas de motocicletas realizadas.
     */
    @Override
    public List<Venta> listarVentasGeneral(EntityManager em) {
        try {
            List<Venta> ventas = em.createQuery("FROM Venta", Venta.class).getResultList();
            return ventas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lista todas las ventas de motocicletas realizadas por un comercial específico.
     *
     * @param em         El EntityManager utilizado para realizar la operación.
     * @param idComercial El ID del comercial del cual se desean obtener las ventas.
     * @return Una lista de todas las ventas de motocicletas realizadas por el comercial especificado.
     */
    @Override
    public List<Venta> listarVentasComercial(EntityManager em, int idComercial) {
        try {
            String jpql = "SELECT v FROM Venta v JOIN v.comercial c WHERE c.id_comercial = :id_comercial";
            List<Venta> ventas = em.createQuery(jpql, Venta.class)
                    .setParameter("id_comercial", idComercial)
                    .getResultList();
            return ventas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}