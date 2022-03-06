/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.controllers;

import id.sch.smkn1kawali.penjualanmotor.interfaces.InterfacePelanggan;
import id.sch.smkn1kawali.penjualanmotor.models.Pelanggan;
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
public class ControllerPelanggan implements InterfacePelanggan {

    @Override
    public void createId(Pelanggan p) throws SQLException {
        String sql = "SELECT kd_pelanggan FROM t_pelanggan ORDER BY kd_pelanggan DESC";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String ambilKode = rs.getString("kd_pelanggan");
                String sub = ambilKode.substring(1, 5);
                int counter = Integer.parseInt(sub) + 1;
                if (counter <= 9) {
                    p.setKode("P000" + Integer.toString(counter));
                } else if (counter <= 99) {
                    p.setKode("P00" + Integer.toString(counter));
                } else if (counter <=999) {
                    p.setKode("P0" + Integer.toString(counter));
                } else {
                    p.setKode("P" + Integer.toString(counter));
                }
            } else {
                p.setKode("P0001");
            }
        }
    }

    @Override
    public void insert(Pelanggan p) throws SQLException {
        String sql = "CALL insert__Customer(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, p.getKode());
        ps.setString(2, p.getNik());
        ps.setString(3, p.getNama());
        ps.setString(4, p.getJk());
        ps.setString(5, p.getAlamat());
        ps.setString(6, p.getKk());
        ps.setString(7, p.getTelepon());
        ps.setString(8, p.getKeterangan());
        ps.setString(9, p.getStatus());
        ps.executeUpdate();
    }

    @Override
    public void update(Pelanggan p) throws SQLException {
        String sql = "CALL update__Customer(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, p.getKode());
        ps.setString(2, p.getNik());
        ps.setString(3, p.getNama());
        ps.setString(4, p.getJk());
        ps.setString(5, p.getAlamat());
        ps.setString(6, p.getKk());
        ps.setString(7, p.getTelepon());
        ps.setString(8, p.getKeterangan());
        ps.setString(9, p.getStatus());
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "CALL delete__Customer(?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Pelanggan> findData(String key) throws SQLException {
        String sql = "SELECT * FROM v_pelanggan WHERE kd_pelanggan LIKE '%" + key + "%'"
                + " OR nik LIKE '%" + key + "%'"
                + " OR nm_pelanggan LIKE '%" + key + "%'"
                + " OR jns_kelamin LIKE '%" + key + "%'"
                + " OR almt_pelanggan LIKE '%" + key + "%'"
                + " OR no_kk LIKE '%" + key + "%'"
                + " OR no_hp LIKE '%" + key + "%'"
                + " OR keterangan LIKE '%" + key + "%'"
                + " OR status LIKE '%" + key + "%'"
                + " OR created_at LIKE '%" + key + "%'";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Pelanggan> list = new ArrayList<>();
        while (rs.next()) {
            Pelanggan p = new Pelanggan();
            p.setKode(rs.getString(1));
            p.setNik(rs.getString(2));
            p.setNama(rs.getString(3));
            p.setJk(rs.getString(4));
            p.setAlamat(rs.getString(5));
            p.setKk(rs.getString(6));
            p.setTelepon(rs.getString(7));
            p.setKeterangan(rs.getString(8));
            p.setStatus(rs.getString(9));
            p.setTanggal(rs.getString(10));
            list.add(p);
        }
        return list;
    }
    
    @Override
    public List<Pelanggan> findAll(int halaman,int banyakBaris) throws SQLException {
        String sql = "SELECT * FROM v_pelanggan limit ?,?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setInt(1, banyakBaris * (halaman - 1));
        ps.setInt(2, banyakBaris);
        ResultSet rs = ps.executeQuery();
        List<Pelanggan> list = new ArrayList<>();
        while (rs.next()) {
            Pelanggan p = new Pelanggan();
            p.setKode(rs.getString(1));
            p.setNik(rs.getString(2));
            p.setNama(rs.getString(3));
            p.setJk(rs.getString(4));
            p.setAlamat(rs.getString(5));
            p.setKk(rs.getString(6));
            p.setTelepon(rs.getString(7));
            p.setKeterangan(rs.getString(8));
            p.setStatus(rs.getString(9));
            p.setTanggal(rs.getString(10));
            list.add(p);
        }
        return list;
    }
    
    @Override
    public int count() throws SQLException {
        int jumlahBaris = 0;
        String sql = "SELECT count(kd_pelanggan) from v_pelanggan";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            jumlahBaris = rs.getInt(1);
        }
        return jumlahBaris;
    }
    
    @Override
    public List<Pelanggan> fillCustomer() throws SQLException {
        String sql = "SELECT DISTINCT kd_pelanggan FROM log__pelanggan";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Pelanggan> list = new ArrayList<>();
        while (rs.next()) {
            Pelanggan p = new Pelanggan();
            p.setKode(rs.getString("kd_pelanggan"));
            list.add(p);
        }
        return list;
    }
    
    @Override
    public List<Pelanggan> getLog(String id) throws SQLException {
        String sql = "SELECT * FROM log__pelanggan WHERE kd_pelanggan = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        List<Pelanggan> list = new ArrayList<>();
        while (rs.next()) {
            Pelanggan p = new Pelanggan();
            p.setKode(rs.getString(1));
            p.setAksi(rs.getString(2));
            p.setTanggal(rs.getString(12));
            list.add(p);
        }
        return list;
    }
    
    @Override
    public Pelanggan viewLog(String id) throws SQLException {
        String sql = "SELECT * FROM log__pelanggan WHERE id = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Pelanggan k = new Pelanggan();
        if (rs.next()) {
            k.setKode(rs.getString(3));
            k.setNik(rs.getString(4));
            k.setNama(rs.getString(5));
            k.setJk(rs.getString(6));
            k.setAlamat(rs.getString(7));
            k.setKk(rs.getString(8));
            k.setTelepon(rs.getString(9));
            k.setKeterangan(rs.getString(10));
            k.setStatus(rs.getString(11));
        }
        return k;
    }
    
}
