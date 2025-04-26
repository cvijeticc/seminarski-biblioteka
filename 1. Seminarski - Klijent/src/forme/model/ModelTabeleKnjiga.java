package forme.model;

import domen.Knjiga;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleKnjiga extends AbstractTableModel {

    List<Knjiga> lista;
    String[] kolone = {"Id", "Naziv", "Žanr", "Godina izdavanja", "Iznos po danu"};

    public ModelTabeleKnjiga(List<Knjiga> lista) {
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
        Knjiga k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getIdKnjiga();
            case 1:
                return k.getNaziv();
            case 2:
                return k.getZanrKnjige();
            case 3:
                return k.getGodinaIzdavanja();
            case 4:
                return k.getIznosPoDanu();
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Knjiga> getLista() {
        return lista;
    }

    public void pretrazi(String naziv, String zanr, String godinaStr, String iznosStr) {
        List<Knjiga> filtriranaLista = lista.stream()
                .filter(k -> (naziv == null || naziv.isEmpty() || k.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .filter(k -> (zanr == null || zanr.isEmpty() || k.getZanrKnjige().toLowerCase().contains(zanr.toLowerCase())))
                .filter(k -> {
                    if (godinaStr == null || godinaStr.isEmpty()) return true;
                    try {
                        int godina = Integer.parseInt(godinaStr);
                        return k.getGodinaIzdavanja() == godina;
                    } catch (NumberFormatException e) {
                        return true; // Ako nije validan broj, ignoriši filter
                    }
                })
                .filter(k -> {
                    if (iznosStr == null || iznosStr.isEmpty()) return true;
                    try {
                        double iznos = Double.parseDouble(iznosStr);
                        return Double.compare(k.getIznosPoDanu(), iznos) == 0;
                    } catch (NumberFormatException e) {
                        return true;
                    }
                })
                .collect(Collectors.toList());

        this.lista = filtriranaLista;
        fireTableDataChanged();
    }
}
