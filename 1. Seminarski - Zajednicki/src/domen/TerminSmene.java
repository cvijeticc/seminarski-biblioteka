/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author andri
 */
public class TerminSmene {
    private int idTerminSmene;
    private int trajanjeSmene;

    @Override
    public String toString() {
        return "TerminSmene{" + "idTerminSmene=" + idTerminSmene + ", trajanjeSmene=" + trajanjeSmene + '}';
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
    
    
}
