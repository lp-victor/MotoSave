/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.DATA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * La clase Encriptador proporciona funcionalidad para encriptar contraseñas utilizando el algoritmo SHA-256.
 * Este encriptador convierte una contraseña de texto plano en una representación hash segura.
 *
 * @author MotoSave
 */
public class Encriptador {
    /**
     * Encripta una contraseña utilizando el algoritmo SHA-256.
     *
     * @param contraseña La contraseña en texto plano que se va a encriptar.
     * @return La representación hash en formato hexadecimal de la contraseña encriptada.
     */
    public static String encriptarContraseña(String contraseña) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // Crea una caja que encripta
            byte[] hash = md.digest(contraseña.getBytes()); // Mete el String en bytes, lo agita y lo hace de array de 256 byte en este caso, representa el 'hash' de la contraseña
            StringBuilder hexString = new StringBuilder(); // Contruye la representacion hexadecimal del hash

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b); // '0xff & b' se utiliza para quitar el signo al byte y evitar problemas con signos negativos
                if (hex.length() == 1)
                    hexString.append('0'); // Si la representacion hexadecimal es de solo un catacter, metemos un cero para asegurar que todas las representaciones tengan 2 caracteres
                hexString.append(hex); // Concatenamos la representacion hexadecimal en el String hexadecimal
            }

            return hexString.toString(); // Lo pasa de vuelta a String y lo devuelve
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Manejar la excepción de manera adecuada en tu aplicación
            return null;
        }
    }
}
