/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Komunikacija.Operacija;
import domen.Citalac;
import domen.Radnik;
import java.util.List;
import operacija.UcitajCitaoceSO;
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

}
