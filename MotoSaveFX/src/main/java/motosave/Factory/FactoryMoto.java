
package motosave.Factory;

import motosave.Modelos.Motocicleta;

import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class FactoryMoto {

    public FactoryMoto() {

    }

    public ArrayList<Motocicleta> fabricarMoto(String marca, String modelo, String color, int cantidad) {
        Motocicleta moto = null;
        ArrayList<Motocicleta> motosCreadas = new ArrayList<>();

        for (int i = 0; i <= cantidad; i++) {
            switch (marca) {
                case "BMW":
                    switch (modelo) {
                        case "NINE_T":
                            moto = new Motocicleta("BMW", "NINE_T", color, 1200, 19900);
                            break;
                        case "RT":
                            moto = new Motocicleta("BMW", "RT", color, 1000, 30000);
                            break;
                        case "GS":
                            moto = new Motocicleta("BMW", "GS", color, 1250, 23600);
                            break;
                        case "ADVENTURE":
                            moto = new Motocicleta("BMW", "ADVENTURE", color, 1250, 24000);
                            break;
                        case "SPORT":
                            moto = new Motocicleta("BMW", "SPORT", color, 1250, 18600);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case "DUCATI":
                    switch (modelo) {
                        case "PANIGALE":
                            moto = new Motocicleta("DUCATI", "PANIGALE", color, 1100, 35200);
                            break;
                        case "MONSTER":
                            moto = new Motocicleta("DUCATI", "MONSTER", color, 821, 20000);
                            break;
                        case "DIAVEL":
                            moto = new Motocicleta("DUCATI", "DIAVEL", color, 1158, 29500);
                            break;
                        case "MULTISTRADA":
                            moto = new Motocicleta("DUCATI", "MULTISTRADA", color, 1200, 22000);
                            break;
                        case "HYPERMOTARD":
                            moto = new Motocicleta("DUCATI", "HYPERMOTARD", color, 937, 15700);
                            break;
                        case "STREETFIGHTER":
                            moto = new Motocicleta("DUCATI", "STREETFIGHTER", color, 1100, 23500);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case "HONDA":
                    switch (modelo) {
                        case "CRF":
                            moto = new Motocicleta("HONDA", "CRF", color, 300, 5870);
                            break;
                        case "CBR":
                            moto = new Motocicleta("HONDA", "CBR", color, 600, 11800);
                            break;
                        case "GOLDWING":
                            moto = new Motocicleta("HONDA", "GOLDWING", color, 1800, 40700);
                            break;
                        case "AFRICA_TWIN":
                            moto = new Motocicleta("HONDA", "AFRICA_TWIN", color, 1100, 19900);
                            break;
                        case "REBEL":
                            moto = new Motocicleta("HONDA", "REBEL", color, 500, 7200);
                            break;
                        case "NC":
                            moto = new Motocicleta("HONDA", "NC", color, 750, 8800);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case "KAWASAKI":
                    switch (modelo) {
                        case "NINJA":
                            moto = new Motocicleta("KAWASAKI", "NINJA", color, 1000, 14999);
                            break;
                        case "ZH2":
                            moto = new Motocicleta("KAWASAKI", "ZH2", color, 1000, 19199);
                            break;
                        case "VERSYS":
                            moto = new Motocicleta("KAWASAKI", "VERSYS", color, 650, 9450);
                            break;
                        case "VULCAN":
                            moto = new Motocicleta("KAWASAKI", "VULCAN", color, 900, 8299);
                            break;
                        case "KLX":
                            moto = new Motocicleta("KAWASAKI", "KLX", color, 300, 6500);
                            break;
                        case "CONCOURS":
                            moto = new Motocicleta("KAWASAKI", "CONCOURS", color, 600, 9499);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case "KTM":
                    switch (modelo) {
                        case "DUKE":
                            moto = new Motocicleta("KTM", "DUKE", color, 1390, 22900);
                            break;
                        case "RC":
                            moto = new Motocicleta("KTM", "RC", color, 890, 39990);
                            break;
                        case "ADVENTURE":
                            moto = new Motocicleta("KTM", "ADVENTURE", color, 890, 15200);
                            break;
                        case "EXC":
                            moto = new Motocicleta("KTM", "EXC", color, 300, 11070);
                            break;
                        case "SX_F":
                            moto = new Motocicleta("KTM", "SX_F", color, 250, 11499);
                            break;
                        case "SMC":
                            moto = new Motocicleta("KTM", "SMC", color, 690, 11299);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case "SUZUKI":
                    switch (modelo) {
                        case "GSX":
                            moto = new Motocicleta("SUZUKI", "GSX", color, 1000, 18900);
                            break;
                        case "HAYABUSA":
                            moto = new Motocicleta("SUZUKI", "HAYABUSA", color, 1300, 21995);
                            break;
                        case "V_STROM":
                            moto = new Motocicleta("SUZUKI", "V_STROM", color, 800, 10699);
                            break;
                        case "BURGMAN":
                            moto = new Motocicleta("SUZUKI", "BURGMAN", color, 650, 11199);
                            break;
                        case "BOULEVARD":
                            moto = new Motocicleta("SUZUKI", "BOULEVARD", color, 300, 8350);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case "YAMAHA":
                    switch (modelo) {
                        case "YZF_R6":
                            moto = new Motocicleta("YAMAHA", "YZF_R6", color, 600, 15840);
                            break;
                        case "VMAX":
                            moto = new Motocicleta("YAMAHA", "VMAX", color, 1700, 22499);
                            break;
                        case "TRACER":
                            moto = new Motocicleta("YAMAHA", "TRACER", color, 900, 16600);
                            break;
                        case "TENERE":
                            moto = new Motocicleta("YAMAHA", "TENERE", color, 700, 13099);
                            break;
                        case "XSR":
                            moto = new Motocicleta("YAMAHA", "XSR", color, 125, 4990);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case "DAISVI":
                    switch (modelo) {
                        case "SUPATRI":
                            moto = new Motocicleta("DAISVI", "SUPATRI", color, 300, 8000);
                            break;
                        case "MSF":
                            moto = new Motocicleta("DAISVI", "MSF", color, 600, 11200);
                            break;
                        case "RUBENTURE":
                            moto = new Motocicleta("DAISVI", "RUBENTURE", color, 1200, 30499);
                            break;
                        case "TRAVESEIRA":
                            moto = new Motocicleta("DAISVI", "TRAVESEIRA", color, 1800, 48299);
                            break;
                        case "MS_TOURING":
                            moto = new Motocicleta("DAISVI", "MS_TOURING", color, 900, 16849);
                            break;
                        case "RAPID_LIM":
                            moto = new Motocicleta("DAISVI", "RAPID_LIM", color, 1200, 33999);
                            break;
                        case "BELMONTER":
                            moto = new Motocicleta("DAISVI", "BELMONTER", color, 125, 2600);
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
