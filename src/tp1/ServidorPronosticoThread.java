/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nico
 */
public class ServidorPronosticoThread extends Thread{
 
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private int idSesion;

    public ServidorPronosticoThread(Socket socket, int id) {
        this.socket = socket;
        this.idSesion = id;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorPronosticoThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconnectar() {
        try {
            socket.close();
            dataOutputStream.close();
            dataInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorPronosticoThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String fechaConsulta = "";
        String respuesta = "";
        try {
            fechaConsulta = dataInputStream.readUTF();
            System.out.println("Respondiendo consulta número " + this.idSesion + " fecha: " + fechaConsulta);
            respuesta= PrediccionClima.getInstance().getPrediccion();
            dataOutputStream.writeUTF(respuesta);
        } catch (IOException ex) {
            Logger.getLogger(ServidorPronosticoThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconnectar();
    }
}
