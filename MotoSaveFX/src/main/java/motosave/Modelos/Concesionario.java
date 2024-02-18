/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.Modelos;

import jakarta.persistence.*;

/**
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

    public Concesionario() {
    }

    public Concesionario(int id_concesionario, String ubicacion) {
        this.id_concesionario = id_concesionario;
        this.ubicacion = ubicacion;
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
        return id_concesionario + " - " + ubicacion;
    }

}
