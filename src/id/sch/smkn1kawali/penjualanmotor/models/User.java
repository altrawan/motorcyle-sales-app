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
public class User {
    private String kode;
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
    private String status;
    private String tanggal;
    private String aksi;
    private String pengirim;
    private String penerima;
    private String subjek;
    private String pesan;
    
    public String getKode() {
        return kode;
    }
    
    public void setKode(String kode) {
        this.kode = kode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getTanggal() {
        return tanggal;
    }
    
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
    public String getAksi() {
        return aksi;
    }
    
    public void setAksi(String aksi) {
        this.aksi = aksi;
    }
    
    public String getPengirim() {
        return penerima;
    }
    
    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }
    
    public String getPenerima() {
        return penerima;
    }
    
    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }
    
    public String getSubjek() {
        return subjek;
    }
    
    public void setSubjek(String subjek) {
        this.subjek = subjek;
    }
    
    public String getPesan() {
        return pesan;
    }
    
    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
