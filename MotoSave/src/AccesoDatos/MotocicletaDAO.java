package AccesoDatos;

import Modelo.Garaje;
import Modelo.Motocicleta;
import Modelo.MotocicletaExcepcion;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public abstract interface MotocicletaDAO {

    public abstract boolean altaMoto(Motocicleta moto);

    public abstract boolean bajaMoto(String matricula);

    public abstract Motocicleta buscarMoto(String matricula)throws MotocicletaExcepcion; // Tira excepcion si busca una moto que no existe

    public abstract void modificarMoto(Motocicleta moto) throws MotocicletaExcepcion; // Tira excepcion si pones los mismos datos de moto que ya estaban.
    
    public abstract void cambiarDeGaraje(Motocicleta moto, Garaje garaje) throws MotocicletaExcepcion; // Tira excepcio si intentas cambiar la moto al mismo garaje.

    public abstract ArrayList<Motocicleta> listarMotocicletas();
    
    public abstract ArrayList<Motocicleta> listarMotocicletasGaraje(int idGaraje) throws MotocicletaExcepcion; // Tira excepcion si el garaje está vacio.
   
    public abstract void moverMoto(Motocicleta moto, Garaje garaje);
    
    public abstract boolean venderMoto(Motocicleta moto, int idUsuario);
    
    public abstract ArrayList<Motocicleta> listarMotosVendidas(int idUsuario);
    
    public ArrayList<Motocicleta> listarMotosVendidasFecha(int idUsuario, int anio, int mes);

}
