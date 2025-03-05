/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Iznajmljivanje;
import forme.PrikazIznajmljivanjaForma;
import forme.model.ModelTabeleIznajmljivanje;
import java.util.List;
import komunikacija.Komunikacija;

/**
 *
 * @author andri
 */
public class PrikazIznajmljivanjaController {
    private final PrikazIznajmljivanjaForma pif;

    public PrikazIznajmljivanjaController(PrikazIznajmljivanjaForma pif) {
        this.pif = pif;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pif.setVisible(true);
    }

    public void pripremiFormu() {
        pif.setLocationRelativeTo(pif);
        List<Iznajmljivanje> iznajmljivanje = Komunikacija.getInstance().ucitajIznajmljivanja();
       
        ModelTabeleIznajmljivanje mti = new ModelTabeleIznajmljivanje(iznajmljivanje);
        pif.getTblIznajmljivanja().setModel(mti);
        
    }

    private void addActionListeners() {

        

    }

    public void osveziFormu() {
        pripremiFormu();
    }
}
