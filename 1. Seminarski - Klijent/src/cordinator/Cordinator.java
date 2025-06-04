/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import domen.Iznajmljivanje;
import domen.Radnik;
import domen.StavkaIznajmljivanja;
import forme.DodajCitaocaForma;
import forme.DodajIznajmljivanjeForma;
import forme.DodajKategorijuCitaocaForma;
import forme.DodajKnjiguForma;
import forme.DodajRadnikaForma;
import forme.DodajStavkuIznajmljivanjaForma;
import forme.DodajTerminSmeneForma;
import forme.FormaMod;
import kontroleri.GlavnaFormaController;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazCitalacaForma;
import forme.PrikazIznajmljivanjaForma;
import forme.PrikazKategorijaCitaocaForma;
import forme.PrikazKnjigaForma;
import forme.PrikazRadnikaForma;
import forme.PrikazTerminaSmenaForma;
import forme.model.ModelTabeleIznajmljivanje;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DodajCitaocaController;
import kontroleri.DodajIznajmljivanjeController;
import kontroleri.DodajKategorijuCitaocaController;
import kontroleri.DodajKnjiguController;
import kontroleri.DodajRadnikaController;
import kontroleri.DodajStavkuIznajmljivanjaController;
import kontroleri.DodajTerminSmeneController;
import kontroleri.LoginController;
import kontroleri.PrikazCitalacaController;
import kontroleri.PrikazIznajmljivanjaController;
import kontroleri.PrikazKategorijaCitaocaController;
import kontroleri.PrikazKnjigaController;
import kontroleri.PrikazRadnikaController;
import kontroleri.PrikazTerminaSmenaController;

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
    private DodajIznajmljivanjeController diController;
    private DodajStavkuIznajmljivanjaController dsiController;
    private DodajRadnikaController drController;
    private PrikazRadnikaController prController;
    private DodajKnjiguController dkController;
    private PrikazKnjigaController pkController;
    private DodajKategorijuCitaocaController dkcController;
    private PrikazKategorijaCitaocaController pkcController;
    private DodajTerminSmeneController dtsController;
    private PrikazTerminaSmenaController ptsController;
    
    
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
        Cordinator.getInstance().dodajParam("prikazCitalacaController", pcController);
        //
        pcController.otvoriFormu();
    }

    public void otvoriDodajCitaocaFormu() {
        dcController = new DodajCitaocaController(new DodajCitaocaForma());
        dcController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriDodajIznajmljivanjeFormu() {
        diController = new DodajIznajmljivanjeController(new DodajIznajmljivanjeForma());
        diController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriDodajStavkuIznajmljivanjaFormu(Iznajmljivanje iznajmljivnaje, DodajIznajmljivanjeController dic) {
        dsiController = new DodajStavkuIznajmljivanjaController(new DodajStavkuIznajmljivanjaForma(), iznajmljivnaje, dic, new ModelTabeleIznajmljivanje(null));
        dsiController.otvoriFormu(FormaMod.DODAJ);
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

    public void otvoriIzmeniIznajmljivanjeFormu() {
        diController = new DodajIznajmljivanjeController(new DodajIznajmljivanjeForma());
        diController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPrikazIznajmljivanjaFormu() {
        piController = new PrikazIznajmljivanjaController(new PrikazIznajmljivanjaForma());
        Cordinator.getInstance().dodajParam("prikazIznajmljivanjaController", piController);
        //Dodao sam u hashmapu kljuc ovaj string gore i value je piController
        //i posle u DodajIznajmljivanjeController uzimam value od toga kljuca i osveziFormu
        piController.otvoriFormu();

    }

        public void osveziFormu() {
        pcController.osveziFormu();
    }
        
        public void osveziFormuKategorijeCitalaca() {
            pkcController.osveziFormu();
    }

    public void osveziFormuIznajmljivanja() {
        piController.osveziFormu();
    }
    
    public void osveziFormuTerminSmene(){
        ptsController.osveziFormu();
    }
    
    

    public void otvoriDodajRadnikaFormu() {
        drController = new DodajRadnikaController(new DodajRadnikaForma());
        drController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniRadnikaFormu() {
        drController = new DodajRadnikaController(new DodajRadnikaForma());
        drController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPrikazRadnikaFormu() {
        prController = new PrikazRadnikaController(new PrikazRadnikaForma());
        Cordinator.getInstance().dodajParam("prikazRadnikaController", prController);
        prController.otvoriFormu();
    }

    public void otvoriDodajKnjiguFormu() {
        dkController = new DodajKnjiguController(new DodajKnjiguForma());
        dkController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniKnjiguFormu() {
        dkController = new DodajKnjiguController(new DodajKnjiguForma());
        dkController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPrikaziKnjigeFormu() {
        pkController = new PrikazKnjigaController(new PrikazKnjigaForma());
        Cordinator.getInstance().dodajParam("prikazKnjigaController", pkController);
        pkController.otvoriFormu();
    }

    public void otvoriDodajKategorijuCitaocaFormu() {
        dkcController = new DodajKategorijuCitaocaController(new DodajKategorijuCitaocaForma());
        dkcController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniKategorijuCitaocaFormu() {
        dkcController = new DodajKategorijuCitaocaController(new DodajKategorijuCitaocaForma());
        dkcController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPrikaziKategorijuCitalacaForma() {
        pkcController = new PrikazKategorijaCitaocaController(new PrikazKategorijaCitaocaForma());
        Cordinator.getInstance().dodajParam("prikazKategorijaCitaocaController", pkcController);
        pkcController.otvoriFormu();
    }

    public void оtvoriDodajTerminSmeneFormu() {
        dtsController = new DodajTerminSmeneController(new DodajTerminSmeneForma());
        dtsController.otvoriFormu(FormaMod.DODAJ);
    }
    

    public void otvoriIzmeniTerminSmeneFormu() {
        dtsController = new DodajTerminSmeneController(new DodajTerminSmeneForma());
        dtsController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPrikazTerminaSmenaFormu() {
    ptsController = new PrikazTerminaSmenaController(new PrikazTerminaSmenaForma());
    Cordinator.getInstance().dodajParam("prikazTerminSmeneController", ptsController);
    ptsController.otvoriFormu();
}

    public void otvoriIzmeniStavkuIznajmljivanjeFormu() {
    StavkaIznajmljivanja si = (StavkaIznajmljivanja)
            Cordinator.getInstance().vratiParam("stavkaIznajmljivanja");
    Iznajmljivanje i = new Iznajmljivanje();
    i.setIdIznajmljivanja(si.getIdIznajmljivanje());
    dsiController = new DodajStavkuIznajmljivanjaController(new DodajStavkuIznajmljivanjaForma(),
            i,        null,null);
    dsiController.otvoriFormu(FormaMod.IZMENI);
}


    


    
}
