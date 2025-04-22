/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Radnik;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andri
 */
public class ModelTabeleRadnik extends AbstractTableModel {

    private List<Radnik> lista;
    private final String[] kolone = {"ID", "Ime", "Prezime", "Email", "Korisničko ime"};

    public ModelTabeleRadnik(List<Radnik> lista) {
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
        Radnik r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getIdRadnik();
            case 1:
                return r.getIme();
            case 2:
                return r.getPrezime();
            case 3:
                return r.getEmail();
            case 4:
                return r.getKorisnickoIme();
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Radnik> getLista() {
        return lista;
    }

    public void pretrazi(String ime, String prezime, String email, String korisnickoIme) {
        List<Radnik> filtrirano = lista.stream()
                .filter(r -> (ime == null || ime.isEmpty() || r.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(r -> (prezime == null || prezime.isEmpty() || r.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(r -> (email == null || email.isEmpty() || r.getEmail().toLowerCase().contains(email.toLowerCase())))
                .filter(r -> (korisnickoIme == null || korisnickoIme.isEmpty() || r.getKorisnickoIme().toLowerCase().contains(korisnickoIme.toLowerCase())))
                .collect(Collectors.toList());

        this.lista = filtrirano;
        fireTableDataChanged();
    }
}
