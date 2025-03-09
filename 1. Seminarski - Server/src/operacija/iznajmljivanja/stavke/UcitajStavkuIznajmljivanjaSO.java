/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanja.stavke;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class UcitajStavkuIznajmljivanjaSO extends ApstraktnaGenerickaOperacija{
     List<StavkaIznajmljivanja> stavkaIznajmljivanja;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN knjiga ON knjiga.idKnjiga = stavkaiznajmljivanja.idKnjiga"
                + " WHERE idIznajmljivanje = "+(int)param + " " ;
        stavkaIznajmljivanja = broker.getAll(new StavkaIznajmljivanja(), uslov);
    }

    public List<StavkaIznajmljivanja> getStavkaIznajmljivanja() {
        return stavkaIznajmljivanja;
    }
    
    
    
}
