package motosave.DATA;

import motosave.Modelos.Motocicleta;

public class MotocicletaCantidad {

    private String concesionario;
    private Motocicleta motocicleta;
    private String modelo;
    private String marca;
    private String color;
    private int cc;
    private double precio_compra;
    public double precio_final;
    private int cantidad;

    //  public double precio_final;

    public MotocicletaCantidad() {
    }

    public MotocicletaCantidad(Motocicleta motocicleta, int cantidad) {
        this.concesionario = motocicleta.getConcesionario().getUbicacion();
        this.motocicleta = motocicleta;
        this.modelo = motocicleta.getModelo();
        this.marca = motocicleta.getMarca();
        this.color = motocicleta.getColor();
        this.cc = motocicleta.getCc();
        this.precio_compra = motocicleta.getPrecio_compra();
        this.cantidad = cantidad;
//        setPrecio_final();
        setPrecio_final(motocicleta.getPrecio_compra());
    }


    public int getCantidad() {
        return cantidad;
    }


    public double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(double precio_compra) {
        this.precio_final = (int) (precio_compra * LOAD.beneficio);
    }

    public String getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(String concesionario) {
        this.concesionario = concesionario;
    }

    //    public void setPrecio_final() {
//        this.precio_final = super.getPrecio_compra()*LOAD.beneficio;
//    }
    public Motocicleta getMotocicleta() {
        return motocicleta;
    }

    public void setMotocicleta(Motocicleta motocicleta) {
        this.motocicleta = motocicleta;
    }

    @Override
    public String toString() {
        return "MotocicletaCantidad{" +
                "concesionario='" + concesionario + '\'' +
                ", motocicleta=" + motocicleta +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                ", cc=" + cc +
                ", precio_compra=" + precio_compra +
                ", precio_final=" + precio_final +
                ", cantidad=" + cantidad +
                '}';
    }
}