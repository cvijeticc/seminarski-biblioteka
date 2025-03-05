/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import domen.Radnik;
import forme.DodajCitaocaForma;
import forme.FormaMod;
import kontroleri.GlavnaFormaController;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazCitalacaForma;
import forme.PrikazIznajmljivanjaForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DodajCitaocaController;
import kontroleri.LoginController;
import kontroleri.PrikazCitalacaController;
import kontroleri.PrikazIznajmljivanjaController;

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
    private Map<String, Object> parametri;
    private PrikazIznajmljivanjaController piController;

    private Cordinator() {
        parametri = new HashMap<>();
    }

    public static Cordinator getInstance() {
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
        dcController.otvoriFormu(FormaMod.DODAJ);
    }

    public Radnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Radnik ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void dodajParam(String s, Object o) {
        parametri.put(s, o);

    }

    public Object vratiParam(String s) {
        return parametri.get(s); //vraca objekat koji se nalazi pod tim kljucem
    }

        public void otvoriIzmeniCitaocaFormu() {
        dcController = new DodajCitaocaController(new DodajCitaocaForma());
        dcController.otvoriFormu(FormaMod.IZMENI);
        
    }
        
        public void otvoriPrikazIznajmljivanjaFormu() {
        piController = new PrikazIznajmljivanjaController(new PrikazIznajmljivanjaForma());
        piController.otvoriFormu();
    }

    public void osveziFormu() {
        pcController.osveziFormu();
    }
 
}
