/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author andri
 */
public class KategorijaCitaoca implements ApstraktniDomenskiObjekat{
    private int idKategorijaCitaoca;
    private String nazivKategorije;
    private String beneficije;
    
    public KategorijaCitaoca() {
    }

    public KategorijaCitaoca(int idKategorijaCitaoca, String nazivKategorije, String beneficije) {
        this.idKategorijaCitaoca = idKategorijaCitaoca;
        this.nazivKategorije = nazivKategorije;
        this.beneficije = beneficije;
    }

    

    @Override
    public String toString() {
        return  nazivKategorije;
    }

    public int getIdKategorijaCitaoca() {
        return idKategorijaCitaoca;
    }

    public void setIdKategorijaCitaoca(int idKategorijaCitaoca) {
        this.idKategorijaCitaoca = idKategorijaCitaoca;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public String getBeneficije() {
        return beneficije;
    }

    public void setBeneficije(String beneficije) {
        this.beneficije = beneficije;
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
        final KategorijaCitaoca other = (KategorijaCitaoca) obj;
        if (this.idKategorijaCitaoca != other.idKategorijaCitaoca) {
            return false;
        }
        return Objects.equals(this.nazivKategorije, other.nazivKategorije);
    }

    @Override
    public String vratiNazivTabele() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiPrimarnikljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ApstraktniDomenskiObjekat vratiobjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
