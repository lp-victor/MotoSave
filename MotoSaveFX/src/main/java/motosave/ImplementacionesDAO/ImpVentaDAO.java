/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import motosave.DAO.VentaDAO;
import motosave.Modelos.Venta;

/**
 *
 * @author victo
 */
public class ImpVentaDAO implements VentaDAO {
    @Override
    public void realizarVenta(EntityManager em, Venta venta) {
        try {
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
