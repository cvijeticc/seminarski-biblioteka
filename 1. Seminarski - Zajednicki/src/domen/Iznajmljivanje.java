/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author andri
 */
public class Iznajmljivanje implements ApstraktniDomenskiObjekat {

    private int idIznajmljivanja;
    private double ukupanIznos;
    private String opisIznajmljivanja;
    private Radnik idRadnik;
    private Citalac idCitalac;
    private List<StavkaIznajmljivanja> stavke;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanja, double ukupanIznos, String opisIznajmljivanja, Radnik idRadnik, Citalac idCitalac, List<StavkaIznajmljivanja> stakve) {
        this.idIznajmljivanja = idIznajmljivanja;
        this.ukupanIznos = ukupanIznos;
        this.opisIznajmljivanja = opisIznajmljivanja;
        this.idRadnik = idRadnik;
        this.idCitalac = idCitalac;
        this.stavke = stakve;
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" + "idIznajmljivanja=" + idIznajmljivanja + ", ukupanIznos=" + ukupanIznos + ", opisIznajmljivanja=" + opisIznajmljivanja + ", idRadnik=" + idRadnik + ", idCitalac=" + idCitalac + ", stakve=" + stavke + '}';
    }

    public int getIdIznajmljivanja() {
        return idIznajmljivanja;
    }

    public void setIdIznajmljivanja(int idIznajmljivanja) {
        this.idIznajmljivanja = idIznajmljivanja;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Radnik getIdRadnik() {
        return idRadnik;
    }

    public void setIdRadnik(Radnik idRadnik) {
        this.idRadnik = idRadnik;
    }

    public Citalac getIdCitalac() {
        return idCitalac;
    }

    public void setIdCitalac(Citalac idCitalac) {
        this.idCitalac = idCitalac;
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIznajmljivanja> stakve) {
        this.stavke = stakve;
    }

    public String getOpisIznajmljivanja() {
        return opisIznajmljivanja;
    }

    public void setOpisIznajmljivanja(String opisIznajmljivanja) {
        this.opisIznajmljivanja = opisIznajmljivanja;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        if (Double.doubleToLongBits(this.ukupanIznos) != Double.doubleToLongBits(other.ukupanIznos)) {
            return false;
        }
        if (!Objects.equals(this.opisIznajmljivanja, other.opisIznajmljivanja)) {
            return false;
        }
        return Objects.equals(this.stavke, other.stavke);
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
//            int idIznajmljivanja = rs.getInt("iznajmljivanje.idIznajmljivanja");
            int idIznajmljivanja = rs.getInt("iznajmljivanje.idIznajmljivanja");
            double ukupanIznos = rs.getDouble("iznajmljivanje.ukupanIznos");
            String opisIznajmljivanja = rs.getString("iznajmljivanje.opisIznajmljivanja");
            // Ucitavanje kategorije citaoca
//            int idKategorijaCitaoca = rs.getInt("citalac.idKategorijaCitaoca");
//            String nazivKategorijaCitaoca = rs.getString("kategorijaCitaoca.nazivKategorije");
//            KategorijaCitaoca kategorijaCitaoca = new KategorijaCitaoca(idKategorijaCitaoca, nazivKategorijaCitaoca, null);
            int idRadnik = rs.getInt("iznajmljivanje.idRadnik");
            int idCitalac = rs.getInt("iznajmljivanje.idCitalac");
            String imeRadnika = rs.getString("radnik.ime");
            String imeCitaoca = rs.getString("citalac.ime");
            String prezimeRadnika = rs.getString("radnik.prezime");
            String prezimeCitaoca = rs.getString("citalac.prezime");

            Radnik r = new Radnik(idRadnik, imeRadnika, prezimeRadnika, null, null, null);
           
            Citalac c =  new Citalac(idCitalac, imeCitaoca, prezimeCitaoca, null, null);
            
            Iznajmljivanje i = new Iznajmljivanje(idIznajmljivanja, ukupanIznos, opisIznajmljivanja, r, c, null);
            lista.add(i);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ukupanIznos,opisIznajmljivanja,idRadnik,idCitalac";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        //'ukupanIznos','dasad','oinmoi'
        return ukupanIznos + ",'" + opisIznajmljivanja + "'," + idRadnik.getIdRadnik() + ","
                + idCitalac.getIdCitalac();
    }

    @Override
    public String vratiPrimarnikljuc() {
        return "iznajmljivanje.idIznajmljivanja = " + idIznajmljivanja;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiobjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ukupanIznos = '" + ukupanIznos + "', opisIznajmljivanja ='" + opisIznajmljivanja + "', idRadnik.getIdRadnik() = "
                + idRadnik.getIdRadnik() + "idCitalac.getIdCitalac()" + idCitalac.getIdCitalac();

    }

}
