/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.models.tables;

import id.sch.smkn1kawali.penjualanmotor.models.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Acer One
 */
public class TableUser extends AbstractTableModel {
    List<User> list = new ArrayList<>();
    
    private final String HEADER[] = { 
        "Kode User", "Nama", "Username", "Email", "Role", "Status", "Tanggal"
    };
    
    public TableUser(List<User> list){
        this.list = list;
    }
    
    public void save(User u){
        list.add(u);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    public void edit(int index, User u){
        list.set(index, u);
        fireTableRowsUpdated(index, index);
    }
    
    public void delete(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public User findOne(int index){
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
        User k = list.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return k.getKode();
            case 1:
                return k.getName();    
            case 2:
                return k.getUsername();
            case 3:
                return k.getEmail();
            case 4:
                return k.getRole();
            case 5:
                return k.getStatus();
            case 6:
                return k.getTanggal();
            default:
                return null;
        }
    } 
}
