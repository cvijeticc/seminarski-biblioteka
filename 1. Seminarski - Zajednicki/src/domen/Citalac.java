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
public class Citalac {
    private int idCitalac;
    private String ime;
    private String prezime;
    private String email;
    private KategorijaCitaoca KategorijaCitaoca;

    public Citalac() {
    }

    public Citalac(int idCitalac, String ime, String prezime, String email, KategorijaCitaoca KategorijaCitaoca) {
        this.idCitalac = idCitalac;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.KategorijaCitaoca = KategorijaCitaoca;
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

    public KategorijaCitaoca getKategorijaCitaoca() {
        return KategorijaCitaoca;
    }

    public void setKategorijaCitaoca(KategorijaCitaoca KategorijaCitaoca) {
        this.KategorijaCitaoca = KategorijaCitaoca;
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
    
    
    
}
