package operacija.kategorijaCitaoca;

import domen.KategorijaCitaoca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class ObrisiKategorijuCitaocaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof KategorijaCitaoca)) {
            throw new Exception("Sistem ne može da obriše kategoriju čitaoca.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((KategorijaCitaoca) param);
    }
}
