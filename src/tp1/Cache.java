/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author nico
 */
public class Cache {

    private final ConcurrentHashMap<String, String> cacheHoroscopo;
    private final ConcurrentHashMap<String, String> cachePronostico;
    private static Cache single_instance = null;

    private Cache() {
        cacheHoroscopo = new ConcurrentHashMap<>();
        cachePronostico = new ConcurrentHashMap<>();
    }

    public static Cache getInstance() {
        if (single_instance == null) {
            single_instance = new Cache();
        }
        return single_instance;
    }

    public String getHoroscopo(String signo) {
//        System.out.println("H " + cacheHoroscopo.get(signo));
        return cacheHoroscopo.get(signo);
    }

    public String getPronostico(String fecha) {
//        System.out.println("P " + cachePronostico.get(fecha));
        return cachePronostico.get(fecha);
    }

    public void addHoroscopo(String signo, String horoscopo) {
        cacheHoroscopo.putIfAbsent(signo, horoscopo);
    }

    public void addPronostico(String fecha, String pronostico) {
        cachePronostico.putIfAbsent(fecha, pronostico);
    }

}
