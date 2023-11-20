/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos.Serializar;

import AccesoDatos.GarajeDAO;
import AuxSerializacion.myOOS;
import Modelo.Garaje;
import Modelo.GarajeExcepcion;
import Modelo.Motocicleta;
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
 *
 * @author victo
 */
public class SerializarGarajeDAO implements GarajeDAO {

    private String pathGarajesDATA = "./serializados/garajes.object";

    File f ;

    //=========ENTRADA=============
    private FileInputStream fis = null;
    private ObjectInputStream ois = null;
    //=========SALIDA=============
    private FileOutputStream fos = null;
    private ObjectOutputStream oos = null;

    @Override
    public boolean altaGaraje(Garaje garaje) {
        f = new File(pathGarajesDATA + "/" + garaje.getIdGaraje());
        int cont = 0;

        try {

            if (!f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new ObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f, true);
                oos = new myOOS(fos);
            }

            oos.writeObject(garaje);

        } catch (FileNotFoundException e) {
            System.out.println("Error, el fichero no se encuentra");
            return false;
        } catch (IOException e) {
            System.err.println("Error al realizar el alta del garaje: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            cerrarRecursos();
        }
        return true;
    }

    @Override
    public boolean bajaGaraje(int idGaraje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Garaje buscarGaraje(int idGaraje) {
        f = new File(pathGarajesDATA + "/" + idGaraje);

        Object e = new Object();

        try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                e = ois.readObject();
            }
        } catch (FileNotFoundException ent) {
            System.out.println("Error, el fichero no se encuentra");
        } catch (ClassNotFoundException ent) {
            System.out.println("Error, la clase no se encuentra");
        } catch (IOException ent) {
            System.out.println("Error, al leer el fichero");
            ent.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return (Garaje) e;
    }

    @Override
    public void modificarGaraje(Garaje garaje) throws GarajeExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int plazasLibres(Garaje garaje) {
        ArrayList<Garaje> garajes = listarGaraje();

        for (Garaje g : garajes) {
            if (g.getIdGaraje() == garaje.getIdGaraje()) {
                return g.getPlazasLibres();
            }
        }
        return -1; // Retorna -1 si no se encuentra el garaje
    }

    @Override
    public ArrayList<Garaje> listarGaraje() {
        ArrayList<Object> objects = new ArrayList();

//         Hardcodeado a muerte ya nos rayaremos la cabeza
        f = new File(pathGarajesDATA);

        Object e;

        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            while (true) {
                e = ois.readObject();
                objects.add(e);

            }
        } catch (EOFException ent) {
            System.out.println("Fichero leido");
        } catch (FileNotFoundException ent) {
            System.out.println("Error, el fichero no se encuentra");
        } catch (ClassNotFoundException ent) {
            System.out.println("Error, la clase no se encuentra");
        } catch (IOException ent) {
            System.out.println("Error, al leer el fichero XXX");
            ent.printStackTrace();
        }
        ArrayList<Garaje> garajes = new ArrayList();

        for (Object object : objects) {
            garajes.add((Garaje) object);
        }

        return garajes;
    }

    @Override
    public int buscarIdGaraje(String nombreGaraje) {
        ArrayList<Garaje> garajes = listarGaraje();

        for (Garaje garaje : garajes) {
            if (garaje.getSucursal().equals(nombreGaraje)) {
                return garaje.getIdGaraje();
            }
        }
        return -1; // Retorna -1 si no se encuentra el garaje con el nombre dado
    }

    private void cerrarRecursos() {
        try {
            if (fos != null) {
                fos.close();
            }
            if (oos != null) {
                oos.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (ois != null) {
                ois.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar los recursos: " + e.getMessage());
        }
    }

}
