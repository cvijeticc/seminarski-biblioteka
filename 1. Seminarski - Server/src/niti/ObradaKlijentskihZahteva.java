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
import domen.Iznajmljivanje;
import domen.KategorijaCitaoca;
import domen.Knjiga;
import domen.Radnik;
import domen.StavkaIznajmljivanja;
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
                    case UCITAJ_KATEGORIJE_CITAOCA:
                        List<KategorijaCitaoca> kategorija = Controller.getInstance().ucitajKategorijeCitaoca();
                        odgovor.setOdgovor(kategorija);
                        break;
                    case DODAJ_CITAOCA:
                        Citalac c = (Citalac) zahtev.getParametar();
                        Controller.getInstance().dodajCitaoca(c);
                        odgovor.setOdgovor(null);
                        break;
                    case AZURIRAJ_CITAOCA:
                        Citalac c1 = (Citalac) zahtev.getParametar();
                        Controller.getInstance().azurirajCitaoca(c1);
                        odgovor.setOdgovor(null);
                        break;
                    case AZURIRAJ_IZNAJMLJIVANJE:
                        Iznajmljivanje i = (Iznajmljivanje) zahtev.getParametar();
                        Controller.getInstance().azurirajIznajmljivanje(i);
                        odgovor.setOdgovor(odgovor);
                        
                        
                        break;
                    case UCITAJ_IZNAJMLJIVANJA:
                        List<Iznajmljivanje> iznajmljivanja = Controller.getInstance().ucitajIznajmljivanja();
                        System.out.println("Klasa obrada klijentskih zahteva: ");
                        System.out.println(iznajmljivanja);
                        odgovor.setOdgovor(iznajmljivanja);
                        break;
                    case UCITAJ_STAVKU_IZNAJMLJIVANJA:
                        List<StavkaIznajmljivanja> stavkaIznajmljivanja = Controller.getInstance().ucitajStavkuIznajmljivanja((int)zahtev.getParametar());
                        System.out.println("Klasa obrada klijentskih zahteva: ");
                        System.out.println(stavkaIznajmljivanja);
                        odgovor.setOdgovor(stavkaIznajmljivanja);
                        break;
                    case UCITAJ_RADNIKE:
                        List<Radnik> radnici = Controller.getInstance().ucitajRadnike();
                        odgovor.setOdgovor(radnici);
                        break;
                    case UCITAJ_KNJIGE:
                        List<Knjiga> knjige = Controller.getInstance().ucitajKnjige();
                        odgovor.setOdgovor(knjige);
                        break;
                    case UCITAJ_IZNOS_PO_DANU:
                        int iznosPoDanu = Controller.getInstance().ucitajIznosPoDanu((int)zahtev.getParametar());
                        odgovor.setOdgovor(iznosPoDanu);
                        break;
                    case DODAJ_IZNAJMLJIVANJE:
                        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) zahtev.getParametar();
                        Controller.getInstance().dodajIznajmljivanje(iznajmljivanje);
                        odgovor.setOdgovor(null);
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
