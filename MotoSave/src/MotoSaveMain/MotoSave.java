

package MotoSaveMain;

import POJO.DAO.Garaje;


public class MotoSave {

    
    public static void main(String[] args) {
        INTERFACES.ConexionBBDD.conectarBBDD();
        
        Garaje pisha = new Garaje();
        pisha.menuGaraje();
        
        INTERFACES.ConexionBBDD.desconectarBBDD();
    }

}
