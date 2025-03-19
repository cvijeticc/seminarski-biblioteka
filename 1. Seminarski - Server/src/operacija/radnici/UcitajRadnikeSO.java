/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.radnici;

import domen.Radnik;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class UcitajRadnikeSO extends ApstraktnaGenerickaOperacija{
    List<Radnik> radnici;
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        radnici = broker.getAll(new Radnik(), "");
    }

    public List<Radnik> getRadnici() {
        return radnici;
    }
    
    
    
}
