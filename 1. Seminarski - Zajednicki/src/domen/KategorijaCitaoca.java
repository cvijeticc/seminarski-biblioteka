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
public class KategorijaCitaoca {
    private int idKategorijaCitaoca;
    private String nazivKategorije;

    public KategorijaCitaoca() {
    }

    public KategorijaCitaoca(int idKategorijaCitaoca, String nazivKategorije) {
        this.idKategorijaCitaoca = idKategorijaCitaoca;
        this.nazivKategorije = nazivKategorije;
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
    
    
    
}
