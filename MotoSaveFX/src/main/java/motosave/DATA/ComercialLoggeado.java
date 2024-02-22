package motosave.DATA;

import motosave.Modelos.Comercial;
/**
 * La clase ComercialLoggeado proporciona funcionalidad para almacenar y recuperar un objeto Comercial
 * que representa al usuario comercial que ha iniciado sesión en el sistema.
 * Utiliza el patrón SINGLETON para garantizar que solo exista una instancia de Comercial almacenada en todo el sistema.
 * Esta clase es útil para mantener la sesión del usuario comercial durante la duración de la aplicación.
 *
 * @author MotoSave
 */
public class ComercialLoggeado {

    /**
     * Recupera el objeto Comercial que representa al usuario comercial que ha iniciado sesión.
     *
     * @return El objeto Comercial del usuario comercial que ha iniciado sesión.
     */
    private static Comercial comercial = null;

    public static Comercial getComercialLoggeado() {
        return comercial;
    }

    /**
     * Establece el objeto Comercial que representa al usuario comercial que ha iniciado sesión.
     *
     * @param comercial_e El objeto Comercial que se va a establecer como usuario comercial que ha iniciado sesión.
     */
    public static void setComercialLoggeado(Comercial comercial_e) {
        comercial = comercial_e;
    }

}
