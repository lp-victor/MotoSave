/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACCESODATOS.DAO;

import POJO.DAO.Garaje;
import POJO.DAO.GarajeExcepcion;
import POJO.DAO.Motocicleta;
import POJO.DAO.MotocicletaExcepcion;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class CSVMotocicletaDAO implements MotocicletaDAO {

    @Override
    public void altaMoto(Motocicleta moto) {
        // LeerCSV para comprobar duplicado
        // Si no existe meterla con append(true) 
        
    }

    @Override
    public void bajaMoto(int matricula) {
        // LeerCSV y que seleccione a moto que quiera
        // Guardamos el fichero de nmuevo con todas las motos menos la que ha seleccionado append(false).
        
            
    
    }

    @Override
    public Motocicleta buscarMoto(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificarMoto(Motocicleta moto) throws MotocicletaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cambiarDeGaraje(Motocicleta moto, Garaje garaje) throws MotocicletaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Motocicleta> listarMotocicletas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Motocicleta> listarMotocicletasGaraje(int idGaraje) throws GarajeExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
