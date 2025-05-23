/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andri
 */
public class Knjiga implements ApstraktniDomenskiObjekat {

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
        return naziv;
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
        int hash = 3;
        hash = 79 * hash + this.idKnjiga;
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
        return this.idKnjiga == other.idKnjiga;
    }

    

    @Override
    public String vratiNazivTabele() {
        return "knjiga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idKnjiga = rs.getInt("knjiga.idKnjiga");
            String naziv = rs.getString("knjiga.naziv");
            String zanrKnjige = rs.getString("knjiga.zanrKnjige");
            int godinaIzdavanja = rs.getInt("knjiga.godinaIzdavanja");
            double iznosPoDanu = rs.getDouble("knjiga.iznosPoDanu");

            Knjiga k = new Knjiga(idKnjiga, naziv, zanrKnjige, godinaIzdavanja, iznosPoDanu);
            lista.add(k);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,zanrKnjige,godinaIzdavanja,iznosPoDanu";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "','" + zanrKnjige + "'," + godinaIzdavanja + "," + iznosPoDanu;
    }

    @Override
    public String vratiPrimarnikljuc() {
        return "knjiga.idknjiga= " + idKnjiga;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiobjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv = '" + naziv + "', zanrKnjige = '" + zanrKnjige + "', godinaIzdavanja = " + godinaIzdavanja
                + ", iznosPoDanu = " + iznosPoDanu;
    }

}
