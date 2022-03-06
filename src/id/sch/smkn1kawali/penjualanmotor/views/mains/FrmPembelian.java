/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.views.mains;

import id.sch.smkn1kawali.penjualanmotor.controllers.ControllerPembelian;
import id.sch.smkn1kawali.penjualanmotor.interfaces.InterfacePembelian;
import id.sch.smkn1kawali.penjualanmotor.models.Motor;
import id.sch.smkn1kawali.penjualanmotor.models.Pembelian;
import id.sch.smkn1kawali.penjualanmotor.models.tables.TableMotor;
import id.sch.smkn1kawali.penjualanmotor.utilitys.DateRenderer;
import id.sch.smkn1kawali.penjualanmotor.utilitys.Messages;
import id.sch.smkn1kawali.penjualanmotor.views.FrmMenuUtama;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Acer One
 */
public class FrmPembelian extends javax.swing.JInternalFrame {
    InterfacePembelian i;
    List<Motor> l = new ArrayList<>();
    List<Pembelian> list = new ArrayList<>();
    Boolean tabel = false;
    Boolean edit = false;
    String id, kode, status;
    int jumlah, tampung, bunga;
    
    /**
     * Creates new form FrmPembelian
     */
    public FrmPembelian() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        i = new ControllerPembelian();
    }
    
    //coding menampilkan data ke dalam tabel motor di form
    private void tampildata() {
        try {
            l = i.getAll();
            Object data[][] = new Object[l.size()][7];
            int x = 0;
            for (Motor m : l) {
                data[x][0] = m.getKode();
                data[x][1] = m.getType();
                data[x][2] = m.getWarna();
                data[x][3] = m.getHarga();
                data[x][4] = m.getStok();
                data[x][5] = m.getStatus();
                data[x][6] = m.getKeterangan();
                x++;
            }
            String judul[] = {"Kode", "Tipe", "Warna", "Harga", "Stok", "Status", "Keterangan"};
            tblMotor.setModel(new DefaultTableModel(data, judul));
            jScrollPane1.setViewportView(tblMotor);
        } catch (SQLException e) {
            Messages.setErrorMessage("Ada kesalahan dalam SQL Statement..!");
        }
    }
    
    //Mengambil kode pelanggan, disimpan pada jComboBox kd_pelanggan
    private void ambil_pelanggan() {
        try {
            list = i.fillCustomer();
            for (Pembelian p : list) {
                jComboBox1.addItem(p.getKdPelanggan() + " - " + p.getNama());
            }
        } catch (SQLException ex) {
            Messages.setErrorMessage("Ada kesalahan dalam SQL statement..!");
        }
    }
    
    private void addRow() {
        DefaultTableModel model = (DefaultTableModel) tblMotor.getModel();
        DefaultTableModel model2 = (DefaultTableModel) tblKeranjang.getModel();
        int index[] = tblMotor.getSelectedRows();
        Object[] row = new Object[4];
        int qty = 1;
        //kode1 = tblMotor.getValueAt(tblMotor.getSelectedRow(), 0).toString();
        for (int i = 0; i < index.length; i++) {       
            int a = Integer.parseInt(model.getValueAt(index[i], 4).toString());
            if (a > 0) {
                row[0] = model.getValueAt(index[i], 0);
                row[1] = model.getValueAt(index[i], 1);
                row[2] = model.getValueAt(index[i], 3);
                row[3] = qty;   
                model2.addRow(row);
                        
                for (int j = 1; j > 0; j--) {
                    model.setValueAt(a - 1, index[i], 4);
                }
                
                for (int k = 0; k < tblKeranjang.getRowCount(); k++) {
                    kode = tblKeranjang.getValueAt(k, 0).toString();
                    
                    //stok = Integer.parseInt(tblKeranjang.getValueAt(k, 3).toString());
                    
                }
            } else {
                Messages.setErrorMessage("Stok sudah habis mohon hubungi admin");
            }
        }
    }
    
    private void bersih() {
        buatnomor();
        DefaultTableModel model = (DefaultTableModel) tblKeranjang.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        buttonGroup1.clearSelection();
        jComboBox1.setSelectedIndex(0);
        jLabel7.setText("Rp. 0");
        sDiskon.setValue(0);
        sPajak.setValue(0);
        bunga = 0;
    }
    
    private void buatnomor() {
        try {
            Pembelian p = new Pembelian();
            i.createId(p);
            id = p.getKode();
        } catch (SQLException ex) {
            Messages.setErrorMessage("Ada kesalahan dalam SQL statement..!");
        }
    }
    
    private void setEditOff() {
        jLabel15.setVisible(false);
        jComboBox2.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMotor = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKeranjang = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        sDiskon = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        sPajak = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnTambah = new javax.swing.JButton();
        btnKurang = new javax.swing.JButton();
        btnProses = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Daftar Motor");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("PEMBELIAN");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Search :");

        tCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCariKeyReleased(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);

        tblMotor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblMotor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblMotor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMotorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMotor);

        jScrollPane2.setAutoscrolls(true);

        tblKeranjang.setAutoCreateRowSorter(true);
        tblKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Type", "Harga", "Jumlah"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKeranjang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblKeranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKeranjangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKeranjang);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Keranjang Belanja");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Tipe Pembayaran");

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton1.setText("Tunai");
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton2.setText("Kredit");
        jRadioButton2.setToolTipText("Pembelian Kredit Maksimal 1 Motor Saja");
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2))
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Total Harga");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Rp. 0");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Kode Pelanggan");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Diskon");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-" }));

        sDiskon.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        sDiskon.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sDiskonStateChanged(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setText("%");

        sPajak.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        sPajak.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sPajakStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel13.setText("%");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Pajak");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Lama Cicilan");

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "12 Bulan", "24 Bulan", "36 Bulan" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 149, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sPajak, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(sDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(sPajak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnTambah.setBackground(new java.awt.Color(42, 39, 41));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("+");
        btnTambah.setBorder(null);
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnKurang.setBackground(new java.awt.Color(42, 39, 41));
        btnKurang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKurang.setForeground(new java.awt.Color(255, 255, 255));
        btnKurang.setText("-");
        btnKurang.setBorder(null);
        btnKurang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurangActionPerformed(evt);
            }
        });

        btnProses.setBackground(new java.awt.Color(42, 39, 41));
        btnProses.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProses.setForeground(new java.awt.Color(255, 255, 255));
        btnProses.setText("Proses");
        btnProses.setBorder(null);
        btnProses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addGap(455, 455, 455))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnKurang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(179, 179, 179))
                        .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKurang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        tampildata();
        bersih();
        ambil_pelanggan();
        setEditOff();
    }//GEN-LAST:event_formInternalFrameOpened

    private void tCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCariKeyReleased
        // TODO add your handling code here:
        try {
            l = i.findData(tCari.getText());
            Object data[][] = new Object[l.size()][7];
            int x = 0;
            for (Motor m : l) {
                data[x][0] = m.getKode();
                data[x][1] = m.getType();
                data[x][2] = m.getWarna();
                data[x][3] = m.getHarga();
                data[x][4] = m.getStok();
                data[x][5] = m.getStatus();
                data[x][6] = m.getKeterangan();
                x++;
            }
            String judul[] = {"Kode", "Tipe", "Warna", "Harga", "Stok", "Status", "Keterangan"};
            tblMotor.setModel(new DefaultTableModel(data, judul));
            jScrollPane1.setViewportView(tblMotor);;
        } catch (SQLException ex) {
            Logger.getLogger(FrmDataKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tCariKeyReleased

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton1.isSelected()) {
            setEditOff();
        }
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton2.isSelected()) {
            jLabel15.setVisible(true);
            jComboBox2.setVisible(true);
        }
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        if (tabel == false) {
            Messages.setErrorMessage("Pilih data dari tabel motor terlebih dahulu..!");
        } else if (status.equals("TIDAK AKTIF")) {
            Messages.setErrorMessage("Data Motor tersebut sudah tidak aktif");
        }  
        else {
            try {
                if (tblKeranjang.getRowCount() == 0) {
                    addRow();
                } else {
//                    if (tblKeranjang.getRowCount() == 1 || Integer.parseInt(tblKeranjang.getModel().getValueAt(0, 3).toString()) >= 1) {
//                        jRadioButton2.setEnabled(false);
//                    } else {
//                        jRadioButton2.setEnabled(true);
//                    }
                    String row = tblMotor.getValueAt(tblMotor.getSelectedRow(), 0).toString();
                    List<Object> li = new ArrayList<>();
                    for (int k = 0; k < tblKeranjang.getRowCount(); k++) { 
                        li.add(tblKeranjang.getValueAt(k, 0).toString());
                    }
                    Object[] coba3 = li.toArray();
                    boolean contains = Arrays.stream(coba3).anyMatch(row::equals);
                    if (contains == false) {
                        addRow();  
                    }
                    else {
                        DefaultTableModel model1 = (DefaultTableModel) tblMotor.getModel();
                        DefaultTableModel model2 = (DefaultTableModel) tblKeranjang.getModel();
                        int b = tblMotor.getSelectedRow();
                        int c = Integer.parseInt(tblMotor.getValueAt(b, 4).toString());
                        //System.out.println(kode + row);
                        
                        if (c > 0) {
                            if (kode.equals(row)) {
                                for (int j = 1; j > 0; j--) {
                                    model1.setValueAt(c - 1, b, 4);
                                }
                                int i = 0;
                                int result = 0;
                                for (i = 0; i < tblKeranjang.getRowCount(); i++) {
                                    result = i;
                                }
                                int a = Integer.parseInt(tblKeranjang.getValueAt(lastDigit(result), 3).toString());
                                model2.setValueAt(a + 1, lastDigit(result), 3);
                            } else {
                                String ambilKode = tblKeranjang.getModel().getValueAt(0, 0).toString();
                                if (ambilKode.equals(row)) {
                                    for (int j = 1; j > 0; j--) {
                                        model1.setValueAt(c - 1, b, 4);
                                    }
                                    int i = 0;
                                    int a = Integer.parseInt(tblKeranjang.getValueAt(i, 3).toString());
                                    model2.setValueAt(a + 1, i, 3);
                                } else {
                                    for (int j = 1; j > 0; j--) {
                                        model1.setValueAt(c - 1, b, 4);
                                    }
                                    int i = 0;
                                    int result = 0;
                                    for (i = 0; i < tblKeranjang.getRowCount(); i++) {
                                        result = i;
                                    }
                                    result -= 1;
                                    int a = Integer.parseInt(tblKeranjang.getValueAt(lastDigit(result), 3).toString());
                                    model2.setValueAt(a + 1, lastDigit(result), 3);
                                }
                                
                            }
                            //System.out.println();
                        } else {
                            Messages.setErrorMessage("Stok sudah habis mohon hubungi admin");
                        }
                    }
                    //Messages.setErrorMessage("Maksimal pembelian motor hanya 1");
                }
                int total = 0;
                for (int i = 0; i < tblKeranjang.getRowCount(); i++) {
                    int harga = Integer.parseInt(tblKeranjang.getValueAt(i, 2).toString());
                    int qty = Integer.parseInt(tblKeranjang.getValueAt(i, 3).toString());
                    total += harga * qty;
                }
                tampung = total;
                jLabel7.setText("Rp. " + Integer.toString(total));
            } catch (ArrayIndexOutOfBoundsException a) {
            }
        }
    }//GEN-LAST:event_btnTambahActionPerformed
    
    private static int lastDigit(int n) {
        return (n % 1000);
    }
    
    private void tblMotorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMotorMouseClicked
        // TODO add your handling code here:
        tabel = true;
        status = tblMotor.getValueAt(tblMotor.getSelectedRow(), 5).toString();
    }//GEN-LAST:event_tblMotorMouseClicked

    private void btnKurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurangActionPerformed
        // TODO add your handling code here:
        if (edit == false) {
            Messages.setErrorMessage("Pilih data dari tabel keranjang terlebih dahulu..!");
        } else {
            
            try {
            DefaultTableModel t = (DefaultTableModel) tblKeranjang.getModel();
            int getSelectedRowForDeletion = tblKeranjang.getSelectedRow();
            
            DefaultTableModel model = (DefaultTableModel) tblMotor.getModel();
            int index[] = tblMotor.getSelectedRows();
            String x = tblMotor.getValueAt(tblMotor.getSelectedRow(), 0).toString();
            String y = tblKeranjang.getValueAt(tblKeranjang.getSelectedRow(), 0).toString();
            int w = Integer.parseInt(t.getValueAt(getSelectedRowForDeletion, 3).toString());
            
            int total = 0;
            int selectedRow = tblKeranjang.getSelectedRow();
            int qty = Integer.parseInt(t.getValueAt(selectedRow, 3).toString());
            for (int h = qty; h > 0; h--) {
                int harga = Integer.parseInt(t.getValueAt(selectedRow, 2).toString());
                int bayar = Integer.parseInt(jLabel7.getText().substring(4));
                total = bayar - harga;
            }
            jLabel7.setText("Rp. " + total);
            
            
            for (int i = 0; i < index.length; i++) {
                int a = Integer.parseInt(model.getValueAt(index[i], 4).toString());
                if (x.equals(y)) {
//                    if (tblKeranjang.getRowCount() == 1 || Integer.parseInt(tblKeranjang.getModel().getValueAt(0, 3).toString()) >= 1) {
//                        jRadioButton2.setEnabled(false);
//                    } else {
//                        jRadioButton2.setEnabled(true);
//                    }
                    int z = Integer.parseInt(t.getValueAt(tblKeranjang.getSelectedRow(), 3).toString());
                    if (z == 1) {
                        
                        for (int j = 1; j > 0; j--) {
                            model.setValueAt(a + 1, index[i], 4);
                        }
                        t.removeRow(getSelectedRowForDeletion);
                    } else {
                        for (int j = 1; j > 0; j--) {
                            model.setValueAt(a + 1, index[i], 4);
                        }
                        
                        int c = Integer.parseInt(tblKeranjang.getValueAt(tblKeranjang.getSelectedRow(), 3).toString());
                        t.setValueAt(c - 1, tblKeranjang.getSelectedRow(), 3);
                    }
                } else {
                    Messages.setErrorMessage("Untuk mengembalikan stok mohon klik kedua table dengan kode motor yang sama");
                }
            } 
            
            } catch (ArrayIndexOutOfBoundsException a) {
            }
        }
    }//GEN-LAST:event_btnKurangActionPerformed

    private void tblKeranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKeranjangMouseClicked
        // TODO add your handling code here:
        edit = true;
        
    }//GEN-LAST:event_tblKeranjangMouseClicked

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        // TODO add your handling code here:
        if (tblKeranjang.getRowCount() == 0 || jRadioButton1.isSelected() == false
                && jRadioButton2.isSelected() == false || jComboBox1.getSelectedIndex() == 0) {
            Messages.setErrorMessage("Lengkapi data terlebih dahulu..!");
        } else {
            try {
                if (jRadioButton1.isSelected()) {
                    Pembelian p = new Pembelian();
                    p.setKode(id);
                    p.setKdPelanggan(jComboBox1.getSelectedItem().toString().substring(0, 5));
                    p.setKdUser(FrmMenuUtama.id);
                    p.setDiskon((Integer) sDiskon.getValue());
                    p.setPajak((Integer) sPajak.getValue());
                    i.insertCash(p);
                    //===========================
                    Pembelian bayar = new Pembelian();
                    for (int r = 0; r < tblKeranjang.getRowCount(); r++) {
                        bayar.setKode(id);
                        String a = tblKeranjang.getValueAt(r, 0).toString();
                        bayar.setKdMotor(a);
                        int b = Integer.parseInt(tblKeranjang.getValueAt(r, 3).toString());
                        bayar.setJumlah(b);
                        int total = Integer.valueOf(jLabel7.getText().substring(4));
                        bayar.setTotal(total);
                        bayar.setSisa(total);
                        i.detailPurchase(bayar);
                    }
                    Messages.setInfomationMessage("Data telah berhasil disimpan\nSilahkan lanjutkan ke proses pembayaran");
                    Messages.setInputMessage("Kode Pembeliann :", id);
                    bersih();
                    setEditOff();
                } else {
                    if (jComboBox2.getSelectedIndex() == 0) {
                        Messages.setErrorMessage("Masukan lama cicilan terlebih dahulu");
                    } else {
                        Pembelian p = new Pembelian();
                        p.setKode(id);
                        p.setKdPelanggan(jComboBox1.getSelectedItem().toString().substring(0, 5));
                        p.setKdUser(FrmMenuUtama.id);
                        p.setDiskon((Integer) sDiskon.getValue());
                        p.setPajak((Integer) sPajak.getValue());
                        int cicilan = Integer.parseInt(jComboBox2.getSelectedItem().toString().substring(0, 2));
                        p.setBunga(bunga);
                        p.setCicilan(cicilan);
                        i.insertCredit(p);
                        //===========================
                        p.setKdMotor(kode);
                        p.setJumlah(jumlah);
                        int total = Integer.valueOf(jLabel7.getText().substring(4));
                        p.setTotal(total);
                        p.setSisa(total);
                        i.detailPurchase(p);
                        Messages.setInfomationMessage("Data telah berhasil disimpan\nSilahkan lanjutkan ke proses pembayaran");
                        Messages.setInputMessage("Kode Pembeliann :", id);
                        bersih();
                        setEditOff();
                    }
                    
                }
            } catch (NumberFormatException | SQLException ex) {
                    Logger.getLogger(FrmPembelian.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnProsesActionPerformed

    private void sDiskonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sDiskonStateChanged
        // TODO add your handling code here:
        if (tblKeranjang.getRowCount() == 0) {
            Messages.setErrorMessage("Pilih terlebih dahulu motor yang akan dibeli");
            sDiskon.setValue(0);
        } else {
            int diskon = (int) sDiskon.getValue();
            int calc = (diskon * tampung) / 100;
            int hasil = tampung - calc;
            jLabel7.setText("Rp. " + hasil);
        }
    }//GEN-LAST:event_sDiskonStateChanged

    private void sPajakStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sPajakStateChanged
        // TODO add your handling code here:
        if (tblKeranjang.getRowCount() == 0) {
            Messages.setErrorMessage("Pilih terlebih dahulu motor yang akan dibeli");
            sPajak.setValue(0);
        } else {
            int pajak = (int) sPajak.getValue();
            int calc = (pajak * tampung) / 100;
            int hasil = tampung + calc;
            jLabel7.setText("Rp. " + hasil);
        }
    }//GEN-LAST:event_sPajakStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        int index = jComboBox2.getSelectedIndex();
        switch (index) {
            case 1:
                bunga = 2;
                break;
            case 2:
                bunga = 4;
                break;
            case 3:
                bunga = 6;
                break;
            default:
                bunga = 0;
                break;
        }
        Integer calc = ((tampung * bunga) / 100) * 12;
        Integer hasil = tampung + calc;
        jLabel7.setText("Rp. " + hasil);
    }//GEN-LAST:event_jComboBox2ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKurang;
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnTambah;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner sDiskon;
    private javax.swing.JSpinner sPajak;
    private javax.swing.JTextField tCari;
    private javax.swing.JTable tblKeranjang;
    private javax.swing.JTable tblMotor;
    // End of variables declaration//GEN-END:variables
}
