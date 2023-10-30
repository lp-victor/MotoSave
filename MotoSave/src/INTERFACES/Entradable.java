package INTERFACES;

import ACCESODATOS.DAO.EntradaSalida;
import ACCESODATOS.DAO.EntradaSalida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public interface Entradable {

    Scanner sc = new Scanner(System.in);

    /**
     * Pedimos por Scanner un numero y lo guardamos en un String, controlamos
     * que es un numero entero.
     *
     * @param limite son las secciones totales que tenemos en nuestro almacen,
     * controlar que no meta una que no existe.
     * @return de un String a un int.
     */
    public static int pedirEnteroLimite(int limite) {
        boolean flag = false;
        String sol = "";
        while (flag == false) {
            sol = sc.nextLine();
            if (esInt(sol) == true && Integer.valueOf(sol) <= limite) { //con el limite controlo que meta una seccion que exista. 
                flag = true;
            } else {
                System.out.println("Opcion no disponible!");
            }
        }
        return Integer.valueOf(sol) - 1;
    }

    /**
     * Pedimos un numero por Scanner y lo guardamos en un String, comprueba que
     * sea un numero entero.
     *
     * @return un int del string que pedimos por Scanner
     */
    public static int pedirEntero() {
        boolean flag = false;
        String sol = "";
        while (flag == false) {
            sol = sc.nextLine();
            if (esInt(sol) == true) {
                flag = true;
                //Controlar que si tiene que entrar  un 1,2,3 que si mete un 4 le diga que no hay opcion y que vuelva a meter 
            } else {
                System.out.println("Error!. Vuelve a introducir un numero adecuado");
            }
        }
        return Integer.valueOf(sol);
    }

    /**
     * Metodo privado para comprobar que es un Integer
     *
     * @param entrada, nos entra un String
     * @return un boolean true si es Int y false si no lo es.
     */
    private static boolean esInt(String entrada) {
        try {
            Integer.valueOf(entrada);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Metodo que controla la entrada vacia. Se utiliza el metodo isBlank de la
     * clase String.
     *
     * @return un String comunicando que no puedes dejar vacio la entrada.
     */
    public static String controlTecladoVacio() {
        String sol = "";
        do {
            sol = sc.nextLine();
            if (sol.isBlank()) {
                System.out.println("Error: No pueden haber espacios en blanco");
            }
        } while (sol.isBlank());

        return sol;
    }

    /**
     * Metodo que controla la longitud de la contrase�a. Contarse�a tiene que
     * ser de 4 a 15 caracteres.
     *
     * @return un String que es la contrase�a correcta.
     */
    public static String controlTecladoContrasena() {
        String sol = "";
        do {
            sol = sc.nextLine();
            if (sol.length() < 4) {
                System.out.println("Error: Contrase�a muy corta");
                System.out.println("Introducela de nuevo.");
            } else if (sol.length() > 15) {
                System.out.println("Error: Contrase�a muy larga ");
                System.out.println("Introducela de nuevo.");
            }
        } while (sol.length() < 4 || sol.length() > 15);

        return sol;
    }

    /**
     * M�todo que controla que el a�o introducido por el usuario est� dentro del
     * rango permitido. El m�todo utiliza Scanner.nextLine() para leer la
     * entrada del usuario y verifica que el valor ingresado sea de exactamente
     * 4 d�gitos y se encuentre dentro del rango de a�os permitido (2000-2024).
     *
     * @return Una cadena de texto que indica el a�o introducido por el usuario.
     */
    public static String controlTecladoAnio() {
        String anio;

        do {
            System.out.print("Introduce un a�o entre 2020 y el anio actual: ");
            anio = controlTecladoVacio();
            if (anio.length() != 4) {
                System.out.println("Entrada invalida. Debe ser un anio de 4 digitos.");
                continue;
            }
            try {

            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Debe ser un anio de 4 digitos.");
                continue; // se utiliza la palabra clave continue para volver al inicio del bucle si la entrada del usuario es inv�lida.
            }
        } while (Integer.valueOf(anio) < 2020 || Integer.valueOf(anio) > 2024);

        return anio;
    }

    /**
     * M�todo que controla que el mel introducido por el usuario est� dentro del
     * rango permitido. El m�todo utiliza Scanner.nextLine() para leer la
     * entrada del usuario y verifica que el valor ingresado sea de exactamente
     * 2 d�gitos y se encuentre dentro del rango de a�os permitido (1-12).
     *
     * @return Una cadena de texto que indica el mes introducido por el usuario.
     */
    public static String controlTecladoMes() {
        String mes = "\u0000";
        int opcion;
        System.out.println("1 - Ene |  2 - Feb |  3 - Mar |  4 - Abr");
        System.out.println("5 - May |  6 - Jun |  7 - Jul |  8 - Ago");
        System.out.println("9 - Sep | 10 - Oct | 11 - Nov | 12 - Dic");
        System.out.println("0 - Cancelar");
        do {
            System.out.print("Introduce el numero del mes que quieres consultar: ");
            opcion = pedirEntero();
            switch (opcion) {
                case 1:
                    return mes = "01";
                case 2:
                    return mes = "02";
                case 3:
                    return mes = "03";
                case 4:
                    return mes = "04";
                case 5:
                    return mes = "05";
                case 6:
                    return mes = "06";
                case 7:
                    return mes = "07";
                case 8:
                    return mes = "08";
                case 9:
                    return mes = "09";
                case 10:
                    return mes = "10";
                case 11:
                    return mes = "11";
                case 12:
                    return mes = "12";
                default:
                    System.out.println("Mes inv�lido");
            }
        } while (opcion != 0);
        return mes;
    }

// PARA TODAS LAS CONSULTAS SE DEBE HACER EN UNA CLASE POR EJEMPLO BBDD_GARAJE ?
//    public static void agregarMotoBBDD(String matricula, String nombre, String marca, String color, int cc) {
//        Connection con = EntradaSalida.conectarBBDD(" ", " ", " ");
//        try {
//
//            String query = "INSERT INTO motocicleta (matricula, nombre, marca, color, cc) VALUES (?, ?, ?, ?, ?)";
//
//            PreparedStatement pstm = con.prepareStatement(query);
//            
//            pstm.setString(1, matricula);
//            pstm.setString(2, nombre);
//            pstm.setString(3, marca);
//            pstm.setString(4, color);
//            pstm.setInt(5, cc);
//            pstm.executeUpdate();
//
//            pstm.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error:  " + e.toString());
//        }
//        EntradaSalida.desconectarBBDD(con);
}
