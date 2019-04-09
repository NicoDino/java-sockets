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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.logging.*;

class ClienteThread extends Thread {

    protected Socket socket;
    protected ObjectOutputStream output;
    protected ObjectInputStream input;
    private final int personaId;
    private final int serverPort;
    private final String serverAddress;
    private String signo;
    private String fechaString;
    private LocalDateTime fecha = LocalDateTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String[] consulta = new String[2];

    public ClienteThread(int id, String server, int port) {
        this.personaId = id;
        this.serverAddress = server;
        this.serverPort = port;
    }

    @Override
    public void run() {
        try {
            // suponiendo que pedimos un pronóstico extendido a max. 15 dias
            // generamos un random entre 0 y 15.
            // luego agregamos esa cantidad de días a la fecha actual
            Random rand = new Random();
            int indiceRandom = rand.nextInt(15);
            fecha = fecha.plusDays(indiceRandom);
            fechaString = fecha.format(dateFormatter);
            // obtenemos un signo al azar
            signo = SignosZodiaco.getInstance().getSigno();
            this.consulta[0] = signo;
            this.consulta[1] = fechaString;
            socket = new Socket(this.serverAddress, this.serverPort);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Cliente " + this.personaId + " consulta: " + this.signo + " " + this.fechaString);
            output.writeObject(this.consulta);
            String[] respuesta = (String[]) input.readObject();
            
            System.out.println("Cliente " + this.personaId + ", signo: " + this.signo + ". Servidor devuelve horóscopo: " + respuesta[0]
                    + ". Pronóstico " + this.fechaString +": "+respuesta[1]);
            input.close();
            output.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
