package operacija.terminsmene;

import domen.TerminSmene;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class ObrisiTerminSmeneSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof TerminSmene)) {
            throw new Exception("Sistem ne može da obriše termin smene - neispravan parametar.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((TerminSmene) param);
    }
}
