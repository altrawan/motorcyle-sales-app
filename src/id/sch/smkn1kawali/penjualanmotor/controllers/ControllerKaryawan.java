/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.controllers;

import id.sch.smkn1kawali.penjualanmotor.utilitys.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import id.sch.smkn1kawali.penjualanmotor.interfaces.InterfaceKaryawan;
import id.sch.smkn1kawali.penjualanmotor.models.Karyawan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer One
 */
public class ControllerKaryawan implements InterfaceKaryawan {

    @Override
    public void createId(Karyawan k) throws SQLException {
        String sql = "SELECT kd_karyawan FROM t_karyawan ORDER BY kd_karyawan DESC";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String ambilKode = rs.getString("kd_karyawan");
                String sub = ambilKode.substring(1, 5);
                int counter = Integer.parseInt(sub) + 1;
                if (counter <= 9) {
                    k.setKode("K000" + Integer.toString(counter));
                } else if (counter <= 99) {
                    k.setKode("K00" + Integer.toString(counter));
                } else if (counter <=999) {
                    k.setKode("K0" + Integer.toString(counter));
                } else {
                    k.setKode("K" + Integer.toString(counter));
                }
            } else {
                k.setKode("K0001");
            }
        }
    }

    @Override
    public void insert(Karyawan k) throws SQLException {
        String sql = "CALL insert__Employee(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, k.getKode());
        ps.setString(2, k.getNik());
        ps.setString(3, k.getNama());
        ps.setString(4, k.getJk());
        ps.setString(5, k.getAlamat());
        ps.setString(6, k.getKk());
        ps.setString(7, k.getTelepon());
        ps.setString(8, k.getKeterangan());
        ps.setString(9, k.getStatus());
        ps.executeUpdate();
    }

    @Override
    public void update(Karyawan k) throws SQLException {
        String sql = "CALL update__Employee(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, k.getKode());
        ps.setString(2, k.getNik());
        ps.setString(3, k.getNama());
        ps.setString(4, k.getJk());
        ps.setString(5, k.getAlamat());
        ps.setString(6, k.getKk());
        ps.setString(7, k.getTelepon());
        ps.setString(8, k.getKeterangan());
        ps.setString(9, k.getStatus());
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "CALL delete__Employee(?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Karyawan> findData(String key) throws SQLException {
        String sql = "SELECT * FROM v_karyawan WHERE kd_karyawan LIKE '%" + key + "%'"
                + " OR nik LIKE '%" + key + "%'"
                + " OR nm_karyawan LIKE '%" + key + "%'"
                + " OR jns_kelamin LIKE '%" + key + "%'"
                + " OR almt_karyawan LIKE '%" + key + "%'"
                + " OR no_kk LIKE '%" + key + "%'"
                + " OR no_hp LIKE '%" + key + "%'"
                + " OR keterangan LIKE '%" + key + "%'"
                + " OR status LIKE '%" + key + "%'"
                + " OR created_at LIKE '%" + key + "%'";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Karyawan> list = new ArrayList<>();
        while (rs.next()) {
            Karyawan k = new Karyawan();
            k.setKode(rs.getString(1));
            k.setNik(rs.getString(2));
            k.setNama(rs.getString(3));
            k.setJk(rs.getString(4));
            k.setAlamat(rs.getString(5));
            k.setKk(rs.getString(6));
            k.setTelepon(rs.getString(7));
            k.setKeterangan(rs.getString(8));
            k.setStatus(rs.getString(9));
            k.setTanggal(rs.getString(10));
            list.add(k);
        }
        return list;
    }
    
    @Override
    public List<Karyawan> findAll(int halaman,int banyakBaris) throws SQLException {
        String sql = "SELECT * FROM v_karyawan limit ?,?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setInt(1, banyakBaris * (halaman - 1));
        ps.setInt(2, banyakBaris);
        ResultSet rs = ps.executeQuery();
        List<Karyawan> list = new ArrayList<>();
        while (rs.next()) {
            Karyawan k = new Karyawan();
            k.setKode(rs.getString(1));
            k.setNik(rs.getString(2));
            k.setNama(rs.getString(3));
            k.setJk(rs.getString(4));
            k.setAlamat(rs.getString(5));
            k.setKk(rs.getString(6));
            k.setTelepon(rs.getString(7));
            k.setKeterangan(rs.getString(8));
            k.setStatus(rs.getString(9));
            k.setTanggal(rs.getString(10));
            list.add(k);
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
    public List<Karyawan> fillEmployee() throws SQLException {
        String sql = "SELECT DISTINCT kd_karyawan FROM log__karyawan";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Karyawan> list = new ArrayList<>();
        while (rs.next()) {
            Karyawan k = new Karyawan();
            k.setKode(rs.getString("kd_karyawan"));
            list.add(k);
        }
        return list;
    }
    
    @Override
    public List<Karyawan> getLog(String id) throws SQLException {
        String sql = "SELECT * FROM log__karyawan WHERE kd_karyawan = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        List<Karyawan> list = new ArrayList<>();
        while (rs.next()) {
            Karyawan k = new Karyawan();
            k.setKode(rs.getString(1));
            k.setAksi(rs.getString(2));
            k.setTanggal(rs.getString(12));
            list.add(k);
        }
        return list;
    }
    
    @Override
    public Karyawan viewLog(String id) throws SQLException {
        String sql = "SELECT * FROM log__karyawan WHERE id = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Karyawan k = new Karyawan();
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
