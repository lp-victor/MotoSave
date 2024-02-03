/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.Modelos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "concesionario")
public class Concesionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concesionario")
    private int id_concesionario;
    
    private String ubicacion;
    
     // Relación con Comercial (un concesionario tiene muchos comerciales)


    public Concesionario() {
    }

    public Concesionario(String ubicacion) {
        this.ubicacion = ubicacion;
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

    @Override
    public String toString() {
        return "Concesionario{" + "id_concesionario=" + id_concesionario + ", ubicacion=" + ubicacion + '}';
    }

}
