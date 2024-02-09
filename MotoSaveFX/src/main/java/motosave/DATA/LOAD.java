/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.DATA;

import motosave.ImplementacionesDAO.ImpAdministradorDAO;
import motosave.ImplementacionesDAO.ImpComercialDAO;
import motosave.ImplementacionesDAO.ImpConcesionarioDAO;
import motosave.Modelos.Administrador;
import motosave.Modelos.Comercial;
import motosave.Modelos.Concesionario;
import jakarta.persistence.EntityManager;
import motosave.Persistencia.miEntityManager;

/**
 *
 * @author victo
 */
public class LOAD {

    // Salario base para poder calcular un plus por ventas.
    // 100€ x 10 motos vendidas o % por nº de ventas.
    // private final double salarioBase = 1400;
    ImpConcesionarioDAO concDAO = new ImpConcesionarioDAO();
    ImpAdministradorDAO adminDAO = new ImpAdministradorDAO();
    ImpComercialDAO comDAO = new ImpComercialDAO();

    public static double beneficio = 1.35;
    
    Concesionario Granada = new Concesionario("Granada");
    Concesionario Madrid = new Concesionario("Madrid");
    Concesionario Barcelona = new Concesionario("Barcelona");

    Administrador administrador = new Administrador("admin", "admin");

    Comercial c1 = new Comercial(Madrid,"juan","1234","12345678A","Juan Alberto", "De los Rios");

    // Creacion de datos para poder
    public LOAD (){
        concDAO.agregarConcesionario(Granada, miEntityManager.getEntityManager());
        concDAO.agregarConcesionario(Madrid, miEntityManager.getEntityManager());
        concDAO.agregarConcesionario(Barcelona, miEntityManager.getEntityManager());

        adminDAO.crearAdministrador(miEntityManager.getEntityManager(), administrador);

        comDAO.anadirComercial(miEntityManager.getEntityManager(),c1);
    }


}
