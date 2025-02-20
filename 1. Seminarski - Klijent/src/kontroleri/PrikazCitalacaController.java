/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Citalac;
import forme.GlavnaForma;
import forme.PrikazCitalacaForma;
import forme.model.ModelTabeleCitalac;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private void pripremiFormu() {
        List<Citalac> citaoci = Komunikacija.getInstance().ucitajCitaoce();
        ModelTabeleCitalac mtc = new ModelTabeleCitalac(citaoci);
        pcf.getTblCitaoci().setModel(mtc);
    }
    
    private void addActionListeners() {
        
        pcf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pcf.getTblCitaoci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pcf, "Sistem ne moze da obrise citaoca", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                ModelTabeleCitalac mtc = (ModelTabeleCitalac) pcf.getTblCitaoci().getModel();
                Citalac c = mtc.getLista().get(red);
                    try {
                        //znaci uzeli smo tu listu gde su svi citaoci i iz te liste smo izvukli tog koji je selektovan u tom redu
                        Komunikacija.getInstance().obrisiCitaoca(c);
                        JOptionPane.showMessageDialog(pcf, "Objekat klase Citalac je obrisan", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(pcf, "Sistem ne moze da obrise citaoca", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
                
    }
}
