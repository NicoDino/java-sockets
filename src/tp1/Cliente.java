/**
 *
 * @author nico
 */
package tp1;

import java.util.ArrayList;

public class Cliente {
     private final static int PORT = 5000;
     private final static String SERVER = "127.0.0.1";
     
    public static void main(String[] args) {
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 24; i++) {
            clients.add(new PersonaThread(i,SERVER,PORT));
        }
        for (Thread thread : clients) {
            thread.start();
        }
    }
}

