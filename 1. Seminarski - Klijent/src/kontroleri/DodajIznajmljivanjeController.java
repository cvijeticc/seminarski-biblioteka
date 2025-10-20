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
import forme.model.ModelTabeleStavkaIznajmljivanja;
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
    private final Iznajmljivanje iznajmljivanje;

    public DodajIznajmljivanjeController(DodajIznajmljivanjeForma dif, Iznajmljivanje iznajmljivanje) {
        this.dif = dif;
        this.iznajmljivanje = iznajmljivanje;
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

//        List<StavkaIznajmljivanja> izvor
//                = (iznajmljivanjeZaAzuriranje != null && iznajmljivanjeZaAzuriranje.getStavke() != null)
//                ? iznajmljivanjeZaAzuriranje.getStavke()
//                : iznajmljivanje.getStavke();
//
//        dif.getTblStavkeIznajmljivanja().setModel(new ModelTabeleStavkaIznajmljivanja(izvor));
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case IZMENI:
                dif.getBtnKreirajIznajmljivanje().setVisible(false);
                dif.getBtnDodajStavkuIznajmljivanja().setVisible(false);
                //ako je case izmeni onda sigurno mora forma vec da bude popunjena sa necim
                //e pa samo vracamo ono iznajmljivanje koje smo uhvatili u hashmapu od ranije
                dif.getTxtId().setText(iznajmljivanje.getIdIznajmljivanja() + "");
                dif.getTxtUkupanIznos().setText(iznajmljivanje.getUkupanIznos() + "");
                dif.getTxtOpisIznajmljivanja().setText(iznajmljivanje.getOpisIznajmljivanja());
                ModelTabeleStavkaIznajmljivanja mtsi = new ModelTabeleStavkaIznajmljivanja(iznajmljivanje.getStavke());
                dif.getTblStavkeIznajmljivanja().setModel(mtsi);
                break;
            case DODAJ:
                dif.getBtnKreirajIznajmljivanje().setVisible(true);
                dif.getBtnDodajStavkuIznajmljivanja().setVisible(true);
                dif.getBtnAzuriraj().setVisible(false);
                dif.getTxtId().setVisible(false);
                dif.getLblId().setVisible(false);
                break;
            default:
                throw new AssertionError();
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

//                ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getTblStavke().getModel();
                //jer je pif null
                DodajStavkuIznajmljivanjaController dsiController = new DodajStavkuIznajmljivanjaController(
                        new DodajStavkuIznajmljivanjaForma(),
                        iznajmljivanje,//ovde prosledjujemo objekat iznajmljivanje koji smo napravili
                        DodajIznajmljivanjeController.this,//i prosledjujemo referencu na roditeljski kontroler
                        null);

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
        
        dif.addBtnAzurirajStavkuIznajmljivanjaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = dif.getTblStavkeIznajmljivanja().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(dif, "Mora da bude selektovana neka stavka iznajmljivanja", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleStavkaIznajmljivanja mtsi = (ModelTabeleStavkaIznajmljivanja) dif.getTblStavkeIznajmljivanja().getModel();
                    StavkaIznajmljivanja si = mtsi.getLista().get(red);
                    Cordinator.getInstance().dodajParam("stavkaIznajmljivanja", si);
                    //sada se u hashmapi nalazi si pod kljucem "stavkaIznajmljivanja"
                    Cordinator.getInstance().otvoriIzmeniStavkuIznajmljivanjeFormu();


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

                Iznajmljivanje i = new Iznajmljivanje(id, ukupanIznos, opisIznajmljivanja, radnik, citalac, null);
                i.setStavke(((ModelTabeleStavkaIznajmljivanja) dif.getTblStavkeIznajmljivanja().getModel()).getLista());

                try {
                    System.out.println("CLIENT SEND: iznajmljivanjeId=" + i.getIdIznajmljivanja() + ", stavki=" + (i.getStavke()==null? "null" : i.getStavke().size()));
                    Komunikacija.getInstance().azurirajIznajmljivanje(i);
                    JOptionPane.showMessageDialog(dif, "Uspešno azurirao iznajmljivanje!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

                    dif.dispose();

                    PrikazIznajmljivanjaController prikazController
                            = (PrikazIznajmljivanjaController) Cordinator.getInstance().vratiParam("prikazIznajmljivanjaController");

                    prikazController.osveziFormu();

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

    void popuniTabeluStavkama(ModelTabeleStavkaIznajmljivanja mtsi) {
        dif.getTblStavkeIznajmljivanja().setModel(mtsi);
    }

}
