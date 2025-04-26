package kontroleri;

import cordinator.Cordinator;
import domen.Knjiga;
import forme.DodajKnjiguForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author andri
 */
public class DodajKnjiguController {

    private final DodajKnjiguForma dkf;

    public DodajKnjiguController(DodajKnjiguForma dkf) {
        this.dkf = dkf;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu();
        pripremiFormu(mod);

        dkf.setVisible(true);
    }

    private void addActionListeners() {

        dkf.getBtnDodaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String naziv = dkf.getTxtNaziv().getText().trim();
                String zanr = dkf.getTxtZanrKnjige().getText().trim();
                int godina = Integer.parseInt(dkf.getTxtGodinaIzdavanja().getText().trim());
                double iznosPoDanu = Double.parseDouble(dkf.getTxtIznosPoDanu().getText().trim());

                Knjiga knjiga = new Knjiga(-1, naziv, zanr, godina, iznosPoDanu);

                int izbor = JOptionPane.showConfirmDialog(
                        dkf,
                        "Da li ste sigurni da želite da dodate novu knjigu?",
                        "Potvrda",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (izbor != JOptionPane.YES_OPTION) {
                    return;
                }

                try {
                    Komunikacija.getInstance().dodajKnjigu(knjiga);
                    JOptionPane.showMessageDialog(dkf, "Uspešno kreirana knjiga!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Neuspešno kreiranje knjige!", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        dkf.getBtnAzuriraj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int id = Integer.parseInt(dkf.getTxtId().getText().trim());
                String naziv = dkf.getTxtNaziv().getText().trim();
                String zanr = dkf.getTxtZanrKnjige().getText().trim();
                int godina = Integer.parseInt(dkf.getTxtGodinaIzdavanja().getText().trim());
                double iznosPoDanu = Double.parseDouble(dkf.getTxtIznosPoDanu().getText().trim());

                Knjiga knjiga = new Knjiga(id, naziv, zanr, godina, iznosPoDanu);

                try {
                    Komunikacija.getInstance().azurirajKnjigu(knjiga);
                    JOptionPane.showMessageDialog(dkf, "Uspešno ažurirana knjiga!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                    PrikazKnjigaController prikazController =
                            (PrikazKnjigaController) Cordinator.getInstance().vratiParam("prikazKnjigaController");
                    prikazController.osveziFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Neuspešno ažuriranje knjige!", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void pripremiFormu() {
        dkf.setLocationRelativeTo(null);
        dkf.getTxtId().setEnabled(false);
        dkf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dkf.getBtnAzuriraj().setVisible(false);
                dkf.getBtnDodaj().setVisible(true);
                dkf.getBtnDodaj().setEnabled(true);
                dkf.getTxtId().setVisible(false);
                dkf.getLblId().setVisible(false);
                break;
            case IZMENI:
                dkf.getBtnAzuriraj().setVisible(true);
                dkf.getBtnDodaj().setVisible(false);
                dkf.getBtnAzuriraj().setEnabled(true);

                Knjiga knjiga = (Knjiga) Cordinator.getInstance().vratiParam("knjiga");
                dkf.getTxtNaziv().setText(knjiga.getNaziv());
                dkf.getTxtZanrKnjige().setText(knjiga.getZanrKnjige());
                dkf.getTxtGodinaIzdavanja().setText(String.valueOf(knjiga.getGodinaIzdavanja()));
                dkf.getTxtIznosPoDanu().setText(String.valueOf(knjiga.getIznosPoDanu()));
                dkf.getTxtId().setText(String.valueOf(knjiga.getIdKnjiga()));
                break;
            default:
                throw new AssertionError();
        }
    }
}
