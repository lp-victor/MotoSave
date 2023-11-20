/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AuxSerializacion;

import Modelo.Garaje;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author victor, Israel, David
 */
public class AuxSerial {

    static File f = new File("./serializados/garajes.object");
    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;

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

        for (Garaje garaje : listarGaraje()) {
            System.out.println(garaje);
        }
    }

//    public static void altaGaraje(Garaje garaje) {
//        String pathGarajesDATA = "./serializados/garajes.object";
//
//        try {
//            if (!f.exists()) {
//
//                oos = new ObjectOutputStream(new FileOutputStream(f, true));
//            } else {
//
//                oos = new myOOS(new FileOutputStream(f, true));
//            }
//
//            oos.writeObject(garaje);
//        } catch (IOException e) {
//            System.err.println("Error al realizar el alta del garaje: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            try {
//                if (oos != null) {
//                    oos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public static ArrayList<Garaje> listarGaraje() {
        ArrayList<Garaje> garajes = new ArrayList<>();
        String pathGarajesDATA = "./serializados/garajes.object";

        try {
            f = new File(pathGarajesDATA);
            ois = new ObjectInputStream(new FileInputStream(f));

            while (true) {
                Object e = ois.readObject();
                garajes.add((Garaje) e);
            }
        } catch (EOFException e) {
            System.out.println("Fichero leído");
        } catch (FileNotFoundException e) {
            System.out.println("Error, el fichero no se encuentra");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return garajes;
    }

    public static void altaGaraje(Garaje garaje) {
        f = new File( "./serializados/" + garaje.getIdGaraje());
        int cont = 0;

        try {

            if (!f.exists()) {
                oos = new ObjectOutputStream(new FileOutputStream(f, true));
            } else {

                oos = new myOOS(new FileOutputStream(f, true));
            }

            oos.writeObject(garaje);

        } catch (FileNotFoundException e) {
            System.out.println("Error, el fichero no se encuentra");

        } catch (IOException e) {
            System.err.println("Error al realizar el alta del garaje: " + e.getMessage());
            e.printStackTrace();

        }

    }
}
