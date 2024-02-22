/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package motosave.EnumeradosMoto;

/**
 * @author victo
 */
public enum Marcas {

    HONDA, YAMAHA, BMW, DUCATI, KAWASAKI, SUZUKI, KTM, DAISVI;


    public Marcas str2Marcas(String in) {
        switch (in) {
            case "HONDA":
                return HONDA;
            case "YAMAHA":
                return YAMAHA;
            case "BMW":
                return BMW;
            case "DUCATI":
                return DUCATI;
            case "KAWASAKI":
                return KAWASAKI;
            case "SUZUKI":
                return SUZUKI;
            case "KTM":
                return KTM;
            case "DAISVI":
                return DAISVI;
            default:
                System.out.println("Error.");
                return null;

        }
    }
}
