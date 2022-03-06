/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.models.tables;

import id.sch.smkn1kawali.penjualanmotor.models.Pelanggan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Acer One
 */
public class TablePelanggan extends AbstractTableModel {
    List<Pelanggan> list = new ArrayList<>();
    
    private final String HEADER[] = { 
        "Kode Karyawan", "NIK", "Nama", "Jenis Kelamin", "Alamat", "No. KK",
        "No. HP", "Keterangan", "Status", "Tanggal"
    };
    
    public TablePelanggan(List<Pelanggan> list){
        this.list = list;
    }
    
    public void save(Pelanggan p){
        list.add(p);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    public void edit(int index, Pelanggan p){
        list.set(index, p);
        fireTableRowsUpdated(index, index);
    }
    
    public void delete(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public Pelanggan findOne(int index){
        return list.get(index);
    }
 
    @Override
    public int getRowCount() {
        return list.size();
    }
 
    @Override
    public int getColumnCount() {
        return HEADER.length;
    }
    
    @Override
    public String getColumnName(int column){
        return HEADER[column];
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pelanggan p = list.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return p.getKode();
            case 1:
                return p.getNik();    
            case 2:
                return p.getNama();
            case 3:
                return p.getJk();
            case 4:
                return p.getAlamat();
            case 5:
                return p.getKk();
            case 6:
                return p.getTelepon();
            case 7:
                return p.getKeterangan();
            case 8:
                return p.getStatus();
            case 9:
                return p.getTanggal();
            default:
                return null;
        }
    }
}
