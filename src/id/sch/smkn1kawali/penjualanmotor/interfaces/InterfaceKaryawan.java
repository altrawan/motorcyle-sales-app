/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.interfaces;

import id.sch.smkn1kawali.penjualanmotor.models.Karyawan;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Acer One
 */
public interface InterfaceKaryawan {
    
    public void createId(Karyawan k) throws SQLException;
    
    public void insert(Karyawan k) throws SQLException;
    
    public void update(Karyawan k) throws SQLException;
    
    public void delete(String id) throws SQLException;
    
    public List<Karyawan> findData(String key) throws SQLException;
    
    public List<Karyawan> findAll(int halaman,int banyakBaris) throws SQLException;
    
    public int count() throws SQLException;
    
    public List<Karyawan> fillEmployee() throws SQLException;
    
    public List<Karyawan> getLog(String id) throws SQLException;
    
    public Karyawan viewLog(String id) throws SQLException;
    
}
