/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enumerados;

/**
 *
 * @author victo
 */
public enum metodoPersistencia {
    
    JDBC ("JDBC"), FICHEROS ("FICHEROS");
    
    private String tipoPers;

    private metodoPersistencia(String tipoPers) {
        this.tipoPers = tipoPers;
    }

    public String getTipoPers() {
        return tipoPers;
    }

    public void setTipoPers(String tipoPers) {
        this.tipoPers = tipoPers;
    }
            
}
