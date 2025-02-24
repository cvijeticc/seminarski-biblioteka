/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import domen.Radnik;
import forme.DodajCitaocaForma;
import kontroleri.GlavnaFormaController;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazCitalacaForma;
import kontroleri.DodajCitaocaController;
import kontroleri.LoginController;
import kontroleri.PrikazCitalacaController;

/**
 *
 * @author andri
 */
public class Cordinator {
    private static Cordinator instance;
    private Radnik ulogovani;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazCitalacaController pcController;
    private DodajCitaocaController dcController;
    
    private Cordinator() {
    }
    
    public static Cordinator getInstance(){
        if (instance == null) {
            instance = new Cordinator();
        }
        return instance;
        
    }   

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

    public void otvoriPrikazCitalacaFormu() {
        pcController = new PrikazCitalacaController(new PrikazCitalacaForma());
        pcController.otvoriFormu();
    }
    
    public void otvoriDodajCitaocaFormu() {
        dcController = new DodajCitaocaController(new DodajCitaocaForma());
        dcController.otvoriFormu();
    }
    
    public Radnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Radnik ulogovani) {
        this.ulogovani = ulogovani;
    }

    



    
    
}
