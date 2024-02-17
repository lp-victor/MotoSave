/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.DATA;

import motosave.EnumeradosMoto.ModelosDAISVI;
import motosave.EnumeradosMoto.ModelosHONDA;
import motosave.EnumeradosMoto.ModelosYAMAHA;
import motosave.Factory.FactoryMoto;
import motosave.ImplementacionesDAO.*;
import motosave.Modelos.*;
import motosave.Persistencia.miEntityManager;

import java.util.ArrayList;

import static motosave.EnumeradosMoto.Colores.*;
import static motosave.EnumeradosMoto.Marcas.*;

/**
 * @author victo
 */
public class LOAD {

    public static double beneficio = 1.35;
    ImpConcesionarioDAO concDAO = new ImpConcesionarioDAO();
    ImpAdministradorDAO adminDAO = new ImpAdministradorDAO();
    ImpComercialDAO comDAO = new ImpComercialDAO();
    ImpMotocicletaDAO motoDAO = new ImpMotocicletaDAO();
    ImpClienteDAO clienteDAO = new ImpClienteDAO();

    // =============== CARGA DE LA BASE DE DATOS ===============

    // Concesionarios
    Concesionario Granada = new Concesionario("Granada");
    Concesionario Madrid = new Concesionario("Madrid");
    Concesionario Barcelona = new Concesionario("Barcelona");

    // Usuarios
    Administrador administrador = new Administrador("admin", "admin");
    Comercial c1 = new Comercial(Madrid, "juan", "1234", "12345678A", "Juan Alberto", "De los Rios");

    // Clientes

    Cliente cliente1 = new Cliente("MotoTodo","mototodo@yahoo.com",916370127,"C/ Mar oceana 10, Las Rozas");
    Cliente cliente2 = new Cliente("SpeedMotos","speedmotos@gmail.com",914567897,"Avenida Principe de Asturias 17, Fuencarral");
    Cliente cliente3 = new Cliente("Moto Racing","motoracing@gmail.com",919456785,"C/ Mar de plata 11, Poligono Marconi, Parla");
    Cliente cliente4 = new Cliente("Moto Ocasion","moto.ocasion@hotmail.com",913456784,"C/ Real 34, Nave 6, Colmenar Viejo");


    // Motos
    FactoryMoto factoryMoto = new FactoryMoto();
    ArrayList<Motocicleta> motos = factoryMoto.fabricarMotos(HONDA, ModelosHONDA.CBR.toString(), AZUL, 3);
    ArrayList<Motocicleta> motos1 = factoryMoto.fabricarMotos(HONDA, ModelosHONDA.CBR.toString(), AZUL, 6);
    ArrayList<Motocicleta> motos2 = factoryMoto.fabricarMotos(YAMAHA, ModelosYAMAHA.VMAX.toString(), NEGRO, 9);
    ArrayList<Motocicleta> motos3 = factoryMoto.fabricarMotos(DAISVI, ModelosDAISVI.TRAVESEIRA.toString(), VERDE, 2);
    ArrayList<Motocicleta> motos4 = factoryMoto.fabricarMotos(HONDA, ModelosHONDA.CBR.toString(), ROJO, 3);

    // Creacion de datos para poder
    public LOAD() {
        concDAO.agregarConcesionario(Granada, miEntityManager.getEntityManager());
        concDAO.agregarConcesionario(Madrid, miEntityManager.getEntityManager());
        concDAO.agregarConcesionario(Barcelona, miEntityManager.getEntityManager());

        adminDAO.crearAdministrador(miEntityManager.getEntityManager(), administrador);

        comDAO.anadirComercial(miEntityManager.getEntityManager(), c1);


        for (Motocicleta moto : motos) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos1) {
            moto.setConcesionario(Granada);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto2 : motos2) {
            moto2.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto2, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto3 : motos3) {
            moto3.setConcesionario(Granada);
            motoDAO.guardarMoto(moto3, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto4 : motos4) {
            moto4.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto4, miEntityManager.getEntityManager());
        }

        clienteDAO.anadirCliente(miEntityManager.getEntityManager(), cliente1);
        clienteDAO.anadirCliente(miEntityManager.getEntityManager(), cliente2);
        clienteDAO.anadirCliente(miEntityManager.getEntityManager(), cliente3);
        clienteDAO.anadirCliente(miEntityManager.getEntityManager(), cliente4);

    }


}
