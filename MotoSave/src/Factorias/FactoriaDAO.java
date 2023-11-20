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

/**
 * @author victor, Israel, David
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

    public static GarajeDAO crearGarajeDAO(metodoPersistencia tipoPers) {
        switch (tipoPers) {
            case FICHEROS:
                return new SerializarGarajeDAO();
            case JDBC:
                return new JDBCGarajeDAO();
            default:
                throw new AssertionError();
        }
    }

}
