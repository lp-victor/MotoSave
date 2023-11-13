package AuxSerializacion;

import Modelo.Garaje;
import Modelo.Motocicleta;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializacion {
    
    private static final String pathGarajesDATA = "./serializados/Garajes";
    private static String pathMotocicletasDATA = "./serializados/Motocicletas";
    
    private static File f = null;
    //=========ENTRADA=============
    private static FileInputStream fis = null;
    private static ObjectInputStream ois = null;
    //=========SALIDA=============
    private static FileOutputStream fos = null;
    private static ObjectOutputStream oos = null;

    //FICHERO PARA GUARDAR EL GARAJE CON SUS MOTOCICLETAS
    public static Object leerGaraje(Garaje garaje) {
        f = new File (pathGarajesDATA + "/" + garaje.getSucursal());
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
    
    public static Object leerMotocicleta(Motocicleta moto) {
        f = new File (pathGarajesDATA + "/" + moto.getMatricula());
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
    
    // Este se usa solo para cuando se borra un elemento del fichero .data
    private static void guardarGaraje(Garaje garaje) {
        f = new File (pathGarajesDATA + "/" + garaje.getSucursal());
        int cont = 0;
        
        try {
            
            if(!f.exists()){
                fos=new FileOutputStream(f,true);
                oos=new ObjectOutputStream(fos);
            } else {
                fos=new FileOutputStream(f,true);
                oos =new myOOS(fos);
            }
            
            oos.writeObject(garaje);

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
    
        // Este se usa solo para cuando se borra un elemento del fichero .data
    private static boolean guardarMotocicleta(Motocicleta moto) {
        f = new File (pathGarajesDATA + "/" + moto.getMatricula());
        int cont = 0;
        
        try {            
            if(!f.exists()){
                fos=new FileOutputStream(f,true);
                oos=new ObjectOutputStream(fos);
            } else {
                fos=new FileOutputStream(f,true);
                oos =new myOOS(fos);
            }            
            oos.writeObject(moto);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error, el fichero no se encuentra");
            return false;
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
        return false;
    }
    
    public static void guardarGaraje(ArrayList<Garaje> entrada) {
        int cont = 0;
        
        try {
            
            if(!f.exists()){
                fos=new FileOutputStream(f,true);
                oos=new ObjectOutputStream(fos);
            } else {
                fos=new FileOutputStream(f,true);
                oos =new myOOS(fos);
            }
            
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
