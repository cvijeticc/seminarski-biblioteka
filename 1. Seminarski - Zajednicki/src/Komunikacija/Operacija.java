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
    OBRISI_RADNIKA,
    DODAJ_KNJIGU, 
    OBRISI_KNJIGU, 
    AZURIRAJ_KNJIGU, 
    DODAJ_KATEGORIJU_CITAOCA, 
    AZURIRAJ_KATEGORIJU_CITAOCA, 
    OBRISI_KATEGORIJU_CITAOCA, 
    DODAJ_TERMIN_SMENE, 
    UCITAJ_TERMINE_SMENE, 
    OBRISI_TERMIN_SMENE, 
    AZURIRAJ_TERMIN_SMENE, 
    AZURIRAJ_STAVKU_IZNAJMLJIVANJA, 
    OBRISI_STAVKU_IZNAJMLJIVANJA, 
}
