/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanja;

import domen.Iznajmljivanje;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class UcitajIznajmljivanjaSO extends ApstraktnaGenerickaOperacija{
    List<Iznajmljivanje> iznajmljivanja;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
            iznajmljivanja = broker.getAllIznajmljivanje(new Iznajmljivanje(), "");
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }
}
