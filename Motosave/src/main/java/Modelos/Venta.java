
package Modelos;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "venta")
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_venta;
    
    @Temporal(TemporalType.DATE)
    private Date fecha_venta;
    //private enum descuento;
    
    // Relación con Comercial (una venta pertenece a un comercial)
    @ManyToOne
    @JoinColumn(name = "comercial_id")
    private Comercial comercial;
    private String n_bastidor_moto;
    private double precio_moto; // precio_moto = precio_compra * beneficio.
    
    @ElementCollection // Necesario para mapear una coleccion de elementos en hibernate.
    @CollectionTable(name = "info_cliente", joinColumns = @JoinColumn(name = "id_venta"))
    @MapKeyColumn(name = "Info")
    @Column(name = "Detalles")
    private Map<String, String> datos_cliente = new HashMap<>();
    private double precio_final; //precio_final = precio_moto * descuento.

    // Lo unico que introduce el comercial es el mapa de los datos del cliente, el resto de datos se autogestionan en el back.
    public Venta(Comercial comercial, String n_bastidor_moto, double precio_moto, Map<String, String> datos_cliente, double precio_final) {
        this.fecha_venta = new Date();
        this.comercial = comercial; 
        this.n_bastidor_moto = n_bastidor_moto;
        this.precio_moto = precio_moto;
        this.datos_cliente = datos_cliente;
        this.precio_final = precio_final;
    }
    
    public String getFormattedFecha() {
        // Formatear la fecha según el formato AA-mm-DD
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        return sdf.format(fecha_venta);
    }
}
