package motosave.DATA;

import motosave.Modelos.Motocicleta;

public class MotocicletaCantidad {

    private String modelo;
    private String marca;
    private String color;
    private int cc;
    private double precio_compra;
    private int cantidad;

    //  public double precio_final;

    public MotocicletaCantidad() {
    }

    public MotocicletaCantidad(Motocicleta motocicleta, int cantidad) {
        this.modelo = motocicleta.getModelo();
        this.marca = motocicleta.getMarca();
        this.color = motocicleta.getColor();
        this.cc = motocicleta.getCc();
        this.precio_compra = motocicleta.getPrecio_compra();
        this.cantidad = cantidad;
//        setPrecio_final();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    //    public double getPrecio_final() {
//        return precio_final;
//    }

//    public void setPrecio_final() {
//        this.precio_final = super.getPrecio_compra()*LOAD.beneficio;
//    }


    @Override
    public String toString() {
        return "MotocicletaCantidad{" +
                "modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                ", cc=" + cc +
                ", precio_compra=" + precio_compra +
                ", cantidad=" + cantidad +
                '}';
    }
}
