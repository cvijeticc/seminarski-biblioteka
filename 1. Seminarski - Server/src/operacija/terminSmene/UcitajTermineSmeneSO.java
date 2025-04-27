package operacija.terminsmene;

import domen.TerminSmene;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author andri
 */
public class UcitajTermineSmeneSO extends ApstraktnaGenerickaOperacija {

    private List<TerminSmene> terminiSmene;

    @Override
    protected void preduslovi(Object param) throws Exception {
        // Nema posebnih preduslova za ovu operaciju
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        terminiSmene = broker.getAll(new TerminSmene(), "");
    }

    public List<TerminSmene> getTerminiSmene() {
        return terminiSmene;
    }
}
