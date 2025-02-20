package forme.model;

import domen.Citalac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleCitalac extends AbstractTableModel {

    List<Citalac> lista;
    String[] kolone = {"id", "ime", "prezime", "email"};

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
    
    
}
