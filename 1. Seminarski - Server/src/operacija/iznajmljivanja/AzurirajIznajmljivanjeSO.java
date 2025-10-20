/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanja;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class AzurirajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

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

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Iznajmljivanje i = (Iznajmljivanje) param;
        System.out.println("SERVER RECV: iznajmljivanjeId=" + i.getIdIznajmljivanja() + ", stavki=" + (i.getStavke()==null? "null" : i.getStavke().size()));

        // 1) UPDATE roditelja
        broker.edit(i);

        // 2) OBRIŠI SVE STAVKE za tog roditelja (po FK)
        System.out.println("DELETE stavke for id=" + i.getIdIznajmljivanja());
        
        ((repository.db.impl.DbRepositoryGeneric) broker)
                .deleteStavkeByIznajmljivanjeId(i.getIdIznajmljivanja());

        // 3) INSERT SVE TRENUTNE STAVKE iz objekta
        if (i.getStavke() != null) {
            for (StavkaIznajmljivanja si : i.getStavke()) {
                System.out.println("INSERT stavka: idIznaj=" + si.getIdIznajmljivanje() + ", rb=" + si.getRb() + ", total=" + si.getUkupanIznosStavke());
                si.setIdIznajmljivanje(i.getIdIznajmljivanja());
                if (si.getRb() == 0) {
                    si.setRb(1); // ako ti se desi nula, postavi nešto
                }
                ((repository.db.impl.DbRepositoryGeneric) broker).addStavkuIznajmljivanja(si);
            }
        }
    }

}
