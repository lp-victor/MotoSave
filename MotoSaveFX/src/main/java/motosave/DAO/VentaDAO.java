/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Venta;

/**
 * @author victo
 */
public interface VentaDAO {

    public abstract void realizarVenta(EntityManager em, Venta venta);

}
