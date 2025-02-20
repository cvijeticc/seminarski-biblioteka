/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.citaoci;

import domen.Citalac;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class UcitajCitaoceSO extends ApstraktnaGenerickaOperacija{

    List<Citalac> citaoci;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
            citaoci = broker.getAll(new Citalac(), "");
    }

    public List<Citalac> getCitaoci() {
        return citaoci;
    }
    
    
    
}
