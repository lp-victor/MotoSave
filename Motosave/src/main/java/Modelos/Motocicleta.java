package Modelos;

import DATA.LOAD;
import jakarta.persistence.*;


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

    public Motocicleta(String marca, String modelo, String color, int cc, double precio_compra) {
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

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }

    public int getId_moto() {
        return id_moto;
    }

    public void setId_moto(int id_moto) {
        this.id_moto = id_moto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }
    
    
}
