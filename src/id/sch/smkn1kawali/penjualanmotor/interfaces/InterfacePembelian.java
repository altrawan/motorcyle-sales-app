/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.interfaces;

import id.sch.smkn1kawali.penjualanmotor.models.Motor;
import id.sch.smkn1kawali.penjualanmotor.models.Pembelian;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Acer One
 */
public interface InterfacePembelian {
    
    public List<Motor> getAll() throws SQLException;
    
    public List<Motor> findData(String key) throws SQLException;
    
    public List<Pembelian> fillCustomer() throws SQLException;
    
    public void createId(Pembelian p) throws SQLException;
    
    public void insertCash(Pembelian p) throws SQLException;
    
    public void detailPurchase(Pembelian p) throws SQLException;
    
    public void insertCredit(Pembelian p) throws SQLException;
    
}
