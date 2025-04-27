package kontroleri;

import cordinator.Cordinator;
import domen.TerminSmene;
import forme.PrikazTerminaSmenaForma;
import forme.model.ModelTabeleTerminSmene;
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
public class PrikazTerminaSmenaController {

    private final PrikazTerminaSmenaForma ptsf;

    public PrikazTerminaSmenaController(PrikazTerminaSmenaForma ptsf) {
        this.ptsf = ptsf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ptsf.setVisible(true);
    }

    public void pripremiFormu() {
        List<TerminSmene> termini = Komunikacija.getInstance().ucitajTermineSmena();
        ModelTabeleTerminSmene mtts = new ModelTabeleTerminSmene(termini);
        ptsf.getTblTerminSmene().setModel(mtts);
        ptsf.setLocationRelativeTo(null);
        ptsf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addActionListeners() {
        ptsf.getBtnObrisi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ptsf.getTblTerminSmene().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ptsf, "Sistem ne može da obriše termin smene jer nijedan nije selektovan.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleTerminSmene mtts = (ModelTabeleTerminSmene) ptsf.getTblTerminSmene().getModel();
                    TerminSmene ts = mtts.getLista().get(red);
                    int izbor = JOptionPane.showConfirmDialog(
                            ptsf,
                            "Da li ste sigurni da želite da izbrišete termin smene?",
                            "Potvrda brisanja",
                            JOptionPane.YES_NO_CANCEL_OPTION
                    );
                    if (izbor != JOptionPane.YES_OPTION) {
                        return;
                    }
                    try {
                        Komunikacija.getInstance().obrisiTerminSmene(ts);
                        JOptionPane.showMessageDialog(ptsf, "Termin smene je uspešno obrisan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(ptsf, "Termin smene ne može da bude obrisan.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        ptsf.getBtnAzuriraj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ptsf.getTblTerminSmene().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ptsf, "Mora da bude selektovan neki termin smene.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleTerminSmene mtts = (ModelTabeleTerminSmene) ptsf.getTblTerminSmene().getModel();
                    TerminSmene ts = mtts.getLista().get(red);
                    Cordinator.getInstance().dodajParam("terminsmene", ts);
                    Cordinator.getInstance().otvoriIzmeniTerminSmeneFormu();
                }
            }
        });

        ptsf.getBtnPretrazi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trajanjeText = ptsf.getTxtTrajanjeSmene().getText().trim();
                String brojSmeneText = ptsf.getTxtBrojSmene().getText().trim();

                if (trajanjeText.isEmpty() && brojSmeneText.isEmpty()) {
                    JOptionPane.showMessageDialog(ptsf, "Unesi bar jedan kriterijum za pretragu.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int trajanje = trajanjeText.isEmpty() ? -1 : Integer.parseInt(trajanjeText);
                int brojSmene = brojSmeneText.isEmpty() ? -1 : Integer.parseInt(brojSmeneText);

                ModelTabeleTerminSmene mtts = (ModelTabeleTerminSmene) ptsf.getTblTerminSmene().getModel();
                mtts.pretrazi(trajanje, brojSmene);
            }
        });

        ptsf.getBtnResetujPretragu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }
}
