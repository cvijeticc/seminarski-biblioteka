/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.net.Socket;
import Komunikacija.Posiljalac;
import Komunikacija.Primalac;
import Komunikacija.Zahtev; 
import Komunikacija.Operacija;
import Komunikacija.Odgovor;

import domen.Radnik;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andri
 */
public class Komunikacija {

    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void Konekcija() {
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("Server nije povezan");
        }

    }

    public Radnik logIn(String ki, String pass) {
        Radnik r = new Radnik();
        r.setKorisnickoIme(ki);
        r.setSifra(pass);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, r);
        //sad saljemo zahtev
        posiljalac.posalji(zahtev); /////////////ovde je bila greska
        
        Odgovor odg = (Odgovor) primalac.primi();
        r= (Radnik) odg.getOdgovor();
        return r;
        
//        Odgovor odg =  primalac.primi();
//        Odg
        
       
    }

}
