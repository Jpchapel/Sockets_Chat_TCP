/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class ServidorTCPMultiThread extends Thread {

    private Socket socket = null;
    private BufferedReader bufferedReader = null;
    private PrintWriter printWriter = null;
    private final int idCliente;

    public ServidorTCPMultiThread(int idCliente, Socket socket) {
        this.idCliente = idCliente;
        this.socket = socket;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCPMultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String mensaje;

        do {
            try {
                mensaje = bufferedReader.readLine();
                System.out.println("Cliente " + idCliente + ": " + mensaje);
                printWriter.println(mensaje);
            } catch (IOException ex) {
                Logger.getLogger(ServidorTCPMultiThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }
}
