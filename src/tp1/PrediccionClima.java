package tp1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author nico
 */
public class PrediccionClima {

//    Clase singleton provee predicciones random 
    private static PrediccionClima single_instance = null;
    String[] predicciones = {
        "Llueve",
        "Nubes",
        "Soleado",
        "Tormenta",
        "Tornado",
        "Terremoto ",
        "Seco",
        "Viento"};

    private PrediccionClima() {
    }

    public static PrediccionClima getInstance() {
        if (single_instance == null) {
            single_instance = new PrediccionClima();
        }
        return single_instance;
    }

    public String getPrediccion() {
        Random rand = new Random();
        int indiceRandom = rand.nextInt(7);
        return predicciones[indiceRandom];
    }

}
