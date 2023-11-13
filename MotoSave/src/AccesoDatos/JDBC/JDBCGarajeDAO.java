/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos.JDBC;

import AccesoDatos.GarajeDAO;
import INTERFACES.ConexionBBDD;
import Modelo.Garaje;
import Modelo.GarajeExcepcion;
import Modelo.Motocicleta;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author victo
 */
public class JDBCGarajeDAO implements GarajeDAO {

    private Connection con;

    public JDBCGarajeDAO(Connection con_e) {
        this.con = con_e;
    }

    public JDBCGarajeDAO() {
    }

    @Override
    public boolean altaGaraje(Garaje garaje) {
        String insert = "INSERT INTO garajes('sucursal') VALUES (?)";
        try (PreparedStatement pstm = con.prepareStatement(insert)) {
            pstm.setString(1, garaje.getSucursal());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido agregar el garaje.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public boolean bajaGaraje(int idGaraje) {
        String delete = "DELETE FROM garajes WHERE idGaraje = '?'";
        try (PreparedStatement pstm = con.prepareStatement(delete)) {
            pstm.setInt(1, idGaraje);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido eliminar el garaje.","Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public int buscarIdGaraje(String nombreGaraje) {
        String query = "SELECT idGaraje FROM garajes WHERE sucursal = ?";
        try (PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.setString(1, nombreGaraje);
            ResultSet res = pstm.executeQuery();
            if (res.next()) {
                return res.getInt("idGaraje");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido recuperar el garaje (nombre).", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return 0; // Evitar falso positivo
    }

    @Override
    public Garaje buscarGaraje(int idGaraje) {
        Garaje garaje = new Garaje();
        String query = "SELECT * FROM garajes WHERE idGaraje = ?";

        try (PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.setInt(1, idGaraje);
            ResultSet res = pstm.executeQuery();
            if (res.next()) {
                garaje.setIdGaraje(res.getInt("idGaraje"));
                garaje.setSucursal(res.getString("sucursal"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido recupera los garaje (id).", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return garaje;
    }

    @Override
    public void modificarGaraje(Garaje garaje) throws GarajeExcepcion {
        //Falta
    }

    @Override
    public int plazasLibres(Garaje garaje) throws GarajeExcepcion {
        String query = "SELECT plazas FROM garajes WHERE idGaraje = ?";

        try (PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.setInt(1, garaje.getIdGaraje());
            ResultSet res = pstm.executeQuery();
            if (res.next()) {
                return res.getInt("plazas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido recuperar las plazas del garaje.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        return -2;
    }

    @Override
    public ArrayList<Garaje> listarGaraje() {
        ArrayList<Garaje> garajes = new ArrayList();
        String query = "SELECT * FROM garajes";

        try (PreparedStatement pstm = con.prepareStatement(query); ResultSet res = pstm.executeQuery()) {
            while (res.next()) {
                Garaje aux = new Garaje();
                aux.setIdGaraje(res.getInt("idGaraje"));
                aux.setSucursal(res.getString("sucursal"));
                garajes.add(aux);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error: No se han podido recuperar los garajes.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return garajes;
    }

}
