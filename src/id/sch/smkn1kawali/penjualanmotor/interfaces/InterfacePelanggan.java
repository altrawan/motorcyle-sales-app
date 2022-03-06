/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.interfaces;

import id.sch.smkn1kawali.penjualanmotor.models.Pelanggan;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Acer One
 */
public interface InterfacePelanggan {
    
    public void createId(Pelanggan p) throws SQLException;
    
    public void insert(Pelanggan p) throws SQLException;
    
    public void update(Pelanggan p) throws SQLException;
    
    public void delete(String id) throws SQLException;
    
    public List<Pelanggan> findData(String key) throws SQLException;
    
    public List<Pelanggan> findAll(int halaman,int banyakBaris) throws SQLException;
    
    public int count() throws SQLException;
    
    public List<Pelanggan> fillCustomer() throws SQLException;
    
    public List<Pelanggan> getLog(String id) throws SQLException;
    
    public Pelanggan viewLog(String id) throws SQLException;
    
}
