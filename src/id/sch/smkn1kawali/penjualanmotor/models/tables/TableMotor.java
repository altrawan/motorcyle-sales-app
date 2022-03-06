/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.models.tables;

import id.sch.smkn1kawali.penjualanmotor.models.Motor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Acer One
 */
public class TableMotor extends AbstractTableModel {
    List<Motor> list = new ArrayList<>();
    
    private final String HEADER[] = { 
        "Kode Motor", "Jenis", "Tipe", "Warna", "Harga", "Gambar", "Stok", "Keterangan", "Status", "Tanggal"
    };
    
    public TableMotor(List<Motor> list){
        this.list = list;
    }
    
    public void save(Motor m){
        list.add(m);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    public void edit(int index, Motor m){
        list.set(index, m);
        fireTableRowsUpdated(index, index);
    }
    
    public void delete(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public Motor findOne(int index){
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
        Motor k = list.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return k.getKode();
            case 1:
                return k.getJenis();    
            case 2:
                return k.getType();
            case 3:
                return k.getWarna();
            case 4:
                return k.getHarga();
            case 5:
                return k.getGambar();
            case 6:
                return k.getStok();
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
