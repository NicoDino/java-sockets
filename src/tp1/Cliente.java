/**
 *
 * @author nico
 */
package tp1;

import java.util.ArrayList;

public class Cliente {
     private final static int ServidorCentral_PORT = 5000;
     private final static String SERVER = "127.0.0.1";
     
    public static void main(String[] args) {
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 100; i++) {
            clients.add(new ClienteThread(i,SERVER,ServidorCentral_PORT));
        }
        for (Thread thread : clients) {
            thread.start();
        }
    }
}

