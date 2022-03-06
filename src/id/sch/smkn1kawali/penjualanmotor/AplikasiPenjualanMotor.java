/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor;

import id.sch.smkn1kawali.penjualanmotor.views.FrmLogin;
import id.sch.smkn1kawali.penjualanmotor.views.FrmSplashScreen;

/**
 *
 * @author Acer One
 */
public class AplikasiPenjualanMotor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new FrmLogin().show();
    }
    
    //SIMPAN QUERY
    //SELECT * FROM t_user WHERE (role = 'ADMIN' AND status NOT IN ('AKTIF')) OR role = "GUEST"
    //QUERY VALIDASI MENAMPILKAN USER DENGAN ROLE ADMIN 'TIDAK AKTIF' & GUEST
}
