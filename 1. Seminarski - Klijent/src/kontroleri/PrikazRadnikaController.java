/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Radnik;
import forme.PrikazRadnikaForma;
import forme.model.ModelTabeleRadnik;
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
public class PrikazRadnikaController {
    private final PrikazRadnikaForma prf;

    public PrikazRadnikaController(PrikazRadnikaForma prf) {
        this.prf = prf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        prf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Radnik> radnici = Komunikacija.getInstance().ucitajRadnike();
        ModelTabeleRadnik mtr = new ModelTabeleRadnik(radnici);
        prf.getTblRadnici().setModel(mtr);
        prf.setLocationRelativeTo(null);
        prf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addActionListeners() {

        prf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = prf.getTblRadnici().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(prf, "Sistem ne može da obriše radnika jer nijedan nije selektovan.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleRadnik mtr = (ModelTabeleRadnik) prf.getTblRadnici().getModel();
                    Radnik r = mtr.getLista().get(red);
//                    System.out.println("Radnik je " + r.getIme() +" " + r.getPrezime());
                    try {
                        Komunikacija.getInstance().obrisiRadnika(r);
                        JOptionPane.showMessageDialog(prf, "Radnik je uspešno obrisan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(prf, "Radnik ne može da bude obrisan.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        prf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prf.getTblRadnici().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(prf, "Mora da bude selektovan neki radnik.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleRadnik mtr = (ModelTabeleRadnik) prf.getTblRadnici().getModel();
                    Radnik r = mtr.getLista().get(red);
                    Cordinator.getInstance().dodajParam("radnik", r);
                    Cordinator.getInstance().otvoriIzmeniRadnikaFormu();
                }
            }
        });

        prf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = prf.getTxtId().getText().trim();
                String ime = prf.getTxtIme().getText().trim();
                String prezime = prf.getTxtPrezime().getText().trim();
                String email = prf.getTxtEmail().getText().trim();
                String korisnickoIme = prf.getTxtKorisnickoIme().getText().trim();

                if (id.isEmpty() && ime.isEmpty() && prezime.isEmpty() && email.isEmpty() && korisnickoIme.isEmpty()) {
                    JOptionPane.showMessageDialog(prf, "Unesi bar jedan kriterijum za pretragu.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                ModelTabeleRadnik mtr = (ModelTabeleRadnik) prf.getTblRadnici().getModel();
                mtr.pretrazi(id, ime, prezime, email, korisnickoIme);
            }
        });

        prf.addBtnResetujPretraguActionListener(new ActionListener() {
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
