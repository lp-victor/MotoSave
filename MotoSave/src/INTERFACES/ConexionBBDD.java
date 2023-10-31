/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INTERFACES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author victo
 */
public class ConexionBBDD {
    
    static Connection con = null;
    static final String CONEXION = "jdbc:mysql://localhost:3306/";
    static final String BBDD = "motosavebbdd";
    static final String USUARIO = "root";
    static final String PASS = null;

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
    
}
