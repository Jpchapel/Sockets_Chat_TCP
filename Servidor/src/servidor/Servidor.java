/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;


/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    private static int PUERTO = 5000;
    
    public static void main(String[] args) {
        new Conexion().ejecutar(2);
    }
}
