/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.utilitys;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Acer One
 */
public class Database {
    public static Connection koneksi;
    public static Statement stm;
    public static Connection getConnection() {
        if (koneksi == null) {
            try {
                MysqlDataSource db = new MysqlDataSource();
                db.setDatabaseName("db_penjualan_motor");
                db.setUser("root");
                db.setPassword("");
                koneksi = db.getConnection();
            }
            catch (SQLException e) {
                System.out.println("Error #1 : " + e.getMessage());
            }
        }
        return koneksi;
    }
}
