/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.Objects;

/**
 *
 * @author andri
 */
public class Knjiga {
    private int idKnjiga;
    private String naziv;
    private String zanrKnjige;
    private int godinaIzdavanja;
    private double iznosPoDanu;

    public Knjiga() {
    }

    public Knjiga(int idKnjiga, String naziv, String zanrKnjige, int godinaIzdavanja, double iznosPoDanu) {
        this.idKnjiga = idKnjiga;
        this.naziv = naziv;
        this.zanrKnjige = zanrKnjige;
        this.godinaIzdavanja = godinaIzdavanja;
        this.iznosPoDanu = iznosPoDanu;
    }

    @Override
    public String toString() {
        return "Knjiga{" + "naziv=" + naziv + '}';
    }

    public int getIdKnjiga() {
        return idKnjiga;
    }

    public void setIdKnjiga(int idKnjiga) {
        this.idKnjiga = idKnjiga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getZanrKnjige() {
        return zanrKnjige;
    }

    public void setZanrKnjige(String zanrKnjige) {
        this.zanrKnjige = zanrKnjige;
    }

    public int getGodinaIzdavanja() {
        return godinaIzdavanja;
    }

    public void setGodinaIzdavanja(int godinaIzdavanja) {
        this.godinaIzdavanja = godinaIzdavanja;
    }

    public double getIznosPoDanu() {
        return iznosPoDanu;
    }

    public void setIznosPoDanu(double iznosPoDanu) {
        this.iznosPoDanu = iznosPoDanu;
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
        final Knjiga other = (Knjiga) obj;
        if (this.godinaIzdavanja != other.godinaIzdavanja) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.zanrKnjige, other.zanrKnjige);
    }
    
    
}
