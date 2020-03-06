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
public class ServidorTCPMultiThread extends Thread{
    Socket socket = null;
    BufferedReader bufferedReader = null;    
    PrintWriter printWriter = null;
    int idCliente;

    public ServidorTCPMultiThread(int idCliente, Socket socket) {
        this.idCliente = idCliente;
        this.socket = socket;
        
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")));
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCPMultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String mensaje = new String();
        
        do {
            try {
                mensaje = bufferedReader.readLine();
                //mensaje = new String(mensaje.getBytes());
                System.out.println("Cliente" +idCliente + ": " + mensaje);
                printWriter.println(mensaje);
            } catch (IOException ex) {
                Logger.getLogger(ServidorTCPMultiThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }
}
