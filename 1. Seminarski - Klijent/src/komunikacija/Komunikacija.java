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
import domen.TerminSmene;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import konfiguracija.Konfiguracija;

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
            int port = Integer.parseInt(Konfiguracija.getInstance().getProperty("port"));
            System.out.println("Klijent pokrenut na portu " + port);
            socket = new Socket("localhost", port);

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
        System.out.println("📤 Šaljem zahtev za AZURIRAJ_IZNAJMLJIVANJE sa podacima: " + i);

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

    public void obrisiRadnika(Radnik r) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_RADNIKA, r);

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

    public void dodajKnjigu(Knjiga k) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KNJIGU, k);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno kreirana knjiga");
        } else {
            System.out.println("Neuspešno kreirana knjiga");
        }
    }

    public void obrisiKnjigu(Knjiga k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KNJIGU, k);

        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno obrisana knjiga");
        } else {
            System.out.println("Neuspešno obrisana knjiga");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Greška prilikom brisanja knjige");
        }
    }

    public void azurirajKnjigu(Knjiga k) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KNJIGU, k);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno ažurirana knjiga");
            Cordinator.getInstance().osveziFormu();
        } else {
            System.out.println("Neuspešno ažurirana knjiga");
        }
    }

    public void dodajKategorijuCitaoca(KategorijaCitaoca kategorija) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KATEGORIJU_CITAOCA, kategorija);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno kreirana kategorija čitaoca");
        } else {
            System.out.println("Neuspešno kreirana kategorija čitaoca");
        }
    }

    public void azurirajKategorijuCitaoca(KategorijaCitaoca kc) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KATEGORIJU_CITAOCA, kc);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno ažurirana kategorija čitaoca");
            Cordinator.getInstance().osveziFormuKategorijeCitalaca();
        } else {
            System.out.println("Neuspešno ažurirana kategorija čitaoca");
        }
    }

    public void obrisiKategorijuCitaoca(KategorijaCitaoca kc) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KATEGORIJU_CITAOCA, kc);

        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno obrisana kategorija čitaoca");
        } else {
            System.out.println("Neuspešno obrisana kategorija čitaoca");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Greška prilikom brisanja kategorije čitaoca");
        }
    }

    public void dodajTerminSmene(TerminSmene termin) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_TERMIN_SMENE, termin);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno kreiran termin smene");
        } else {
            System.out.println("Neuspešno kreiran termin smene");
        }
    }

    public List<TerminSmene> ucitajTermineSmena() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TERMINE_SMENE, null);
        List<TerminSmene> termini;

        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        termini = (List<TerminSmene>) odgovor.getOdgovor();

        return termini;
    }

    public void obrisiTerminSmene(TerminSmene ts) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_TERMIN_SMENE, ts);

        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno obrisan termin smene");
        } else {
            System.out.println("Neuspešno obrisan termin smene");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Greška prilikom brisanja termina smene");
        }
    }

    public void azurirajTerminSmene(TerminSmene termin) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_TERMIN_SMENE, termin);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno ažuriran termin smene");
            Cordinator.getInstance().osveziFormuTerminSmene();
        } else {
            System.out.println("Neuspešno ažuriran termin smene");
        }
    }

    public void azurirajStavkuIznajmljivanja(StavkaIznajmljivanja si) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_STAVKU_IZNAJMLJIVANJA, si);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();

        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno ažurirana stavka iznajmljivanja");
            Cordinator.getInstance().osveziFormuIznajmljivanja();
        } else {
            System.out.println("Neuspešno ažurirana stavka iznajmljivanja");
        }
    }

    public boolean obrisiStavkuIznajmljivanja(StavkaIznajmljivanja stavka) {

        Zahtev zahtev = new Zahtev(Operacija.OBRISI_STAVKU_IZNAJMLJIVANJA, stavka);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("✔ Stavka iznajmljivanja uspešno obrisana.");
            return true;
        } else {
            System.out.println("❌ Brisanje stavke iznajmljivanja nije uspelo.");
            ((Exception) odg.getOdgovor()).printStackTrace();
            return false;
        }

    }

}
