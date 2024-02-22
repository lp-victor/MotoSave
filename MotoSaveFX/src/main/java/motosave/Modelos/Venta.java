package motosave.Modelos;

import jakarta.persistence.*;

import java.util.Date;

/**
 * @author MotoSave
 */
@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_venta;

    @Temporal(TemporalType.DATE)
    private Date fecha_venta;

    // Relación con Comercial (una venta pertenece a un comercial)
    @ManyToOne
    @JoinColumn(name = "id_comercial")
    private Comercial comercial;

    @ManyToOne
    @JoinColumn(name = "id_moto")
    private Motocicleta moto;

    private double precio_final; //precio_final = precio_moto * descuento.

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Venta() {
    }

    public Venta(Date fecha_venta, Comercial comercial, Motocicleta moto, double precio_final, Cliente cliente) {
        this.fecha_venta = fecha_venta;
        this.comercial = comercial;
        this.moto = moto;
        this.precio_final = precio_final;
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId_venta() {
        return id_venta;
    }


    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Comercial getComercial() {
        return comercial;
    }

    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }

    public Motocicleta getMoto() {
        return moto;
    }

    public void setMoto(Motocicleta moto) {
        this.moto = moto;
    }

    public double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(double precio_final) {
        this.precio_final = precio_final;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id_venta=" + id_venta +
                ", fecha_venta=" + fecha_venta +
                ", comercial=" + comercial +
                ", moto=" + moto +
                ", precio_final=" + precio_final +
                ", cliente=" + cliente +
                '}';
    }
}
