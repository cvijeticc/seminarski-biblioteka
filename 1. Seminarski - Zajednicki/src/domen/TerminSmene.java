/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andri
 */
public class TerminSmene implements ApstraktniDomenskiObjekat{
    private int idTerminSmene;
    private int trajanjeSmene;
    private int brojSmene;

    @Override
    public String toString() {
        return "TerminSmene{" + "idTerminSmene=" + idTerminSmene + ", trajanjeSmene=" + trajanjeSmene + '}';
    }

    public TerminSmene() {
    }

    public TerminSmene(int idTerminSmene, int trajanjeSmene, int brojSmene) {
        this.idTerminSmene = idTerminSmene;
        this.trajanjeSmene = trajanjeSmene;
        this.brojSmene = brojSmene;
    }

    
    
    public int getIdTerminSmene() {
        return idTerminSmene;
    }

    public void setIdTerminSmene(int idTerminSmene) {
        this.idTerminSmene = idTerminSmene;
    }

    public int getTrajanjeSmene() {
        return trajanjeSmene;
    }

    public void setTrajanjeSmene(int trajanjeSmene) {
        this.trajanjeSmene = trajanjeSmene;
    }

    public int getBrojSmene() {
        return brojSmene;
    }

    public void setBrojSmene(int brojSmene) {
        this.brojSmene = brojSmene;
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
        final TerminSmene other = (TerminSmene) obj;
        if (this.idTerminSmene != other.idTerminSmene) {
            return false;
        }
        return this.trajanjeSmene == other.trajanjeSmene;
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
