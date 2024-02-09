package motosave.DATA;
import motosave.Modelos.Motocicleta;

public class MotocicletaCantidad extends Motocicleta{

    public int cantidad;
    public double precio_final;

    public MotocicletaCantidad() {
    }

    public MotocicletaCantidad(Motocicleta motocicleta, int cantidad) {
        this.cantidad = cantidad;
        setPrecio_final();
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
        this.precio_final = super.getPrecio_compra()*LOAD.beneficio;
    }
}
