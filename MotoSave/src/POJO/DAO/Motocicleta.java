

package POJO.DAO;

import java.io.Serializable;
import java.util.Objects;


public class Motocicleta implements Serializable {

    private String matricula;
    private String nombre;
    private String marca;    
    private String modelo; 
    private String color;   
    private int CC;
    

    
    public Motocicleta(String matricula){
        
    }

    public Motocicleta(String matricula, String marca, String modelo, String color, int CC) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.CC = CC;
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
        if (this.CC != other.CC) {
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

    @Override
    public String toString() {
        return "Motocicleta{" + "matricula=" + matricula + ", nombre=" + marca + ", marca=" + modelo + ", color=" + color + ", CC=" + CC + '}';
    }
    
    public int compareTo(Motocicleta entrada){
        Motocicleta aux = (Motocicleta) entrada;
        return this.color.compareTo(aux.color);
    }
}
