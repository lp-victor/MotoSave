package motosave.Modelos;

import motosave.DATA.Encriptador;
import jakarta.persistence.*;

import java.util.ArrayList;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "comercial")
public class Comercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comercial;

    // Relación con Concesionario (muchos comerciales pertenecen a un concesionario)
    @ManyToOne
    @JoinColumn(name = "id_concesionario")
    private Concesionario concesionario;

    // Relación con Venta (un comercial realiza muchas ventas)
    private String usuario;
    private String contraseña;
    private String NIF;
    private String nombre;
    private String apellido;
    private double salario; // Campo calculado con las ventas realizadas

    public Comercial() {
    }

    public Comercial(Concesionario concesionario, String usuario, String contraseña, String NIF, String nombre, String apellido) {
        this.concesionario = concesionario;
        this.usuario = usuario;
        setContrasena(contraseña); // La contraseña se encripta segun se crea el comercial
        this.NIF = NIF;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getId_comercial() {
        return id_comercial;
    }

    public void setId_comercial(int id_comercial) {
        this.id_comercial = id_comercial;
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setContrasena(String contraseña) {
        this.contraseña = Encriptador.encriptarContraseña(contraseña);
    }




}
