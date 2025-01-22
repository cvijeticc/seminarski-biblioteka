/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author andri
 */
public class RadnikTS implements ApstraktniDomenskiObjekat{
    private LocalDate datumSmene;
    private Radnik radnik;
    private TerminSmene terminSmene;

    public RadnikTS() {
    }

    public RadnikTS(LocalDate datumSmene, Radnik radnik, TerminSmene terminSmene) {
        this.datumSmene = datumSmene;
        this.radnik = radnik;
        this.terminSmene = terminSmene;
    }

    @Override
    public String toString() {
        return "RadnikTS{" + "datumSmene=" + datumSmene + ", radnik=" + radnik + ", terminSmene=" + terminSmene + '}';
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public TerminSmene getTerminSmene() {
        return terminSmene;
    }

    public void setTerminSmene(TerminSmene terminSmene) {
        this.terminSmene = terminSmene;
    }

    

    

    public LocalDate getDatumSmene() {
        return datumSmene;
    }

    public void setDatumSmene(LocalDate datumSmene) {
        this.datumSmene = datumSmene;
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
        final RadnikTS other = (RadnikTS) obj;
        if (!Objects.equals(this.datumSmene, other.datumSmene)) {
            return false;
        }
        return Objects.equals(this.radnik, other.radnik);
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
