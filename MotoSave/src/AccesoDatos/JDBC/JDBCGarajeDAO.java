/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos.JDBC;

import AccesoDatos.GarajeDAO;
import INTERFACES.ConexionBBDD;
import Modelo.Garaje;
import Modelo.GarajeExcepcion;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author victor, Israel, David
 */
public class JDBCGarajeDAO implements GarajeDAO {

    private Connection con;

    public JDBCGarajeDAO() {
        this.con = ConexionBBDD.getCon();
    }

    /**
     * Agrega un nuevo garaje a la base de datos.
     *
     * @param garaje El garaje a ser dado de alta.
     * @return true si se agregó correctamente, false si hubo un error.
     */
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

    /**
     * Elimina un garaje de la base de datos dado su ID.
     *
     * @param idGaraje El ID del garaje a ser eliminado.
     * @return true si se eliminó correctamente, false si hubo un error.
     */
    @Override
    public boolean bajaGaraje(int idGaraje) {
        String delete = "DELETE FROM garajes WHERE idGaraje = '?'";
        try (PreparedStatement pstm = con.prepareStatement(delete)) {
            pstm.setInt(1, idGaraje);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido eliminar el garaje.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Busca y devuelve el ID de un garaje dado su nombre.
     *
     * @param nombreGaraje El nombre del garaje a buscar.
     * @return El ID del garaje si se encontró, de lo contrario devuelve 0.
     */
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

    /**
     * Busca y devuelve un objeto Garaje dado su ID.
     *
     * @param idGaraje El ID del garaje a buscar.
     * @return El objeto Garaje si se encontró, de lo contrario devuelve null.
     */
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

    /**
     * Obtiene la cantidad de plazas libres en un garaje específico.
     *
     * @param garaje El garaje del que se desea conocer las plazas libres.
     * @return La cantidad de plazas libres si se obtiene correctamente, -1 si
     * hay un error de conexión y -2 si no se encuentran datos.
     * @throws GarajeExcepcion si hay una excepción al obtener las plazas
     * libres.
     */
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

    /**
     * Obtiene una lista de todos los garajes existentes en la base de datos.
     *
     * @return Una lista de objetos Garaje si se obtienen correctamente, null si
     * hay un error.
     */
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
            JOptionPane.showMessageDialog(null, "Error: No se han podido recuperar los garajes.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return garajes;
    }

}
