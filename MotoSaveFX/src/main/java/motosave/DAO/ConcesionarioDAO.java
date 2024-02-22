/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Concesionario;

/**
 * @author MotoSave
 */
public interface ConcesionarioDAO {

    void agregarConcesionario(Concesionario conc, EntityManager em);

}
