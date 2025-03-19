/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Iznajmljivanje;
import domen.Knjiga;
import domen.StavkaIznajmljivanja;
import forme.DodajStavkuIznajmljivanjaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author andri
 */
public class DodajStavkuIznajmljivanjaController {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy"); // Prilagodi format ako treba
    private int brojDana;
    private double iznosPoDanu;
    private LocalDate datumOd;
    private LocalDate datumDo;
    private Knjiga selektovanaKnjiga;
    private double ukupanIznos;
    private List<StavkaIznajmljivanja> listaStavkiIznajmljivanja = new ArrayList<>();
    private Iznajmljivanje iznajmljivanje;
    private final DodajStavkuIznajmljivanjaForma dsif;
    private DodajIznajmljivanjeController dic;
//    iznajmljivanje.setStavke(listaStavkiIznajmljivanja); zasto ovo ne moze

    public DodajStavkuIznajmljivanjaController(DodajStavkuIznajmljivanjaForma dsif, Iznajmljivanje iznajmljivanje, DodajIznajmljivanjeController dic) {
        this.dic = dic;
        this.iznajmljivanje = iznajmljivanje;
        this.dsif = dsif;
        addActionListeners();
        addItemListeners();
        addListenerForDate();

        dsif.setLocationRelativeTo(null);
        dsif.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void otvoriFormu() {
        pripremiFormu();
        dsif.setVisible(true);

    }

    private void pripremiFormu() {
        dsif.setLocationRelativeTo(null);
        dsif.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dsif.getCmbKnjige().removeAllItems();

        List<Knjiga> knjige = Komunikacija.getInstance().ucitajKnjige();
        dsif.getCmbKnjige().addItem(null);
        for (Knjiga k : knjige) {
            dsif.getCmbKnjige().addItem(k);
        }
        zabraniPromenuPolja();

    }

    private void zabraniPromenuPolja() {
        dsif.getTxtBrojDana().setEnabled(false);
        dsif.getTxtIznosPoDanu().setEnabled(false);
        dsif.getTxtUkupanIznosStavke().setEnabled(false);

    }

    private void addActionListeners() {
        dsif.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                if (validiraj()) {
                    StavkaIznajmljivanja si = new StavkaIznajmljivanja();
                    si.setBrojDana(brojDana);
                    si.setDatumDo(datumDo);
                    si.setDatumOd(datumOd);
//                    si.setIdIznajmljivanje(brojDana);
                    si.setIdKnjiga(selektovanaKnjiga);
                    si.setIznosPoDanu(iznosPoDanu);
                    String opisStavke = dsif.getTxtOpisStakve().getText();
                    si.setOpisStavke(opisStavke);
                    int rb = iznajmljivanje.getStavke().size() + 1;
                    si.setRb(rb);
                    si.setUkupanIznosStavke(ukupanIznos);
                    listaStavkiIznajmljivanja.add(si);

                    iznajmljivanje.setStavke(listaStavkiIznajmljivanja);
                    dic.setUkupanIznos();
                }

            }

            private boolean validiraj() {
                if (dsif.getTxtOpisStakve().getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dsif, "Moraju svi atributi da budu popunjeni", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (dsif.getTxtDatumDo().getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dsif, "Moraju svi atributi da budu popunjeni", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (dsif.getTxtDatumOd().getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dsif, "Moraju svi atributi da budu popunjeni", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (dsif.getTxtBrojDana().getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dsif, "Moraju svi atributi da budu popunjeni", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (dsif.getTxtIznosPoDanu().getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dsif, "Moraju svi atributi da budu popunjeni", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (dsif.getTxtUkupanIznosStavke().getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dsif, "Moraju svi atributi da budu popunjeni", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (dsif.getCmbKnjige().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(dsif, "Moraju svi atributi da budu popunjeni", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                if (brojDana <= 0) {
                    JOptionPane.showMessageDialog(dsif, "Mora datum od da bude pre datuma do", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                JOptionPane.showMessageDialog(dsif, "Uspesna prica", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }

        });

    }

    private void addItemListeners() {
        dsif.getCmbKnjige().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selektovanaKnjiga = (Knjiga) dsif.getCmbKnjige().getSelectedItem();
                    if (selektovanaKnjiga != null) {
                        iznosPoDanu = selektovanaKnjiga.getIznosPoDanu();
                        dsif.getTxtIznosPoDanu().setText(iznosPoDanu + "");
                        izracunajUkupanIznos();
                    }
                }
            }
        });
    }

    private void izracunajBrojDana() {
        try {
            // Parsiranje stringova iz tekstualnih polja u LocalDate
            datumOd = LocalDate.parse(dsif.getTxtDatumOd().getText(), dtf);
            datumDo = LocalDate.parse(dsif.getTxtDatumDo().getText(), dtf);

            // Računanje broja dana između dva datuma
            brojDana = (int) ChronoUnit.DAYS.between(datumOd, datumDo);
            if (brojDana <= 0) {
                return;
            }
            // Postavljanje vrednosti u tekstualno polje
            dsif.getTxtBrojDana().setText(String.valueOf(brojDana));
            izracunajUkupanIznos();
        } catch (DateTimeParseException ignored) {
        }
    }

    private void addListenerForDate() {
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                izracunajBrojDana();
            }
        };

        dsif.getTxtDatumOd().addKeyListener(keyListener);
        dsif.getTxtDatumDo().addKeyListener(keyListener);

        izracunajBrojDana();

    }

    public void izracunajUkupanIznos() {
        if (brojDana <= 0 || iznosPoDanu == 0) {
            return;
        }
        ukupanIznos = brojDana * iznosPoDanu;
        dsif.getTxtUkupanIznosStavke().setText(ukupanIznos + "");
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

}
