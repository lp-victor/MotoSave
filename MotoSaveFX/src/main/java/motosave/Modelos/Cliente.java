package motosave.Modelos;

import jakarta.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String correo;
    private int telefono;
    private String direccion;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String correo, int telefono, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}
