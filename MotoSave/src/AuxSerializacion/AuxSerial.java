/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AuxSerializacion;

import Modelo.Garaje;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class AuxSerial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Garaje garaje_aux1 = new Garaje(1, "Madrid", 10);
        Garaje garaje_aux2 = new Garaje(2, "Sevilla", 10);
        Garaje garaje_aux3 = new Garaje(3, "Barcelona", 10);
        ArrayList<Garaje> garajes = new ArrayList<>();

        garajes.add(garaje_aux1);
        garajes.add(garaje_aux2);
        garajes.add(garaje_aux3);

        for (Garaje garaje : garajes) {
            altaGaraje(garaje);
        }

    }

    public static void altaGaraje(Garaje garaje) {
        String pathGarajesDATA = "./serializados/garajes.obj";

        File f = null;
        //=========ENTRADA=============
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        //=========SALIDA=============
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        f = new File(pathGarajesDATA);
        int cont = 0;

        try {

            if (!f.exists()) {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f, true);
                oos = new myOOS(fos);
            }

            oos.writeObject(garaje);

        } catch (FileNotFoundException e) {
            System.out.println("Error, el fichero no se encuentra");

        } catch (IOException e) {
            System.err.println("Error al realizar el alta del garaje: " + e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                
            if (fos != null) {
                fos.close();
            }
            if (oos != null) {
                oos.close();
            }
            }catch (IOException e){
                
            }
        }

    }

}
