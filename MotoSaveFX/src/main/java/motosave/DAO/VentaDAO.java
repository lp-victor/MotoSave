/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Venta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author victo
 */
public interface VentaDAO {

    public abstract void realizarVenta(EntityManager em, Venta venta);
    public abstract List<Venta> listarVentasGeneral(EntityManager em);
    public abstract List<Venta> listarVentasComercial(EntityManager em, int idComercial);
    public abstract List<Venta> listarVentasComercialUltimoMes(EntityManager em, int idComercial);
    public abstract List<Venta> listarVentasComercialUltimoAnio(EntityManager em, int idComercial);
    public abstract List<Venta> listarVentasComercialUltimaSemana(EntityManager em, int idComercial);
    public abstract List<Venta> listarVentasConcesionario(EntityManager em, int idConcesionario);
}
