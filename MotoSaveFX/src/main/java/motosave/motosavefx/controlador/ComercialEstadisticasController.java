package motosave.motosavefx.controlador;

import jakarta.persistence.EntityManager;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import motosave.Modelos.Comercial;

import motosave.DATA.ComercialLoggeado;
import motosave.ImplementacionesDAO.ImpVentaDAO;
import motosave.Modelos.Comercial;
import motosave.Modelos.Venta;

import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ComercialEstadisticasController implements Initializable {
    @FXML
    private ComboBox<String> CB_tiempo;
    @FXML
    private Button BTN_salir;
    @FXML
    private BarChart<String, Number> BC_barra_liquido;
    @FXML
    private Pane P_comercialEstadisticas;
    @FXML
    private Button BTN_ventas;
    @FXML
    private Label L_bienvenido;
    @FXML
    private LineChart<String, Number> LC_barra_ventas;
    @FXML
    private Label L_sede_comercial;
    @FXML
    private Label L_indentificacion_comercial;

    private ImpVentaDAO ventadao;
    private Comercial comercial;
    private EntityManager miEntity;
    private int c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ventadao = new ImpVentaDAO();
        comercial= ComercialLoggeado.getComercialLoggeado();
        miEntity=miEntityManager.getEntityManager();
        L_indentificacion_comercial.setText(comercial.getNombre());
        L_sede_comercial.setText(String.valueOf(comercial.getConcesionario()));
        cargarCB();
        rellenarGraficosDatos();


    }


    public void cargarCB() {
        CB_tiempo.getItems().addAll("Ultima semana", "Ultimo Mes", "Ultimo Año");

    }


    public void rellenarGraficosDatos() {
        if ("Ultima semana".equals(CB_tiempo.getValue())) {
            rellenarVentasUltimaSemana();
        } else if ("Ultimo Mes".equals(CB_tiempo.getValue())) {
            rellenarVentasUltimoMes();
        } else if ("Ultimo Año".equals(CB_tiempo.getValue())) {
            rellenarVentasUltimoAno();
        }
    }


    // Método genérico para obtener las ventas en un intervalo de fechas
    private List<Venta> obtenerVentasEnIntervalo(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Venta> ventasEnIntervalo = new ArrayList<>();

        List<Venta> todasLasVentas = ventadao.listarVentasComercial(miEntity, comercial.getId_comercial());

        for (Venta venta : todasLasVentas) {
            LocalDate fechaVenta = venta.getFecha_venta().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            if (fechaVenta.isAfter(fechaInicio.minusDays(1)) && fechaVenta.isBefore(fechaFin.plusDays(1))) {
                ventasEnIntervalo.add(venta);
            }
        }

        return ventasEnIntervalo;
    }



    private void rellenarVentasUltimaSemana() {
        // Lógica para obtener las ventas de la última semana
        // Puedes utilizar LocalDate.now() para obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Obtener el primer día de la última semana (restar 6 días a la fecha actual)
        LocalDate primerDiaUltimaSemana = fechaActual.minusDays(6);

        // Realizar la búsqueda de ventas para la última semana
        List<Venta> ventasUltimaSemana = obtenerVentasEnIntervalo(primerDiaUltimaSemana, fechaActual);

        rellenar_ventasPorSemana(ventasUltimaSemana);
    }

    private void rellenarVentasUltimoMes() {
        // Lógica para obtener las ventas del último mes
        // Puedes utilizar LocalDate.now() para obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Obtener el primer día del mes actual
        LocalDate primerDiaUltimoMes = fechaActual.withDayOfMonth(1);

        // Realizar la búsqueda de ventas para el último mes
        List<Venta> ventasUltimoMes = obtenerVentasEnIntervalo(primerDiaUltimoMes, fechaActual);

        rellenar_ventasPorMes(ventasUltimoMes);
    }

    private void rellenarVentasUltimoAno() {

        // Puedes utilizar LocalDate.now() para obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Obtener el primer día del año actual
        LocalDate primerDiaUltimoAno = fechaActual.withDayOfYear(1);

        // Realizar la búsqueda de ventas para el último año
        List<Venta> ventasUltimoAno = obtenerVentasEnIntervalo(primerDiaUltimoAno, fechaActual);


        rellenar_ventasPorAño(ventasUltimoAno);

    }




    //==============================================GRAFICO VENTAS================================================================

    private void rellenar_ventasPorSemana(List<Venta> ventas){}

    private void rellenar_ventasPorMes(List<Venta> ventas){}

    private void rellenar_ventasPorAño(List<Venta> ventas) {
        c1=0;c2=0;c3=0;c4=0;c5=0;c6=0;c7=0;c8=0;c9=0;c10=0;c11=0;c12=0;

        for (Venta venta:ventas){
            LocalDate date = venta.getFecha_venta().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            if(date.getMonthValue()== 1){
                c1+=1;
            }else if(date.getMonthValue()== 2){
                c2+=1;
            }else if(date.getMonthValue()== 3){
                c3+=1;
            }else if(date.getMonthValue()== 4){
                c4+=1;
            }else if(date.getMonthValue()== 5){
                c5+=1;
            }else if(date.getMonthValue()== 6){
                c6+=1;
            }else if(date.getMonthValue()== 7){
                c7+=1;
            }else if(date.getMonthValue()== 8){
                c8+=1;
            }else if(date.getMonthValue()== 9){
                c9+=1;
            }else if(date.getMonthValue()== 10){
                c10+=1;
            }else if(date.getMonthValue()== 11){
                c11+=1;
            }else if(date.getMonthValue()== 12){
                c12+=1;
            }
        }
        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("Enero", c1));
        set1.getData().add(new XYChart.Data("Febrero", c2));
        set1.getData().add(new XYChart.Data("Marzo", c3));
        set1.getData().add(new XYChart.Data("Abril", c4));
        set1.getData().add(new XYChart.Data("Mayo", c5));
        set1.getData().add(new XYChart.Data("Junio", c6));
        set1.getData().add(new XYChart.Data("Julio", c7));
        set1.getData().add(new XYChart.Data("Agosto", c8));
        set1.getData().add(new XYChart.Data("Septiembre", c9));
        set1.getData().add(new XYChart.Data("Octubre", c10));
        set1.getData().add(new XYChart.Data("Noviembre", c11));
        set1.getData().add(new XYChart.Data("Diciembre", c12));

        BC_barra_liquido.getData().addAll(set1);
    }


    //========================================GRAFICO LIQUIDO====================================================================

    public void rellenar_liquidoPorSemana(List<Venta> ventas) {

        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("1", 3500));
        set1.getData().add(new XYChart.Data("2", 2030));
        set1.getData().add(new XYChart.Data("3", 1300));
        set1.getData().add(new XYChart.Data("4", 3100));
        set1.getData().add(new XYChart.Data("5", 4200));
        set1.getData().add(new XYChart.Data("6", 2900));

        LC_barra_ventas.getData().addAll(set1);
    }

    public void rellenar_liquidoPorMes(List<Venta> ventas) {

        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("1", 3500));
        set1.getData().add(new XYChart.Data("2", 2030));
        set1.getData().add(new XYChart.Data("3", 1300));
        set1.getData().add(new XYChart.Data("4", 3100));
        set1.getData().add(new XYChart.Data("5", 4200));
        set1.getData().add(new XYChart.Data("6", 2900));

        LC_barra_ventas.getData().addAll(set1);
    }

    public void rellenar_liquidoPorAño(List<Venta> ventas) {

        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("1", 3500));
        set1.getData().add(new XYChart.Data("2", 2030));
        set1.getData().add(new XYChart.Data("3", 1300));
        set1.getData().add(new XYChart.Data("4", 3100));
        set1.getData().add(new XYChart.Data("5", 4200));
        set1.getData().add(new XYChart.Data("6", 2900));

        LC_barra_ventas.getData().addAll(set1);
    }


    //===============================================================================================================



    @FXML
    public void abrir_ventas(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Comercial_Ventas.fxml"));

            Parent root = loader.load();

            ComercialVentasController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage myStage = (Stage) this.BTN_ventas.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void cerrar_sesion(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Login.fxml"));

            Parent root = loader.load();

            LoginController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage myStage = (Stage) this.BTN_salir.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

    // Falta hacer el DAO de ventas para esto
//    public void actualizarLineChartVentasPorSeleccion(ComboBox<String> comboBox) {
//        String seleccion = comboBox.getSelectionModel().getSelectedItem();
//        EntityManager em = miEntityManager.getEntityManager();
//
//        try {
//            // Preparar la consulta base
//            String jpql = "SELECT v.fechaVenta, COUNT(v) FROM Venta v WHERE v.fechaVenta >= :fechaInicio GROUP BY v.fechaVenta ORDER BY v.fechaVenta";
//
//            // Calcular fecha de inicio basada en la selección
//            LocalDate fechaInicio = LocalDate.now(); // Valor por defecto para evitar warning
//            switch (seleccion) {
//                case "Última Semana":
//                    fechaInicio = LocalDate.now().minusWeeks(1);
//                    break;
//                case "Último Mes":
//                    fechaInicio = LocalDate.now().minusMonths(1);
//                    break;
//                case "Último Año":
//                    fechaInicio = LocalDate.now().minusYears(1);
//                    break;
//            }
//
//            // Ejecutar consulta
//            List<Object[]> resultados = em.createQuery(jpql, Object[].class)
//                    .setParameter("fechaInicio", fechaInicio)
//                    .getResultList();
//
//            // Preparar la serie de datos para la gráfica
//            XYChart.Series<String, Number> serieVentas = new XYChart.Series<>();
//            serieVentas.setName("Ventas"); // Este nombre aparecerá como leyenda en la gráfica
//
//            // Llenar la serie con los resultados de la consulta
//            for (Object[] resultado : resultados) {
//                LocalDate fecha = (LocalDate) resultado[0];
//                Long cantidad = (Long) resultado[1];
//                serieVentas.getData().add(new XYChart.Data<>(fecha.toString(), cantidad));
//            }
//
//            // Actualizar la LineChart
//            LC_barra_ventas.getData().clear();
//            LC_barra_ventas.getData().add(serieVentas);
//        } finally {
//            em.close();
//        }
//    }

    // Cerrar la aplicación al presionar el botón salir
    @Deprecated
    public void cerrarAplicacion(ActionEvent actionEvent) {
        Stage stage = (Stage) BTN_salir.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void rellenar_ventas(Event event) {
    }

    @FXML
    public void rellenar_liquido(Event event) {
    }
}