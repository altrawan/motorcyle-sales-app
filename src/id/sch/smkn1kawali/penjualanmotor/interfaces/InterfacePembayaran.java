/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.interfaces;

import id.sch.smkn1kawali.penjualanmotor.models.Pembayaran;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Acer One
 */
public interface InterfacePembayaran {
    
    public Pembayaran getDataById(String id) throws SQLException;
    
    public List<Pembayaran> getData(String id) throws SQLException;
    
    public void updateDetail(Pembayaran p) throws SQLException;
    
    public void statusCash(Pembayaran p) throws SQLException;
    
    public void statusCredit(Pembayaran p) throws SQLException;
    
    public Pembayaran getDetailById(Integer id) throws SQLException;
    
    public Pembayaran getSale(String id) throws SQLException;
    
    public List<Pembayaran> getAngsuran(String id) throws SQLException;
    
    public void insertAngsuran(Pembayaran p) throws SQLException;
    
    public void update(Pembayaran p) throws SQLException;
    
    public void status(Pembayaran p) throws SQLException;
    
    public Pembayaran getSum(String id) throws SQLException;
    
}
