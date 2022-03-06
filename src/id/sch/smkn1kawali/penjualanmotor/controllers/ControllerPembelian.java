/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.controllers;

import id.sch.smkn1kawali.penjualanmotor.interfaces.InterfacePembelian;
import id.sch.smkn1kawali.penjualanmotor.models.Motor;
import id.sch.smkn1kawali.penjualanmotor.models.Pembelian;
import id.sch.smkn1kawali.penjualanmotor.utilitys.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Acer One
 */
public class ControllerPembelian implements InterfacePembelian {
    
    @Override
    public List<Motor> getAll() throws SQLException {  
        String sql = "SELECT * FROM v_motor";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Motor> list = new ArrayList<>();
        while (rs.next()) {
            Motor m = new Motor();
            m.setKode(rs.getString(1));
            m.setType(rs.getString(3));
            m.setWarna(rs.getString(4));
            m.setHarga(rs.getInt(5));
            m.setStok(rs.getInt(7));
            m.setKeterangan(rs.getString(8));
            m.setStatus(rs.getString(9));
            list.add(m);
        }
        return list;
    }
    
    @Override
    public List<Motor> findData(String key) throws SQLException {
        String sql = "SELECT * FROM v_motor WHERE kd_motor LIKE '%" + key + "%'"
                + " OR type LIKE '%" + key + "%'"
                + " OR warna LIKE '%" + key + "%'"
                + " OR harga LIKE '%" + key + "%'"
                + " OR stok LIKE '%" + key + "%'"
                + " OR keterangan LIKE '%" + key + "%'"
                + " OR status LIKE '%" + key + "%'";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Motor> list = new ArrayList<>();
        while (rs.next()) {
            Motor m = new Motor();
            m.setKode(rs.getString(1));
            m.setType(rs.getString(3));
            m.setWarna(rs.getString(4));
            m.setHarga(rs.getInt(5));
            m.setStok(rs.getInt(7));
            m.setKeterangan(rs.getString(8));
            m.setStatus(rs.getString(9));
            list.add(m);
        }
        return list;
    }
    
    @Override
    public List<Pembelian> fillCustomer() throws SQLException {
        String sql = "SELECT * FROM t_pelanggan";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Pembelian> list = new ArrayList<>();
        while (rs.next()) {
            Pembelian p = new Pembelian();
            p.setKdPelanggan(rs.getString("kd_pelanggan"));
            p.setNama(rs.getString("nm_pelanggan"));
            list.add(p);
        }
        return list;
    }
    
    @Override
    public void createId(Pembelian p) throws SQLException {
        String sql = "SELECT kd_beli FROM t_beli ORDER BY kd_beli DESC";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String tgl = sdf.format(date);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String ambilKode = rs.getString("kd_beli");
                String sub = ambilKode.substring(10, 15);
                int counter = Integer.parseInt(sub) + 1;
                if (counter <= 9) {
                    p.setKode("PJ" + tgl + "0000" + Integer.toString(counter));
                } else if (counter <= 99) {
                    p.setKode("PJ" + tgl + "000" + Integer.toString(counter));
                } else if (counter <= 999) {
                    p.setKode("PJ" + tgl + "00" + Integer.toString(counter));
                } else if (counter <= 9999) {
                    p.setKode("PJ" + tgl + "0" + Integer.toString(counter));
                } else {
                    p.setKode("PJ" + tgl + Integer.toString(counter));
                }
            } else {
                p.setKode("PJ" + tgl + "00001");
            }
        }
    }

    @Override
    public void insertCash(Pembelian p) throws SQLException {
        String sql = "CALL insert__CashPurchase(?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, p.getKode());
        ps.setString(2, p.getKdUser());
        ps.setString(3, p.getKdPelanggan());
        ps.setInt(4, p.getDiskon());
        ps.setInt(5, p.getPajak());
        ps.executeUpdate();
    }
    
    @Override
    public void detailPurchase(Pembelian p) throws SQLException {
        String sql = "CALL insert__DetailPurchase(?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, p.getKode());
        ps.setString(2, p.getKdMotor());
        ps.setInt(3, p.getJumlah());
        ps.setInt(4, p.getTotal());
        ps.setInt(5, p.getSisa());
        ps.executeUpdate();
    }
    
    @Override
    public void insertCredit(Pembelian p) throws SQLException {
        String sql = "CALL insert__CreditPurchase(?,?,?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, p.getKode());
        ps.setString(2, p.getKdUser());
        ps.setString(3, p.getKdPelanggan());
        ps.setInt(4, p.getDiskon());
        ps.setInt(5, p.getPajak());
        ps.setInt(6, p.getBunga());
        ps.setInt(7, p.getCicilan());
        ps.executeUpdate();
    }
    
}
