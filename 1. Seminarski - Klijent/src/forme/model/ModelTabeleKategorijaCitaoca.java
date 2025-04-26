package forme.model;

import domen.KategorijaCitaoca;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleKategorijaCitaoca extends AbstractTableModel {

    private List<KategorijaCitaoca> lista;
    private final String[] kolone = {"Id", "Naziv kategorije", "Beneficije"};

    public ModelTabeleKategorijaCitaoca(List<KategorijaCitaoca> lista) {
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
        KategorijaCitaoca kc = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kc.getIdKategorijaCitaoca();
            case 1:
                return kc.getNazivKategorije();
            case 2:
                return kc.getBeneficije();
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<KategorijaCitaoca> getLista() {
        return lista;
    }

    public void pretrazi(String naziv, String beneficije) {
        List<KategorijaCitaoca> filtriranaLista = lista.stream()
                .filter(kc -> (naziv == null || naziv.isEmpty() || kc.getNazivKategorije().toLowerCase().contains(naziv.toLowerCase())))
                .filter(kc -> (beneficije == null || beneficije.isEmpty() || kc.getBeneficije().toLowerCase().contains(beneficije.toLowerCase())))
                .collect(Collectors.toList());

        this.lista = filtriranaLista;
        fireTableDataChanged();
    }
}
