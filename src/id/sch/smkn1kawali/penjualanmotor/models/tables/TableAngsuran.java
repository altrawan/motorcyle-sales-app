/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.models.tables;

import id.sch.smkn1kawali.penjualanmotor.models.Pembayaran;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Acer One
 */
public class TableAngsuran extends AbstractTableModel {
    List<Pembayaran> l;

    public TableAngsuran(List<Pembayaran> l) {
        this.l = l;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return l.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "No";
            case 1:
                return "Tanggal";
            case 2:
                return "Bayar";
            case 3:
                return "Jumlah";
            default:
                return null;
            //Kode Motor, Jenis, Type, Warna dan Harga
            //adalah nama untuk kolom header tabel
        }
    }
    
    int i = 1;
    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return i++;
            case 1:
                return l.get(row).getTanggal();
            case 2:
                return l.get(row).getCicilan() + " Bulan";
            case 3:
                return "Rp. " + l.get(row).getJumlah();
            default:
                return null;
        }
    }
}
