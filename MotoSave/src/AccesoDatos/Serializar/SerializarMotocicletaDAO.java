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

/**
 * @author victor, Israel, David
 */

public class SerializarMotocicletaDAO implements MotocicletaDAO {

    private static String pathDATA = "./serializados/";

    private static File f = null;
    //=========ENTRADA=============
    private static FileInputStream fis = null;
    private static ObjectInputStream ois = null;
    //=========SALIDA=============
    private static FileOutputStream fos = null;
    private static ObjectOutputStream oos = null;

    /**
     * Almacena una motocicleta en un archivo.
     *
     * @param moto La motocicleta a almacenar.
     * @return true si se almacena correctamente, false si ocurre algún error.
     */
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

    /**
     * Elimina una motocicleta del archivo según su matrícula.
     *
     * @param matricula La matrícula de la motocicleta a eliminar.
     * @return true si se elimina correctamente, false si ocurre algún error.
     */
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

    /**
     * Busca una motocicleta en el archivo según su matrícula.
     *
     * @param matricula La matrícula de la motocicleta a buscar.
     * @return La motocicleta encontrada o null si no se encuentra.
     */
    @Override
    public Motocicleta buscarMoto(String matricula) {
        ArrayList<Motocicleta> motos = listarMotocicletas();
        for (Motocicleta moto : motos) {
            if (moto.getMatricula().equals(matricula)) {
                return moto;
            }
        }
        return null;
    }

    /**
     * Modifica los detalles de una motocicleta en el archivo.
     *
     * @param moto La motocicleta con los detalles modificados.
     * @throws MotocicletaExcepcion si hay un error al modificar la motocicleta.
     */
    @Override
    public void modificarMoto(Motocicleta moto) throws MotocicletaExcepcion {
        if (bajaMoto(moto.getMatricula())) {
            altaMoto(moto);
        } else {
            throw new MotocicletaExcepcion("La motocicleta no existe en el sistema.");
        }
    }

    /**
     * Cambia la asignación de un garaje para una motocicleta.
     *
     * @param moto La motocicleta a mover.
     * @param garaje El garaje al que se moverá la motocicleta.
     * @throws MotocicletaExcepcion si hay un error al cambiar el garaje de la
     * motocicleta.
     */
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

    /**
     * Obtiene una lista de todas las motocicletas almacenadas en el archivo.
     *
     * @return Una lista de motocicletas si se recuperan correctamente, null si
     * hay un error.
     */
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

    /**
     * Obtiene una lista de motocicletas almacenadas en un garaje específico.
     *
     * @param idGaraje ID del garaje del que se desea obtener la lista de
     * motocicletas.
     * @return Una lista de motocicletas en el garaje especificado.
     * @throws MotocicletaExcepcion si hay un error al obtener la lista de
     * motocicletas del garaje.
     */
    @Override
    public ArrayList<Motocicleta> listarMotocicletasGaraje(int idGaraje) throws MotocicletaExcepcion {
        ArrayList<Motocicleta> motos = new ArrayList<>();
        f = new File(pathDATA + idGaraje + ".object");
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

    /**
     * Guarda una lista de motocicletas en un archivo correspondiente al ID del
     * garaje.
     *
     * @param motos La lista de motocicletas a guardar.
     * @param idGaraje El ID del garaje que identifica el archivo.
     */
    private void guardarMotos(ArrayList<Motocicleta> motos, int idGaraje) {
        f = new File(pathDATA + idGaraje + ".object");
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

    /**
     * Cierra los recursos de entrada/salida utilizados para la manipulación de
     * archivos.
     */
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
