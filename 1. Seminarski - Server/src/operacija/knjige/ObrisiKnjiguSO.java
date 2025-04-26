/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.knjige;

import domen.Knjiga;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class ObrisiKnjiguSO extends ApstraktnaGenerickaOperacija {

    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param ==null || !(param instanceof Knjiga)) {
            throw new Exception("Sistem ne moze da obrise knjigu");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Knjiga)param);
    }
    
}
