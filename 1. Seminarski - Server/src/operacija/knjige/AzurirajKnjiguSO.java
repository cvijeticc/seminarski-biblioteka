package operacija.knjige;

import domen.Knjiga;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class AzurirajKnjiguSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Knjiga)) {
            throw new Exception("Sistem ne može da ažurira knjigu - neispravan parametar.");
        }

        Knjiga k = (Knjiga) param;

        if (k.getNaziv() == null || k.getNaziv().isEmpty() || k.getNaziv().length() < 2) {
            throw new Exception("Greška: naziv knjige nije validan.");
        }
        if (k.getZanrKnjige() == null || k.getZanrKnjige().isEmpty()) {
            throw new Exception("Greška: žanr knjige je obavezan.");
        }
        if (k.getGodinaIzdavanja() <= 0) {
            throw new Exception("Greška: godina izdavanja mora biti pozitivan broj.");
        }
        if (k.getIznosPoDanu() <= 0) {
            throw new Exception("Greška: iznos po danu mora biti veći od nule.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Knjiga) param);
    }
}
