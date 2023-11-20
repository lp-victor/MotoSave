package AccesoDatos.Serializar;

import AccesoDatos.MotocicletaDAO;
import Modelo.Garaje;
import Modelo.Motocicleta;
import Modelo.MotocicletaExcepcion;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializarMotocicletaDAO implements MotocicletaDAO {

    private static String pathDATA = "./serializados/";

    private static File f = null;
    //=========ENTRADA=============
    private static FileInputStream fis = null;
    private static ObjectInputStream ois = null;
    //=========SALIDA=============
    private static FileOutputStream fos = null;
    private static ObjectOutputStream oos = null;

    @Override
    public boolean altaMoto(Motocicleta moto) {
        f = new File(pathDATA + moto.getIdGaraje() + ".object");
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f, true));
            oos.writeObject(moto);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    @Override
    public boolean bajaMoto(String matricula) {
        ArrayList<Motocicleta> motocicletas = listarMotocicletas();
        try {
            for (Motocicleta moto : motocicletas) {
                int idGaraje_aux = moto.getIdGaraje();
                if (moto.getMatricula().equals(matricula)) {
                    motocicletas.remove(moto);
                    guardarMotos(motocicletas, idGaraje_aux); // No se si fufará hay que probarlo  
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Motocicleta buscarMoto(String matricula)  {
        ArrayList<Motocicleta> motos = listarMotocicletas();
        for (Motocicleta moto : motos) {
            if (moto.getMatricula().equals(matricula)) {
                return moto;
            }
        }
        return null;
    }

    @Override
    public void modificarMoto(Motocicleta moto) throws MotocicletaExcepcion {
        if (bajaMoto(moto.getMatricula())) {
            altaMoto(moto);
        } else {
            throw new MotocicletaExcepcion("La motocicleta no existe en el sistema.");
        }
    }

    @Override
    public void cambiarDeGaraje(Motocicleta moto, Garaje garaje) throws MotocicletaExcepcion {
        ArrayList<Motocicleta> motos = listarMotocicletas();

        boolean encontrada = false;
        for (Motocicleta m : motos) {
            if (m.getMatricula().equals(moto.getMatricula())) {
                m.setIdGaraje(garaje.getIdGaraje());
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            throw new MotocicletaExcepcion("La motocicleta no se encuentra en el sistema.");
        }
        guardarMotos(motos, garaje.getIdGaraje());
    }

    @Override
    public ArrayList<Motocicleta> listarMotocicletas() {
        ArrayList<Motocicleta> motos = new ArrayList<>();
        try {
            fis = new FileInputStream(pathDATA);
            ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Motocicleta moto = (Motocicleta) ois.readObject();
                    motos.add(moto);
                } catch (EOFException e) {
                    break; // Se alcanzó el final del archivo
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Manejo básico de excepciones para depuración
        } finally {
            cerrarRecursos();
        }
        return motos;
    }

    @Override
    public ArrayList<Motocicleta> listarMotocicletasGaraje(int idGaraje) throws MotocicletaExcepcion {
        ArrayList<Motocicleta> motos = new ArrayList<>();
        f = new File(pathDATA+idGaraje+".object");
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Motocicleta moto = (Motocicleta) ois.readObject();
                    motos.add(moto);
                } catch (EOFException e) {
                    break; // Se alcanzó el final del archivo
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Manejo básico de excepciones para depuración
        } finally {
            cerrarRecursos();
        }
        return motos;
    }

    @Override
    public void moverMoto(Motocicleta moto, Garaje garaje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void guardarMotos(ArrayList<Motocicleta> motos, int idGaraje) {
        f = new File(pathDATA+idGaraje+".object");
        try {
            fos = new FileOutputStream(pathDATA);
            oos = new ObjectOutputStream(fos);

            for (Motocicleta moto : motos) {
                oos.writeObject(moto);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
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
