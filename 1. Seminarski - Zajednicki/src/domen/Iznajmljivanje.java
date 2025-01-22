/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author andri
 */
public class Iznajmljivanje {
    private int idIznajmljivanja;
    private double ukupanIznos;
    private String opisIznajmljivanja;
    private Radnik radnik;
    private Citalac citalac;
    private List<StavkaIznajmljivanja> stakve;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanja, double ukupanIznos, String opisIznajmljivanja, Radnik radnik, Citalac citalac, List<StavkaIznajmljivanja> stakve) {
        this.idIznajmljivanja = idIznajmljivanja;
        this.ukupanIznos = ukupanIznos;
        this.opisIznajmljivanja = opisIznajmljivanja;
        this.radnik = radnik;
        this.citalac = citalac;
        this.stakve = stakve;
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" + "idIznajmljivanja=" + idIznajmljivanja + ", ukupanIznos=" + ukupanIznos + ", radnik=" + radnik + ", citalac=" + citalac + ", stakve=" + stakve + '}';
    }

    public int getIdIznajmljivanja() {
        return idIznajmljivanja;
    }

    public void setIdIznajmljivanja(int idIznajmljivanja) {
        this.idIznajmljivanja = idIznajmljivanja;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Citalac getCitalac() {
        return citalac;
    }

    public void setCitalac(Citalac citalac) {
        this.citalac = citalac;
    }

    public List<StavkaIznajmljivanja> getStakve() {
        return stakve;
    }

    public void setStakve(List<StavkaIznajmljivanja> stakve) {
        this.stakve = stakve;
    }

    public String getOpisIznajmljivanja() {
        return opisIznajmljivanja;
    }

    public void setOpisIznajmljivanja(String opisIznajmljivanja) {
        this.opisIznajmljivanja = opisIznajmljivanja;
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        if (!Objects.equals(this.radnik, other.radnik)) {
            return false;
        }
        if (!Objects.equals(this.citalac, other.citalac)) {
            return false;
        }
        return Objects.equals(this.stakve, other.stakve);
    }
    
    
    
}
