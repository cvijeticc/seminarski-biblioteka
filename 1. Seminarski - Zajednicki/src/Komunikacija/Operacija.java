/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Komunikacija;

import java.io.Serializable;

/**
 *
 * @author andri
 */
public enum Operacija implements Serializable {
    LOGIN, 
    UCITAJ_CITAOCE, 
    OBRISI_CITAOCA, 
    UCITAJ_KATEGORIJE_CITAOCA, 
    DODAJ_CITAOCA,
    AZURIRAJ_CITAOCA, 
    UCITAJ_IZNAJMLJIVANJA, 
    UCITAJ_STAVKU_IZNAJMLJIVANJA,
    UCITAJ_RADNIKE,
    UCITAJ_KNJIGE,
    UCITAJ_IZNOS_PO_DANU, 
    DODAJ_IZNAJMLJIVANJE, 
    AZURIRAJ_IZNAJMLJIVANJE, 
    DODAJ_RADNIKA, 
    AZURIRAJ_RADNIKA, 
    OBRISI_RADNIKA
}
