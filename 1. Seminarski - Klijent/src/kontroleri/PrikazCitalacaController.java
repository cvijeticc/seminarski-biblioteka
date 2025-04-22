/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Citalac;
import domen.KategorijaCitaoca;
import forme.PrikazCitalacaForma;
import forme.model.ModelTabeleCitalac;
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
public class PrikazCitalacaController {

    private final PrikazCitalacaForma pcf;

    public PrikazCitalacaController(PrikazCitalacaForma pcf) {
        this.pcf = pcf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pcf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Citalac> citaoci = Komunikacija.getInstance().ucitajCitaoce();
        ModelTabeleCitalac mtc = new ModelTabeleCitalac(citaoci);
        pcf.getTblCitaoci().setModel(mtc);
        pcf.setLocationRelativeTo(null);
        pcf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<KategorijaCitaoca> kategorije = Komunikacija.getInstance().ucitajKategorijeCitalaca();

        pcf.getCmbKategorijaCitaoca().addItem(null);
        for (KategorijaCitaoca kategorija : kategorije) {

            pcf.getCmbKategorijaCitaoca().addItem(kategorija);
        }
    }

    private void addActionListeners() {

        pcf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pcf.getTblCitaoci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pcf, "Sistem ne moze da obrise citaoca, jer nije selektovan niko", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleCitalac mtc = (ModelTabeleCitalac) pcf.getTblCitaoci().getModel();
                    Citalac c = mtc.getLista().get(red);
                    try {
                        // Pokusaj brisanja citaoca
                        Komunikacija.getInstance().obrisiCitaoca(c);
                        JOptionPane.showMessageDialog(pcf, "Objekat klase Citalac je obrisan", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(pcf, "Citalac ne moze da bude obrisan, jer je referenciran u drugoj tabeli", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        pcf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pcf.getTblCitaoci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pcf, "Mora da bude selektovan neki citalac", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleCitalac mtc = (ModelTabeleCitalac) pcf.getTblCitaoci().getModel();
                    Citalac c = mtc.getLista().get(red);
                    Cordinator.getInstance().dodajParam("citalac", c);
                    Cordinator.getInstance().otvoriIzmeniCitaocaFormu();

                }
            }
        });

        pcf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pcf.getTxtIme().getText().trim();
                String prezime = pcf.getTxtPrezime().getText().trim();
                String email = pcf.getTxtEmail().getText().trim();
                KategorijaCitaoca kategorija = (KategorijaCitaoca) pcf.getCmbKategorijaCitaoca().getSelectedItem();
                if (ime.isEmpty() && prezime.isEmpty() && email.isEmpty() && kategorija == null) {
                    JOptionPane.showMessageDialog(pcf, "Navedi neku informaciju o citaocu", "Moras popuniti nesto", JOptionPane.INFORMATION_MESSAGE);;
                    return;

                }
                ModelTabeleCitalac mtc = (ModelTabeleCitalac) pcf.getTblCitaoci().getModel();
                mtc.pretrazi(ime, prezime, email, kategorija);

            }
        });

        pcf.addBtnResetujPretraguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pripremiFormu();//mogao sam i osveziFormu();

            }
        });

    }

    public void osveziFormu() {
        pripremiFormu();
    }
}
