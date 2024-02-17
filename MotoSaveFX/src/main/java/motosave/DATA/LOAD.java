/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.DATA;

import motosave.EnumeradosMoto.*;
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
    ArrayList<Motocicleta> motos = factoryMoto.fabricarMotos(HONDA, ModelosHONDA.CBR.toString(), AZUL, 5);
    ArrayList<Motocicleta> motos1 = factoryMoto.fabricarMotos(HONDA, ModelosHONDA.CRF.toString(), ROJO, 5);
    ArrayList<Motocicleta> motos2 = factoryMoto.fabricarMotos(HONDA, ModelosHONDA.NC.toString(), BLANCO, 5);
    ArrayList<Motocicleta> motos3 = factoryMoto.fabricarMotos(HONDA, ModelosHONDA.AFRICA_TWIN.toString(), AZUL, 5);
    ArrayList<Motocicleta> motos4 = factoryMoto.fabricarMotos(HONDA, ModelosHONDA.GOLDWING.toString(), GRIS, 5);
    ArrayList<Motocicleta> motos5 = factoryMoto.fabricarMotos(YAMAHA, ModelosYAMAHA.YZF_R6.toString(), AZUL, 5);
    ArrayList<Motocicleta> motos6 = factoryMoto.fabricarMotos(YAMAHA, ModelosYAMAHA.TRACER.toString(), ROJO, 5);
    ArrayList<Motocicleta> motos7 = factoryMoto.fabricarMotos(YAMAHA, ModelosYAMAHA.VMAX.toString(), NEGRO, 5);
    ArrayList<Motocicleta> motos8 = factoryMoto.fabricarMotos(DAISVI, ModelosDAISVI.TRAVESEIRA.toString(), AZUL, 5);
    ArrayList<Motocicleta> motos9 = factoryMoto.fabricarMotos(DAISVI, ModelosDAISVI.BELMONTER.toString(), VERDE, 5);
    ArrayList<Motocicleta> motos10 = factoryMoto.fabricarMotos(DAISVI, ModelosDAISVI.RAPID_LIM.toString(),NEGRO , 5);
    ArrayList<Motocicleta> motos11 = factoryMoto.fabricarMotos(DAISVI, ModelosDAISVI.RUBENTURE.toString(), ROJO, 5);
    ArrayList<Motocicleta> motos12 = factoryMoto.fabricarMotos(DAISVI, ModelosDAISVI.SUPATRI.toString(), VERDE, 5);
    ArrayList<Motocicleta> motos13 = factoryMoto.fabricarMotos(DUCATI, ModelosDUCATI.PANIGALE.toString(), ROJO, 5);
    ArrayList<Motocicleta> motos14 = factoryMoto.fabricarMotos(DUCATI, ModelosDUCATI.MONSTER.toString(), ROJO, 5);
    ArrayList<Motocicleta> motos15 = factoryMoto.fabricarMotos(DUCATI, ModelosDUCATI.HYPERMOTARD.toString(), NEGRO, 5);
    ArrayList<Motocicleta> motos16 = factoryMoto.fabricarMotos(DUCATI, ModelosDUCATI.DIAVEL.toString(), BLANCO, 5);
    ArrayList<Motocicleta> motos17 = factoryMoto.fabricarMotos(SUZUKI, ModelosSUZUKI.GSX.toString(), AZUL, 5);
    ArrayList<Motocicleta> motos18 = factoryMoto.fabricarMotos(SUZUKI, ModelosSUZUKI.HAYABUSA.toString(), AMARILLO, 5);
    ArrayList<Motocicleta> motos19 = factoryMoto.fabricarMotos(SUZUKI, ModelosSUZUKI.V_STROM.toString(), AZUL, 5);
    ArrayList<Motocicleta> motos20 = factoryMoto.fabricarMotos(KAWASAKI, ModelosKAWASAKI.NINJA.toString(), VERDE, 5);
    ArrayList<Motocicleta> motos21 = factoryMoto.fabricarMotos(KAWASAKI, ModelosKAWASAKI.VULCAN.toString(), NEGRO, 5);
    ArrayList<Motocicleta> motos22 = factoryMoto.fabricarMotos(KAWASAKI, ModelosKAWASAKI.ZH2.toString(),VERDE, 5);
    ArrayList<Motocicleta> motos23 = factoryMoto.fabricarMotos(BMW, ModelosBMW.RT.toString(), GRIS, 5);
    ArrayList<Motocicleta> motos24 = factoryMoto.fabricarMotos(BMW, ModelosBMW.NINE_T.toString(), NEGRO, 5);
    ArrayList<Motocicleta> motos25 = factoryMoto.fabricarMotos(BMW, ModelosBMW.GS.toString(), BLANCO, 5);
    ArrayList<Motocicleta> motos26 = factoryMoto.fabricarMotos(BMW, ModelosBMW.ADVENTURE.toString(), AZUL, 5);
    ArrayList<Motocicleta> motos27 = factoryMoto.fabricarMotos(KTM, ModelosKTM.EXC.toString(), NARANJA, 5);
    ArrayList<Motocicleta> motos28 = factoryMoto.fabricarMotos(KTM, ModelosKTM.DUKE.toString(),NARANJA, 5);
    ArrayList<Motocicleta> motos29 = factoryMoto.fabricarMotos(KTM, ModelosKTM.RC.toString(), NEGRO, 5);

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
        for (Motocicleta moto : motos2) {
            moto.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos3) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos4) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos5) {
            moto.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos6) {
            moto.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos7) {
            moto.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos8) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos9) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos10) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos11) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos12) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos13) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos14) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos15) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos16) {
            moto.setConcesionario(Madrid);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos17) {
            moto.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos18) {
            moto.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos19) {
            moto.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos20) {
            moto.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos21) {
            moto.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos22) {
            moto.setConcesionario(Barcelona);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos23) {
            moto.setConcesionario(Granada);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos24) {
            moto.setConcesionario(Granada);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos25) {
            moto.setConcesionario(Granada);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos26) {
            moto.setConcesionario(Granada);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos27) {
            moto.setConcesionario(Granada);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos28) {
            moto.setConcesionario(Granada);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }
        for (Motocicleta moto : motos29) {
            moto.setConcesionario(Granada);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
        }


        clienteDAO.anadirCliente(miEntityManager.getEntityManager(), cliente1);
        clienteDAO.anadirCliente(miEntityManager.getEntityManager(), cliente2);
        clienteDAO.anadirCliente(miEntityManager.getEntityManager(), cliente3);
        clienteDAO.anadirCliente(miEntityManager.getEntityManager(), cliente4);

    }


}
