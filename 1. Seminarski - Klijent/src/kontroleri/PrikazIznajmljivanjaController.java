/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import forme.PrikazIznajmljivanjaForma;
import forme.model.ModelTabeleIznajmljivanje;
import forme.model.ModelTabeleStavkaIznajmljivanja;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
        addActionListener();
        addMouseListener();
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

        List<StavkaIznajmljivanja> stavkaIznajmljivanja = new ArrayList<>();
        System.out.println("Klasa prcontroller");
        System.out.println(stavkaIznajmljivanja);
        ModelTabeleStavkaIznajmljivanja mtsi = new ModelTabeleStavkaIznajmljivanja(stavkaIznajmljivanja);
        pif.getTblStavke().setModel(mtsi);

    }

    private void addActionListener() {

    }

    public void osveziFormu() {
        pripremiFormu();
    }

    private void addMouseListener() {
        //pif = prikaz iznajmljivanja forma
        pif.getTblIznajmljivanja().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pif.getTblIznajmljivanja().getSelectedRow();
                if (red != -1) {
                    ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getTblIznajmljivanja().getModel();
                    Iznajmljivanje iznajmljivanje = mti.getLista().get(red);
                    //uzimamo bas to iznajmljivanje na tom selektovanom redu
                    List<StavkaIznajmljivanja> stavkeIznajmljivanja = Komunikacija.getInstance().ucitajStavkuIznajmljivanja(iznajmljivanje.getIdIznajmljivanja());
                    ModelTabeleStavkaIznajmljivanja mtsi = new ModelTabeleStavkaIznajmljivanja(stavkeIznajmljivanja);
                    pif.getTblStavke().setModel(mtsi);
                }
            }
        });
    }

}
