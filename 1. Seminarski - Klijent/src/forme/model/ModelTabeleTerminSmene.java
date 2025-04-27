package forme.model;

import domen.TerminSmene;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleTerminSmene extends AbstractTableModel {

    private List<TerminSmene> lista;
    private final String[] kolone = {"Id", "Trajanje smene", "Broj smene"};

    public ModelTabeleTerminSmene(List<TerminSmene> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TerminSmene ts = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ts.getIdTerminSmene();
            case 1:
                return ts.getTrajanjeSmene();
            case 2:
                return ts.getBrojSmene();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<TerminSmene> getLista() {
        return lista;
    }

    public void pretrazi(int trajanje, int brojSmene) {
        List<TerminSmene> filtriranaLista = lista.stream()
                .filter(ts -> (trajanje == -1 || ts.getTrajanjeSmene() == trajanje))
                .filter(ts -> (brojSmene == -1 || ts.getBrojSmene() == brojSmene))
                .collect(Collectors.toList());

        this.lista = filtriranaLista;
        fireTableDataChanged();
    }
}
