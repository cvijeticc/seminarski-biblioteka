package operacija.iznajmljivanja.stavke;

import domen.StavkaIznajmljivanja;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class ObrisiStavkuIznajmljivanjaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof StavkaIznajmljivanja)) {
            throw new Exception("Sistem ne može da obriše stavku iznajmljivanja.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((StavkaIznajmljivanja) param);
    }
}
