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
import cordinator.Cordinator;
import domen.Citalac;
import domen.Iznajmljivanje;
import domen.KategorijaCitaoca;
import domen.Knjiga;

import domen.Radnik;
import domen.StavkaIznajmljivanja;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        r = (Radnik) odg.getOdgovor();
        return r;

//        Odgovor odg =  primalac.primi();
//        Odg
    }

    public List<Citalac> ucitajCitaoce() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_CITAOCE, null);
        List<Citalac> citaoci = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        citaoci = (List<Citalac>) odg.getOdgovor();
        return citaoci;

    }

    public void obrisiCitaoca(Citalac c) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_CITAOCA, c);

        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspesno obrisano");
        } else {
            System.out.println("Neuspesno obrisano");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Greska");
        }

    }

    public List<KategorijaCitaoca> ucitajKategorijeCitalaca() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KATEGORIJE_CITAOCA, null);
        List<KategorijaCitaoca> kategorije = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        kategorije = (List<KategorijaCitaoca>) odg.getOdgovor();
        return kategorije;

    }

    public void dodajCitaoca(Citalac c) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_CITAOCA, c);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspesno kreiran citaoc");
        } else {
            System.out.println("Neuspesno kreiran citaoc");
        }
    }

    public void azurirajCitaoca(Citalac c) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_CITAOCA, c);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspesno azuriran citaoc");
            Cordinator.getInstance().osveziFormu();
        } else {
            System.out.println("Neuspesno azuriran citaoc");
        }

    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_IZNAJMLJIVANJA, null);
        List<Iznajmljivanje> iznajmljivanja = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        iznajmljivanja = (List<Iznajmljivanje>) odg.getOdgovor();
        return iznajmljivanja;

    }

    public List<StavkaIznajmljivanja> ucitajStavkuIznajmljivanja(int idIznajmljivanja) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKU_IZNAJMLJIVANJA, idIznajmljivanja);
        List<StavkaIznajmljivanja> stavkaIznajmljivanja;

        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        stavkaIznajmljivanja = (List<StavkaIznajmljivanja>) odg.getOdgovor();
        System.out.println("Klasa komunikacija");
        System.out.println(stavkaIznajmljivanja);
        return stavkaIznajmljivanja;
    }

    public List<Radnik> ucitajRadnike() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RADNIKE, null);
        List<Radnik> radnici;
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        radnici = (List<Radnik>) odg.getOdgovor();
        return radnici;
    }

    public List<Knjiga> ucitajKnjige() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KNJIGE, null);
        List<Knjiga> knjige;
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        knjige = (List<Knjiga>) odg.getOdgovor();
        return knjige;
    }

    public int ucitajIznosPoDanu(int idKnjiga) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_IZNOS_PO_DANU, idKnjiga);
        int iznosPoDanu;
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        iznosPoDanu = (int) odg.getOdgovor();
        return iznosPoDanu;

    }

    public void dodajIznajmljivanje(Iznajmljivanje i) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_IZNAJMLJIVANJE, i);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspesno kreirano iznajmljivanje");
        } else {
            System.out.println("Neuspesno kreirano iznajmljivanje");
        }
    }

    public void azurirajIznajmljivanje(Iznajmljivanje i) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_IZNAJMLJIVANJE, i);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspesno azurirano iznajmljivanje");
            Cordinator.getInstance().osveziFormuIznajmljivanja();
        } else {
            System.out.println("Neuspesno azurirano iznajmljivanje");
        }
    }

    public void dodajRadnika(Radnik r) {
    Zahtev zahtev = new Zahtev(Operacija.DODAJ_RADNIKA, r);
    posiljalac.posalji(zahtev);
    Odgovor odg = (Odgovor) primalac.primi();
    if (odg.getOdgovor() == null) {
        System.out.println("Uspešno kreiran radnik");
    } else {
        System.out.println("Neuspešno kreiran radnik");
    }
}

    public void azurirajRadnika(Radnik r) {
    Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_RADNIKA, r);
    posiljalac.posalji(zahtev);
    Odgovor odg = (Odgovor) primalac.primi();
    if (odg.getOdgovor() == null) {
        System.out.println("Uspešno ažuriran radnik");
        Cordinator.getInstance().osveziFormu();
    } else {
        System.out.println("Neuspešno ažuriran radnik");
    }
}


}
