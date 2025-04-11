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
import forme.DodajIznajmljivanjeForma;
import forme.DodajStavkuIznajmljivanjaForma;
import forme.FormaMod;
import forme.PrikazIznajmljivanjaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author andri
 */
public class DodajIznajmljivanjeController {

    private final DodajIznajmljivanjeForma dif;
//    private final DodajStavkuIznajmljivanjaForma dsif;
    private Iznajmljivanje iznajmljivanje = new Iznajmljivanje();

    public DodajIznajmljivanjeController(DodajIznajmljivanjeForma dif) {
        this.dif = dif;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu();
        pripremiFormu(mod);

        dif.setVisible(true);

    }

    private void pripremiFormu() {
        dif.getTxtUkupanIznos().setEditable(false);
        dif.getTxtId().setEditable(false);
        dif.setLocationRelativeTo(null);
        dif.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dif.getCmbCitaoci().removeAllItems();
        dif.getCmbRadnici().removeAllItems();
        List<Radnik> radnici = Komunikacija.getInstance().ucitajRadnike();
        List<Citalac> citaoci = Komunikacija.getInstance().ucitajCitaoce();

        for (Citalac c : citaoci) {
            dif.getCmbCitaoci().addItem(c);
        }
        for (Radnik r : radnici) {
            dif.getCmbRadnici().addItem(r);
        }

    }

    private void addActionListener() {
        dif.addBtnDodajStavkuIznajmljivanjaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                Cordinator.getInstance().otvoriDodajStavkuIznajmljivanjaFormu(iznajmljivanje, DodajIznajmljivanjeController.this);
                //popunjavam sa forme u jedan objekat
            }
        });

        dif.addBtnKreirajIznajmljivanjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreiraj(e);
            }

            private void kreiraj(ActionEvent e) {
                DodajStavkuIznajmljivanjaController dsiController = new DodajStavkuIznajmljivanjaController(new DodajStavkuIznajmljivanjaForma(), iznajmljivanje, DodajIznajmljivanjeController.this);

                if (validacija()) {
                    iznajmljivanje.setIdCitalac((Citalac) dif.getCmbCitaoci().getSelectedItem());
                    iznajmljivanje.setIdRadnik((Radnik) dif.getCmbRadnici().getSelectedItem());
                    iznajmljivanje.setOpisIznajmljivanja(dif.getTxtOpisIznajmljivanja().getText());
                    iznajmljivanje.setUkupanIznos(Double.parseDouble(dif.getTxtUkupanIznos().getText()));
                    Komunikacija.getInstance().dodajIznajmljivanje(iznajmljivanje);
                    JOptionPane.showMessageDialog(dif, "Uspesno kreirano iznajmljivanje sa stavkama iznajmljivanja",
                            "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        dif.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int id = Integer.parseInt(dif.getTxtId().getText());
                double ukupanIznos = Double.parseDouble(dif.getTxtUkupanIznos().getText().trim());
                String opisIznajmljivanja = dif.getTxtOpisIznajmljivanja().getText().trim();
                Radnik radnik = (Radnik) dif.getCmbRadnici().getSelectedItem();
                Citalac citalac = (Citalac) dif.getCmbCitaoci().getSelectedItem();

                // Kreiranje objekta iznajmljivanje
                Iznajmljivanje i = new Iznajmljivanje(id, ukupanIznos, opisIznajmljivanja, radnik, citalac, null);

                try {
                    Komunikacija.getInstance().azurirajIznajmljivanje(i);
                    JOptionPane.showMessageDialog(dif, "Uspešno azurirao iznajmljivanje!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

                    dif.dispose();

                    PrikazIznajmljivanjaController prikazController
                            = (PrikazIznajmljivanjaController) Cordinator.getInstance().vratiParam("prikazIznajmljivanjaController");

                    prikazController.osveziFormu(); // ✅ Tvoje osvežavanje tabele

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dif, "Neuspešno azurirano iznajmljivanje!", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    private boolean validacija() {

        if (dif.getTxtOpisIznajmljivanja().getText().isBlank()) {
            JOptionPane.showMessageDialog(dif, "Popuni opis",
                    "Popuni opis", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (dif.getTxtUkupanIznos().getText().isBlank()) {
            JOptionPane.showMessageDialog(dif, "Moras dodati stavku ili stavke iznajmljivanja"
                    + " da bi se popunio ukupan iznos iznajmljivanja",
                    "Popuni opis", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

    public void setUkupanIznos() {
        double ukupanIznos = 0;
        for (StavkaIznajmljivanja si : iznajmljivanje.getStavke()) {
            ukupanIznos += si.getUkupanIznosStavke();

        }
        dif.getTxtUkupanIznos().setText(ukupanIznos + "");

    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case IZMENI:
                dif.getBtnKreirajIznajmljivanje().setVisible(false);
                dif.getBtnDodajStavkuIznajmljivanja().setVisible(false);
                Iznajmljivanje i = (Iznajmljivanje) Cordinator.getInstance().vratiParam("iznajmljivanje");
                dif.getTxtId().setText(i.getIdIznajmljivanja() + "");
                dif.getTxtUkupanIznos().setText(i.getUkupanIznos() + "");
                dif.getTxtOpisIznajmljivanja().setText(i.getOpisIznajmljivanja());
                dif.getCmbCitaoci().setEnabled(false);
                dif.getCmbRadnici().setEnabled(false);
                break;
            case DODAJ:
                dif.getBtnKreirajIznajmljivanje().setVisible(true);
                dif.getBtnDodajStavkuIznajmljivanja().setVisible(true);
                dif.getBtnAzuriraj().setVisible(false);
                break;
            default:
                throw new AssertionError();
        }
    }

}
