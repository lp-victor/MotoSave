package ACCESODATOS.DAO;

import INTERFACES.ConexionBBDD;
import POJO.DAO.Garaje;
import POJO.DAO.GarajeExcepcion;
import POJO.DAO.Motocicleta;
import POJO.DAO.MotocicletaExcepcion;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCMotocicletaDAO implements MotocicletaDAO {
          
    
    private static Connection con = null;
    private static final String CONEXION = "jdbc:mysql://localhost:3306/";
    private static final String BBDD = "MotoSaveBBDD";
    private static final String USUARIO = "root";
    private static final String PASS = null;

    public static boolean conectarBBDD() {
        try {
            con = DriverManager.getConnection(CONEXION + BBDD, USUARIO, PASS);
        } catch (SQLException e) {
            System.out.println("Error:  " + e.toString());
            return false;
        }
        return true;
    }
    
    public static boolean desconectarBBDD() {
        try {
            if (con != null) {
                con.close();
            }            
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion.");
            System.out.println(ex.toString());
            return false;
        }
        return true;
    }

    @Override
    public void altaMoto(Motocicleta moto) {
          
    }

    @Override
    public void bajaMoto(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
