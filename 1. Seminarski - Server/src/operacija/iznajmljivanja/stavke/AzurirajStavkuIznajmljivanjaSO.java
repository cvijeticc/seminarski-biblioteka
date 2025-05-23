/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanja.stavke;

import domen.StavkaIznajmljivanja;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class AzurirajStavkuIznajmljivanjaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof StavkaIznajmljivanja)) {
            throw new Exception("Sistem ne može da ažurira stavku iznajmljivanja.");
        }

        StavkaIznajmljivanja si = (StavkaIznajmljivanja) param;

        if (si.getOpisStavke() == null || si.getOpisStavke().trim().isEmpty()) {
            throw new Exception("Opis stavke mora biti popunjen.");
        }

        if (si.getDatumOd() == null || si.getDatumDo() == null) {
            throw new Exception("Datumi moraju biti popunjeni.");
        }

        if (si.getBrojDana() <= 0) {
            throw new Exception("Broj dana mora biti veći od 0.");
        }

        if (si.getIznosPoDanu() <= 0) {
            throw new Exception("Iznos po danu mora biti veći od 0.");
        }

        if (si.getIdKnjiga() == null) {
            throw new Exception("Knjiga mora biti odabrana.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        StavkaIznajmljivanja si = (StavkaIznajmljivanja) param;
        System.out.println("☐ SQL UPDATE: " + si.vratiVrednostiZaIzmenu());

        broker.edit((StavkaIznajmljivanja) param);
    }
}
