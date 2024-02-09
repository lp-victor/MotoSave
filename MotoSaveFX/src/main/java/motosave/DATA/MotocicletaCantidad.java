package motosave.DATA;
import motosave.Modelos.Motocicleta;

public class MotocicletaCantidad {

    private Motocicleta motocicleta;
    private int cantidad;
    private double precio_final;

    public MotocicletaCantidad() {
    }

    public MotocicletaCantidad(Motocicleta motocicleta, int cantidad) {
        this.motocicleta = motocicleta;
        this.cantidad = cantidad;
        setPrecio_final();
    }


    public Motocicleta getMotocicleta() {
        return motocicleta;
    }

    public void setMotocicleta(Motocicleta motocicleta) {
        this.motocicleta = motocicleta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final() {
        this.precio_final =motocicleta.getPrecio_compra()*LOAD.beneficio;
    }
}
