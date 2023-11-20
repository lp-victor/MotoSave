/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INTERFACES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author victor, Israel, David
 */
public class ConexionBBDD {

    private static Connection con;
    private static final String CONEXION = "jdbc:mysql://localhost:3306/";
    private static final String BBDD = "motosavebbdd";
    private static final String USUARIO = "root";
    private static final String PASS = null;

    

    public static void conectarBBDD() {
        try {
            con = DriverManager.getConnection(CONEXION + BBDD, USUARIO, PASS);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    public static Connection getCon() {
        return con;
    }
}
