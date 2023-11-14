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
 *
 * @author victo
 */
public class ConexionBBDD {

    private Connection con;
    private final String CONEXION = "jdbc:mysql://localhost:3306/";
    private final String BBDD = "motosavebbdd";
    private final String USUARIO = "root";
    private final String PASS = null;

    public ConexionBBDD() {
        conectarBBDD();
    }

    public void conectarBBDD() {
        try {
            con = DriverManager.getConnection(CONEXION + BBDD, USUARIO, PASS);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean desconectarBBDD() {
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

    public Connection getCon() {
        return con;
    }
}
