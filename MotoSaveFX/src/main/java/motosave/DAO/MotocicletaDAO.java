/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package motosave.DAO;

import jakarta.persistence.EntityManager;
import motosave.Modelos.Motocicleta;

import java.util.List;

/**
 * @author victo
 */
public interface MotocicletaDAO {

    void guardarMoto(Motocicleta moto, EntityManager em);

    void actualizarMoto(Motocicleta moto, EntityManager em);

    void eliminarMoto(Motocicleta moto, EntityManager em);

    List<Motocicleta> listarMotos(EntityManager em);

    List<Motocicleta> listarMotosConcesionario(int id_concesionario, EntityManager em);

}
