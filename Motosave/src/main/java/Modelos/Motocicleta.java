package Modelos;

import DATA.LOAD;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "motocicleta")
public class Motocicleta {
    

    // Relación con Concesionario (muchas motos pertenecen a un concesionario)
    @ManyToOne
    @JoinColumn(name = "concesionario_id")
    private Concesionario concesionario;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_moto = 0;
    
//  private String n_bastidor;
    private String marca;
    private String modelo;
    private String color;
    private int cc;
    private double precio_venta;
    private double precio_compra;

    public Motocicleta(Concesionario concesionario,  String marca, String modelo, String color, int cc, double precio_compra) {
        this.concesionario = concesionario;
//        this.n_bastidor = generarNumeroBastidorAleatorio();
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.cc = cc;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_compra * LOAD.beneficio;
    }

//    private String generarNumeroBastidorAleatorio() {
//        // Añadir al numero de bastidor el id_moto para que sea diferente el numero de bastidor
//        // Falta un get del id moto para que le llegue a esta función y genere el nº de bastidor
//        
//        return  modelo_n_bastidor + ;
//    }
    
    
}
