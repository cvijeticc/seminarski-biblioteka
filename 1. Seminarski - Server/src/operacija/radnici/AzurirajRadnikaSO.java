/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.radnici;

import domen.Radnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class AzurirajRadnikaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Radnik)) {
            throw new Exception("Sistem ne može da ažurira radnika - neispravan parametar.");
        }

        Radnik r = (Radnik) param;

        if (r.getIme() == null || r.getIme().isEmpty() || r.getIme().length() < 3) {
            throw new Exception("Greška: ime radnika nije validno.");
        }
        if (r.getPrezime() == null || r.getPrezime().isEmpty() || r.getPrezime().length() < 3) {
            throw new Exception("Greška: prezime radnika nije validno.");
        }
        if (r.getKorisnickoIme() == null || r.getKorisnickoIme().isEmpty()) {
            throw new Exception("Greška: korisničko ime je obavezno.");
        }
        if (r.getSifra() == null || r.getSifra().isEmpty()) {
            throw new Exception("Greška: šifra je obavezna.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Radnik) param);
    }
    
}
