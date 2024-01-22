
package Modelos;

/**
 *
 * @author victo
 */
public class Usuario {
    // Salario base para poder calcular un plus por ventas.
    // 100€ x 10 motos vendidas o % por nº de ventas.
    private final double salarioBase = 1400;
    
    private boolean admin;
    private Concesionario lugarTrabajo;
    private String NIF;
    private String nombre;
    private String apellido;
    private double salario;
            
}
