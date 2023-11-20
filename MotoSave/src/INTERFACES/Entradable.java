package INTERFACES;

import java.util.Scanner;

/**
 * @author victor, Israel, David
 */

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
    public static boolean esInt(String entrada) {
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

    public static boolean validarMatricula(String matricula) {
        // Verificar que la matrícula tenga 7 caracteres
        if (matricula.length() != 7) {
            return false;
        }
        // Verificar los primeros 4 char son numeros, con el metodo Character.isDigit comprueba que el char es numero.
        for (int i = 0; i < 3; i++) {
            if (!Character.isDigit(matricula.charAt(i))) {
                return false;
            }
        }
        // Verificar los últimos 3 char son letras, con el metodo Character.isLetter comprueba que el char es una letra.
        for (int i = 4; i < 6; i++) {
            if (!Character.isLetter(matricula.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}

