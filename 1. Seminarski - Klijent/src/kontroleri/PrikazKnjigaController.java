package kontroleri;

import cordinator.Cordinator;
import domen.Knjiga;
import forme.PrikazKnjigaForma;
import forme.model.ModelTabeleKnjiga;
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
public class PrikazKnjigaController {

    private final PrikazKnjigaForma pkf;

    public PrikazKnjigaController(PrikazKnjigaForma pkf) {
        this.pkf = pkf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Knjiga> knjige = Komunikacija.getInstance().ucitajKnjige();
        ModelTabeleKnjiga mtk = new ModelTabeleKnjiga(knjige);
        pkf.getTblKnjige().setModel(mtk);
        pkf.setLocationRelativeTo(null);
        pkf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addActionListeners() {

        pkf.getBtnObrisi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int red = pkf.getTblKnjige().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da obriše knjigu jer nijedna nije selektovana.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) pkf.getTblKnjige().getModel();
                    Knjiga k = mtk.getLista().get(red);
                    int izbor
                            = JOptionPane.showConfirmDialog(pkf, "Da li ste sigurni da "
                                    + "zelite da izbrisete knjigu", "Potvrda brisanja", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (izbor != JOptionPane.YES_OPTION ) {
                        return;
                    } 
                        try {
                            Komunikacija.getInstance().obrisiKnjigu(k);
                            JOptionPane.showMessageDialog(pkf, "Knjiga je uspešno obrisana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                            pripremiFormu();
                        } catch (Exception exc) {
                            JOptionPane.showMessageDialog(pkf, "Knjiga ne može da bude obrisana.", "Greška", JOptionPane.ERROR_MESSAGE);
                        }
                    
                }
            }
        });

        pkf.getBtnAzuriraj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getTblKnjige().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Mora da bude selektovana neka knjiga.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) pkf.getTblKnjige().getModel();
                    Knjiga k = mtk.getLista().get(red);
                    Cordinator.getInstance().dodajParam("knjiga", k);
                    Cordinator.getInstance().otvoriIzmeniKnjiguFormu();
                }
            }
        });

        pkf.getBtnPretrazi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pkf.getTxtIme().getText().trim();
                String zanr = pkf.getTxtPrezime().getText().trim();
                String godina = pkf.getTxtEmail().getText().trim();
                String iznos = pkf.getTxtIznosPoDanu().getText().trim();

                if (naziv.isEmpty() && zanr.isEmpty() && godina.isEmpty() && iznos.isEmpty()) {
                    JOptionPane.showMessageDialog(pkf, "Unesi bar jedan kriterijum za pretragu.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) pkf.getTblKnjige().getModel();
                mtk.pretrazi(naziv, zanr, godina, iznos);
            }
        });

        pkf.getBtnResetujPretragu().addActionListener(new ActionListener() {
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
