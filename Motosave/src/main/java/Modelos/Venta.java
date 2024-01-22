
package Modelos;

/**
 *
 * @author victo
 */
public class Venta {

    private double porcBeneficio = 1.40;

    private Cliente comprador;
    private Usuario vendedor;
    private Concesionario lugarVenta;
    private Motocicleta motocicletaVendida;
    private double descuento;
    private double totalVenta;

    public Venta(Cliente comprador, Usuario vendedor, Concesionario lugarVenta, Motocicleta motocicletaVendida, double descuento) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.lugarVenta = lugarVenta;
        this.descuento = descuento;
        this.motocicletaVendida = motocicletaVendida;
        setTotalVenta();
    }

    public double getPorcBeneficio() {
        return porcBeneficio;
    }

    public void setPorcBeneficio(double porcBeneficio) {
        this.porcBeneficio = porcBeneficio;
    }

    public Cliente getComprador() {
        return comprador;
    }

    public void setComprador(Cliente comprador) {
        this.comprador = comprador;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Concesionario getLugarVenta() {
        return lugarVenta;
    }

    public void setLugarVenta(Concesionario lugarVenta) {
        this.lugarVenta = lugarVenta;
    }

    public Motocicleta getMotocicletaVendida() {
        return motocicletaVendida;
    }

    public void setMotocicletaVendida(Motocicleta motocicletaVendida) {
        this.motocicletaVendida = motocicletaVendida;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    private void setTotalVenta() {
        this.totalVenta = (motocicletaVendida.getPrecioCosteMoto() * porcBeneficio) - ((motocicletaVendida.getPrecioCosteMoto() * porcBeneficio) * descuento);;
    }

    @Override
    public String toString() {
        return "Venta{" + "porcBeneficio=" + porcBeneficio + ", comprador=" + comprador + ", vendedor=" + vendedor + ", lugarVenta=" + lugarVenta + ", motocicletaVendida=" + motocicletaVendida + ", descuento=" + descuento + ", totalVenta=" + totalVenta + '}';
    }
    
}
