/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import motosave.DAO.VentaDAO;
import motosave.Modelos.Motocicleta;
import motosave.Modelos.Venta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementación de la interfaz VentaDAO.
 * Proporciona un método para realizar la venta de una motocicleta.
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

    @Override
    public List<Venta> listarVentasGeneral(EntityManager em) {
        try {
            List<Venta> ventas = em.createQuery("SELECT m FROM venta m", Venta.class).getResultList();
            return ventas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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

    //PROBAR ESTE METODO Y VER SI SACA LAS VENTAS DEL ULTIMO MES
    @Override
    public List<Venta> listarVentasComercialUltimoMes(EntityManager em, int idComercial) {
        try {
            // Obtener el mes actual
            Calendar calendar = new GregorianCalendar();
            int mesActual = calendar.get(Calendar.MONTH) + 1;  // Se suma 1 porque en Calendar los meses van de 0 a 11

            String jpql = "SELECT v FROM Venta v JOIN v.comercial c WHERE c.idComercial = :idComercial " +
                    "AND EXTRACT(MONTH FROM v.fechaVenta) = :mesActual";

            List<Venta> ventas = em.createQuery(jpql, Venta.class)
                    .setParameter("idComercial", idComercial)
                    .setParameter("mesActual", mesActual)
                    .getResultList();

            return ventas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Venta> listarVentasComercialUltimoAnio(EntityManager em, int idComercial) {
        return null;
    }

    @Override
    public List<Venta> listarVentasComercialUltimaSemana(EntityManager em, int idComercial) {
        return null;
    }

    @Override
    public List<Venta> listarVentasConcesionario(EntityManager em, int idConcesionario) {
        try {
            String jpql = "SELECT v FROM venta v WHERE v.motocicleta.concesionario.id_concesionario = :id_concesionario";
            List<Venta> ventas = (em.createQuery(jpql, Venta.class)
                    .setParameter("id_concesionario", idConcesionario)
                    .getResultList());
            return ventas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}