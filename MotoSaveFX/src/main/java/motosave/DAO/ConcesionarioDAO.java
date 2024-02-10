/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Concesionario;

import java.util.List;

/**
 * @author victo
 */
public interface ConcesionarioDAO {

    public abstract void agregarConcesionario(Concesionario conc, EntityManager em);

    public abstract void eliminarConcesionario(EntityManager em);

    public abstract List<String> listarNombreConcesionarios(EntityManager em);

    public abstract Concesionario buscarConcesionario(EntityManager em, int id_concesionario);
}
