/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.sch.smkn1kawali.penjualanmotor.utilitys;

import javax.swing.JOptionPane;

/**
 *
 * @author Acer One
 */
public class Messages {
    
    public static void setInfomationMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void setErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Peringatan", JOptionPane.ERROR_MESSAGE);
    }
    
    public static Boolean setConfirmMessage(String message) {
        Boolean pesan = JOptionPane.showConfirmDialog(null, message, "Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION;
        return pesan;
    }
    
    public static String setInputMessage(String title, String message) {
        return JOptionPane.showInputDialog(title, message);
    }
}
