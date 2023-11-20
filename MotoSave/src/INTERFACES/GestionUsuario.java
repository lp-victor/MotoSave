/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package INTERFACES;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author victor, Israel, David
 */
public class GestionUsuario {

    private Connection con;

    public GestionUsuario() {
        this.con = ConexionBBDD.getCon();
    }

    /**
     * Verifica si el usuario y contraseña ingresados existen en la base de
     * datos. Realiza una consulta a la base de datos para comprobar la
     * existencia del usuario y su contraseña.
     *
     * @param usuario Nombre de usuario a verificar.
     * @param pass Contraseña del usuario a verificar.
     * @return True si el usuario y la contraseña coinciden y existen en la base
     * de datos, de lo contrario, False.
     */
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

    /**
     * Verifica si el usuario tiene privilegios de administrador. Realiza una
     * consulta a la base de datos para comprobar si el usuario es un
     * administrador.
     *
     * @param usuario Nombre de usuario a comprobar.
     * @param pass Contraseña del usuario a comprobar.
     * @return True si el usuario es un administrador, de lo contrario, False.
     */
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
