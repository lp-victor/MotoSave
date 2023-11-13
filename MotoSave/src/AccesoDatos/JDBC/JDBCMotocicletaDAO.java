package AccesoDatos.JDBC;

import AccesoDatos.MotocicletaDAO;
import INTERFACES.ConexionBBDD;
import Modelo.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class JDBCMotocicletaDAO implements MotocicletaDAO {

    private Connection con;

    public JDBCMotocicletaDAO(Connection con_e) {
        this.con = con_e;
    }

    public JDBCMotocicletaDAO() {
    }

    @Override
    public boolean altaMoto(Motocicleta moto) {
        Motocicleta moto_aux = moto;
        String insert = "INSERT INTO moto VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = con.prepareStatement(insert)) {

            pstm.setInt(1, moto_aux.getIdGaraje());
            pstm.setString(2, moto_aux.getMatricula());
            pstm.setString(3, moto_aux.getMarca());
            pstm.setString(4, moto_aux.getModelo());
            pstm.setString(5, moto_aux.getColor());
            pstm.setInt(6, moto_aux.getCC());

            pstm.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la nueva moto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
//        finally {
//            desconectarBBDD();
//        }
        return true;
    }

    @Override
    public boolean bajaMoto(String matricula) {
        String delete = "DELETE FROM moto WHERE matricula = ?";
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

    @Override
    public Motocicleta buscarMoto(String matricula) {
        Motocicleta moto = new Motocicleta();
        String query = "SELECT * FROM moto WHERE matricula = ?";
        try (PreparedStatement pstm = con.prepareStatement(query);) {
            pstm.setString(1, matricula);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                moto.setIdGaraje(res.getInt("idGaraje"));
                moto.setMatricula(res.getString("matricula"));
                moto.setMarca(res.getString("marca"));
                moto.setModelo(res.getString("modelo"));
                moto.setColor(res.getString("Color"));
                moto.setCC(res.getInt("cc"));
            }
            return moto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido recuperar la moto.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
//        finally {
//            desconectarBBDD();
//        }
    }

    @Override
    public void modificarMoto(Motocicleta moto) throws MotocicletaExcepcion {
        String update = "UPDATE moto SET idGaraje = ?, matricula = ?, marca = ?, modelo = ?, color = ?, cc = ? WHERE matricula = ?";
        try (PreparedStatement pstm = con.prepareStatement(update)) {
            pstm.setInt(1, moto.getIdGaraje());
            pstm.setString(2, moto.getMatricula());
            pstm.setString(3, moto.getMarca());
            pstm.setString(4, moto.getModelo());
            pstm.setString(5, moto.getColor());
            pstm.setInt(6, moto.getCC());
            pstm.setString(7, moto.getMatricula());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: no se ha podido modificar la moto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
//        finally {
//            desconectarBBDD();
//        }

    }

    @Override
    public void cambiarDeGaraje(Motocicleta moto, Garaje garaje) throws MotocicletaExcepcion {
        String update = "UPDATE moto SET idGaraje = ? WHERE matricula = ?";
        try (PreparedStatement pstm = con.prepareStatement(update)) {
            pstm.setInt(1, garaje.getIdGaraje());
            pstm.setString(2, moto.getMatricula());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: no se ha podido mover la moto de garaje.", "Error", JOptionPane.ERROR_MESSAGE);
        }
//        finally {
//            desconectarBBDD();
//        }
    }

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
                motos.add(moto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido recuperar los garajes.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
//        finally {
//            desconectarBBDD();
//        }
        return motos;
    }

    @Override
    public ArrayList<Motocicleta> listarMotocicletasGaraje(int idGaraje) throws MotocicletaExcepcion {
        ArrayList<Motocicleta> motos = new ArrayList();
        String query = "SELECT * FROM moto WHERE idGaraje = ?";

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
                motos.add(moto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido recuperar los garajes.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
//        finally {
//            desconectarBBDD();
//        }
        return motos;
    }

}
