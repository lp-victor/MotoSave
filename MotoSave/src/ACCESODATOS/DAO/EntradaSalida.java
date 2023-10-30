package ACCESODATOS.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EntradaSalida {

    private static File f = null;
    private static FileWriter fw = null;
    //=========ENTRADA=============
    private static FileInputStream fis = null;
    private static ObjectInputStream ois = null;
    //=========SALIDA=============
    private static FileOutputStream fos = null;
    private static ObjectOutputStream oos = null;
    //=========JDBC===============

    public static ArrayList<String> leerCSV(String path) {
        f = new File(path);
        ArrayList<String> sol = new ArrayList();

        Scanner sc = null;
        String entrada = "";

        try {
            sc = new Scanner(f);
            try {

                while (sc.hasNextLine()) {//si tienes una siguiente linea sigues,,, eso significa hasnextline    
                    entrada = sc.nextLine();
                    //  System.out.println(entrada);

                    String entrada_tratada[] = entrada.split(",");
                    //new Moptocicleta tenia esto de entrada (); entrada_tratada[2], Integer.valueOf(entrada_tratada[3])
                    //Motocicleta aux = new Motocicleta();
                    //aux1.add(aux);

                }
            } catch (NoSuchElementException ex) {
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error IO: " + ex.toString());
            ex.printStackTrace();
        } finally {

            if (sc != null) {
                sc.close();
            }
        }
        return sol;
    }

    public static void guardarCSV(String entrada, String path) {

        ArrayList<String> ventas = new ArrayList();
        ventas.add(entrada);

        try {
//            f = new File("./CSV");
//            f.mkdir();//Crear carpeta de CSV
            f = new File(path);
            f.createNewFile();//Crea el archivo
            fw = new FileWriter(f, true);//si quitamos el true , sobreescribe lo que habia!    APPEND DESACTIVADO, CAMBIARLO AL FINALIZAR EL PROGRAMA
            for (String v : ventas) {
                //fw.write(v.split());
                fw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el fichero");
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                System.out.println("Error a la hora de cerrar el programa");
                ex.printStackTrace();
            }
        }
    }

    //FICHERO PARA GUARDAR EL GARAJE CON SUS MOTOCICLETAS
    public static Object leerObj(String path) {

        Object e = new Object();

        try (FileInputStream fis = new FileInputStream(path); ObjectInputStream ois = new ObjectInputStream(fis)) {

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
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ent) {
                System.out.println("Error, al cerrar el archivo");
                ent.printStackTrace();
            }
        }
        return e;

    }

    public static void guardarObj(Object entrada, String path) {

        int cont = 0;
        try {

            fos = new FileOutputStream(path, true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(entrada);

        } catch (FileNotFoundException e) {
            System.out.println("Error, el fichero no se encuentra");
        } catch (IOException e) {
            System.out.println("Error, al abrir el archivo");
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                System.out.println("Error, al cerrar el archivo");
                e.printStackTrace();
            }
        }
    }
}
