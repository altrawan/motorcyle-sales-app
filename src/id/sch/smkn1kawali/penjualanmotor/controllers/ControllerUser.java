/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.controllers;

import id.sch.smkn1kawali.penjualanmotor.interfaces.InterfaceUser;
import id.sch.smkn1kawali.penjualanmotor.models.User;
import id.sch.smkn1kawali.penjualanmotor.utilitys.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer One
 */
public class ControllerUser implements InterfaceUser {
    
    @Override
    public User loadByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM t_user WHERE username=? OR email=?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, username);
        ResultSet rs = ps.executeQuery();
        User u = new User();
        if (rs.next()) {
            u.setKode(rs.getString("kd_user"));
            u.setName(rs.getString("name"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setRole(rs.getString("role"));
        }
        return u;
    }
    
    @Override
    public void createId(User u) throws SQLException {
        String sql = "SELECT kd_user FROM t_user ORDER BY kd_user DESC";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String ambilKode = rs.getString("kd_user");
                String sub = ambilKode.substring(1, 5);
                int counter = Integer.parseInt(sub) + 1;
                if (counter <= 9) {
                    u.setKode("U000" + Integer.toString(counter));
                } else if (counter <= 99) {
                    u.setKode("U00" + Integer.toString(counter));
                } else if (counter <=999) {
                    u.setKode("U0" + Integer.toString(counter));
                } else {
                    u.setKode("U" + Integer.toString(counter));
                }
            } else {
                u.setKode("U0001");
            }
        }
    }

    @Override
    public void insert(User u) throws SQLException {
        String sql = "CALL insert__User(?,?,?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, u.getKode());
        ps.setString(2, u.getName());
        ps.setString(3, u.getUsername());
        ps.setString(4, u.getEmail());
        ps.setString(5, u.getPassword());
        ps.setString(6, u.getRole());
        ps.setString(7, u.getStatus());
        ps.executeUpdate();
    }

    @Override
    public void update(User u) throws SQLException {
        String sql = "CALL update__User(?,?,?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, u.getKode());
        ps.setString(2, u.getName());
        ps.setString(3, u.getUsername());
        ps.setString(4, u.getEmail());
        ps.setString(5, u.getPassword());
        ps.setString(6, u.getRole());
        ps.setString(7, u.getStatus());
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "CALL delete__User(?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<User> findData(String key) throws SQLException {
        String sql = "SELECT * FROM v_user WHERE kd_user LIKE '%" + key + "%'"
                + " OR name LIKE '%" + key + "%'"
                + " OR username LIKE '%" + key + "%'"
                + " OR email LIKE '%" + key + "%'"
                + " OR password LIKE '%" + key + "%'"
                + " OR role LIKE '%" + key + "%'"
                + " OR status LIKE '%" + key + "%'"
                + " OR created_at LIKE '%" + key + "%'";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            u.setKode(rs.getString(1));
            u.setName(rs.getString(2));
            u.setUsername(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setPassword(rs.getString(5));
            u.setRole(rs.getString(6));
            u.setStatus(rs.getString(7));
            u.setTanggal(rs.getString(8));
            list.add(u);
        }
        return list;
    }
    
    @Override
    public List<User> findAll(int halaman,int banyakBaris) throws SQLException {
        String sql = "SELECT * FROM v_user limit ?,?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setInt(1, banyakBaris * (halaman - 1));
        ps.setInt(2, banyakBaris);
        ResultSet rs = ps.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            u.setKode(rs.getString(1));
            u.setName(rs.getString(2));
            u.setUsername(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setPassword(rs.getString(5));
            u.setRole(rs.getString(6));
            u.setStatus(rs.getString(7));
            u.setTanggal(rs.getString(8));
            list.add(u);
        }
        return list;
    }
    
    @Override
    public int count() throws SQLException {
        int jumlahBaris = 0;
        String sql = "SELECT count(kd_user) from v_user";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            jumlahBaris = rs.getInt(1);
        }
        return jumlahBaris;
    }
    
    @Override
    public List<User> fillUser() throws SQLException {
        String sql = "SELECT DISTINCT kd_user FROM log__user";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            u.setKode(rs.getString("kd_user"));
            list.add(u);
        }
        return list;
    }
    
    @Override
    public List<User> getLog(String id) throws SQLException {
        String sql = "SELECT * FROM log__user WHERE kd_user = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            u.setKode(rs.getString(1));
            u.setAksi(rs.getString(2));
            u.setTanggal(rs.getString(11));
            list.add(u);
        }
        return list;
    }
    
    @Override
    public User viewLog(String id) throws SQLException {
        String sql = "SELECT * FROM log__user WHERE id = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        User u = new User();
        if (rs.next()) {
            u.setKode(rs.getString(3));
            u.setName(rs.getString(4));
            u.setUsername(rs.getString(5));
            u.setEmail(rs.getString(6));
            u.setRole(rs.getString(8));
            u.setStatus(rs.getString(10));
        }
        return u;
    }
    
    @Override
    public List<User> fillUser(String id) throws SQLException {
        String sql = "SELECT * FROM t_user WHERE kd_user NOT IN (?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            u.setKode(rs.getString("kd_user"));
            u.setName(rs.getString("name"));
            list.add(u);
        }
        return list;
    }
    
    @Override
    public void message(User u) throws SQLException {
        String sql = "CALL insert__Message(?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, u.getPengirim());
        ps.setString(2, u.getPenerima());
        ps.setString(3, u.getSubjek());
        ps.setString(4, u.getPesan());
        ps.executeUpdate();
    }
    
    @Override
    public User getCount(String id) throws SQLException {
        String sql = "SELECT COUNT(status) AS inbox FROM t_message WHERE status='UNREAD' AND receiver=?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        User u = new User();
        if (rs.next()) {
            u.setKode(id);
        }
        return u;
    }
}
