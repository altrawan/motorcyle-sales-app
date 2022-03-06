/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.interfaces;

import id.sch.smkn1kawali.penjualanmotor.models.Motor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Acer One
 */
public interface InterfaceMotor {
    
    public void createId(Motor m) throws SQLException;
    
    public void insert(Motor m) throws SQLException;
    
    public void update(Motor m) throws SQLException;
    
    public void delete(String id) throws SQLException;
    
    public List<Motor> findData(String key) throws SQLException;
    
    public List<Motor> findAll(int halaman,int banyakBaris) throws SQLException;
    
    public int count() throws SQLException;
    
    public List<Motor> fillMotor() throws SQLException;
    
    public List<Motor> getLog(String id) throws SQLException;
    
    public Motor viewLog(String id) throws SQLException;
    
}
