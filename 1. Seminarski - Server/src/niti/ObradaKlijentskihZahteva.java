/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import Komunikacija.Odgovor;
import Komunikacija.Posiljalac;
import Komunikacija.Primalac;
import Komunikacija.Zahtev;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andri
 */
public class ObradaKlijentskihZahteva extends Thread{
    Socket socket;
    Primalac primalac;
    Posiljalac posiljalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    
    
    @Override
    public void run() {
        while (!kraj) {            
            Zahtev zahtev = (Zahtev) primalac.primi();
            Odgovor odgovor = new Odgovor();
            
            switch (zahtev.getOperacija()) {
//                case val:
//                    
//                    break;
                default:
                    System.out.println("Greska ta operacija ne postoji");
//                    throw new AssertionError();
            }
            posiljalac.posalji(odgovor);
        }
        
    }
    
    public void prekini(){
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    };
    
    
}
