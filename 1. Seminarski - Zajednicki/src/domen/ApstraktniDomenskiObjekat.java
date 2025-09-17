/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author andri
 */
public interface ApstraktniDomenskiObjekat extends Serializable {
    
    public String vratiNazivTabele ();//nema one reci abstract posto se podrazumeva jer je interface
    public List<ApstraktniDomenskiObjekat> vratiListu (ResultSet rs) throws Exception; //vrati listu svih citaoca, iznajmljivanja, knjiga...
    public String vratiKoloneZaUbacivanje();//INSERT INTO ___ (a,b,c) values (sasa,dsada,dsada)
    public String vratiVrednostiZaUbacivanje();
    public String vratiPrimarnikljuc();//.. where id = ___ 
    public ApstraktniDomenskiObjekat vratiobjekatIzRS (ResultSet rs) throws Exception; // ovo vraca samo jedan red kao vrati listu samo za jedan red
    public String vratiVrednostiZaIzmenu();//update ___ set ime = "NOVO", prezime = "NOVO" ...

    // struktura
//    INSERT INTO {vratiNazivTabele()} ({vratiKoloneZaUbacivanje()}) VALUES ({vratiVrednostiZaUbacivanje()})
//              npr. INSERT INTO citalac (ime, prezime) VALUES ('Marko','Markovic')
//    UPDATE {vratiNazivTabele()} SET {vratiVrednostiZaIzmenu()} WHERE {vratiPrimarnikljuc()}
//              npr. UPDATE citalac SET ime = 'Marko', prezime = 'Markovic' WHERE idCitalac = 7;
//    SELECT * FROM {vratiNazivTabele()} ... pa onda vratiListu(rs) ili vratiobjekatIzRS(rs)
}
