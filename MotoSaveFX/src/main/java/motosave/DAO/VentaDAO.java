/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Venta;

import java.util.List;

/**
 * @author victo
 */
public interface VentaDAO {

    void realizarVenta(EntityManager em, Venta venta);

    List<Venta> listarVentasGeneral(EntityManager em);

    List<Venta> listarVentasComercial(EntityManager em, int idComercial);
}
