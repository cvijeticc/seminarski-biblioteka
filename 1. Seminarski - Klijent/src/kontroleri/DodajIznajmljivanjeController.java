/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Citalac;
import domen.Iznajmljivanje;
import domen.Radnik;
import domen.StavkaIznajmljivanja;
import forme.DodajIznajmljivanjeForma;
import forme.DodajStavkuIznajmljivanjaForma;
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
public class DodajIznajmljivanjeController {
    private final DodajIznajmljivanjeForma dif;
//    private final DodajStavkuIznajmljivanjaForma dsif;
    private Iznajmljivanje iznajmljivanje = new Iznajmljivanje();

    public DodajIznajmljivanjeController(DodajIznajmljivanjeForma dif) {
        this.dif = dif;
        addActionListener();
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        
        dif.setVisible(true);
    
    }

    private void pripremiFormu() {
        dif.getTxtIdIznajmljivanja().setEditable(false);
        dif.setLocationRelativeTo(null);
        dif.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dif.getCmbCitaoci().removeAllItems();
        dif.getCmbRadnici().removeAllItems();
        List<Radnik> radnici = Komunikacija.getInstance().ucitajRadnike();
        List<Citalac> citaoci = Komunikacija.getInstance().ucitajCitaoce();
        
        for (Citalac c : citaoci) {
            dif.getCmbCitaoci().addItem(c);
        }
        for (Radnik r : radnici) {
            dif.getCmbRadnici().addItem(r);
        }
        
        
    }

    private void addActionListener() {
        dif.dodajStavkuIznajmljivanjaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                Cordinator.getInstance().otvoriDodajStavkuIznajmljivanjaFormu(iznajmljivanje,DodajIznajmljivanjeController.this);
                //popunjavam sa forme u jedan objekat
            }
        });
        
        dif.kreirajIznajmljivanjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreiraj(e);
            }

            private void kreiraj(ActionEvent e) {
                DodajStavkuIznajmljivanjaController dsiController = new DodajStavkuIznajmljivanjaController(new DodajStavkuIznajmljivanjaForma(), iznajmljivanje,DodajIznajmljivanjeController.this);
                
                if (validacija()) {
                    iznajmljivanje.setIdCitalac((Citalac) dif.getCmbCitaoci().getSelectedItem());
                    iznajmljivanje.setIdRadnik((Radnik) dif.getCmbRadnici().getSelectedItem());
                    iznajmljivanje.setOpisIznajmljivanja(dif.getTxtOpisIznajmljivanja().getText());
                    iznajmljivanje.setUkupanIznos(Double.parseDouble(dif.getTxtUkupanIznos().getText()));
                    Komunikacija.getInstance().dodajIznajmljivanje(iznajmljivanje);
                    JOptionPane.showMessageDialog(dif, "Uspesno kreirano iznajmljivanje sa stavkama iznajmljivanja",
                            "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                }
                
               
            }
        });
    }
    
    private boolean validacija(){
        
         if (dif.getTxtOpisIznajmljivanja().getText().isBlank()) {
             JOptionPane.showMessageDialog(dif, "Popuni opis",
                        "Popuni opis", JOptionPane.INFORMATION_MESSAGE);
             return false;
        }
         if (                 dif.getTxtUkupanIznos().getText().isBlank()) {
             JOptionPane.showMessageDialog(dif, "Moras dodati stavku ili stavke iznajmljivanja"
                     + " da bi se popunio ukupan iznos iznajmljivanja",
                        "Popuni opis", JOptionPane.INFORMATION_MESSAGE);
             return false;
        }

            
        
    return true;
    }
    
    public void setUkupanIznos(){
         double ukupanIznos = 0;
        for (StavkaIznajmljivanja si : iznajmljivanje.getStavke()) {
             ukupanIznos += si.getUkupanIznosStavke();
            
        }
        dif.getTxtUkupanIznos().setText(ukupanIznos+"");
    
    }
    
}
