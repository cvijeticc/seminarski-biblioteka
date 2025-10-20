/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.citaoci;

import domen.Citalac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class KreirajCitaocaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Citalac)) {//param nije instance(objekat) klase Citalac
            throw new Exception("Sistem ne moze da kreira citaoca");
        }
        
        Citalac c = (Citalac) param;
        if (c.getIme() == null || c.getIme().isEmpty() || c.getIme().length() < 3 ) {
            throw new Exception("Greska, ime");
        }
        if (c.getPrezime()== null || c.getPrezime().isEmpty() || c.getPrezime().length() < 3 ) {
            throw new Exception("Greska, prezime");
        }
        
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Citalac) param);
    }

}
