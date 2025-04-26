package kontroleri;

import cordinator.Cordinator;
import domen.KategorijaCitaoca;
import forme.PrikazKategorijaCitaocaForma;
import forme.model.ModelTabeleKategorijaCitaoca;
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
public class PrikazKategorijaCitaocaController {

    private final PrikazKategorijaCitaocaForma pkcf;

    public PrikazKategorijaCitaocaController(PrikazKategorijaCitaocaForma pkcf) {
        this.pkcf = pkcf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pkcf.setVisible(true);
    }

    public void pripremiFormu() {
        List<KategorijaCitaoca> kategorije = Komunikacija.getInstance().ucitajKategorijeCitalaca();
        ModelTabeleKategorijaCitaoca mtkc = new ModelTabeleKategorijaCitaoca(kategorije);
        pkcf.getTblKategorijeCitalaca().setModel(mtkc);
        pkcf.setLocationRelativeTo(null);
        pkcf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addActionListeners() {
        pkcf.getBtnObrisi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int red = pkcf.getTblKategorijeCitalaca().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkcf, "Sistem ne može da obriše kategoriju jer nijedna nije selektovana.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleKategorijaCitaoca mtkc = (ModelTabeleKategorijaCitaoca) pkcf.getTblKategorijeCitalaca().getModel();
                    KategorijaCitaoca kc = mtkc.getLista().get(red);
                    int izbor = JOptionPane.showConfirmDialog(
                            pkcf,
                            "Da li ste sigurni da želite da izbrišete kategoriju?",
                            "Potvrda brisanja",
                            JOptionPane.YES_NO_CANCEL_OPTION
                    );
                    if (izbor != JOptionPane.YES_OPTION) {
                        return;
                    }
                    try {
                        Komunikacija.getInstance().obrisiKategorijuCitaoca(kc);
                        JOptionPane.showMessageDialog(pkcf, "Kategorija je uspešno obrisana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(pkcf, "Kategorija ne može da bude obrisana.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        pkcf.getBtnAzuriraj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkcf.getTblKategorijeCitalaca().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkcf, "Mora da bude selektovana neka kategorija.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleKategorijaCitaoca mtkc = (ModelTabeleKategorijaCitaoca) pkcf.getTblKategorijeCitalaca().getModel();
                    KategorijaCitaoca kc = mtkc.getLista().get(red);
                    Cordinator.getInstance().dodajParam("kategorija", kc);
                    Cordinator.getInstance().otvoriIzmeniKategorijuCitaocaFormu();
                }
            }
        });

        pkcf.getBtnPretrazi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pkcf.getTxtNazivKategorije().getText().trim();
                String beneficije = pkcf.getTxtBeneficije().getText().trim();

                if (naziv.isEmpty() && beneficije.isEmpty()) {
                    JOptionPane.showMessageDialog(pkcf, "Unesi bar jedan kriterijum za pretragu.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                ModelTabeleKategorijaCitaoca mtkc = (ModelTabeleKategorijaCitaoca) pkcf.getTblKategorijeCitalaca().getModel();
                mtkc.pretrazi(naziv, beneficije);
            }
        });

        pkcf.getBtnResetujPretragu().addActionListener(new ActionListener() {
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
