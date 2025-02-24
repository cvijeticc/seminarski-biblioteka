/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Citalac;
import domen.KategorijaCitaoca;
import domen.Radnik;
import forme.DodajCitaocaForma;
import forme.PrikazCitalacaForma;
import forme.model.ModelTabeleCitalac;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author andri
 */
public class DodajCitaocaController {

    private final DodajCitaocaForma dcf;

    public DodajCitaocaController(DodajCitaocaForma dcf) {
        this.dcf = dcf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        dcf.setVisible(true);
    }

    private void addActionListeners() {

        dcf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String ime = dcf.getTxtIme().getText().trim();
                String prezime = dcf.getTxtPrezime().getText().trim();
                String email = dcf.getTxtEmail().getText().trim();
                KategorijaCitaoca kategorija = (KategorijaCitaoca) dcf.getCmbKategorijaCitaoca().getSelectedItem();

                // Kreiranje objekta čitaoca
                Citalac c = new Citalac(-1, ime, prezime, email, kategorija);

                // Prikaz prozora za potvrdu
                int izbor = JOptionPane.showConfirmDialog(
                        dcf,
                        "Da li želite da dodate novog čitaoca?",
                        "Potvrda",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                // Ako korisnik izabere "No" ili "Cancel", prekida se izvršavanje metode
                if (izbor != JOptionPane.YES_OPTION) {
                    dcf.dispose();
                    return;
                }

                // Ako je korisnik potvrdio (kliknuo "Yes"), pokušavamo da dodamo čitaoca
                try {
                    Komunikacija.getInstance().dodajCitaoca(c);
                    JOptionPane.showMessageDialog(dcf, "Uspešno kreiran čitalac!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dcf.dispose(); // Zatvaramo prozor nakon uspešnog dodavanja
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dcf, "Neuspešno kreiranje čitaoca!", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    private void pripremiFormu() {
        //ovde se popunjava comboBox sa svim kategorijamaCitalaca
        List<KategorijaCitaoca> kategorije = Komunikacija.getInstance().ucitajKategorijeCitalaca();

        for (KategorijaCitaoca kategorija : kategorije) {

            dcf.getCmbKategorijaCitaoca().addItem(kategorija);
        }

    }
}
