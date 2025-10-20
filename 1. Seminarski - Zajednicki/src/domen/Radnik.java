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
public class Radnik implements ApstraktniDomenskiObjekat {

    private int idRadnik;
    private String ime;
    private String prezime;
    private String email;
    private String korisnickoIme;
    private String sifra;
//    private List<TerminSmene> ListaTerminaSmena;

    public Radnik() {
    }

    public Radnik(int idRadnik, String ime, String prezime, String email, String korisnickoIme, String sifra) {
        this.idRadnik = idRadnik;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return (ime != null && prezime != null) ? ime + " " + prezime : "ID: " + idRadnik;
    }

    public int getIdRadnik() {
        return idRadnik;
    }

    public void setIdRadnik(int idRadnik) {
        this.idRadnik = idRadnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Radnik other = (Radnik) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String vratiNazivTabele() {
        return "radnik";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idRadnik = rs.getInt("radnik.idRadnik");
            String ime = rs.getString("radnik.ime");
            String prezime = rs.getString("radnik.prezime");
            String email = rs.getString("radnik.email");
            String korisnickoIme = rs.getString("radnik.korisnickoIme");
            String sifra = rs.getString("radnik.sifra");

            Radnik r = new Radnik(idRadnik, ime, prezime, email, korisnickoIme, sifra);
            lista.add(r);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,korisnickoIme,sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + email + "','" + korisnickoIme + "','" + sifra + "'";
    }

    @Override
    public String vratiPrimarnikljuc() {
        return "radnik.idradnik=" + idRadnik;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiobjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', email = '" + email
                + "', korisnickoIme = '" + korisnickoIme + "', sifra = '" + sifra + "'";
    }

}
