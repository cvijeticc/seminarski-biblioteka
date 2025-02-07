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
public class Citalac implements ApstraktniDomenskiObjekat{
    private int idCitalac;
    private String ime;
    private String prezime;
    private String email;
    private KategorijaCitaoca idKategorijaCitaoca;
    

    public Citalac() {
    }

    public Citalac(int idCitalac, String ime, String prezime, String email, KategorijaCitaoca KategorijaCitaoca) {
        this.idCitalac = idCitalac;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.idKategorijaCitaoca = idKategorijaCitaoca;
    }

    @Override
    public String toString() {
        return  ime + " " + prezime;
    }

    public int getIdCitalac() {
        return idCitalac;
    }

    public void setIdCitalac(int idCitalac) {
        this.idCitalac = idCitalac;
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

    public KategorijaCitaoca getIdKategorijaCitaoca() {
        return idKategorijaCitaoca;
    }

    public void setIdKategorijaCitaoca(KategorijaCitaoca idKategorijaCitaoca) {
        this.idKategorijaCitaoca = idKategorijaCitaoca;
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
        final Citalac other = (Citalac) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String vratiNazivTabele() {
        return "citalac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) { 
            int idCitalac = rs.getInt("citalac.idCitalac");
            String ime = rs.getString("citalac.ime");
            String prezime = rs.getString("citalac.prezime");
            String email= rs.getString("citalac.email");
//            KategorijaCitaoca idKategorijaCitaoca = (KategorijaCitaoca) rs.getObject("citalac.idKategorijaCitaoca");
            int idKategorijaCitaoca = rs.getInt("citalac.idKategorijaCitaoca");
            KategorijaCitaoca kategorijaCitaoca = new KategorijaCitaoca(idKategorijaCitaoca, null, null);



            Citalac c = new Citalac(idCitalac, ime, prezime, email, kategorijaCitaoca);
            lista.add(c);
        }
                
                
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,idKategorijaCitaoca";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + email + "'" + idKategorijaCitaoca.getIdKategorijaCitaoca();
    }

    @Override
    public String vratiPrimarnikljuc() {
        //citalac.idCitalac = 3
        return "citalac.idCitalac = " + idCitalac;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiobjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime = '"+ime+"', prezime ='"+prezime+"', email = '"+email
                + "'idKategorijaCitaoca" + idKategorijaCitaoca;
    }
    
    
    
}
