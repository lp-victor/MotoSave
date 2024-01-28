/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.Motosave.motosave;

import DATA.LOAD;
import ImplementacionesDAO.ImpConcesionarioDAO;
import ImplementacionesDAO.ImpMotocicletaDAO;
import Modelos.Comercial;
import Modelos.Concesionario;
import Modelos.Motocicleta;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import persistencia.HibernateConfig;

public class Motosave {

    public static void main(String[] args) {
        //EL arraylist podemos quitarlo de los modelos ya que las tabalas hacen esa funcion de 
        //relacion con las motos y los conesionarios mediante la id.

//        HibernateConfig hibernateConfig = new HibernateConfig();
//
//        EntityManager em = hibernateConfig.getEntityManager();
//
//        //LOAD load_data = new LOAD(em);
//        ImpMotocicletaDAO motoDAO = new ImpMotocicletaDAO();
//        List<Comercial> come=new ArrayList();
//        List<Motocicleta>motos=new ArrayList();
//
//        Comercial com1 =new Comercial( "comercial1", "1234", "123456", "Alvaro", "Ruiz");
//        Motocicleta moto1 = new Motocicleta( "Honda", "CBR", "Rojo", 1250, 7000);
////        come.add(com1);
////        motos.add(moto1);
//        Concesionario c1 = new Concesionario("Granada" );
//        
//        ImpConcesionarioDAO conceDao = new ImpConcesionarioDAO();
//        conceDao.agregarConcesionario(c1, em);
//        motoDAO.guardarMoto(moto1, em);
//
//        hibernateConfig.closeConnection();
        HibernateConfig hibernateConfig = new HibernateConfig();
        EntityManager em = hibernateConfig.getEntityManager();


        ImpConcesionarioDAO conceDao = new ImpConcesionarioDAO();
        ImpMotocicletaDAO motoDAO = new ImpMotocicletaDAO();

        Motocicleta m1 = null;
        ArrayList<Motocicleta> motos = null;

        motos = motoDAO.listarMotosConcesionario(1,em);

        for (Motocicleta moto : motos) {
            System.out.println(moto.toString());
        }

    }
}

//            // Crear concesionarios
//            Concesionario c1 = new Concesionario("Madrid");
//            Concesionario c2 = new Concesionario("Sevilla");
//
//            Motocicleta moto1 = new Motocicleta("Yamaha", "T-Max", "Negro", 660, 9850);
//            Motocicleta moto2 = new Motocicleta("Honda", "CBR", "Rojo", 1200, 11000);
//
//            // Guardar el concesionario y la motocicleta en la base de datos
//            conceDao.agregarConcesionario(c1, em);
//            conceDao.agregarConcesionario(c2, em);
//
//            c1.agregarMotocicleta(moto1, em);
//            c2.agregarMotocicleta(moto2, em);
//
//            //he metido los metodos de guardar motos en la bse de  datos en un metodo en concesioanrio para que las guarde.
//            motoDAO.guardarMoto(moto1, em);
//            motoDAO.guardarMoto(moto2, em);
//
//            motos = motoDAO.listarMotos(em);
//
//            m1 = motoDAO.obtenerMotoId(1, em);
//            transaction.commit();
//            System.out.println("Transacción completada con éxito.");
