/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Comercial;

import java.util.ArrayList;

/**
 *
 * @author victo
 */
public interface ComercialDAO {

    public abstract boolean loggearComercial (EntityManager em, String contraseña, String usuario);

    public abstract void anadirComercial (EntityManager em, Comercial comercial);

    public abstract void modificarComercial (EntityManager em, Comercial comercial);

    public abstract void eliminarComercial (EntityManager em, Comercial comercial);

    public abstract ArrayList<Comercial> listarComerciales (EntityManager em);

}
