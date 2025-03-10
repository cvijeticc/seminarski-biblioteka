/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Radnik;
import forme.GlavnaForma;

/**
 *
 * @author andri
 */ 
public class GlavnaFormaController {
    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
    }

    public void otvoriFormu() {
        Radnik ulogovani = Cordinator.getInstance().getUlogovani();
        String imePrezime = ulogovani.getIme()+ " " + ulogovani.getPrezime();
        gf.setVisible(true);
        gf.getLblUlogovani().setText(imePrezime);
    }
    
    
}
