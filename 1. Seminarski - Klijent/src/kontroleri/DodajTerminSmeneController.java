package kontroleri;

import cordinator.Cordinator;
import domen.TerminSmene;
import forme.DodajTerminSmeneForma;
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
public class DodajTerminSmeneController {

    private final DodajTerminSmeneForma dtsf;

    public DodajTerminSmeneController(DodajTerminSmeneForma dtsf) {
        this.dtsf = dtsf;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu();
        pripremiFormu(mod);
        dtsf.setVisible(true);
    }

    private void addActionListeners() {
        dtsf.getBtnDodaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    int trajanje = Integer.parseInt(dtsf.getTxtTrajanjeSmene().getText().trim());
                    int brojSmene = Integer.parseInt((String) dtsf.getCmbBrojSmene().getSelectedItem());

                    TerminSmene termin = new TerminSmene(-1, trajanje, brojSmene);

                    int izbor = JOptionPane.showConfirmDialog(
                            dtsf,
                            "Da li ste sigurni da želite da dodate novi termin smene?",
                            "Potvrda dodavanja",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (izbor != JOptionPane.YES_OPTION) {
                        return;
                    }

                    Komunikacija.getInstance().dodajTerminSmene(termin);
                    JOptionPane.showMessageDialog(dtsf, "Uspešno kreiran termin smene!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dtsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dtsf, "Neuspešno kreiranje termina smene!", "Greška", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        dtsf.getBtnAzuriraj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
    try {
        int id = Integer.parseInt(dtsf.getTxtId().getText().trim());
        int trajanje = Integer.parseInt(dtsf.getTxtTrajanjeSmene().getText().trim());
        int brojSmene = Integer.parseInt((String) dtsf.getCmbBrojSmene().getSelectedItem());

        TerminSmene termin = new TerminSmene(id, trajanje, brojSmene);

        Komunikacija.getInstance().azurirajTerminSmene(termin);

        // Proveri da li kontroler postoji
        PrikazTerminaSmenaController prikazController =
                (PrikazTerminaSmenaController) Cordinator.getInstance().vratiParam("prikazTerminaSmenaController");
        if (prikazController != null) {
            prikazController.osveziFormu();
        }

        // Ako sve prođe bez izuzetka, onda tek obavesti korisnika
        JOptionPane.showMessageDialog(dtsf, "Uspešno ažuriran termin smene!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
        dtsf.dispose();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(dtsf, "Neuspešno ažuriranje termina smene!", "Greška", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

        });
    }

    private void pripremiFormu() {
        dtsf.setLocationRelativeTo(null);
        dtsf.getTxtId().setEnabled(false);
        dtsf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dtsf.getBtnAzuriraj().setVisible(false);
                dtsf.getBtnDodaj().setVisible(true);
                dtsf.getBtnDodaj().setEnabled(true);
                dtsf.getTxtId().setVisible(false);
                dtsf.getLblId().setVisible(false);
                break;
            case IZMENI:
                dtsf.getBtnAzuriraj().setVisible(true);
                dtsf.getBtnDodaj().setVisible(false);
                dtsf.getBtnAzuriraj().setEnabled(true);

                TerminSmene termin = (TerminSmene) Cordinator.getInstance().vratiParam("terminsmene");
                if (termin != null) {
                    dtsf.getTxtTrajanjeSmene().setText(String.valueOf(termin.getTrajanjeSmene()));
                    dtsf.getCmbBrojSmene().setSelectedItem(String.valueOf(termin.getBrojSmene()));
                    dtsf.getTxtId().setText(String.valueOf(termin.getIdTerminSmene()));
                }
                break;
            default:
                throw new AssertionError();
        }
    }
}
