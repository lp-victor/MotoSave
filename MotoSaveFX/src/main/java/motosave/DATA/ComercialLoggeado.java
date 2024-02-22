package motosave.DATA;

import motosave.Modelos.Comercial;

public class ComercialLoggeado {

    // Clase con patron singleton

    private static Comercial comercial = null;

    private ComercialLoggeado() {
    }

    public static Comercial getComercialLoggeado() {
        return comercial;
    }

    public static void setComercialLoggeado(Comercial comercial_e) {
        comercial = comercial_e;
    }

}
