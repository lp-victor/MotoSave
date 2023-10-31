package AccesoDatos;

import Modelo.Garaje;
import Modelo.GarajeExcepcion;
import Modelo.Motocicleta;
import Modelo.MotocicletaExcepcion;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author USER
 */
public abstract interface MotocicletaDAO {

    public abstract void altaMoto(Motocicleta moto);

    public abstract void bajaMoto(int matricula);

    public abstract Motocicleta buscarMoto(int matricula);

    public abstract void modificarMoto(Motocicleta moto) throws MotocicletaExcepcion; // Tira excepcion si pones los mismos datos de moto que ya estaban.
    
    public abstract void cambiarDeGaraje(Motocicleta moto, Garaje garaje) throws MotocicletaExcepcion; // Tira excepcio si intentas cambiar la moto al mismo garaje.

    public abstract ArrayList<Motocicleta> listarMotocicletas();
    
    public abstract ArrayList<Motocicleta> listarMotocicletasGaraje(int idGaraje) throws GarajeExcepcion; // Tira excepcion si el garaje está vacio.
   
    
    

}
