/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factorias;

import AccesoDatos.JDBC.*;
import AccesoDatos.Serializar.*;
import java.sql.Connection;

/**
 *
 * @author victo
 */
public class FactoriaDAO {

    public static Object crearObjetoDAO(String tipoPers, Connection con_e) {
        switch (tipoPers) {
            case "JDBC_GARAJE":
                return new JDBCGarajeDAO(con_e);
            case "JDBC_MOTOCICLETA":
                return new JDBCMotocicletaDAO(con_e);
            default:
                throw new AssertionError();
        }
    }

    public static Object crearObjetoDAO(String tipoPers) {
        switch (tipoPers) {
            case "SERIALIZAR_GARAJE":
                return new SerializarGarajeDAO();

            case "SERIALIZAR_MOTOCICLETA":
                return new SerializarMotocicletaDAO();
            default:
                throw new AssertionError();
        }
    }

}
