package AccesoDatos.JDBC;

import AccesoDatos.MotocicletaDAO;
import INTERFACES.ConexionBBDD;
import Modelo.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author victor, Israel, David
 */

public class JDBCMotocicletaDAO implements MotocicletaDAO {

    private Connection con;

    public JDBCMotocicletaDAO() {
        this.con = ConexionBBDD.getCon();
    }

    /**
     * Agrega una nueva motocicleta a la base de datos. Este método tiene
     * incluido el rollback
     *
     * @param moto La motocicleta que se va a agregar.
     * @return true si se agrega correctamente, false si hay un error.
     */
    @Override
    public boolean altaMoto(Motocicleta moto) {
        Motocicleta moto_aux = moto;
        String insert = "INSERT INTO motos VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = con.prepareStatement(insert)) {
            con.setAutoCommit(false);
            pstm.setInt(1, moto_aux.getIdGaraje());
            pstm.setString(2, moto_aux.getMatricula());
            pstm.setString(3, moto_aux.getMarca());
            pstm.setString(4, moto_aux.getModelo());
            pstm.setString(5, moto_aux.getColor());
            pstm.setInt(6, moto_aux.getCC());
            pstm.setInt(7, moto_aux.getPrecio());

            pstm.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al realizar el rollback", "Error", JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, "Error al agregar la nueva moto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Elimina una motocicleta de la base de datos basada en su matrícula.
     *
     * @param matricula La matrícula de la motocicleta que se desea eliminar.
     * @return true si se elimina correctamente, false si hay un error.
     */
    @Override
    public boolean bajaMoto(String matricula) {
        String delete = "DELETE FROM motos WHERE matricula = ?";
        try (PreparedStatement pstm = con.prepareStatement(delete)) {
            pstm.setString(1, matricula);
            if (pstm.executeUpdate() == 0) {
                throw new MotocicletaExcepcion("Error: No se ha podido borrar la moto.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido dar de baja a la moto.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (MotocicletaExcepcion e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Busca una motocicleta en la base de datos basada en su matrícula.
     *
     * @param matricula La matrícula de la motocicleta que se desea buscar.
     * @return La motocicleta encontrada si se recupera correctamente, null si
     * hay un error.
     */
    @Override
    public Motocicleta buscarMoto(String matricula) {
        Motocicleta moto = new Motocicleta();
        String query = "SELECT * FROM motos WHERE matricula = ?";
        try (PreparedStatement pstm = con.prepareStatement(query);) {
            pstm.setString(1, matricula);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                moto.setIdGaraje(res.getInt("idGaraje"));
                moto.setMatricula(res.getString("matricula"));
                moto.setMarca(res.getString("marca"));
                moto.setModelo(res.getString("modelo"));
                moto.setColor(res.getString("color"));
                moto.setCC(res.getInt("cc"));
                moto.setPrecio(res.getInt("precio"));
            }
            return moto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido recuperar la moto.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Revisar modificar moto, idGaraje?, rematricular.
    @Override
    public void modificarMoto(Motocicleta moto) throws MotocicletaExcepcion {
        String update = "UPDATE motos SET matricula = ?, marca = ?, modelo = ?, color = ?, cc = ?, precio = ? WHERE matricula = ?";
        try (PreparedStatement pstm = con.prepareStatement(update)) {
            pstm.setInt(1, moto.getIdGaraje());
            pstm.setString(2, moto.getMatricula());
            pstm.setString(3, moto.getMarca());
            pstm.setString(4, moto.getModelo());
            pstm.setString(5, moto.getColor());
            pstm.setInt(6, moto.getCC());
            pstm.setInt(7, moto.getPrecio());
            int res = pstm.executeUpdate();
            if (res == 0) {
                JOptionPane.showMessageDialog(null, "Moto modificada correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: no se ha podido modificar la moto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Cambia la ubicación de una motocicleta a otro garaje en la base de datos.
     *
     * @param moto La motocicleta que se va a mover.
     * @param garaje El garaje al que se va a mover la motocicleta.
     * @throws MotocicletaExcepcion si hay un error al mover la motocicleta de
     * garaje.
     */
    @Override
    public void cambiarDeGaraje(Motocicleta moto, Garaje garaje) throws MotocicletaExcepcion {
        String update = "UPDATE motos SET idGaraje = ? WHERE matricula = ?";
        try (PreparedStatement pstm = con.prepareStatement(update)) {
            pstm.setInt(1, garaje.getIdGaraje());
            pstm.setString(2, moto.getMatricula());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: no se ha podido mover la moto de garaje.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Obtiene una lista de todas las motocicletas almacenadas en la base de
     * datos.
     *
     * @return Una lista de motocicletas si se recuperan correctamente, null si
     * hay un error.
     */
    @Override
    public ArrayList<Motocicleta> listarMotocicletas() {
        ArrayList<Motocicleta> motos = new ArrayList();
        String query = "SELECT * FROM motos";

        try (PreparedStatement pstm = con.prepareStatement(query); ResultSet res = pstm.executeQuery()) {
            while (res.next()) {
                Motocicleta moto = new Motocicleta();
                moto.setIdGaraje(res.getInt("idGaraje"));
                moto.setMatricula(res.getString("matricula"));
                moto.setMarca(res.getString("marca"));
                moto.setModelo(res.getString("modelo"));
                moto.setColor(res.getString("Color"));
                moto.setCC(res.getInt("cc"));
                moto.setPrecio(res.getInt("precio"));
                motos.add(moto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido recuperar los garajes.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return motos;
    }

    /**
     * Obtiene una lista de todas las motocicletas almacenadas en un garaje
     * específico.
     *
     * @param idGaraje El ID del garaje del que se desean recuperar las
     * motocicletas.
     * @return Una lista de motocicletas si se recuperan correctamente, null si
     * hay un error.
     * @throws MotocicletaExcepcion si hay un error al recuperar las
     * motocicletas del garaje.
     */
    @Override
    public ArrayList<Motocicleta> listarMotocicletasGaraje(int idGaraje) throws MotocicletaExcepcion {
        ArrayList<Motocicleta> motos = new ArrayList();
        String query = "SELECT * FROM motos WHERE idGaraje = ?";

        try (PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.setInt(1, idGaraje);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                Motocicleta moto = new Motocicleta();
                moto.setIdGaraje(res.getInt("idGaraje"));
                moto.setMatricula(res.getString("matricula"));
                moto.setMarca(res.getString("marca"));
                moto.setModelo(res.getString("modelo"));
                moto.setColor(res.getString("Color"));
                moto.setCC(res.getInt("cc"));
                moto.setPrecio(res.getInt("precio"));
                motos.add(moto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido recuperar los garajes.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return motos;
    }

    @Override
    public void moverMoto(Motocicleta moto, Garaje garaje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
