package forme.model;

import domen.Citalac;
import domen.KategorijaCitaoca;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleCitalac extends AbstractTableModel {

    List<Citalac> lista;
    String[] kolone = {"id", "ime", "prezime", "email", "kategorija citaoca"};

    public ModelTabeleCitalac(List<Citalac> lista) {
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
        Citalac c = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getIdCitalac();
            case 1:
                return c.getIme();
            case 2:
                return c.getPrezime();
            case 3:
                return c.getEmail();
            case 4:
                return c.getIdKategorijaCitaoca().getIdKategorijaCitaoca();//ovde treba naziv
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Citalac> getLista() {
        return lista;
    }

    public void pretrazi(String ime, String prezime, String email, KategorijaCitaoca kategorija) {
                //lista je prosledjena lista kad se pravi tabela
        List<Citalac> filteredList = lista.stream()
                //p je svaki pojedinacni objekat iz liste, kao da se prolazi sa for each petljom
                .filter(p -> (ime == null || ime.isEmpty() || p.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(p -> (prezime == null || prezime.isEmpty() || p.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(p -> (email == null || email.isEmpty() || p.getEmail().toLowerCase().contains(email.toLowerCase())))
                .filter(p -> (kategorija == null || p.getIdKategorijaCitaoca().getIdKategorijaCitaoca() == kategorija.getIdKategorijaCitaoca()))
                .collect(Collectors.toList());

        this.lista = filteredList;
        fireTableDataChanged();

    }

}
