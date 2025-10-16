/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanja;

import domen.Iznajmljivanje;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class AzurirajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
       if (param == null || !(param instanceof Iznajmljivanje)) {//param nije instance(objekat) klase Citalac
            throw new Exception("Sistem ne moze da azurira iznajmljivanje");
        }
        Iznajmljivanje i = (Iznajmljivanje) param;
        if (i.getOpisIznajmljivanja() == null || i.getOpisIznajmljivanja().isEmpty()) {
            throw new Exception("Mora da bude popunjen opis");
        }
    }

    /// ///// OVO JE POD TRANSKACIJOM I ZATO ZAJEDNO MORAS DA EDITUJES I STAVKE I IZNAJM
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        System.out.println("🛠️ IzmeniIznajmljivanjeSO: pozvano sa " + param);

        ///update iznajmljivanje OVDE
        broker.edit((Iznajmljivanje)param);

        ///update stavke OVDE
//        for
        AzurirajIznajmljivanjeSO azurirajSO = new AzurirajIznajmljivanjeSO();
//        for (StavkaIznajm s : sveStavke)

        //znaci moras i preduslove i izvrsavnaje
        azurirajSO.preduslovi();
        azurirajSO.izvrsiOperaciju(stavka, );
    }
    
}
