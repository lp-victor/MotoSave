package AccesoDatos.JDBC;

import AccesoDatos.MotocicletaDAO;
import INTERFACES.ConexionBBDD;
import static INTERFACES.ConexionBBDD.conectarBBDD;
import static INTERFACES.ConexionBBDD.desconectarBBDD;
import Modelo.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class JDBCMotocicletaDAO extends ConexionBBDD implements MotocicletaDAO {

    @Override
    public boolean altaMoto(Motocicleta moto) {
        Motocicleta moto_aux = moto;
        String insert = "INSERT INTO moto VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = conectarBBDD().prepareStatement(insert)) {

            pstm.setInt(1, moto_aux.getIdGaraje());
            pstm.setString(2, moto_aux.getMatricula());
            pstm.setString(3, moto_aux.getMarca());
            pstm.setString(4, moto_aux.getModelo());
            pstm.setString(5, moto_aux.getColor());
            pstm.setInt(6, moto_aux.getCC());

            pstm.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la nueva moto");
            return false;
        } finally {
            desconectarBBDD();
        }
        return true;
    }

    @Override
    public boolean bajaMoto(String matricula) {
        String delete = "DELETE FROM moto WHERE matricula = '?'";
        try (PreparedStatement pstm = conectarBBDD().prepareStatement(delete)) {
            pstm.setString(1, matricula);
        } catch (SQLException ex) {
            System.out.println("Error: No se ha podido dar de baja a la moto.");
            return false;
        } finally {
            desconectarBBDD();
        }
        return true;
    }

    @Override
    public Motocicleta buscarMoto(String matricula) {
        Motocicleta moto = new Motocicleta();
        String query = "SELECT * FROM moto WHERE matricula = '" + matricula + "'";
        try (Statement stm = conectarBBDD().createStatement();) {
            ResultSet res = stm.executeQuery(query);
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
            System.out.println("Error: No se han podido recuperar la moto.");
            return null;
        } finally {
            desconectarBBDD();
        }
    }

    @Override
    public void modificarMoto(Motocicleta moto) throws MotocicletaExcepcion {
        String update = "UPDATE moto SET idGaraje = ?, matricula = ?, marca = ?, modelo = ?, color = ?, cc = ? WHERE matricula = ?";
        try (PreparedStatement pstm = conectarBBDD().prepareStatement(update)) {
            pstm.setInt(1, moto.getIdGaraje());
            pstm.setString(2, moto.getMatricula());
            pstm.setString(3, moto.getMarca());
            pstm.setString(4, moto.getModelo());
            pstm.setString(5, moto.getColor());
            pstm.setInt(6, moto.getCC());
            pstm.setString(7, moto.getMatricula());
        } catch (SQLException e) {
            System.out.println("Error: no se ha podido modificar la moto.");
        } finally {
            desconectarBBDD();
        }

    }

    @Override
    public void cambiarDeGaraje(Motocicleta moto, Garaje garaje) throws MotocicletaExcepcion {
        String update = "UPDATE moto SET idGaraje = ? WHERE matricula = ?";
        try (PreparedStatement pstm = conectarBBDD().prepareStatement(update)) {
            pstm.setInt(1, garaje.getIdGaraje());
            pstm.setString(2, moto.getMatricula());
        } catch (SQLException e) {
            System.out.println("Error: no se ha podido mover la moto de garaje.");
        } finally {
            desconectarBBDD();
        }
    }

    @Override
    public ArrayList<Motocicleta> listarMotocicletas() {
        ArrayList<Motocicleta> motos = new ArrayList();
        String query = "SELECT * FROM motos";

        try (PreparedStatement pstm = conectarBBDD().prepareStatement(query); ResultSet res = pstm.executeQuery()) {
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
            System.out.println("Error: No se han podido recuperar los garajes.");
            return null;
        } finally {
            desconectarBBDD();
        }
        return motos;
    }

    @Override
    public ArrayList<Motocicleta> listarMotocicletasGaraje(int idGaraje) throws MotocicletaExcepcion {
        ArrayList<Motocicleta> motos = new ArrayList();
        String query = "SELECT * FROM motos WHERE idGaraje = ?";

        try (PreparedStatement pstm = conectarBBDD().prepareStatement(query)) {
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
            System.out.println("Error: No se han podido recuperar los garajes.");
            return null;
        } finally {
            desconectarBBDD();
        }
        return motos;
    }

}
