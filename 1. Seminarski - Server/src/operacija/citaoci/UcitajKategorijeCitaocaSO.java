/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.citaoci;

import domen.KategorijaCitaoca;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class UcitajKategorijeCitaocaSO extends ApstraktnaGenerickaOperacija{

    List<KategorijaCitaoca> kategorije;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        kategorije = broker.getAll(new KategorijaCitaoca(), "");
    }

    public List<KategorijaCitaoca> getKategorije() {
        return kategorije;
    }

    
    
    
}
