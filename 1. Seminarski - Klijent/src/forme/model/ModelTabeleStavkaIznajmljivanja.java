/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaIznajmljivanja;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andri
 */
public class ModelTabeleStavkaIznajmljivanja extends AbstractTableModel {

    List<StavkaIznajmljivanja> lista;
//    private int idIznajmljivanje;
//    private int rb;
//    private String opisStavke;
//    private LocalDate datumOd;
//    private LocalDate datumDo;
//    private int brojDana;
//    private double iznosPoDanu;
//    private double ukupanIznosStavke;
//    private Knjiga idKnjiga;
    String[] kolone = {"idIznajmljivanje", "rb", "opis stavke", "datumOd", "datumDo", "brojDana", "iznosPoDanu", "ukupanIznosStavke", "Knjiga"};

    public ModelTabeleStavkaIznajmljivanja(List<StavkaIznajmljivanja> lista) {
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
        StavkaIznajmljivanja si = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return si.getIdIznajmljivanje();
            case 1:
                return si.getRb();
            case 2:
                return si.getOpisStavke();
            case 3:
                return si.getDatumOd();
            case 4:
                return si.getDatumDo();
            case 5:
                return si.getBrojDana();
            case 6:
                return si.getIznosPoDanu();
            case 7:
                return si.getUkupanIznosStavke();
            case 8:
                return si.getIdKnjiga().getNaziv();
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }

    public void setLista(List<StavkaIznajmljivanja> lista) {
        this.lista = lista;
    }
}
