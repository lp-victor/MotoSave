/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import motosave.Modelos.Concesionario;
import jakarta.persistence.EntityManager;

/**
 *
 * @author victo
 */
public interface ConcesionarioDAO {
    
    public abstract void agregarConcesionario(Concesionario conc, EntityManager em);
    public abstract void eliminarConcesionario(EntityManager em);
    
}
