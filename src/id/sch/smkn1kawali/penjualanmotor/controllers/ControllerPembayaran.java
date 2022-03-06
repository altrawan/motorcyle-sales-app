/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.controllers;

import id.sch.smkn1kawali.penjualanmotor.interfaces.InterfacePembayaran;
import id.sch.smkn1kawali.penjualanmotor.models.Pembayaran;
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
public class ControllerPembayaran implements InterfacePembayaran {

    @Override
    public Pembayaran getDataById(String id) throws SQLException {
        String sql = "SELECT t_bayar.kd_bayar, t_bayar.kd_beli, t_beli.diskon, t_beli.pajak, "
                + "t_bayar.total, t_beli.keterangan, t_beli.tipe, t_bayar.sisa, t_beli.angsuran, "
                + "t_beli.lama_cicilan FROM t_bayar, t_beli WHERE t_bayar.kd_beli = t_beli.kd_beli "
                + "AND t_bayar.kd_beli = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Pembayaran p = new Pembayaran();
        if (rs.next()) {
            p.setId(rs.getInt(1));
            p.setKode(rs.getString(2));
            p.setDiskon(rs.getInt(3));
            p.setPajak(rs.getInt(4));
            p.setTotal(rs.getInt(5));
            p.setKeterangan(rs.getString(6));
            p.setTipe(rs.getString(7));
            p.setSisa(rs.getInt(8));
            p.setAngsuran(rs.getInt(9));
            p.setCicilan(rs.getInt(10));
        }
        return p;
    }

    @Override
    public List<Pembayaran> getData(String id) throws SQLException {
        String sql = "SELECT t_motor.type, t_motor.warna, t_motor.harga, t_bayar.qty FROM t_bayar, t_motor WHERE t_bayar.kd_motor = t_motor.kd_motor AND t_bayar.kd_beli = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        List<Pembayaran> list = new ArrayList<>();
        while (rs.next()) {
            Pembayaran p = new Pembayaran();
            p.setTipe(rs.getString(1));
            p.setWarna(rs.getString(2));
            p.setHarga(rs.getInt(3));
            p.setJumlah(rs.getInt(4));
            list.add(p);
        }
        return list;
    }
    
    @Override
    public void updateDetail(Pembayaran p) throws SQLException {
        String sql = "CALL update__DetailPurchase(?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setInt(1, p.getId());
        ps.setInt(2, p.getBayar());
        ps.setInt(3, p.getKembalian());
        ps.setInt(4, p.getSisa());
        ps.executeUpdate();
    }
    
    @Override
    public void statusCash(Pembayaran p) throws SQLException {
        String sql = "CALL update__CashStatus(?)";
        PreparedStatement ps = Database.getConnection().prepareCall(sql);
        ps.setString(1, p.getKode());
        ps.executeUpdate();
    }
    
    @Override
    public void statusCredit(Pembayaran p) throws SQLException {
        String sql = "CALL update__CreditStatus(?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareCall(sql);
        ps.setString(1, p.getKode());
        ps.setInt(2, p.getBayar());
        ps.setInt(3, p.getAngsuran());
        ps.setString(4, p.getKeterangan());
        ps.executeUpdate();
    }
    
    @Override
    public Pembayaran getDetailById(Integer id) throws SQLException {
        String sql = "SELECT * FROM v_detail WHERE kd_bayar = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Pembayaran p = new Pembayaran();
        if (rs.next()) {
            p.setId(rs.getInt(1));
            p.setNama(rs.getString(2));
            p.setNik(rs.getString(3));
            p.setAlamat(rs.getString(4));
            p.setTipe(rs.getString(5));
            p.setWarna(rs.getString(6));
            p.setHarga(rs.getInt(7));
            p.setKode(rs.getString(8));
            p.setUang(rs.getInt(9));
            p.setDiskon(rs.getInt(10));
            p.setPajak(rs.getInt(11));
            p.setBunga(rs.getInt(12));
            p.setCicilan(rs.getInt(13));
            p.setAngsuran(rs.getInt(14));
        }
        return p;
    }
    
    @Override
    public Pembayaran getSale(String id) throws SQLException {
        String sql = "SELECT bayar FROM t_bayar WHERE kd_beli = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Pembayaran p = new Pembayaran();
        if (rs.next()) {
            p.setBayar(rs.getInt(1));
        }
        return p;
    }
    
    @Override
    public List<Pembayaran> getAngsuran(String id) throws SQLException {
        String sql = "SELECT tanggal, cicilan, jumlah FROM t_angsuran WHERE kd_beli = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        List<Pembayaran> list = new ArrayList<>();
        while (rs.next()) {
            Pembayaran p = new Pembayaran();
            p.setTanggal(rs.getString(1));
            p.setCicilan(rs.getInt(2));
            p.setJumlah(rs.getInt(3));
            list.add(p);
        }
        return list;
    }
    
    @Override
    public void insertAngsuran(Pembayaran p) throws SQLException {
        String sql = "CALL insert__Angsuran(?,?,?,?,?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, p.getKode());
        ps.setInt(2, p.getCicilan());
        ps.setInt(3, p.getJumlah());
        ps.setInt(4, p.getBayar());
        ps.setInt(5, p.getKembalian());
        ps.executeUpdate();
    }
    
    @Override
    public void update(Pembayaran p) throws SQLException {
        String sql = "UPDATE t_bayar SET sisa=? WHERE kd_beli=?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setInt(1, p.getSisa());
        ps.setString(2, p.getKode());
        ps.executeUpdate();
    }
    
    @Override
    public void status(Pembayaran p) throws SQLException {
        String sql = "UPDATE t_beli SET kembalian='LUNAS' WHERE kd_beli=?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, p.getKode());
        ps.executeUpdate();
    }
    
    @Override
    public Pembayaran getSum(String id) throws SQLException {
        String sql = "SELECT SUM(cicilan) AS sisa FROM t_angsuran WHERE kd_beli=?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Pembayaran a = new Pembayaran();
        if (rs.next()) {
            a.setCicilan(rs.getInt(1));
        }
        return a;
    }

//    @Override
//    public void insert(Pembayaran a) throws SQLException {
//        String sql = "CALL insert__DetailKredit(?,?,?,?)";
//        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
//        ps.setString(1, a.getKdKredit());
//        ps.setString(2, a.getTanggal());
//        ps.setInt(3, a.getBayar());
//        ps.setInt(4, a.getJumlah());
//        ps.executeUpdate();
//    }
//    
//    @Override
//    public void update(Pembayaran a) throws SQLException {
//        String sql = "UPDATE t_belikredit SET sisa=? WHERE kd_belikredit=?";
//        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
//        ps.setInt(1, a.getSisa());
//        ps.setString(2, a.getKdKredit());
//        ps.executeUpdate();
//    }
//    
//    @Override
//    public void changeStatus(Pembayaran a) throws SQLException {
//        String sql = "UPDATE t_belikredit SET sisa=?, keterangan='LUNAS' WHERE kd_belikredit=?";
//        PreparedStatement ps = Database.getConnection().prepareCall(sql);
//        ps.setInt(1, a.getSisa());
//        ps.setString(2, a.getKdKredit());
//        ps.executeUpdate();
//    }
//    
//    @Override
//    public Pembayaran getSum(String id) throws SQLException {
//        String sql = "SELECT SUM(bayar) AS sisa FROM t_angsuran WHERE kd_belikredit=?";
//        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
//        ps.setString(1, id);
//        ResultSet rs = ps.executeQuery();
//        Pembayaran a = new Pembayaran();
//        if (rs.next()) {
//            a.setBayar(rs.getInt(1));
//        }
//        return a;
//    }
    
}
