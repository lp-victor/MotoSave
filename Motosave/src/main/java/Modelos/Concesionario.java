/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import ImplementacionesDAO.ImpMotocicletaDAO;
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
    @OneToMany(mappedBy = "concesionario")
    private List<Comercial> comerciales;
    
    // Relación con Moto (un concesionario tiene muchas motos)
    @OneToMany(mappedBy = "concesionario")
    private List<Motocicleta> inventario;

    public Concesionario(String ubicacion) {
        this.ubicacion = ubicacion;  
        this.comerciales = new ArrayList<>();  
        this.inventario = new ArrayList<>();
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

    public List<Comercial> getComerciales() {
        return comerciales;
    }

    public void setComerciales(ArrayList<Comercial> comerciales) {
        this.comerciales = comerciales;
    }

    public List<Motocicleta> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Motocicleta> inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        return "Concesionario{" + "id_concesionario=" + id_concesionario + ", ubicacion=" + ubicacion + ", comerciales=" + comerciales + ", inventario=" + inventario + '}';
    }

    public void agregarMotocicleta(Motocicleta moto,EntityManager entityManager) {
        moto.setConcesionario(this);
        inventario.add(moto);
        ImpMotocicletaDAO motoDAO = new ImpMotocicletaDAO();
        motoDAO.guardarMoto(moto, entityManager);
            
    }
    
    public void agregarComercial(Comercial comercial) {
        comercial.setConcesionario(this);
        comerciales.add(comercial);
    }
    
}
