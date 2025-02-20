/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import Komunikacija.Odgovor;
import Komunikacija.Posiljalac;
import Komunikacija.Primalac;
import Komunikacija.Zahtev;
import controller.Controller;
import domen.Citalac;
import domen.Radnik;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andri
 */
public class ObradaKlijentskihZahteva extends Thread {

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
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Radnik r = (Radnik) zahtev.getParametar();
                        r = Controller.getInstance().login(r);
                        odgovor.setOdgovor(r);
                        break;
                    case UCITAJ_CITAOCE:
                        List<Citalac> citaoci = Controller.getInstance().ucitajCitaoce();
                        odgovor.setOdgovor(citaoci);
                        break;
                    case OBRISI_CITAOCA:
                        try {
                        Citalac c = (Citalac) zahtev.getParametar();
                        Controller.getInstance().obrisiCitaoca(c);
                        odgovor.setOdgovor(null);

                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;

                    default:
                        System.out.println("Greska ta operacija ne postoji");
//                    throw new AssertionError();
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void prekini() {
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
;

}
