
package motosave.Factory;

import motosave.Modelos.Motocicleta;
import motosave.EnumeradosMoto.*;

import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class FactoryMoto {

    public FactoryMoto() {

    }

    public ArrayList<Motocicleta> fabricarMotos(Marcas marca, String modelo, Colores color, int cantidad) {
        Motocicleta moto = null;
        ArrayList<Motocicleta> motosCreadas = new ArrayList<>();

        for (int i = 0; i <= cantidad; i++) {
            switch (marca) {
                case BMW:
                    switch (modelo) {
                        case "NINE_T":
                            moto = new Motocicleta("BMW", "NINE_T", color.toString(), 1200, 19900);
                            break;
                        case "RT":
                            moto = new Motocicleta("BMW", "RT", color.toString(), 1000, 30000);
                            break;
                        case "GS":
                            moto = new Motocicleta("BMW", "GS", color.toString(), 1250, 23600);
                            break;
                        case "ADVENTURE":
                            moto = new Motocicleta("BMW", "ADVENTURE", color.toString(), 1250, 24000);
                            break;
                        case "SPORT":
                            moto = new Motocicleta("BMW", "SPORT", color.toString(), 1250, 18600);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case DUCATI:
                    switch (modelo) {
                        case "PANIGALE":
                            moto = new Motocicleta("DUCATI", "PANIGALE", color.toString(), 1100, 35200);
                            break;
                        case "MONSTER":
                            moto = new Motocicleta("DUCATI", "MONSTER", color.toString(), 821, 20000);
                            break;
                        case "DIAVEL":
                            moto = new Motocicleta("DUCATI", "DIAVEL", color.toString(), 1158, 29500);
                            break;
                        case "MULTISTRADA":
                            moto = new Motocicleta("DUCATI", "MULTISTRADA", color.toString(), 1200, 22000);
                            break;
                        case "HYPERMOTARD":
                            moto = new Motocicleta("DUCATI", "HYPERMOTARD", color.toString(), 937, 15700);
                            break;
                        case "STREETFIGHTER":
                            moto = new Motocicleta("DUCATI", "STREETFIGHTER", color.toString(), 1100, 23500);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case HONDA:
                    switch (modelo) {
                        case "CRF":
                            moto = new Motocicleta("HONDA", "CRF", color.toString(), 300, 5870);
                            break;
                        case "CBR":
                            moto = new Motocicleta("HONDA", "CBR", color.toString(), 600, 11800);
                            break;
                        case "GOLDWING":
                            moto = new Motocicleta("HONDA", "GOLDWING", color.toString(), 1800, 40700);
                            break;
                        case "AFRICA_TWIN":
                            moto = new Motocicleta("HONDA", "AFRICA_TWIN", color.toString(), 1100, 19900);
                            break;
                        case "REBEL":
                            moto = new Motocicleta("HONDA", "REBEL", color.toString(), 500, 7200);
                            break;
                        case "NC":
                            moto = new Motocicleta("HONDA", "NC", color.toString(), 750, 8800);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case KAWASAKI:
                    switch (modelo) {
                        case "NINJA":
                            moto = new Motocicleta("KAWASAKI", "NINJA", color.toString(), 1000, 14999);
                            break;
                        case "ZH2":
                            moto = new Motocicleta("KAWASAKI", "ZH2", color.toString(), 1000, 19199);
                            break;
                        case "VERSYS":
                            moto = new Motocicleta("KAWASAKI", "VERSYS", color.toString(), 650, 9450);
                            break;
                        case "VULCAN":
                            moto = new Motocicleta("KAWASAKI", "VULCAN", color.toString(), 900, 8299);
                            break;
                        case "KLX":
                            moto = new Motocicleta("KAWASAKI", "KLX", color.toString(), 300, 6500);
                            break;
                        case "CONCOURS":
                            moto = new Motocicleta("KAWASAKI", "CONCOURS", color.toString(), 600, 9499);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case KTM:
                    switch (modelo) {
                        case "DUKE":
                            moto = new Motocicleta("KTM", "DUKE", color.toString(), 1390, 22900);
                            break;
                        case "RC":
                            moto = new Motocicleta("KTM", "RC", color.toString(), 890, 39990);
                            break;
                        case "ADVENTURE":
                            moto = new Motocicleta("KTM", "ADVENTURE", color.toString(), 890, 15200);
                            break;
                        case "EXC":
                            moto = new Motocicleta("KTM", "EXC", color.toString(), 300, 11070);
                            break;
                        case "SX_F":
                            moto = new Motocicleta("KTM", "SX_F", color.toString(), 250, 11499);
                            break;
                        case "SMC":
                            moto = new Motocicleta("KTM", "SMC", color.toString(), 690, 11299);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case SUZUKI:
                    switch (modelo) {
                        case "GSX":
                            moto = new Motocicleta("SUZUKI", "GSX", color.toString(), 1000, 18900);
                            break;
                        case "HAYABUSA":
                            moto = new Motocicleta("SUZUKI", "HAYABUSA", color.toString(), 1300, 21995);
                            break;
                        case "V_STROM":
                            moto = new Motocicleta("SUZUKI", "V_STROM", color.toString(), 800, 10699);
                            break;
                        case "BURGMAN":
                            moto = new Motocicleta("SUZUKI", "BURGMAN", color.toString(), 650, 11199);
                            break;
                        case "BOULEVARD":
                            moto = new Motocicleta("SUZUKI", "BOULEVARD", color.toString(), 300, 8350);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case YAMAHA:
                    switch (modelo) {
                        case "YZF_R6":
                            moto = new Motocicleta("YAMAHA", "YZF_R6", color.toString(), 600, 15840);
                            break;
                        case "VMAX":
                            moto = new Motocicleta("YAMAHA", "VMAX", color.toString(), 1700, 22499);
                            break;
                        case "TRACER":
                            moto = new Motocicleta("YAMAHA", "TRACER", color.toString(), 900, 16600);
                            break;
                        case "TENERE":
                            moto = new Motocicleta("YAMAHA", "TENERE", color.toString(), 700, 13099);
                            break;
                        case "XSR":
                            moto = new Motocicleta("YAMAHA", "XSR", color.toString(), 125, 4990);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case DAISVI:
                    switch (modelo) {
                        case "SUPATRI":
                            moto = new Motocicleta("DAISVI", "SUPATRI", color.toString(), 300, 8000);
                            break;
                        case "MSF":
                            moto = new Motocicleta("DAISVI", "MSF", color.toString(), 600, 11200);
                            break;
                        case "RUBENTURE":
                            moto = new Motocicleta("DAISVI", "RUBENTURE", color.toString(), 1200, 30499);
                            break;
                        case "TRAVESEIRA":
                            moto = new Motocicleta("DAISVI", "TRAVESEIRA", color.toString(), 1800, 48299);
                            break;
                        case "MS_TOURING":
                            moto = new Motocicleta("DAISVI", "MS_TOURING", color.toString(), 900, 16849);
                            break;
                        case "RAPID_LIM":
                            moto = new Motocicleta("DAISVI", "RAPID_LIM", color.toString(), 1200, 33999);
                            break;
                        case "BELMONTER":
                            moto = new Motocicleta("DAISVI", "BELMONTER", color.toString(), 125, 2600);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                default:
                    throw new AssertionError();
            }

            motosCreadas.add(moto);
        }

        return motosCreadas;
    }

}
