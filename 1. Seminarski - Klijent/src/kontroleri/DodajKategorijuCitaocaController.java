package kontroleri;

import cordinator.Cordinator;
import domen.KategorijaCitaoca;
import forme.DodajKategorijuCitaocaForma;
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
public class DodajKategorijuCitaocaController {

    private final DodajKategorijuCitaocaForma dkcf;

    public DodajKategorijuCitaocaController(DodajKategorijuCitaocaForma dkcf) {
        this.dkcf = dkcf;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu();
        pripremiFormu(mod);

        dkcf.setVisible(true);
    }

    private void addActionListeners() {
        dkcf.getBtnDodaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String naziv = dkcf.getTxtNazivKategorije().getText().trim();
                String beneficije = dkcf.getTxtBeneficije().getText().trim();

                KategorijaCitaoca kategorija = new KategorijaCitaoca(-1, naziv, beneficije);

                int izbor = JOptionPane.showConfirmDialog(
                        dkcf,
                        "Da li ste sigurni da želite da dodate novu kategoriju čitaoca?",
                        "Potvrda dodavanja kategorije",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (izbor != JOptionPane.YES_OPTION) {
                    return;
                }

                try {
                    Komunikacija.getInstance().dodajKategorijuCitaoca(kategorija);
                    JOptionPane.showMessageDialog(dkcf, "Uspešno kreirana kategorija!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dkcf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkcf, "Neuspešno kreiranje kategorije!", "Greška", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        dkcf.getBtnAzuriraj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int id = Integer.parseInt(dkcf.getTxtId().getText().trim());
                String naziv = dkcf.getTxtNazivKategorije().getText().trim();
                String beneficije = dkcf.getTxtBeneficije().getText().trim();

                KategorijaCitaoca kategorija = new KategorijaCitaoca(id, naziv, beneficije);

                try {
                    Komunikacija.getInstance().azurirajKategorijuCitaoca(kategorija);
                    JOptionPane.showMessageDialog(dkcf, "Uspešno ažurirana kategorija!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dkcf.dispose();
//                     Osveži formu za prikaz kategorija ako postoji (možeš napraviti ako treba)
                     PrikazKategorijaCitaocaController prikazController =
                             (PrikazKategorijaCitaocaController) Cordinator.getInstance().vratiParam("prikazKategorijaCitaocaController");
                     prikazController.osveziFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkcf, "Neuspešno ažuriranje kategorije!", "Greška", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    private void pripremiFormu() {
        dkcf.setLocationRelativeTo(null);
        dkcf.getTxtId().setEnabled(false);
        dkcf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dkcf.getBtnAzuriraj().setVisible(false);
                dkcf.getBtnDodaj().setVisible(true);
                dkcf.getBtnDodaj().setEnabled(true);
                dkcf.getTxtId().setVisible(false);
                dkcf.getLblId().setVisible(false);
                break;
            case IZMENI:
                dkcf.getBtnAzuriraj().setVisible(true);
                dkcf.getBtnDodaj().setVisible(false);
                dkcf.getBtnAzuriraj().setEnabled(true);

                KategorijaCitaoca kategorija = (KategorijaCitaoca) Cordinator.getInstance().vratiParam("kategorija");
                dkcf.getTxtNazivKategorije().setText(kategorija.getNazivKategorije());
                dkcf.getTxtBeneficije().setText(kategorija.getBeneficije());
                dkcf.getTxtId().setText(String.valueOf(kategorija.getIdKategorijaCitaoca()));
                break;
            default:
                throw new AssertionError();
        }
    }
}
