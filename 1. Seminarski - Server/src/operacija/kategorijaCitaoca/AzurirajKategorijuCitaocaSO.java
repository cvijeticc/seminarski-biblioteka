package operacija.kategorijaCitaoca;

import domen.KategorijaCitaoca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class AzurirajKategorijuCitaocaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof KategorijaCitaoca)) {
            throw new Exception("Sistem ne može da ažurira kategoriju čitaoca - neispravan parametar.");
        }

        KategorijaCitaoca kc = (KategorijaCitaoca) param;

        if (kc.getNazivKategorije() == null || kc.getNazivKategorije().isEmpty() || kc.getNazivKategorije().length() < 2) {
            throw new Exception("Greška: naziv kategorije nije validan.");
        }
        if (kc.getBeneficije() == null || kc.getBeneficije().isEmpty()) {
            throw new Exception("Greška: beneficije su obavezne.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((KategorijaCitaoca) param);
    }
}
