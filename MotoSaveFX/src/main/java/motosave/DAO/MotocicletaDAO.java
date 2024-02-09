/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import motosave.Modelos.Motocicleta;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

/**
 *
 * @author victo
 */
public interface MotocicletaDAO {

    public abstract void guardarMoto(Motocicleta moto, EntityManager em);

    public abstract void actualizarMoto(Motocicleta moto, EntityManager em);

    public abstract void eliminarMoto(Motocicleta moto, EntityManager em);

    public abstract Motocicleta obtenerMotoPorId(int id_moto, EntityManager em);

    public abstract ArrayList<Motocicleta> listarMotos(EntityManager em);

    public abstract ArrayList<Motocicleta> listarMotosConcesionario(int id_concesionario, EntityManager em);

}
