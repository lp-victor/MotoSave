/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ACCESODATOS.DAO;

import POJO.DAO.Garaje;
import POJO.DAO.GarajeExcepcion;
import POJO.DAO.Motocicleta;
import POJO.DAO.MotocicletaExcepcion;

/**
 *
 * @author victo
 */
public interface GarajeDAO {
    
    public abstract void altaGaraje(Garaje garaje);

    public abstract void bajaGaraje(int idGaraje);

    public abstract Garaje buscarGaraje(int idGaraje);

    public abstract void modificarGaraje(Motocicleta moto) throws GarajeExcepcion; // Tira excepcion si pones los mismos datos de moto que ya estaban.
    
    public abstract int plazasLibres(Garaje garaje) throws GarajeExcepcion;
}
