/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author andri
 */
public class StavkaIznajmljivanja {
    private int idIznajmljivanje;
    private int rb;
    private String opisStavke;
    private LocalDate datumOd;
    private LocalDate datumDo;
    private int brojDana;
    private double iznosPoDanu;
    private double ukupanIznosStavke;
    private Knjiga knjiga;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(int idIznajmljivanje, int rb, String opisStavke, LocalDate datumOd, LocalDate datumDo, int brojDana, double iznosPoDanu, double ukupanIznosStavke, Knjiga knjiga) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.rb = rb;
        this.opisStavke = opisStavke;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.brojDana = brojDana;
        this.iznosPoDanu = iznosPoDanu;
        this.ukupanIznosStavke = ukupanIznosStavke;
        this.knjiga = knjiga;
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
        if (this.rb != other.rb) {
            return false;
        }
        if (Double.doubleToLongBits(this.ukupanIznosStavke) != Double.doubleToLongBits(other.ukupanIznosStavke)) {
            return false;
        }
        if (!Objects.equals(this.opisStavke, other.opisStavke)) {
            return false;
        }
        return Objects.equals(this.knjiga, other.knjiga);
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

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }
    
    
          
    
    
}
