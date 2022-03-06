/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.models.tables;

import id.sch.smkn1kawali.penjualanmotor.models.Karyawan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Acer One
 */
public class TableKaryawan extends AbstractTableModel {
    List<Karyawan> list = new ArrayList<>();
    
    private final String HEADER[] = { 
        "Kode Karyawan", "NIK", "Nama", "Jenis Kelamin", "Alamat", "No. KK",
        "No. HP", "Keterangan", "Status", "Tanggal"
    };
    
    public TableKaryawan(List<Karyawan> list){
        this.list = list;
    }
    
    public void save(Karyawan k){
        list.add(k);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    public void edit(int index, Karyawan k){
        list.set(index, k);
        fireTableRowsUpdated(index, index);
    }
    
    public void delete(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public Karyawan findOne(int index){
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
        Karyawan k = list.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return k.getKode();
            case 1:
                return k.getNik();    
            case 2:
                return k.getNama();
            case 3:
                return k.getJk();
            case 4:
                return k.getAlamat();
            case 5:
                return k.getKk();
            case 6:
                return k.getTelepon();
            case 7:
                return k.getKeterangan();
            case 8:
                return k.getStatus();
            case 9:
                return k.getTanggal();
            default:
                return null;
        }
    }
}
