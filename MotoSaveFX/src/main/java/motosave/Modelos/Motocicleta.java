package motosave.Modelos;

import jakarta.persistence.*;

/**
 * @author victo
 */
@Entity
@Table(name = "motocicleta")
public class Motocicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_moto;

    @ManyToOne
    @JoinColumn(name = "id_concesionario")
    private Concesionario concesionario;

    private String marca;
    private String modelo;
    private String color;
    private int cc;
    private double precio_compra;

    public Motocicleta() {
    }

    public Motocicleta(String marca, String modelo, String color, int cc, double precio_compra) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.cc = cc;
        this.precio_compra = precio_compra;
    }

    public int getId_moto() {
        return id_moto;
    }

    public void setId_moto(int id_moto) {
        this.id_moto = id_moto;
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
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

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    @Override
    public String toString() {
        return "Motocicleta{" + "id_moto=" + id_moto + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color + ", cc=" + cc + ", precio_compra=" + precio_compra + '}';
    }


}
