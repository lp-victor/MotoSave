/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.DATA;

import motosave.ImplementacionesDAO.ImpConcesionarioDAO;
import motosave.Modelos.Concesionario;
import jakarta.persistence.EntityManager;
import motosave.Persistencia.HibernateConfig;

/**
 *
 * @author victo
 */
public class LOAD {

    // Salario base para poder calcular un plus por ventas.
    // 100€ x 10 motos vendidas o % por nº de ventas.
    // private final double salarioBase = 1400;
    ImpConcesionarioDAO concDAO = new ImpConcesionarioDAO();
            
    public static String modelo_n_bastidor = "1VID826MS33A";
    public static double beneficio = 1.35;
    
    Concesionario Granada = new Concesionario("Granada");
    Concesionario Madrid = new Concesionario("Madrid");
    Concesionario Barcelona = new Concesionario("Barcelona");

    // Persistencia
    public static HibernateConfig hibernateConfig;
    public static EntityManager em;
    
    public LOAD (){
    }
    
    
    public LOAD(EntityManager entityManager) {
        
       concDAO.agregarConcesionario(Granada, entityManager);
       concDAO.agregarConcesionario(Madrid, entityManager);
       concDAO.agregarConcesionario(Barcelona, entityManager);
       
    }
    
    public static void cargarEntityManager () {
        hibernateConfig = new HibernateConfig();
        em = hibernateConfig.getEntityManager();
    }

    public static EntityManager getEntityManagerCargado () {
        return em;
    }

    public static void cerrarEntityManager(){
        em.close();
    }

}
