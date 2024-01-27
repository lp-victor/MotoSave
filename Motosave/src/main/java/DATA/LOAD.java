/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DATA;

import ImplementacionesDAO.ImpConcesionarioDAO;
import Modelos.Comercial;
import Modelos.Concesionario;
import Modelos.Motocicleta;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class LOAD {
    
    ImpConcesionarioDAO concDAO = new ImpConcesionarioDAO();
            
    public static String modelo_n_bastidor = "1VID826MS33A";
    public static double beneficio = 1.35;
    
    Concesionario Granada = new Concesionario("Granada", new ArrayList<Comercial>(), new ArrayList<Motocicleta>());
    Concesionario Madrid = new Concesionario("Madrid", new ArrayList<Comercial>(), new ArrayList<Motocicleta>());
    Concesionario Barcelona = new Concesionario("Barcelona", new ArrayList<Comercial>(), new ArrayList<Motocicleta>());
    
    public LOAD(EntityManager entityManager) {
       concDAO.agregarConcesionario(Granada, entityManager);
       concDAO.agregarConcesionario(Madrid, entityManager);
       concDAO.agregarConcesionario(Barcelona, entityManager);
       
    }

    
}
