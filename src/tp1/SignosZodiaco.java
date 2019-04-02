package tp1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nico
 */
import java.util.Random;

public class SignosZodiaco {

    private final String[] arraySignos = {"Aries" , "Tauro", "Géminis", 
        "Cáncer", "Leo", "Virgo", "Libra", "Escorpio", 
        "Sagitario", "Capricornio", "Acuario", "Piscis"};
    
    public SignosZodiaco(){
        
    }
    
    public String getSigno(){
        Random rand = new Random();
        // Generamos un numero aleatorio entre [0 - 11].
        int indiceRandom = rand.nextInt(12);
        return this.arraySignos[indiceRandom];
    }
}
