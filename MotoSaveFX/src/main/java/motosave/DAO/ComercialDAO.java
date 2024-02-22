/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Comercial;

import java.util.ArrayList;

/**
 * @author MotoSave
 */
public interface ComercialDAO {

    boolean loggearComercial(EntityManager em, String contraseña, String usuario);

    void anadirComercial(EntityManager em, Comercial comercial);

    boolean buscarNIFComercial(EntityManager em, String usuario);

    boolean buscarUsuarioComercial(EntityManager em, String NIF);

    void eliminarComercial(EntityManager em, Comercial comercial);

    ArrayList<Comercial> listarComerciales(EntityManager em);

}
