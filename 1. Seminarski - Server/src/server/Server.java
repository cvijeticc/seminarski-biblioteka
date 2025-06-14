/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author andri
 */
public class Server extends Thread{

    boolean kraj = false;
    ServerSocket serverSoket;

    @Override
    public void run() {//pokreniServer
        
        try {
            int port = Integer.parseInt( Konfiguracija.getInstance().getProperty("port"));
            System.out.println("Server pokrenut na portu " + port);
            serverSoket = new ServerSocket(port);
            while (!kraj) {
                Socket s = serverSoket.accept();
                System.out.println("Klijent je povezan");

                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                okz.start();
                
            }   } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
        

    }

    public void zaustaviServer() {
        kraj = true;
        try {
            serverSoket.close();
        } catch (IOException ex) {
            
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}

