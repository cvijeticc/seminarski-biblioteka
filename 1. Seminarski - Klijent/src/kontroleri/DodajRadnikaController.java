/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Citalac;
import domen.KategorijaCitaoca;
import domen.Radnik;
import forme.DodajRadnikaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author andri
 */
public class DodajRadnikaController {
    private final DodajRadnikaForma drf;

    public DodajRadnikaController(forme.DodajRadnikaForma drf) {
        this.drf = drf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod){
        pripremiFormu();
        pripremiFormu(mod);
        drf.setVisible(true);
    }
    
    private void addActionListener() {
        
    drf.addBtnDodajActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String ime = drf.getTxtIme().getText().trim();
            String prezime = drf.getTxtPrezime().getText().trim();
            String email = drf.getTxtEmail().getText().trim();
            String korisnickoIme = drf.getTxtKorisnickoIme().getText().trim();
            String sifra = drf.getTxtSifra().getText().trim();

            Radnik r = new Radnik(-1, ime, prezime, email, korisnickoIme, sifra);
            System.out.println("Ime i prezime je " + r.getIme() + " " + r.getPrezime());
            int izbor = JOptionPane.showConfirmDialog(
                    drf,
                    "Da li ste sigurni da želite da dodate novog radnika?",
                    "Potvrda",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (izbor != JOptionPane.YES_OPTION) {
                return;
            }

            try {
                Komunikacija.getInstance().dodajRadnika(r);
                JOptionPane.showMessageDialog(drf, "Uspešno dodat radnik!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                drf.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(drf, "Neuspešno dodavanje radnika!", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    drf.addBtnAzurirajActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(drf.getTxtId().getText());
            String ime = drf.getTxtIme().getText().trim();
            String prezime = drf.getTxtPrezime().getText().trim();
            String email = drf.getTxtEmail().getText().trim();
            String korisnickoIme = drf.getTxtKorisnickoIme().getText().trim();
            String sifra = drf.getTxtSifra().getText().trim();

            Radnik r = new Radnik(id, ime, prezime, email, korisnickoIme, sifra);

            try {
                Komunikacija.getInstance().azurirajRadnika(r);
                JOptionPane.showMessageDialog(drf, "Uspešno ažuriran radnik!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                drf.dispose();
                PrikazRadnikaController prikazController =
                        (PrikazRadnikaController) Cordinator.getInstance().vratiParam("prikazRadnikaController");
                prikazController.osveziFormu();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(drf, "Neuspešno ažuriranje radnika!", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
    });


    }

    private void pripremiFormu() {
        drf.setLocationRelativeTo(null);
        drf.getTxtId().setEnabled(false);
        drf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                drf.getBtnAzuriraj().setVisible(false);
                drf.getBtnDodaj().setVisible(true);
                drf.getBtnDodaj().setEnabled(true);
                drf.getTxtId().setVisible(false);
                drf.getLblId().setVisible(false);
                break;
            case IZMENI:
                drf.getBtnAzuriraj().setVisible(true);
                drf.getBtnDodaj().setVisible(false);
                drf.getBtnAzuriraj().setEnabled(true);

//                Citalac c = (Citalac) Cordinator.getInstance().vratiParam("citalac");
//                drf.getTxtIme().setText(c.getIme());
//                drf.getTxtPrezime().setText(c.getPrezime());
//                drf.getTxtEmail().setText(c.getEmail());
//                drf.getTxtId().setText(c.getIdCitalac() + "");
                
                Radnik r = (Radnik) Cordinator.getInstance().vratiParam("radnik");
                drf.getTxtIme().setText(r.getIme());
                drf.getTxtPrezime().setText(r.getPrezime());
                drf.getTxtEmail().setText(r.getEmail());
                drf.getTxtId().setText(r.getIdRadnik()+ "");
                drf.getTxtKorisnickoIme().setText(r.getKorisnickoIme());
                drf.getTxtSifra().setText(r.getSifra()+ "");
                
                
                break;
            default:
                throw new AssertionError();
        }
    }
}
