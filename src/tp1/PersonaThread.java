/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

/**
 *
 * @author nico
 */
import java.io.*;
import java.net.Socket;
import java.util.logging.*;

class PersonaThread extends Thread {
    protected Socket personaSocket;
    protected DataOutputStream outputStream;
    protected DataInputStream inputStream;
    private final int personaId;
    private final int serverPort;
    private final String serverAddress;
    private String signo;
    
    public PersonaThread(int id, String server, int port) {
        this.personaId = id;
        this.serverAddress = server;
        this.serverPort = port;
    }
    @Override
    public void run() {
//        try {
            signo = new SignosZodiaco().getSigno();
//            personaSocket = new Socket(this.serverAddress, this.serverPort);
//            outputStream = new DataOutputStream(personaSocket.getOutputStream());
//            inputStream = new DataInputStream(personaSocket.getInputStream());
            System.out.println(personaId + " solicita horóscopo de: " + signo );
//            outputStream.writeUTF("hola");
//            String respuesta="";
//            respuesta = inputStream.readUTF();
//            System.out.println(personaId + " Servidor devuelve saludo: " + respuesta);
//            inputStream.close();
//            outputStream.close();
//            personaSocket.close();
//        } catch (IOException ex) {
//            Logger.getLogger(PersonaThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}