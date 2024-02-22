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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static motosave.EnumeradosMoto.Colores.*;
import static motosave.EnumeradosMoto.Marcas.*;

/**
 * La clase LOAD proporciona funcionalidad para cargar datos iniciales en el sistema.
 * Esto incluye la creación de concesionarios, administradores, comerciales, motocicletas, clientes y ventas.
 * Además, ajusta los precios de las motocicletas según un beneficio predefinido.
 * Esta clase se utiliza para inicializar el sistema con datos de muestra.
 *
 * @author MotoSave
 */
public class LOAD {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static double beneficio = 1.35;
    ImpConcesionarioDAO concDAO = new ImpConcesionarioDAO();
    ImpAdministradorDAO adminDAO = new ImpAdministradorDAO();
    ImpComercialDAO comDAO = new ImpComercialDAO();
    ImpMotocicletaDAO motoDAO = new ImpMotocicletaDAO();
    ImpClienteDAO clienteDAO = new ImpClienteDAO();

    ImpVentaDAO ventaDAO = new ImpVentaDAO();

    // =============== CARGA DE LA BASE DE DATOS ===============

    // Concesionarios
    Concesionario Granada = new Concesionario("Granada");
    Concesionario Madrid = new Concesionario("Madrid");
    Concesionario Barcelona = new Concesionario("Barcelona");

    // Usuarios
    Administrador administrador = new Administrador("admin", "admin");
    Comercial c1 = new Comercial(Madrid, "isra", "1234", "12345678A", "Israel", "Ramirez Sanchez");
    Comercial c2 = new Comercial(Granada, "david", "1234", "12345678B", "David", "Gonzalez Vilches");
    Comercial c3 = new Comercial(Barcelona, "victor", "1234", "12345678C", "Victor", "Lopez Jimenez");

    // Clientes
    Cliente cliente1 = new Cliente("MotoTodo", "mototodo@yahoo.com", 916370127, "C/ Mar oceana 10, Las Rozas");
    Cliente cliente2 = new Cliente("SpeedMotos", "speedmotos@gmail.com", 914567897, "Avenida Principe de Asturias 17, Fuencarral");
    Cliente cliente3 = new Cliente("Moto Racing", "motoracing@gmail.com", 919456785, "C/ Mar de plata 11, Poligono Marconi, Parla");
    Cliente cliente4 = new Cliente("Moto Ocasion", "moto.ocasion@hotmail.com", 913456784, "C/ Real 34, Nave 6, Colmenar Viejo");


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
    ArrayList<Motocicleta> motos10 = factoryMoto.fabricarMotos(DAISVI, ModelosDAISVI.RAPID_LIM.toString(), NEGRO, 5);
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
    ArrayList<Motocicleta> motos22 = factoryMoto.fabricarMotos(KAWASAKI, ModelosKAWASAKI.ZH2.toString(), VERDE, 5);
    ArrayList<Motocicleta> motos23 = factoryMoto.fabricarMotos(BMW, ModelosBMW.RT.toString(), GRIS, 5);
    ArrayList<Motocicleta> motos24 = factoryMoto.fabricarMotos(BMW, ModelosBMW.NINE_T.toString(), NEGRO, 5);
    ArrayList<Motocicleta> motos25 = factoryMoto.fabricarMotos(BMW, ModelosBMW.GS.toString(), BLANCO, 5);
    ArrayList<Motocicleta> motos26 = factoryMoto.fabricarMotos(BMW, ModelosBMW.ADVENTURE.toString(), AZUL, 5);
    ArrayList<Motocicleta> motos27 = factoryMoto.fabricarMotos(KTM, ModelosKTM.EXC.toString(), NARANJA, 5);
    ArrayList<Motocicleta> motos28 = factoryMoto.fabricarMotos(KTM, ModelosKTM.DUKE.toString(), NARANJA, 5);
    ArrayList<Motocicleta> motos29 = factoryMoto.fabricarMotos(KTM, ModelosKTM.RC.toString(), NEGRO, 5);

    /**
     * Constructor de la clase LOAD.
     * Carga datos iniciales en el sistema, incluyendo concesionarios, administradores, comerciales, motocicletas,
     * clientes y ventas.
     */
    public LOAD() {
        concDAO.agregarConcesionario(Granada, miEntityManager.getEntityManager());
        concDAO.agregarConcesionario(Madrid, miEntityManager.getEntityManager());
        concDAO.agregarConcesionario(Barcelona, miEntityManager.getEntityManager());

        adminDAO.crearAdministrador(miEntityManager.getEntityManager(), administrador);

        comDAO.anadirComercial(miEntityManager.getEntityManager(), c1);
        comDAO.anadirComercial(miEntityManager.getEntityManager(), c2);
        comDAO.anadirComercial(miEntityManager.getEntityManager(), c3);


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

        // ===================== FECHAS =====================
        // Obtiene la fecha actual
        Calendar calendar = Calendar.getInstance();

        // Hace 1 mes
        calendar.add(Calendar.MONTH, -1);
        Date haceUnMes = calendar.getTime();

        // Restablece la fecha actual
        calendar.setTime(new Date());

        // Hace 6 meses
        calendar.add(Calendar.MONTH, -6);
        Date haceSeisMeses = calendar.getTime();

        // Restablece la fecha actual
        calendar.setTime(new Date());

        // Hace 8 meses
        calendar.add(Calendar.MONTH, -8);
        Date haceOchoMeses = calendar.getTime();

        // Hace 2 años
        calendar.add(Calendar.MONTH, -17);
        Date haceDosAnios = calendar.getTime();

        // Restablece la fecha actual
        calendar.setTime(new Date());

        Venta ventaHaceUnMes1 = new Venta(haceUnMes, c1, motos1.get(3), cambiarPrecioMoto(motos1.get(3).getPrecio_compra()), cliente1);
        Venta ventaHaceUnMes2 = new Venta(haceUnMes, c1, motos4.get(1), cambiarPrecioMoto(motos4.get(1).getPrecio_compra()), cliente2);
        Venta ventaHaceUnMes3 = new Venta(haceUnMes, c3, motos2.get(4), cambiarPrecioMoto(motos2.get(4).getPrecio_compra()), cliente3);
        Venta ventaHaceUnMes4 = new Venta(haceUnMes, c2, motos7.get(2), cambiarPrecioMoto(motos7.get(2).getPrecio_compra()), cliente4);
        Venta ventaHaceUnMes5 = new Venta(haceUnMes, c3, motos13.get(2), cambiarPrecioMoto(motos13.get(2).getPrecio_compra()), cliente3);

        Venta ventaHaceSeisMeses1 = new Venta(haceSeisMeses, c3, motos29.get(2), cambiarPrecioMoto(motos29.get(2).getPrecio_compra()), cliente2);
        Venta ventaHaceSeisMeses2 = new Venta(haceSeisMeses, c1, motos26.get(2), cambiarPrecioMoto(motos26.get(2).getPrecio_compra()), cliente1);
        Venta ventaHaceSeisMeses3 = new Venta(haceSeisMeses, c3, motos23.get(2), cambiarPrecioMoto(motos23.get(2).getPrecio_compra()), cliente1);
        Venta ventaHaceSeisMeses4 = new Venta(haceSeisMeses, c3, motos22.get(2), cambiarPrecioMoto(motos22.get(2).getPrecio_compra()), cliente2);
        Venta ventaHaceSeisMeses5 = new Venta(haceSeisMeses, c1, motos21.get(2), cambiarPrecioMoto(motos21.get(2).getPrecio_compra()), cliente3);

        Venta ventaHaceOchoMeses1 = new Venta(haceOchoMeses, c2, motos12.get(1), cambiarPrecioMoto(motos12.get(1).getPrecio_compra()), cliente4);
        Venta ventaHaceOchoMeses2 = new Venta(haceOchoMeses, c3, motos5.get(3), cambiarPrecioMoto(motos5.get(3).getPrecio_compra()), cliente2);
        Venta ventaHaceOchoMeses3 = new Venta(haceOchoMeses, c2, motos16.get(2), cambiarPrecioMoto(motos16.get(2).getPrecio_compra()), cliente3);
        Venta ventaHaceOchoMeses4 = new Venta(haceOchoMeses, c3, motos17.get(1), cambiarPrecioMoto(motos17.get(1).getPrecio_compra()), cliente1);
        Venta ventaHaceOchoMeses5 = new Venta(haceOchoMeses, c2, motos12.get(4), cambiarPrecioMoto(motos12.get(4).getPrecio_compra()), cliente2);
        Venta ventaHaceOchoMeses6 = new Venta(haceOchoMeses, c1, motos11.getFirst(), cambiarPrecioMoto(motos11.getFirst().getPrecio_compra()), cliente1);

        Venta ventahaceDosAnios1 = new Venta(haceDosAnios, c3, motos5.get(2), cambiarPrecioMoto(motos5.get(2).getPrecio_compra()), cliente2);
        Venta ventahaceDosAnios2 = new Venta(haceDosAnios, c2, motos16.get(3), cambiarPrecioMoto(motos16.get(3).getPrecio_compra()), cliente3);
        Venta ventahaceDosAnios3 = new Venta(haceDosAnios, c3, motos17.get(2), cambiarPrecioMoto(motos17.get(2).getPrecio_compra()), cliente1);
        Venta ventahaceDosAnios4 = new Venta(haceDosAnios, c2, motos12.get(3), cambiarPrecioMoto(motos12.get(3).getPrecio_compra()), cliente2);
        Venta ventahaceDosAnios5 = new Venta(haceDosAnios, c1, motos10.getFirst(), cambiarPrecioMoto(motos10.getFirst().getPrecio_compra()), cliente1);

        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceUnMes1);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceUnMes2);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceUnMes3);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceUnMes4);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceUnMes5);

        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceSeisMeses1);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceSeisMeses2);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceSeisMeses3);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceSeisMeses4);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceSeisMeses5);

        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceOchoMeses1);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceOchoMeses2);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceOchoMeses3);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceOchoMeses4);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceOchoMeses5);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaHaceOchoMeses6);

        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventahaceDosAnios1);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventahaceDosAnios2);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventahaceDosAnios3);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventahaceDosAnios4);
        ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventahaceDosAnios5);

    }

    /**
     * Cambia el precio de una motocicleta aplicando un beneficio.
     *
     * @param precio El precio original de la motocicleta.
     * @return El nuevo precio de la motocicleta con el beneficio aplicado.
     */
    private double cambiarPrecioMoto(double precio) {
        precio = precio * LOAD.beneficio;
        if (precio % 1 != 0) {
            precio = Double.parseDouble(String.valueOf((int) precio));
        }
        return precio;
    }
}
