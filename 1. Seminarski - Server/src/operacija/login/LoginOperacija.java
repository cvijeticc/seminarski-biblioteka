/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import domen.Citalac;
import domen.Radnik;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Radnik radnik;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Radnik)) {//param nije instance klase Citalac
            throw new Exception("Sistem ne moze da uloguje citaoca");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Radnik> sviRadnici = broker.getAll((Radnik) param, null);
        System.out.println("Klasa LoginOperacija SO" + sviRadnici);

        if (sviRadnici.contains((Radnik) param)) {
            for (Radnik r : sviRadnici) {
                if (r.equals((Radnik) param)) {
                    radnik = r;// to je radnik koji je inicijalizovan na pocetku klase
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
