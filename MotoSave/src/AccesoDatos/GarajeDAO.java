/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package AccesoDatos;

import Modelo.Garaje;
import Modelo.GarajeExcepcion;
import Modelo.Motocicleta;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
public interface GarajeDAO {
    
    public abstract boolean altaGaraje(Garaje garaje);

    public abstract boolean bajaGaraje(int idGaraje);

    public abstract Garaje buscarGaraje(int idGaraje);
    
    public abstract int buscarIdGaraje (String nombreGaraje);

    public abstract void modificarGaraje(Garaje garaje) throws GarajeExcepcion; // Tira excepcion si pones los mismos datos de moto que ya estaban.
    
    public abstract int plazasLibres(Garaje garaje) throws GarajeExcepcion;
    
    public abstract ArrayList<Garaje> listarGaraje();
    
    
}
