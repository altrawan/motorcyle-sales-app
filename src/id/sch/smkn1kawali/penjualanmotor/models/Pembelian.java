/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.models;

/**
 *
 * @author Acer One
 */
public class Pembelian {
    private String kode;
    private String tanggal;
    private String kd_user;
    private String kd_pelanggan;
    private String nama;
    private String kd_motor;
    private String tipe;
    private Integer diskon;
    private Integer pajak;
    private Integer bunga;
    private Integer cicilan;
    private Integer total;
    private String keterangan;
    private Integer jumlah;
    private Integer sisa;
    
    public String getKode() {
        return kode;
    }
    
    public void setKode(String kode) {
        this.kode = kode;
    }
    
    public String getTanggal() {
        return tanggal;
    }
    
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
    public String getKdPelanggan() {
        return kd_pelanggan;
    }
    
    public void setKdPelanggan(String kd_pelanggan) {
        this.kd_pelanggan = kd_pelanggan;
    }
    
    public String getKdUser() {
        return kd_user;
    }
    
    public void setKdUser(String kd_user) {
        this.kd_user = kd_user;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getKdMotor() {
        return kd_motor;
    }
    
    public void setKdMotor(String kd_motor) {
        this.kd_motor = kd_motor;
    }
    
    public String getTipe() {
        return tipe;
    }
    
    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
    
    public Integer getDiskon() {
        return diskon;
    }
    
    public void setDiskon(Integer diskon) {
        this.diskon = diskon;
    }
    
    public Integer getPajak() {
        return pajak;
    }
    
    public void setPajak(Integer pajak) {
        this.pajak = pajak;
    }
    
    public Integer getBunga() {
        return bunga;
    }
    
    public void setBunga(Integer bunga) {
        this.bunga = bunga;
    }
    
    public Integer getCicilan() {
        return cicilan;
    }
    
    public void setCicilan(Integer cicilan) {
        this.cicilan = cicilan;
    }
    
    public Integer getTotal() {
        return total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
    
    public String getKeterangan() {
        return keterangan;
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    public Integer getJumlah() {
        return jumlah;
    }
    
    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
    
    public Integer getSisa() {
        return sisa;
    }
    
    public void setSisa(Integer sisa) {
        this.sisa = sisa;
    }
    
}
