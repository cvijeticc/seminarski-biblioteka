/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import com.mysql.cj.protocol.Resultset;
import domen.ApstraktniDomenskiObjekat;
import domen.Iznajmljivanje;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import javax.swing.JOptionPane;
import repository.db.DbConnectionFactory;

import repository.db.DbRepository;

/**
 *
 * @author andri
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        String upit;

        upit = "SELECT * FROM " + param.vratiNazivTabele();

        if (uslov != null) {
            upit += uslov;
        }
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);

        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        //insert into pacijent (ime, prezime) values ('Marko','Markovic');
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje()
                + ") VALUES ( " + param.vratiVrednostiZaUbacivanje() + " )";
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET "
                + param.vratiVrednostiZaIzmenu() + " WHERE " + param.vratiPrimarnikljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();

    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarnikljuc();
        System.out.println(upit);
        try {
            Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
            st.executeUpdate(upit);
            st.close();

        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
           
            throw new Exception("Ne možete obrisati objekat jer je referenciran u drugoj tabeli.");
        } catch (SQLException ex) {
            throw new Exception("SQL Greška pri brisanju: " + ex.getMessage());
        }
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
