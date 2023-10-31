package AccesoDatos.Serializar;





import AccesoDatos.MotocicletaDAO;
import Modelo.Garaje;
import Modelo.GarajeExcepcion;
import Modelo.Motocicleta;
import Modelo.MotocicletaExcepcion;
import java.util.ArrayList;


public class SerializarMotocicletaDAO implements MotocicletaDAO {       
    
    @Override
    public void altaMoto(Motocicleta moto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void bajaMoto(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Motocicleta buscarMoto(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificarMoto(Motocicleta moto) throws MotocicletaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cambiarDeGaraje(Motocicleta moto, Garaje garaje) throws MotocicletaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Motocicleta> listarMotocicletas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Motocicleta> listarMotocicletasGaraje(int idGaraje) throws GarajeExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
