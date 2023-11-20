/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factorias;

import AccesoDatos.GarajeDAO;
import AccesoDatos.JDBC.*;
import AccesoDatos.MotocicletaDAO;
import AccesoDatos.Serializar.*;
import Enumerados.metodoPersistencia;
import java.sql.Connection;

/**
 *
 * @author victo
 */
public class FactoriaDAO {

    public static MotocicletaDAO crearMotocicletaDAO(metodoPersistencia tipoPers) {
        switch (tipoPers) {
            case FICHEROS:
                return new SerializarMotocicletaDAO();
            case JDBC:
                return new JDBCMotocicletaDAO();
            default:
                throw new AssertionError();
        }

    }

    public static GarajeDAO crearGarajeDAO(String tipoPers) {
        switch (tipoPers) {
            case "SERIALIZAR_GARAJE":
                return new SerializarGarajeDAO();
            case "JDBC_GARAJE":
                return new JDBCGarajeDAO();
            default:
                throw new AssertionError();
        }
    }

}
