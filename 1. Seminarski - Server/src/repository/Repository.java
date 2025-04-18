/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import domen.Iznajmljivanje;
import java.util.List;


/**
 *
 * @author andri
 */
public interface Repository<T> {//T je bilo koji objekat iz domena u Zajednickom

    List<T> getAll(T param, String uslov) throws Exception;

    int add(T param) throws Exception;

    void edit(T param) throws Exception;

    void delete(T param) throws Exception;
    
    List<T> getAll();

}
