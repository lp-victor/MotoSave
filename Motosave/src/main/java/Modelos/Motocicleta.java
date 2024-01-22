
package Modelos;

/**
 *
 * @author victo
 */

public class Motocicleta {
    
    private Concesionario concesionario;
    private String nBastidor;
    private String marca;
    private String modelo;
    private String color;
    private int cc;
    private double precioCosteMoto;    
    
    public Motocicleta(Concesionario concesionario, String nBastidor, String marca, String modelo, String color, int cc, double precioCosteMoto) {
        this.concesionario = concesionario;
        this.nBastidor = nBastidor;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.cc = cc;
        this.precioCosteMoto = precioCosteMoto;
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }

    public String getnBastidor() {
        return nBastidor;
    }

    public void setnBastidor(String nBastidor) {
        this.nBastidor = nBastidor;
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

    public double getPrecioCosteMoto() {
        return precioCosteMoto;
    }

    public void setPrecioCosteMoto(double precioCosteMoto) {
        this.precioCosteMoto = precioCosteMoto;
    }

    @Override
    public String toString() {
        return "Motocicleta{" + "concesionario=" + concesionario + ", nBastidor=" + nBastidor + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color + ", cc=" + cc + ", precioCosteMoto=" + precioCosteMoto + '}';
    }
    
}
