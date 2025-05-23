/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Citalac;
import domen.Iznajmljivanje;
import domen.Radnik;
import domen.StavkaIznajmljivanja;
import forme.PrikazIznajmljivanjaForma;
import forme.model.ModelTabeleIznajmljivanje;
import forme.model.ModelTabeleStavkaIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author andri
 */
public class PrikazIznajmljivanjaController {

    private final PrikazIznajmljivanjaForma pif;
    private List<Iznajmljivanje> iznajmljivanja;

    public PrikazIznajmljivanjaController(PrikazIznajmljivanjaForma pif) {
        this.pif = pif;
        addActionListener();
        addMouseListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pif.setVisible(true);
    }

    public void pripremiFormu() {
        pif.getCmbCitalac().removeAllItems();
        pif.getCmbRadnik().removeAllItems();
        pif.getTxtOpisIznajmljivanja().setText("");
        pif.getTxtUkupanIznos().setText("");
        pif.getTxtId().setText("");
        pif.setLocationRelativeTo(null);
        pif.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        iznajmljivanja = Komunikacija.getInstance().ucitajIznajmljivanja();
        ModelTabeleIznajmljivanje mti = new ModelTabeleIznajmljivanje(iznajmljivanja);
        pif.getTblIznajmljivanja().setModel(mti);

        List<StavkaIznajmljivanja> stavkaIznajmljivanja = new ArrayList<>();
        System.out.println("Klasa prcontroller");
        System.out.println(stavkaIznajmljivanja);
        ModelTabeleStavkaIznajmljivanja mtsi = new ModelTabeleStavkaIznajmljivanja(stavkaIznajmljivanja);
        pif.getTblStavke().setModel(mtsi);

        List<Radnik> radnici = Komunikacija.getInstance().ucitajRadnike();

        pif.getCmbRadnik().addItem(null);
        for (Radnik r : radnici) {

            pif.getCmbRadnik().addItem(r);
        }

        List<Citalac> citaoci = Komunikacija.getInstance().ucitajCitaoce();
        pif.getCmbCitalac().addItem(null);
        for (Citalac c : citaoci) {
            pif.getCmbCitalac().addItem(c);
        }

    }

    private void addActionListener() {
        pif.addBtnPretraziIznajmljivanjeActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (pif.getTxtId().getText());
                String ukuopanIznos = pif.getTxtUkupanIznos().getText();
                String opisIznajmljivanja = pif.getTxtOpisIznajmljivanja().getText();
                Radnik radnik = (Radnik) pif.getCmbRadnik().getSelectedItem();
                Citalac citalac = (Citalac) pif.getCmbCitalac().getSelectedItem();
                if (ukuopanIznos.isEmpty() && opisIznajmljivanja.isEmpty() && radnik == null && citalac == null && id.isEmpty()) {
                    JOptionPane.showMessageDialog(pif, "Navedi neku informaciju o iznajmljivanju", "Moras popuniti nesto", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getTblIznajmljivanja().getModel();
                mti.pretrazi(id, ukuopanIznos, opisIznajmljivanja, radnik, citalac);
            }
        });

        pif.addBtnResetujPretraguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //osveziFormu();
                pripremiFormu();
            }
        });

        pif.addBtnAzurirajIznajmljivanjeActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //otvori formu dodaj Iznajmljivanje 
                int red = pif.getTblIznajmljivanja().getSelectedRow();
                System.out.println("📌 Selektovani red u tabeli je: " + red);

                if (red == -1) {
                    JOptionPane.showMessageDialog(pif, "Mora da bude selektovano neko iznajmljivanje", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getTblIznajmljivanja().getModel();
                    Iznajmljivanje i = mti.getLista().get(red); // uzimamo onaj red selektovani

                    Cordinator.getInstance().dodajParam("iznajmljivanje", i);
                    //ovde hvatamo to iznajmljivanje koje cemo kasnije da koristimo 
                    //da popunimo formu za dodavanje ili azuriranje
                    Cordinator.getInstance().otvoriIzmeniIznajmljivanjeFormu();

                }

            }
        });

        pif.addBtnObrisiStavkuIznajmljivanjaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pif.getTblStavke().getSelectedRow();
                System.out.println("🗑️ Selektovani red stavke za brisanje: " + red);

                if (red == -1) {
                    JOptionPane.showMessageDialog(pif, "Morate selektovati stavku iznajmljivanja koju želite da obrišete.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    int potvrda = JOptionPane.showConfirmDialog(pif, "Da li ste sigurni da želite da obrišete izabranu stavku?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);

                    if (potvrda == JOptionPane.YES_OPTION) {
                        ModelTabeleStavkaIznajmljivanja mtsi = (ModelTabeleStavkaIznajmljivanja) pif.getTblStavke().getModel();
                        StavkaIznajmljivanja stavka = mtsi.getLista().get(red);

                        boolean uspesno = Komunikacija.getInstance().obrisiStavkuIznajmljivanja(stavka);

                        if (uspesno) {
                            mtsi.getLista().remove(red);
                            mtsi.fireTableDataChanged();
                            JOptionPane.showMessageDialog(pif, "Stavka iznajmljivanja je uspešno obrisana.");
                        } else {
                            JOptionPane.showMessageDialog(pif, "Brisanje stavke iznajmljivanja nije uspelo.", "Greška", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        pif.addBtnAzurirajStavkuIznajmljivanjaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pif.getTblStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pif, "Mora da bude selektovana neka stavka iznajmljivanja", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleStavkaIznajmljivanja mtsi = (ModelTabeleStavkaIznajmljivanja) pif.getTblStavke().getModel();
                    StavkaIznajmljivanja si = mtsi.getLista().get(red);
                    Cordinator.getInstance().dodajParam("stavkaIznajmljivanja", si);
                    //sada se u hashmapi nalazi si pod kljucem "stavkaIznajmljivanja"
                    Cordinator.getInstance().otvoriIzmeniStavkuIznajmljivanjeFormu();

                }
            }
        });

        pif.addBtnOsveziTabeluIznajmljivanjaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iznajmljivanja = Komunikacija.getInstance().ucitajIznajmljivanja(); // 🔄 novo učitavanje!
                ModelTabeleIznajmljivanje modelIzn = (ModelTabeleIznajmljivanje) pif.getTblIznajmljivanja().getModel();
                modelIzn.setLista(iznajmljivanja); // ažuriraj listu u modelu
                modelIzn.fireTableDataChanged();   // obavesti tabelu
                System.out.println("✅ Tabela uspešno osvežena");
            }
        });

    }

    public void osveziFormu() {
        pripremiFormu();
    }

    private void addMouseListener() {
        //pif = prikaz iznajmljivanja forma
        pif.getTblIznajmljivanja().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pif.getTblIznajmljivanja().getSelectedRow();
                if (red != -1) {
                    ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getTblIznajmljivanja().getModel();
                    Iznajmljivanje iznajmljivanje = mti.getLista().get(red);
                    //uzimamo bas to iznajmljivanje na tom selektovanom redu
                    List<StavkaIznajmljivanja> stavkeIznajmljivanja = Komunikacija.getInstance().
                            ucitajStavkuIznajmljivanja(iznajmljivanje.getIdIznajmljivanja());
                    ModelTabeleStavkaIznajmljivanja mtsi = new ModelTabeleStavkaIznajmljivanja(stavkeIznajmljivanja);
                    pif.getTblStavke().setModel(mtsi);

                    double noviUkupanIznos = stavkeIznajmljivanja.stream()
                            .mapToDouble(StavkaIznajmljivanja::getUkupanIznosStavke)
                            .sum();
                    iznajmljivanje.setUkupanIznos(noviUkupanIznos);

                    Komunikacija.getInstance().azurirajIznajmljivanje(iznajmljivanje);

//                    pif.getTxtUkupanIznos().setText(String.valueOf(noviUkupanIznos));

                }
            }
        });
    }

    public int getSelektovaniRed() {
        return pif.getTblIznajmljivanja().getSelectedRow();
    }

    public Iznajmljivanje getSelektovanoIznajmljivanje() {
        int red = getSelektovaniRed();
        if (red == -1) {
            return null;
        }
        ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getTblIznajmljivanja().getModel();
        return mti.getLista().get(red);
    }

    public void azurirajRedUTabeli(int red, Iznajmljivanje novo) {
        System.out.println("📋 UI: Ažuriram red " + red + " u tabeli sa: " + novo);

        ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getTblIznajmljivanja().getModel();
        mti.azurirajRed(red, novo);
        pif.getTxtUkupanIznos().setText(String.valueOf(novo.getUkupanIznos()));
    }

}
