/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Comercial;

/**
 *
 * @author victo
 */
public interface AdminstradorDAO {

    public abstract boolean loggearAdmin(EntityManager em, String password, String usuario);


}
