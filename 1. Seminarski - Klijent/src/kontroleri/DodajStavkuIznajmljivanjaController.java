/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Iznajmljivanje;
import domen.Knjiga;
import domen.StavkaIznajmljivanja;
import forme.DodajStavkuIznajmljivanjaForma;
import forme.FormaMod;
import forme.model.ModelTabeleIznajmljivanje;
import forme.model.ModelTabeleStavkaIznajmljivanja;
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

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
    private FormaMod mod;
    private ModelTabeleIznajmljivanje modelTabeleIznajmljivanje;

    public DodajStavkuIznajmljivanjaController(DodajStavkuIznajmljivanjaForma dsif, Iznajmljivanje iznajmljivanje, DodajIznajmljivanjeController dic, ModelTabeleIznajmljivanje modelTabeleIznajmljivanje) {
        this.dic = dic;
        this.iznajmljivanje = iznajmljivanje;
        this.dsif = dsif;
        this.modelTabeleIznajmljivanje = modelTabeleIznajmljivanje;
        addActionListeners();
        addItemListeners();
        addListenerForDate();
        addWindowsListener();
    }

    public void otvoriFormu(FormaMod mod) {
        this.mod = mod;
        pripremiFormu();
        pripremiFormu(mod);
        dsif.setVisible(true);

    }

    private void pripremiFormu() {
        dsif.getTxtIdIznajmljivanja().setEditable(false);
        dsif.setLocationRelativeTo(null);
        dsif.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dsif.getCmbKnjige().removeAllItems();

        List<Knjiga> knjige = Komunikacija.getInstance().ucitajKnjige();
        dsif.getCmbKnjige().addItem(null);
        for (Knjiga k : knjige) {
            dsif.getCmbKnjige().addItem(k);
        }
        zabraniPromenuPolja();

    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case IZMENI:
                dsif.getBtnDodaj().setVisible(false);
                dsif.getTxtIdIznajmljivanja().setEditable(false);
                StavkaIznajmljivanja si = (StavkaIznajmljivanja) Cordinator.getInstance()
                        .vratiParam("stavkaIznajmljivanja");

                if (si != null) {
                    dsif.getTxtIdIznajmljivanja().setText(String.valueOf(si.getIdIznajmljivanje()));
                    dsif.getTxtOpisStakve().setText(si.getOpisStavke());
                    dsif.getTxtDatumOd().setText(si.getDatumOd().toString());
                    dsif.getTxtDatumDo().setText(si.getDatumDo().toString());
                    dsif.getTxtBrojDana().setText(String.valueOf(si.getBrojDana()));
                    dsif.getTxtIznosPoDanu().setText(String.valueOf(si.getIznosPoDanu()));
                    dsif.getTxtUkupanIznosStavke().setText(String.valueOf(si.getUkupanIznosStavke()));
                    dsif.getCmbKnjige().setSelectedItem(si.getIdKnjiga());

                    // Postavi interne vrednosti
                    datumOd = si.getDatumOd();
                    datumDo = si.getDatumDo();
                    brojDana = si.getBrojDana();
                    iznosPoDanu = si.getIznosPoDanu();
                    //ukupanIznos = si.getUkupanIznosStavke();
                    selektovanaKnjiga = si.getIdKnjiga();

                    // Osveži izračunate vrednosti u slučaju promene
                    izracunajUkupanIznos();
                    //ta funkcija ce sada da koristi nove vrednosti za 
                    //brojDana i iznosPoDanu

                }
                break;

            case DODAJ:
                dsif.getBtnAzuriraj().setVisible(false);
                dsif.getLblIdIznajmljivanje().setVisible(false);
                dsif.getTxtIdIznajmljivanja().setVisible(false);
                break;

        }
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
                    System.out.println("EDITED STAVKA: rb=" + si.getRb() + ", idIznaj=" + si.getIdIznajmljivanje() + ", totalStavke=" + si.getUkupanIznosStavke());
                    System.out.println("PARENT LIST SIZE (posle izmene): " + iznajmljivanje.getStavke().size());
                    listaStavkiIznajmljivanja.add(si);
                    ModelTabeleStavkaIznajmljivanja mtsi = new ModelTabeleStavkaIznajmljivanja(listaStavkiIznajmljivanja);
                    dic.popuniTabeluStavkama(mtsi);
                    iznajmljivanje.setStavke(listaStavkiIznajmljivanja);
                    //ovde Iznajmljivanje iz roditeljske forme dobija svoju listu Iznajmljivanja
                    dic.setUkupanIznos();
                    //valjda nakon sto sam ja napisao setStavke() ovde gore znaci da sam ja u to
                    //iznajmljivanje setovao stavke i onde su tu i u iznajmljivanju za dsic i za dic
                    //i onda samo setUkupanIznos() i to je to
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
                JOptionPane.showMessageDialog(dsif, "Uspesno dodata stavka iznajmljivanja ", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }

        });

        dsif.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
//                modelTabeleIznajmljivanje.osvezi();
            }

            private void izmeni(ActionEvent e) {
                System.out.println(" Pokrenuta izmena stavke iznajmljivanja");
                try {

//                     Prikupljanje podataka iz forme
                    int id = Integer.parseInt(dsif.getTxtIdIznajmljivanja().getText().trim());
                    String opis = dsif.getTxtOpisStakve().getText().trim();
                    datumOd = LocalDate.parse(dsif.getTxtDatumOd().getText().trim(), dtf);
                    datumDo = LocalDate.parse(dsif.getTxtDatumDo().getText().trim(), dtf);
                    brojDana = Integer.parseInt(dsif.getTxtBrojDana().getText().trim());
                    iznosPoDanu = Double.parseDouble(dsif.getTxtIznosPoDanu().getText().trim());
                    ukupanIznos = Double.parseDouble(dsif.getTxtUkupanIznosStavke().getText().trim());
                    selektovanaKnjiga = (Knjiga) dsif.getCmbKnjige().getSelectedItem();

                    // Uzimanje objekta koji se menja
                    StavkaIznajmljivanja si = (StavkaIznajmljivanja) Cordinator.getInstance().vratiParam("stavkaIznajmljivanja");

                    if (si != null) {
//                        // Ažuriranje vrednosti
                        si.setOpisStavke(opis);
                        si.setDatumOd(datumOd);
                        si.setDatumDo(datumDo);
                        si.setBrojDana(brojDana);
                        si.setIznosPoDanu(iznosPoDanu);
                        si.setUkupanIznosStavke(ukupanIznos);
                        si.setIdKnjiga(selektovanaKnjiga);

                        // Poziv ka serveru za ažuriranje
//                        Komunikacija.getInstance().azurirajStavkuIznajmljivanja(si);
                        System.out.println(" Stavka iznajmljivanja azurirana u bazi: " + si);

                        JOptionPane.showMessageDialog(dsif, "Uspešno izmenjena stavka iznajmljivanja!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        dsif.dispose();

                        PrikazIznajmljivanjaController prikazController
                                = (PrikazIznajmljivanjaController) Cordinator.getInstance().vratiParam("prikazIznajmljivanjaController");

                        int red = prikazController.getSelektovaniRed();
                        if (red != -1) {

                            Iznajmljivanje iznajmljivanje = prikazController.getSelektovanoIznajmljivanje();
                            List<StavkaIznajmljivanja> stavke = Komunikacija.getInstance()
                                    .ucitajStavkuIznajmljivanja(iznajmljivanje.getIdIznajmljivanja());
                            System.out.println("Računam novi ukupan iznos za iznajmljivanje ID: " + iznajmljivanje.getIdIznajmljivanja());

                            double noviUkupan = stavke.stream()
                                    .mapToDouble(StavkaIznajmljivanja::getUkupanIznosStavke)
                                    .sum();
                            iznajmljivanje.setUkupanIznos(noviUkupan);//ove ja upisujem ukupan iznos u ekran DodajIznajmljivanjeForma
                            iznajmljivanje.setStavke(stavke);
                            iznajmljivanje.setUkupanIznos(noviUkupan);
                            Komunikacija.getInstance().azurirajIznajmljivanje(iznajmljivanje);
                            System.out.println("Iznajmljivanje ažurirano u bazi: Novi ukupan iznos = " + iznajmljivanje.getUkupanIznos());
                            System.out.println("UI: Ažuriram red " + red + " u tabeli sa: " + iznajmljivanje);

                            prikazController.azurirajRedUTabeli(red, iznajmljivanje);
                        }

                    } else {
                        JOptionPane.showMessageDialog(dsif, "Greška: stavka iznajmljivanja nije pronađena.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dsif, "Neuspešna izmena stavke iznajmljivanja!", "Greška", JOptionPane.ERROR_MESSAGE);
                }
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

    private void addWindowsListener() {
        dsif.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (mod == FormaMod.IZMENI) {
                    dsif.dispose();
                    return;
                }
                int odgovor = JOptionPane.showConfirmDialog(dsif,
                        "Ako sada izađete iz ove forme, kasnije nećete moći da dodajete stavke u ovo iznajmljivanje.\nDa li ste sigurni da želite da zatvorite formu?",
                        "Potvrda zatvaranja",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (odgovor == JOptionPane.YES_OPTION) {
                    dsif.dispose();
                } else {
                    // Ostaje u formi
                    // Ne radi ništa, samo ignoriše zatvaranje
                }
            }
        });

    }

}
