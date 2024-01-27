/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "concesionario")
public class Concesionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_concesionario;
    
    private String ubicacion;
    
     // Relación con Comercial (un concesionario tiene muchos comerciales)
    @OneToMany(mappedBy = "concesionario")
    private ArrayList<Comercial> comerciales;
    
    // Relación con Moto (un concesionario tiene muchas motos)
    @OneToMany(mappedBy = "concesionario")
    private ArrayList<Motocicleta> inventario;

    public Concesionario(int id_concesionario, String ubicacion, ArrayList<Comercial> comerciales, ArrayList<Motocicleta> inventario) {
        this.id_concesionario = id_concesionario;
        this.ubicacion = ubicacion;
        this.comerciales = comerciales;
        this.inventario = inventario;
    }

    public int getId_concesionario() {
        return id_concesionario;
    }

    public void setId_concesionario(int id_concesionario) {
        this.id_concesionario = id_concesionario;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Comercial> getComerciales() {
        return comerciales;
    }

    public void setComerciales(ArrayList<Comercial> comerciales) {
        this.comerciales = comerciales;
    }

    public ArrayList<Motocicleta> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Motocicleta> inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        return "Concesionario{" + "id_concesionario=" + id_concesionario + ", ubicacion=" + ubicacion + ", comerciales=" + comerciales + ", inventario=" + inventario + '}';
    }
    
}
