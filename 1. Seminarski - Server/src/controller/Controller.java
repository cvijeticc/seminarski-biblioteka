/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import operacija.iznajmljivanja.UcitajIznajmljivanjaSO;
import domen.Citalac;
import domen.Iznajmljivanje;
import domen.KategorijaCitaoca;
import domen.Radnik;
import domen.StavkaIznajmljivanja;
import java.util.List;
import operacija.citaoci.AzurirajCitaocaSO;
import operacija.citaoci.KreirajCitaocaSO;
import operacija.citaoci.ObrisiCitaocaSO;
import operacija.citaoci.UcitajCitaoceSO;
import operacija.citaoci.UcitajKategorijeCitaocaSO;
import operacija.iznajmljivanja.stavke.UcitajStavkuIznajmljivanjaSO;
import operacija.login.LoginOperacija;

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

    
    
    

}
