package Modelo;

import INTERFACES.Entradable;
import Modelo.Motocicleta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Garaje implements Serializable, Entradable {

    private int idGaraje;
    private String sucursal;
    private int plazasLibres;
    ArrayList<Motocicleta> motos = new ArrayList();

    public Garaje() {
    }

    public Garaje(int idGaraje, String sucursal, int plazasLibres) {
        this.idGaraje = idGaraje;
        this.sucursal = sucursal;
        this.plazasLibres = 10 - plazasLibres;
    }        
    
    public Garaje(String sucursal) {
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
    
    public boolean estaVacio(){
        return this.motos.isEmpty();
    }

    @Override
    public String toString() {
        return "Garaje{" + "idGaraje=" + idGaraje + ", sucursal=" + sucursal + ", plazasLibres=" + plazasLibres + '}';
    }

    
}
