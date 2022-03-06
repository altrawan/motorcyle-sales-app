/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.interfaces;

import id.sch.smkn1kawali.penjualanmotor.models.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Acer One
 */
public interface InterfaceUser {
    
    public User loadByUsername(String username) throws SQLException;
    
    public void createId(User u) throws SQLException;
    
    public void insert(User u) throws SQLException;
    
    public void update(User u) throws SQLException;
    
    public void delete(String id) throws SQLException;
    
    public List<User> findData(String key) throws SQLException;
    
    public List<User> findAll(int halaman,int banyauBaris) throws SQLException;
    
    public int count() throws SQLException;
    
    public List<User> fillUser() throws SQLException;
    
    public List<User> getLog(String id) throws SQLException;
    
    public User viewLog(String id) throws SQLException;
    
    public List<User> fillUser(String id) throws SQLException;
    
    public void message(User u) throws SQLException;
    
    public User getCount(String id) throws SQLException;
    
}
