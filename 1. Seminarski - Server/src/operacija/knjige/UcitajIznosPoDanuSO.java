/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.knjige;

import domen.ApstraktniDomenskiObjekat;
import domen.Knjiga;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class UcitajIznosPoDanuSO extends ApstraktnaGenerickaOperacija{
    int iznosPoDanu;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<ApstraktniDomenskiObjekat> lista =broker.getAll(new Knjiga(), " WHERE idKnjiga = " + param);
        if (!lista.isEmpty()) {
        Knjiga k = (Knjiga) lista.get(0);
        iznosPoDanu = (int) k.getIznosPoDanu();
    }
    }

    public int getIznosPoDanu() {
        return iznosPoDanu;
    }
    
}
