/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Iznajmljivanje;
import java.util.List;
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
        System.out.println("Provera prikaza u tabeli:");
        System.out.println("Radnik: " + i.getIdRadnik());
        System.out.println("Čitalac: " + i.getIdCitalac());

        switch (columnIndex) {
            case 0:
                return i.getIdIznajmljivanja();
            case 1:
                return i.getUkupanIznos();
            case 2:
                return i.getOpisIznajmljivanja();
            case 3:
                return i.getIdRadnik().getIme() +" "+i.getIdRadnik().getPrezime();
            case 4:
                return i.getIdCitalac().getIme() +" "+i.getIdCitalac().getPrezime();

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

}
