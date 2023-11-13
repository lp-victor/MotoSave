

package Modelo;

import INTERFACES.Entradable;
import Modelo.Motocicleta;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;



public class Garaje implements Serializable, Entradable {
      
    private int idGaraje;
    private String sucursal = "Garaje";
    private int plazasLibres;
    ArrayList<Motocicleta> motos=new ArrayList();
    
    public Garaje(){
    }
    
    public Garaje(String sucursal){
        this.sucursal = sucursal;
    }
    
    public Garaje(ArrayList<Motocicleta> motos) {
        this.motos = motos;
    }

    public int getIdGaraje() {
        return idGaraje;
    }

    public void setIdGaraje(int idGaraje) {
        this.idGaraje = idGaraje;
    }    

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public int getPlazasLibres() {
        return plazasLibres;
    }

    public void setPlazasLibres(int plazasLibres) {
        this.plazasLibres = plazasLibres;
    }

    public ArrayList<Motocicleta> getMotos() {
        return motos;
    }

    public void setMotos(ArrayList<Motocicleta> motos) {
        this.motos = motos;
    }

    // Hecho pisha dejar por si algo sirve
    // ==============================================================================
    public void menuGaraje() {
      
        int opcion;
        do {
            System.out.println("\u001B[47m" + " -------------------------------------- " + "\u001B[0m");
            System.out.println("\u001B[47m" + "                  GARAJE                " + "\u001B[0m");
            System.out.println("\u001B[47m" + " -------------------------------------- " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   Elija la opción                      " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   1 - Gestion de Motocicletas          " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   2 - Gestion Gastos de Combustible    " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   0 - Atrás                            " + "\u001B[0m");
            System.out.println("\u001B[47m" + " -------------------------------------- " + "\u001B[0m");
            opcion = Entradable.pedirEntero();
            switch (opcion) {
                case 1:
                    gestionMotos();
                    break;
                case 2:
                    gestionCombustible();
                    break;              
                case 0:
                    break;
                default:
                    System.out.println("Error: Opcion no disponible");
                    break;
            }
        } while (opcion != 0);
    }

    private void gestionMotos(){
        int opcion;
        do {
            System.out.println("\u001B[47m" + " -------------------------------------- " + "\u001B[0m");
            System.out.println("\u001B[47m" + "          GESTION MOTOCICLETAS          " + "\u001B[0m");
            System.out.println("\u001B[47m" + " -------------------------------------- " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   Elija la opción                      " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   1 - Listar Motocicletas              " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   2 - Agregar Nueva Motocicleta        " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   3 - Eliminar Motocicleta             " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   0 - Atrás                            " + "\u001B[0m");
            System.out.println("\u001B[47m" + " -------------------------------------- " + "\u001B[0m");
            opcion = Entradable.pedirEntero();
            switch (opcion) {
                case 1:
                    listarMotos();
                    break;
                case 2:
//                    agregarMoto();
                    break; 
                case 3:
                    eliminarMoto();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error: Opcion no disponible");
                    break;
            }
        } while (opcion != 0);
              
    };
 
    
    private void gestionCombustible(){
        int opcion;
        do {
            System.out.println("\u001B[47m" + " -------------------------------------- " + "\u001B[0m");
            System.out.println("\u001B[47m" + "          GESTION GASTOS                " + "\u001B[0m");
            System.out.println("\u001B[47m" + " -------------------------------------- " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   Elija la opción                      " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   1 - Listar Control Combustible       " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   2 - Guardar Gasto Combustible        " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   3 - Eliminar Gasto Combustible       " + "\u001B[0m");
            System.out.println("\u001B[47m" + "   0 - Atrás                            " + "\u001B[0m");
            System.out.println("\u001B[47m" + " -------------------------------------- " + "\u001B[0m");
            opcion = Entradable.pedirEntero();
            switch (opcion) {
                case 1:
                     listarControlCombustible();
                    break;
                case 2:
                    guardarGastoCombustible();
                    break; 
                case 3:
                    eliminarGastoCombustible();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error: Opcion no disponible");
                    break;
            }
        } while (opcion != 0);
    };
    
    //===================GESTION MOTOS====================
    
    private void listarMotos(){
        int aux=0;
        if(motos.isEmpty()){
            System.out.println("No hay Motocicletas en el Garaje");
            return;
        }
        for (Motocicleta moto : motos) {
            aux++;
            System.out.println(aux+ "- " +moto.toString());
        }
    }
    
//    private void agregarMoto(){
//        
//        String nombreM="";
//        String marcaM="";
//        String colorM="";
//        String matriculaM="";
//        int ccM=0;
//        System.out.println("Introduce nombre: ");
//        nombreM=sc.nextLine();
//        System.out.println("Introduce marca: ");
//        marcaM=sc.nextLine();
//        System.out.println("Introduce color: ");
//        colorM=sc.nextLine();
//        System.out.println("Introduce matricula: ");
//        matriculaM=sc.nextLine();
//        System.out.println("Introduce CC: ");
//        ccM=Integer.valueOf(sc.nextLine()); 
//        motos.add(new Motocicleta(1,nombreM, marcaM, colorM, matriculaM, ccM));
////        Entradable.agregarMotoBBDD(matriculaM, nombreM, marcaM, colorM, ccM);
//        System.out.println("Moto Introducida Correctamente");
//        listarMotos();
//    }
    
    private void eliminarMoto(){
        int elegir=0;
        String YoN="";     
        if(motos.isEmpty()){
            System.out.println("No hay motos en tu garaje para eliminar");
            return;
        }
        System.out.println("Elige el numero de la moto que quieres eliminar. escribe 'fin' para salir");  
        listarMotos();
        elegir=Integer.valueOf(sc.nextLine())-1;
        System.out.println("Seguro de eliminar { "+motos.get(elegir).getMarca()+" - "+ motos.get(elegir).getModelo()+" } \nIntroduce (YES/NO)");
        YoN=sc.nextLine();
        if(YoN.equalsIgnoreCase("yes")){
            motos.remove(elegir);
            System.out.println("Moto Eliminada");
            listarMotos();
        }else{
            System.out.println("Saliendo SIN eliminar");
        }
    }
    
    
    //================GESTION DE COMBUSTIBLE==================================
    //FICHERO PARA CONTROL DE GASOLINA (FECHA,LITROS,EUROS)
    private void listarControlCombustible(){
        
    
        
    }
    
    private void guardarGastoCombustible(){
        
    
        
    }
     
    private void eliminarGastoCombustible(){
        
    
        
    }
    
    @Override
    public String toString() {
        return "Garaje{" + "nombre=" + sucursal + ", motos=" + motos + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Garaje other = (Garaje) obj;
        if (!Objects.equals(this.sucursal, other.sucursal)){
            return false;
        }
        return Objects.equals(this.motos, other.motos);
    }
}
