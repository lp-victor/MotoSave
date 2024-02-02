
package motosave.Modelos;

import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "motocicleta_id")
    private int id_moto;
    private double precio_moto; // precio_moto = precio_compra * beneficio.
    
    @ElementCollection // Necesario para mapear una coleccion de elementos en hibernate.
    @CollectionTable(name = "info_cliente", joinColumns = @JoinColumn(name = "id_venta"))
    @MapKeyColumn(name = "Info")
    @Column(name = "Detalles")
    private Map<String, String> datos_cliente = new HashMap<>();
    private double precio_final; //precio_final = precio_moto * descuento.

    public Venta() {
    }

    // Lo unico que introduce el comercial es el mapa de los datos del cliente, el resto de datos se autogestionan en el back.
    public Venta(Comercial comercial, int id_moto, double precio_moto, Map<String, String> datos_cliente, double precio_final) {
        this.fecha_venta = new Date();
        this.comercial = comercial;
        this.id_moto = id_moto;
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
