/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.controllers;

import id.sch.smkn1kawali.penjualanmotor.utilitys.Database;
import id.sch.smkn1kawali.penjualanmotor.interfaces.InterfaceMotor;
import id.sch.smkn1kawali.penjualanmotor.models.Motor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer One
 */
public class ControllerMotor implements InterfaceMotor {
    
    @Override
    public void createId(Motor m) throws SQLException {
        String sql = "SELECT kd_motor FROM t_motor ORDER BY kd_motor DESC";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String ambilKode = rs.getString("kd_motor");
                String sub = ambilKode.substring(1, 5);
                int counter = Integer.parseInt(sub) + 1;
                if (counter <= 9) {
                    m.setKode("M000" + Integer.toString(counter));
                } else if (counter <= 99) {
                    m.setKode("M00" + Integer.toString(counter));
                } else if (counter <=999) {
                    m.setKode("M0" + Integer.toString(counter));
                } else {
                    m.setKode("M" + Integer.toString(counter));
                }
            } else {
                m.setKode("M0001");
            }
        }
    }

    @Override
    public void insert(Motor m) throws SQLException {
        String sql = "CALL insert__Motor(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, m.getKode());
        ps.setString(2, m.getJenis());
        ps.setString(3, m.getType());
        ps.setString(4, m.getWarna());
        ps.setInt(5, m.getHarga());
        ps.setString(6, m.getGambar());
        ps.setInt(7, m.getStok());
        ps.setString(8, m.getKeterangan());
        ps.setString(9, m.getStatus());
        ps.executeUpdate();
    }

    @Override
    public void update(Motor m) throws SQLException {
        String sql = "CALL update__Motor(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, m.getKode());
        ps.setString(2, m.getJenis());
        ps.setString(3, m.getType());
        ps.setString(4, m.getWarna());
        ps.setInt(5, m.getHarga());
        ps.setString(6, m.getGambar());
        ps.setInt(7, m.getStok());
        ps.setString(8, m.getKeterangan());
        ps.setString(9, m.getStatus());
        ps.executeUpdate();
    }
   

    @Override
    public void delete(String id) throws SQLException {
        String sql = "CALL delete__Motor(?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Motor> findData(String key) throws SQLException {
        String sql = "SELECT * FROM v_motor WHERE kd_motor LIKE '%" + key + "%'"
                + " OR jenis LIKE '%" + key + "%'"
                + " OR type LIKE '%" + key + "%'"
                + " OR warna LIKE '%" + key + "%'"
                + " OR harga LIKE '%" + key + "%'"
                + " OR gambar LIKE '%" + key + "%'"
                + " OR stok LIKE '%" + key + "%'"
                + " OR keterangan LIKE '%" + key + "%'"
                + " OR status LIKE '%" + key + "%'"
                + " OR created_at LIKE '%" + key + "%'";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Motor> list = new ArrayList<>();
        while (rs.next()) {
            Motor m = new Motor();
            m.setKode(rs.getString(1));
            m.setJenis(rs.getString(2));
            m.setType(rs.getString(3));
            m.setWarna(rs.getString(4));
            m.setHarga(rs.getInt(5));
            m.setGambar(rs.getString(6));
            m.setStok(rs.getInt(7));
            m.setKeterangan(rs.getString(8));
            m.setStatus(rs.getString(9));
            m.setTanggal(rs.getString(10));
            list.add(m);
        }
        return list;
    }
    
    @Override
    public List<Motor> findAll(int halaman,int banyakBaris) throws SQLException {
        String sql = "SELECT * FROM v_motor limit ?,?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setInt(1, banyakBaris * (halaman - 1));
        ps.setInt(2, banyakBaris);
        ResultSet rs = ps.executeQuery();
        List<Motor> list = new ArrayList<>();
        while (rs.next()) {
            Motor m = new Motor();
            m.setKode(rs.getString(1));
            m.setJenis(rs.getString(2));
            m.setType(rs.getString(3));
            m.setWarna(rs.getString(4));
            m.setHarga(rs.getInt(5));
            m.setGambar(rs.getString(6));
            m.setStok(rs.getInt(7));
            m.setKeterangan(rs.getString(8));
            m.setStatus(rs.getString(9));
            m.setTanggal(rs.getString(10));
            list.add(m);
        }
        return list;
    }
    
    @Override
    public int count() throws SQLException {
        int jumlahBaris = 0;
        String sql = "SELECT count(kd_karyawan) from v_karyawan";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            jumlahBaris = rs.getInt(1);
        }
        return jumlahBaris;
    }
    
    @Override
    public List<Motor> fillMotor() throws SQLException {
        String sql = "SELECT DISTINCT kd_motor FROM log__motor";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Motor> list = new ArrayList<>();
        while (rs.next()) {
            Motor m = new Motor();
            m.setKode(rs.getString("kd_motor"));
            list.add(m);
        }
        return list;
    }
    
    @Override
    public List<Motor> getLog(String id) throws SQLException {
        String sql = "SELECT * FROM log__motor WHERE kd_motor = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        List<Motor> list = new ArrayList<>();
        while (rs.next()) {
            Motor m = new Motor();
            m.setKode(rs.getString(1));
            m.setAksi(rs.getString(2));
            m.setTanggal(rs.getString(12));
            list.add(m);
        }
        return list;
    }
    
    @Override
    public Motor viewLog(String id) throws SQLException {
        String sql = "SELECT * FROM log__motor WHERE id = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Motor m = new Motor();
        if (rs.next()) {
            m.setKode(rs.getString(3));
            m.setJenis(rs.getString(4));
            m.setType(rs.getString(5));
            m.setWarna(rs.getString(6));
            m.setHarga(rs.getInt(7));
            m.setGambar(rs.getString(8));
            m.setStok(rs.getInt(9));
            m.setKeterangan(rs.getString(10));
            m.setStatus(rs.getString(11));
        }
        return m;
    }
}
