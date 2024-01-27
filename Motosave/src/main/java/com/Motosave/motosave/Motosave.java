/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.Motosave.motosave;

import DATA.LOAD;
import ImplementacionesDAO.ImpMotocicletaDAO;
import Modelos.Comercial;
import Modelos.Concesionario;
import Modelos.Motocicleta;
import java.util.ArrayList;
import persistencia.HibernateConfig;

/**
 *
 * @author victo
 */
public class Motosave {

    public static void main(String[] args) {
        HibernateConfig hibernateConfig = new HibernateConfig();
        
        LOAD load_data = new LOAD(hibernateConfig.getEntityManager());
        
        ImpMotocicletaDAO motoDAO = new ImpMotocicletaDAO();
        Concesionario c1 = new Concesionario("Granada", new ArrayList<Comercial>(), new ArrayList<Motocicleta>());
        Motocicleta moto1 = new Motocicleta(c1, "Honda", "CBR", "Rojo", 1250, 7000);
        
        motoDAO.guardarMoto(moto1, hibernateConfig.getEntityManager());
        
        hibernateConfig.closeConnection();
    }
}
