/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nico
 */
public class ServidorCentralThread extends Thread {

    private int SP_PORT = 5002;
    private int SH_PORT = 5001;

    private Socket incomingSocket;
    private ObjectOutputStream queryOutput;
    private ObjectInputStream queryInput;
    private String[] incomingObject = new String[2];
    private String signo;
    private String fecha;

    protected Socket horoscopoSocket;
    protected DataOutputStream horoscopoOutput;
    protected DataInputStream horoscopoInput;

    protected Socket pronosticoSocket;
    protected DataOutputStream pronosticoOutput;
    protected DataInputStream pronosticoInput;
    private String pronosticoCacheado;
    private String horoscopoCacheado;

    public ServidorCentralThread(Socket socket) {
        this.incomingSocket = socket;
        try {
            queryOutput = new ObjectOutputStream(socket.getOutputStream());
            queryInput = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorCentralThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {

            incomingObject = (String[]) queryInput.readObject();

            signo = incomingObject[0];
            fecha = incomingObject[1];
            pronosticoCacheado = (String) Cache.getInstance().getPronostico(fecha);
            horoscopoCacheado = (String) Cache.getInstance().getHoroscopo(signo);

            if (pronosticoCacheado == null) {
                pronosticoSocket = new Socket("localhost", SP_PORT);
                pronosticoInput = new DataInputStream(pronosticoSocket.getInputStream());
                pronosticoOutput = new DataOutputStream(pronosticoSocket.getOutputStream());
                pronosticoOutput.writeUTF(fecha);
                incomingObject[1] = pronosticoInput.readUTF();
                Cache.getInstance().addPronostico(fecha, incomingObject[1]);
//                System.out.println("Cache MISS " + fecha);
            } else {
//                System.out.println("Cache P HIT->" + pronosticoCacheado);
                incomingObject[1] = pronosticoCacheado;
            }

            if (horoscopoCacheado == null) {
                horoscopoSocket = new Socket("localhost", SH_PORT);
                horoscopoOutput = new DataOutputStream(horoscopoSocket.getOutputStream());
                horoscopoInput = new DataInputStream(horoscopoSocket.getInputStream());
                horoscopoOutput.writeUTF(signo);
                incomingObject[0] = horoscopoInput.readUTF();
                Cache.getInstance().addHoroscopo(signo, incomingObject[0]);
//                System.out.println("Cache MISS " + signo);
            } else {
//                System.out.println("Cache H HIT->" + horoscopoCacheado);
                incomingObject[0] = horoscopoCacheado;
            }

            queryOutput.writeObject(incomingObject);

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ServidorCentralThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconnectar();
    }

    public void desconnectar() {
        try {
            incomingSocket.close();
            queryOutput.close();
            queryInput.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorCentralThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
