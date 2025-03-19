/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class KreirajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
//        if (param == null || !(param instanceof Iznajmljivanje)) {
//            throw new Exception("Sistem ne moze da obrise iznajmljivanje");
//        }
//        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) param;
//        //ovde treba neki uslovi da idu kao
//        if (iznajmljivanje.getOpisIznajmljivanja().isEmpty() || iznajmljivanje.getOpisIznajmljivanja() == null) {
//            throw new Exception("Treba da bude unet opis");
//        }
//        if (iznajmljivanje.getIdCitalac() == null) {
//            throw new Exception("Treba da bude izabran citalac");
//        }if (iznajmljivanje.getIdRadnik() == null ) {
//            throw new Exception("Treba da bude izabran radnik");
//        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) param;
        int id = broker.add(iznajmljivanje);
        
        iznajmljivanje.setIdIznajmljivanja(id);
        
        for (StavkaIznajmljivanja si : iznajmljivanje.getStavke()) {
            si.setIdIznajmljivanje(iznajmljivanje.getIdIznajmljivanja());
            broker.add(si);
        }
    }

    
    
}
