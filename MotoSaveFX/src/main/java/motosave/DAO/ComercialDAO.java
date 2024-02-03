/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import jakarta.persistence.EntityManager;

/**
 *
 * @author victo
 */
public interface ComercialDAO {

    public abstract boolean loggearComercial (EntityManager em, String contraseña, String usuario);

}
