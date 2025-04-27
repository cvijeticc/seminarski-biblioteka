package operacija.terminsmene;

import domen.TerminSmene;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class AzurirajTerminSmeneSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof TerminSmene)) {
            throw new Exception("Sistem ne može da ažurira termin smene - neispravan parametar.");
        }

        TerminSmene ts = (TerminSmene) param;

        if (ts.getTrajanjeSmene() <= 0) {
            throw new Exception("Greška: trajanje smene mora biti veće od nule.");
        }
        if (ts.getBrojSmene() < 1 || ts.getBrojSmene() > 3) {
            throw new Exception("Greška: broj smene mora biti 1, 2 ili 3.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((TerminSmene) param);
    }
}
