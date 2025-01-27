# Biblioteka - Sistem za iznajmljivanje knjiga

Ovaj projekat je implementacija informacionog sistema za upravljanje bibliotekama i procesom iznajmljivanja knjiga. Sistem omogućava radnicima biblioteke da prate korisnike, knjige, iznajmljivanja i smene, čime olakšava svakodnevne operacije u biblioteci.

## Funkcionalnosti

- Evidencija korisnika biblioteke (čitalaca).
- Upravljanje kategorijama korisnika sa dodatnim pogodnostima.
- Upravljanje knjigama u biblioteci (naziv, žanr, godina izdavanja, cena po danu).
- Iznajmljivanje knjiga sa evidentiranjem datuma, broja dana i ukupnog iznosa.
- Evidencija radnika i njihovih smena.

## Ključne klase i strukture

- **Radnik:** Sadrži podatke o radnicima biblioteke.
- **Čitalac:** Sadrži podatke o korisnicima biblioteke.
- **KategorijaČitaoca:** Kategorije čitalaca sa pripadajućim beneficijama.
- **Knjiga:** Podaci o knjigama dostupnim za iznajmljivanje.
- **Iznajmljivanje:** Evidencija o iznajmljivanju knjiga.
- **TerminSmene:** Informacije o terminima smena radnika.
- **RadnikTS:** Povezuje radnike sa terminima smena.

## Tehnologije i alati

- **Java** - Glavni programski jezik.
- **MySQL** - Baza podataka za čuvanje podataka o biblioteci.
- **JDBC** - Konekcija između aplikacije i baze podataka.
- **Git** - Verziona kontrola.
- **Maven** - Upravljanje zavisnostima i build-ovanje.
- **SQLyog** - Alat za upravljanje MySQL bazom podataka.

## Instalacija

1. Klonirajte repozitorijum:
   ```bash
   git clone https://github.com/korisnickoIme/biblioteka.git
   ```

