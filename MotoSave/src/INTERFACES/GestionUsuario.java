/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package INTERFACES;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author victo
 */
public class GestionUsuario {

    private Connection con;

    public GestionUsuario() {
        this.con =ConexionBBDD.getCon();
    }

    public boolean verificarUsuario(String usuario, String pass) {
        String query = "SELECT * FROM usuarios WHERE nombre= ? AND password = ?";
        try (PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.setString(1, usuario);
            pstm.setString(2, pass);
            ResultSet res = pstm.executeQuery();

            return res.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al comprobar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    public boolean admin(String usuario, String pass) {
    String query = "SELECT admin FROM usuarios WHERE nombre= ? AND password = ?";
    try (PreparedStatement pstm = con.prepareStatement(query)) {
        pstm.setString(1, usuario);
        pstm.setString(2, pass);
        
        ResultSet res = pstm.executeQuery();
        
        if (res.next()) {
            boolean admin = res.getBoolean("admin");
            return admin;
        } else {
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al comprobar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
    }
    return false;
}

}
