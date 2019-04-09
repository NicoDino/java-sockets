/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nico
 */
public class ServidorCentral {

    private final static int PORT = 5000;

    public static void main(String args[]) throws IOException {
        ServerSocket SC_Socket;
        System.out.print("Inicializando servidor central... ");
        try {
            SC_Socket = new ServerSocket(PORT);
            System.out.println("\t[OK]");

            while (true) {
                Socket socket;
                socket = SC_Socket.accept();
                ((ServidorCentralThread) new ServidorCentralThread(socket)).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorCentral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
