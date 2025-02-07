/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Citalac;
import forme.GlavnaForma;
import forme.PrikazCitalacaForma;
import forme.model.ModelTabeleCitalac;
import java.util.List;
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

    private void addActionListeners() {
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
}
