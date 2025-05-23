/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Citalac;
import domen.Iznajmljivanje;
import domen.Radnik;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andri
 */
public class ModelTabeleIznajmljivanje extends AbstractTableModel {

    List<Iznajmljivanje> lista;
//    private int idIznajmljivanja;
//    private double ukupanIznos;
//    private String opisIznajmljivanja;
//    private Radnik idRadnik;
//    private Citalac idCitalac;
    String[] kolone = {"id", "ukupan iznos", "opis iznajmljivanja", "idRadnik", "idCitalac"};

    public ModelTabeleIznajmljivanje(List<Iznajmljivanje> lista) {
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
        //ovo je kao petlja koja prolazi kroz matricu
        Iznajmljivanje i = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return i.getIdIznajmljivanja();
            case 1:
                return i.getUkupanIznos();
            case 2:
                return i.getOpisIznajmljivanja();
            case 3:
                return i.getIdRadnik().getIme() + " " + i.getIdRadnik().getPrezime();
            case 4:
                return i.getIdCitalac().getIme() + " " + i.getIdCitalac().getPrezime();

            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    public void setLista(List<Iznajmljivanje> lista) {
        this.lista = lista;
    }

    public void pretrazi(String id, String ukupanIznos, String opisIznajmljivanja, Radnik radnik, Citalac citalac) {
        List<Iznajmljivanje> filteredList = lista.stream()
                .filter(p -> id == null || id.isEmpty()
                || String.valueOf(p.getIdIznajmljivanja()).contains(id))
                .filter(p -> opisIznajmljivanja == null || opisIznajmljivanja.isEmpty()
                || p.getOpisIznajmljivanja().toLowerCase().contains(opisIznajmljivanja.toLowerCase()))
                .filter(p -> ukupanIznos == null || ukupanIznos.isEmpty()
                || String.valueOf(p.getUkupanIznos()).contains(ukupanIznos))
                .filter(p -> radnik == null || p.getIdRadnik().getIdRadnik() == radnik.getIdRadnik())
                .filter(p -> citalac == null || p.getIdCitalac().getIdCitalac() == citalac.getIdCitalac())
                .collect(Collectors.toList());

        this.lista = filteredList;
        fireTableDataChanged();
    }

    public void azurirajRed(int red, Iznajmljivanje novo) {
        if (red >= 0 && red < lista.size()) {
            lista.set(red, novo);
            fireTableRowsUpdated(red, red);
            fireTableDataChanged();
        }
    }
    
    public void osvezi(){
        fireTableDataChanged();
        fireTableRowsUpdated(0, lista.size()-1);
    }
    
    public void osvezi(List<Iznajmljivanje> lista){
        this.lista = lista;
        osvezi();
    }

}
