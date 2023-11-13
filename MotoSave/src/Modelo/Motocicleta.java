

package Modelo;

import java.io.Serializable;
import java.util.Objects;


public class Motocicleta implements Serializable {
    
    private int idGaraje;
    private String matricula;
    private String marca;    
    private String modelo; 
    private String color;   
    private int CC;
    private int precio;
    
    public Motocicleta(){
    }
    
    public Motocicleta(String matricula){
    }

    public Motocicleta(int idGaraje, String matricula, String marca, String modelo, String color, int CC, int precio) {
        this.idGaraje = idGaraje;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.CC = CC;
        this.precio = precio;
    }

    public int getIdGaraje() {
        return idGaraje;
    }

    public void setIdGaraje(int idGaraje) {
        this.idGaraje = idGaraje;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public int getCC() {
        return CC;
    }

    public void setCC(int CC) {
        this.CC = CC;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Motocicleta{" + "idGaraje=" + idGaraje + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color + ", CC=" + CC + ", precio=" + precio + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Motocicleta other = (Motocicleta) obj;
        if (this.idGaraje != other.idGaraje) {
            return false;
        }
        if (this.CC != other.CC) {
            return false;
        }
        if (this.precio != other.precio) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        return Objects.equals(this.color, other.color);
    }
   
    
}
