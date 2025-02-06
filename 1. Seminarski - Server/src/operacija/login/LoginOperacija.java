/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import domen.Radnik;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;
import repository.Repository;

/**
 *
 * @author andri
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Radnik radnik;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Radnik> sviRadnici = broker.getAll((Radnik) param, null);
        System.out.println("Klasa LoginOperacija SO" + sviRadnici);

        if (sviRadnici.contains((Radnik) param)) {
            for (Radnik r : sviRadnici) {
                if (r.equals((Radnik) param)) {
                    radnik = r;// to je radnik koji je inicijalizovan na pocetku metode
                    return;
                }
            }

        } else {
         radnik = null;
        }

    }

    public Radnik getRadnik() {
        return radnik;
    }

}
