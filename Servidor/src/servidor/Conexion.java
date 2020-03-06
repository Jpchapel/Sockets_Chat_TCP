/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Conexion {

    public static final int PUERTO = 5000;
    private static final String HOST = "localhost";
    ServerSocket serverSocket = null;
    InetSocketAddress inetSocketAddress = null;
    int idCliente = 0;

    public Conexion() {
        try {
            serverSocket = new ServerSocket();
            inetSocketAddress = new InetSocketAddress(HOST, PUERTO);
            serverSocket.bind(inetSocketAddress);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ejecutar(int cantidadClientes) {
        System.out.println("Escuchando: " + serverSocket);
        ServidorTCPMultiThread servidor;
        ExecutorService executor = Executors.newFixedThreadPool(cantidadClientes);
        try {
            while (true) {
                servidor = new ServidorTCPMultiThread(idCliente++, serverSocket.accept());
                executor.execute(servidor);
            }
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            executor.shutdown();
        }
    }

}
