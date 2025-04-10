/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author andri
 */
public class StavkaIznajmljivanja implements ApstraktniDomenskiObjekat {

    private int idIznajmljivanje;
    private int rb;
    private String opisStavke;
    private LocalDate datumOd;
    private LocalDate datumDo;
    private int brojDana;
    private double iznosPoDanu;
    private double ukupanIznosStavke;
    private Knjiga idKnjiga;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(int idIznajmljivanje, int rb, String opisStavke, LocalDate datumOd, LocalDate datumDo, int brojDana, double iznosPoDanu, double ukupanIznosStavke, Knjiga idKnjiga) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.rb = rb;
        this.opisStavke = opisStavke;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.brojDana = brojDana;
        this.iznosPoDanu = iznosPoDanu;
        this.ukupanIznosStavke = ukupanIznosStavke;
        this.idKnjiga = idKnjiga;
    }

    @Override
    public String toString() {
        return "StavkaIznajmljivanja{" + "rb=" + rb + ", opisStavke=" + opisStavke + '}';
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
        final StavkaIznajmljivanja other = (StavkaIznajmljivanja) obj;
        if (this.idIznajmljivanje != other.idIznajmljivanje) {
            return false;
        }
        if (this.rb != other.rb) {
            return false;
        }
        if (!Objects.equals(this.opisStavke, other.opisStavke)) {
            return false;
        }
        return Objects.equals(this.idKnjiga, other.idKnjiga);
    }

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getOpisStavke() {
        return opisStavke;
       
    }

    public void setOpisStavke(String opisStavke) {
        this.opisStavke = opisStavke;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public double getIznosPoDanu() {
        return iznosPoDanu;
    }

    public void setIznosPoDanu(double iznosPoDanu) {
        this.iznosPoDanu = iznosPoDanu;
    }

    public double getUkupanIznosStavke() {
        return ukupanIznosStavke;
    }

    public void setUkupanIznosStavke(double ukupanIznosStavke) {
        this.ukupanIznosStavke = ukupanIznosStavke;
    }

    public Knjiga getIdKnjiga() {
        return idKnjiga;
    }

    public void setIdKnjiga(Knjiga idKnjiga) {
        this.idKnjiga = idKnjiga;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaiznajmljivanja";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idIznajmljivanje = rs.getInt("idIznajmljivanje");
            int rb = rs.getInt("rb");
            String opisStavke = rs.getString("opisStavke");
            LocalDate datumOd = rs.getObject("datumOd", LocalDate.class);
            LocalDate datumDo = rs.getObject("datumDo", LocalDate.class);
            int brojDana = rs.getInt("brojDana");
            double iznosPoDanu = rs.getDouble("iznosPoDanu");
            double ukupanIznosStavke = rs.getDouble("ukupanIznosStavke");
            int idKnjiga = rs.getInt("idKnjiga");
            String nazivKnjige = rs.getString("knjiga.naziv");

            Knjiga knjiga = new Knjiga(idKnjiga, nazivKnjige, null, -1, 0);
            StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(idIznajmljivanje, rb, opisStavke, datumOd, datumDo, brojDana, iznosPoDanu, ukupanIznosStavke, knjiga);

            System.out.println("ID Iznajmljivanja: " + stavka.getIdIznajmljivanje());
            System.out.println("RB: " + stavka.getRb());
            System.out.println("Opis stavke: " + stavka.getOpisStavke());
            System.out.println("Datum od: " + stavka.getDatumOd());
            System.out.println("Datum do: " + stavka.getDatumDo());
            System.out.println("Broj dana: " + stavka.getBrojDana());
            System.out.println("Iznos po danu: " + stavka.getIznosPoDanu());
            System.out.println("Ukupan iznos stavke: " + stavka.getUkupanIznosStavke());
            System.out.println("ID Knjige: " + knjiga.getIdKnjiga());
            System.out.println("-------------------------");
            lista.add(stavka);
            System.out.println("NAZIV STAVKE " + stavka.idKnjiga.getNaziv());

        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idIznajmljivanje, rb, opisStavke, datumOd, datumDo, brojDana, iznosPoDanu, ukupanIznosStavke, idKnjiga";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        // Pravilno formatiranje vrednosti za SQL upit
        return idIznajmljivanje + "," + rb + ",'" + opisStavke + "','" + datumOd.toString() + "','" + datumDo.toString() + "',"
                + brojDana + "," + iznosPoDanu + "," + ukupanIznosStavke + "," + idKnjiga.getIdKnjiga();
    }

    @Override
    public String vratiPrimarnikljuc() {
        //tavkaiznajmljivanja.idiznajmljivanje =3 AND stavkaiznajmljivanja.rb = 2
//        return "stavkaiznajmljivanja.idiznajmljivanje ="+idIznajmljivanje+" AND " +
//"stavkaiznajmljivanja.rb = "+rb;
        return ""; //kao treba kasnije da se sredi
    }

    @Override
    public ApstraktniDomenskiObjekat vratiobjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "opisStavke = '" + opisStavke + "', "
                + "datumOd = '" + datumOd + "', "
                + "datumDo = '" + datumDo + "', "
                + "brojDana = " + brojDana + ", "
                + "iznosPoDanu = " + iznosPoDanu + ", "
                + "ukupanIznosStavke = " + ukupanIznosStavke + ", "
                + "stavkaiznajmljivanja.idKnjiga = " + idKnjiga;
    }

}
