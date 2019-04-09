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
public class ServidorPronostico {
   private final static int PORT = 5002;

    public static void main(String args[]) throws IOException {
        ServerSocket SH_Socket;
        System.out.print("Inicializando servidor de Pronósticos... ");
        try {
            SH_Socket = new ServerSocket(PORT);
            System.out.println("\t[OK]");
            int nroConsulta = 0;
            while (true) {
                Socket socket;
                socket = SH_Socket.accept();
//                System.out.println("Nueva conexión entrante: " + socket);
                ((ServidorPronosticoThread) new ServidorPronosticoThread(socket, nroConsulta)).start();
                nroConsulta++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorCentral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
