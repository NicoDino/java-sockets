/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author nico
 */
public class PrediccionHoroscopo {
//    Clase singleton provee predicciones random 

    private static PrediccionHoroscopo single_instance = null;
    String[] predicciones = {
        "No pretendas apagar con fuego un incendio, ni remediar con agua una inundación",
        "Cuando el sabio señala a la luna, el necio mira el dedo",
        "Podemos volvernos sabios a través de tres formas distintas. Primero, a través de la reflexión que es la más noble. Segundo, por imitación que es la más fácil. Y la tercera por experiencia, que es la más amarga",
        "Nunca hagas apuestas. Si sabes que has de ganar, eres un pícaro; y si no lo sabes, eres tonto",
        "Antes de empezar un viaje de venganza cava dos tumbas",
        "El silencio es el único amigo que jamás traiciona ",
        "No hay cosa más fría que un consejo cuya aplicación sea imposible",
        "Tres pueden mantener un secreto, si te deshaces de dos"};

    private PrediccionHoroscopo() {
    }

    public static PrediccionHoroscopo getInstance() {
        if (single_instance == null) {
            single_instance = new PrediccionHoroscopo();
        }
        return single_instance;
    }

    public String getPrediccion() {
         Random rand = new Random();
        int indiceRandom = rand.nextInt(7);
        return predicciones[indiceRandom];
    }

}
