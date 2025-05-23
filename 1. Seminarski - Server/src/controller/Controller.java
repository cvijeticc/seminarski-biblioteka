/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import operacija.iznajmljivanja.UcitajIznajmljivanjaSO;
import domen.Citalac;
import domen.Iznajmljivanje;
import domen.KategorijaCitaoca;
import domen.Knjiga;
import domen.Radnik;
import domen.StavkaIznajmljivanja;
import domen.TerminSmene;
import java.util.List;
import operacija.citaoci.AzurirajCitaocaSO;
import operacija.citaoci.KreirajCitaocaSO;
import operacija.citaoci.ObrisiCitaocaSO;
import operacija.citaoci.UcitajCitaoceSO;
import operacija.citaoci.UcitajKategorijeCitaocaSO;
import operacija.iznajmljivanja.AzurirajIznajmljivanjeSO;
import operacija.iznajmljivanja.stavke.UcitajStavkuIznajmljivanjaSO;
import operacija.iznajmljivanja.KreirajIznajmljivanjeSO;
import operacija.iznajmljivanja.stavke.AzurirajStavkuIznajmljivanjaSO;
import operacija.iznajmljivanja.stavke.ObrisiStavkuIznajmljivanjaSO;
import operacija.kategorijaCitaoca.AzurirajKategorijuCitaocaSO;
import operacija.kategorijaCitaoca.KreirajKategorijuCitaocaSO;
import operacija.kategorijaCitaoca.ObrisiKategorijuCitaocaSO;
import operacija.knjige.AzurirajKnjiguSO;
import operacija.knjige.KreirajKnjiguSO;
import operacija.knjige.ObrisiKnjiguSO;
import operacija.knjige.UcitajIznosPoDanuSO;
import operacija.knjige.UcitajKnjigeSO;
import operacija.login.LoginOperacija;
import operacija.radnici.AzurirajRadnikaSO;
import operacija.radnici.KreirajRadnikaSO;
import operacija.radnici.ObrisiRadnikaSO;
import operacija.radnici.UcitajRadnikeSO;
import operacija.terminsmene.AzurirajTerminSmeneSO;
import operacija.terminsmene.KreirajTerminSmeneSO;
import operacija.terminsmene.ObrisiTerminSmeneSO;
import operacija.terminsmene.UcitajTermineSmeneSO;

/**
 *
 * @author andri
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Radnik login(Radnik r) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(r, null);

        System.out.println("Klasa Controller " + operacija.getRadnik());

        return operacija.getRadnik();

//        return dbb.login(r);
    }

    public List<Citalac> ucitajCitaoce() throws Exception {
        UcitajCitaoceSO operacija = new UcitajCitaoceSO();
        operacija.izvrsi(null, null);
        System.out.println("Klasa Controller " + operacija.getCitaoci());
        return operacija.getCitaoci();
    }

    public void obrisiCitaoca(Citalac c) throws Exception {

        ObrisiCitaocaSO operacija = new ObrisiCitaocaSO();
        operacija.izvrsi(c, null);
    }

    public List<KategorijaCitaoca> ucitajKategorijeCitaoca() throws Exception {
        UcitajKategorijeCitaocaSO operacija = new UcitajKategorijeCitaocaSO();
        operacija.izvrsi(null, null);
        System.out.println("Klasa Controller " + operacija.getKategorije());
        return operacija.getKategorije();
    }

    public void dodajCitaoca(Citalac c) throws Exception {
        KreirajCitaocaSO operacija = new KreirajCitaocaSO();
        operacija.izvrsi(c, null);

    }

    public void azurirajCitaoca(Citalac c) throws Exception {
        AzurirajCitaocaSO operacija = new AzurirajCitaocaSO();
        operacija.izvrsi(c, null);
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() throws Exception {
        UcitajIznajmljivanjaSO operacija = new UcitajIznajmljivanjaSO();
        operacija.izvrsi(null, null);
        System.out.println("Klasa Controller " + operacija.getIznajmljivanja());
        return operacija.getIznajmljivanja();
    }

    public List<StavkaIznajmljivanja> ucitajStavkuIznajmljivanja(int id) throws Exception {
        UcitajStavkuIznajmljivanjaSO operacija = new UcitajStavkuIznajmljivanjaSO();
        operacija.izvrsi(id, null);
        System.out.println("Klasa Controller " + operacija.getStavkaIznajmljivanja());
        return operacija.getStavkaIznajmljivanja();
    }

    public List<Radnik> ucitajRadnike() throws Exception {
        UcitajRadnikeSO operacija = new UcitajRadnikeSO();
        operacija.izvrsi(null, null);
        return operacija.getRadnici();
    }

//    public List<Citalac> ucitajCitaoce() throws Exception {
//        UcitajCitaoceSO operacija = new UcitajCitaoceSO();
//        operacija.izvrsi(null, null);
//        System.out.println("Klasa Controller " + operacija.getCitaoci());
//        return operacija.getCitaoci();
//    }
    public List<Knjiga> ucitajKnjige() throws Exception {
        UcitajKnjigeSO operacija = new UcitajKnjigeSO();
        operacija.izvrsi(null, null);
        System.out.println("Klasa Controller " + operacija.getKnjige());
        return operacija.getKnjige();
    }

    public int ucitajIznosPoDanu(int i) throws Exception {
        UcitajIznosPoDanuSO operacija = new UcitajIznosPoDanuSO();
        operacija.izvrsi(null, null);
        System.out.println("Klasa Controller "/* + operacija.getKnjige()*/);
        return operacija.getIznosPoDanu();
    }

    public void dodajIznajmljivanje(Iznajmljivanje i) throws Exception {
        KreirajIznajmljivanjeSO operacija = new KreirajIznajmljivanjeSO();
        operacija.izvrsi(i, null);
    }

    public void azurirajIznajmljivanje(Iznajmljivanje i) throws Exception {
        AzurirajIznajmljivanjeSO operacija = new AzurirajIznajmljivanjeSO();
        operacija.izvrsi(i, null);
    }

    public void dodajRadnika(Radnik r) throws Exception {
        KreirajRadnikaSO operacija = new KreirajRadnikaSO();
        operacija.izvrsi(r, null);
    }

    public void azurirajRadnika(Radnik r) throws Exception {
        AzurirajRadnikaSO operacija = new AzurirajRadnikaSO();
        operacija.izvrsi(r, null);
    }

    public void obrisiRadnika(Radnik r) throws Exception {
        ObrisiRadnikaSO operacija = new ObrisiRadnikaSO();
        operacija.izvrsi(r, null);
    }

    public void dodajKnjigu(Knjiga k) throws Exception {
        KreirajKnjiguSO operacija = new KreirajKnjiguSO();
        operacija.izvrsi(k, null);

    }

    public void obrisiKnjigu(Knjiga knjiga) throws Exception {
        ObrisiKnjiguSO operacija = new ObrisiKnjiguSO();
        operacija.izvrsi(knjiga, null);
    }

    public void azurirajKnjigu(Knjiga k) throws Exception {
        AzurirajKnjiguSO operacija = new AzurirajKnjiguSO();
        operacija.izvrsi(k, null);
    }

    public void dodajKategorijuCitaoca(KategorijaCitaoca kc) throws Exception {
        KreirajKategorijuCitaocaSO operacija = new KreirajKategorijuCitaocaSO();
        operacija.izvrsi(kc, null);
    }

    public void azurirajKategorijuCitaoca(KategorijaCitaoca kc) throws Exception {
        AzurirajKategorijuCitaocaSO operacija = new AzurirajKategorijuCitaocaSO();
        operacija.izvrsi(kc, null);
    }

    public void obrisiKategorijuCitaoca(KategorijaCitaoca kategorija) throws Exception {
        ObrisiKategorijuCitaocaSO operacija = new ObrisiKategorijuCitaocaSO();
        operacija.izvrsi(kategorija, null);
    }

    public void dodajTerminSmene(TerminSmene ts) throws Exception {
        KreirajTerminSmeneSO operacija = new KreirajTerminSmeneSO();
        operacija.izvrsi(ts, null);
    }

    public List<TerminSmene> ucitajTermineSmene() throws Exception {
        UcitajTermineSmeneSO operacija = new UcitajTermineSmeneSO();
        operacija.izvrsi(null, null);
        return operacija.getTerminiSmene();
    }

    public void obrisiTerminSmene(TerminSmene termin) throws Exception {
        ObrisiTerminSmeneSO operacija = new ObrisiTerminSmeneSO();
        operacija.izvrsi(termin, null);
    }

    public void azurirajTerminSmene(TerminSmene ts) throws Exception {
        AzurirajTerminSmeneSO operacija = new AzurirajTerminSmeneSO();
        operacija.izvrsi(ts, null);
    }

    public void azurirajStavkuIznajmljivanja(StavkaIznajmljivanja si) throws Exception {
        AzurirajStavkuIznajmljivanjaSO operacija = new AzurirajStavkuIznajmljivanjaSO();
        operacija.izvrsi(si, null);
    }

    public void obrisiStavkuIznajmljivanja(StavkaIznajmljivanja stavka) throws Exception {
    ObrisiStavkuIznajmljivanjaSO operacija = new ObrisiStavkuIznajmljivanjaSO();
    operacija.izvrsi(stavka, null);
}


   



}
