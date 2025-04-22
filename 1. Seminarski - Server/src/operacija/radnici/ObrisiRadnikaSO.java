/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.radnici;

import domen.Citalac;
import domen.Radnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class ObrisiRadnikaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Radnik)) {//param nije instance(objekat) klase radnik
            throw new Exception("Sistem ne moze da obrise radnika");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Radnik)param);
    }
    
}
