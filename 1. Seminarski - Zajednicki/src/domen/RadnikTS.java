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
public class RadnikTS {
    private int idRadnik;
    private int idTerminSmene;
    private LocalDate datumSmene;

    public RadnikTS() {
    }

    public RadnikTS(int idRadnik, int idTerminSmene, LocalDate datumSmene) {
        this.idRadnik = idRadnik;
        this.idTerminSmene = idTerminSmene;
        this.datumSmene = datumSmene;
    }

    @Override
    public String toString() {
        return "RadnikTS{" + "idRadnik=" + idRadnik + ", idTerminSmene=" + idTerminSmene + ", datumSmene=" + datumSmene + '}';
    }

    public int getIdRadnik() {
        return idRadnik;
    }

    public void setIdRadnik(int idRadnik) {
        this.idRadnik = idRadnik;
    }

    public int getIdTerminSmene() {
        return idTerminSmene;
    }

    public void setIdTerminSmene(int idTerminSmene) {
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
        if (this.idRadnik != other.idRadnik) {
            return false;
        }
        if (this.idTerminSmene != other.idTerminSmene) {
            return false;
        }
        return Objects.equals(this.datumSmene, other.datumSmene);
    }
    
    
}
