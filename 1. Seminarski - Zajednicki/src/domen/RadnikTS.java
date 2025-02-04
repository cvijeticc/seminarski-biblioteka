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
    private Radnik idRadnik;
    private TerminSmene idTerminSmene;

    public RadnikTS() {
    }

    public RadnikTS(LocalDate datumSmene, Radnik radnik, TerminSmene terminSmene) {
        this.datumSmene = datumSmene;
        this.idRadnik = idRadnik;
        this.idTerminSmene = idTerminSmene;
    }

    @Override
    public String toString() {
        return "RadnikTS{" + "datumSmene=" + datumSmene + ", idRadnik=" + idRadnik + ", idTerminSmene=" + idTerminSmene + '}';
    }

    public Radnik getIdRadnik() {
        return idRadnik;
    }

    public void setIdRadnik(Radnik idRadnik) {
        this.idRadnik = idRadnik;
    }

    public TerminSmene getIdTerminSmene() {
        return idTerminSmene;
    }

    public void setIdTerminSmene(TerminSmene idTerminSmene) {
        this.idTerminSmene = idTerminSmene;
    }

 
    public LocalDate getDatumSmene() {
        return datumSmene;
    }

    public void setDatumSmene(LocalDate datumSmene) {
        this.datumSmene = datumSmene;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        return Objects.equals(this.idRadnik, other.idRadnik);
    }

    

    

    @Override
    public String vratiNazivTabele() {
        return "radnikts";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumSmene,idRadnik,idTerminSmene";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiPrimarnikljuc() {
        //radnikts.datumsmene= "2025-01-01" AND radnikts.idradnik = 1 AND radnikts.idterminsmene = 2
        return "radnikts.datumsmene= " +datumSmene+" AND " +
"radnikts.idradnik = "+idRadnik+" AND "+ "radnikts.idterminsmene = "+idTerminSmene;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiobjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumSmene = '"+datumSmene+
"', radnikts.idradnik = "+idRadnik+ ", radnikts.idterminsmene = "+idTerminSmene;
    }
    
    
}
