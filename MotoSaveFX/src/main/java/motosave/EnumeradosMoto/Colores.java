/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package motosave.EnumeradosMoto;

/**
 * @author MotoSave
 */
public enum Colores {

    ROJO, NEGRO, AZUL, VERDE, BLANCO, NARANJA, AMARILLO, PLATEADO, GRIS;

    public Colores str2Color(String in) {
        switch (in) {
            case "ROJO":
                return ROJO;
            case "NEGRO":
                return NEGRO;
            case "AZUL":
                return AZUL;
            case "PLATEADO":
                return PLATEADO;
            case "VERDE":
                return VERDE;
            case "BLANCO":
                return BLANCO;
            case "NARANJA":
                return NARANJA;
            case "AMARILLO":
                return AMARILLO;
            case "GRIS":
                return GRIS;
            default:
                System.out.println("Error.");
                return null;

        }
    }
}
