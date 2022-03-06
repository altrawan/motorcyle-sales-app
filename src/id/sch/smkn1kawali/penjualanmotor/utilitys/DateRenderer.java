/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.smkn1kawali.penjualanmotor.utilitys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Acer One
 */
public class DateRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;
    private Date dateValue;
    private SimpleDateFormat sdfNewValue;
    private String valueToString = "";
    Locale lokal = new Locale("id", "ID", "id-ID");

    public DateRenderer() {
        this.sdfNewValue = new SimpleDateFormat("EEEE, dd MMMM yyyy", lokal);
    }

    @Override
    public void setValue(Object value) {
        
        if ((value != null)) {
            String stringFormat = value.toString();
            try {
                dateValue = new SimpleDateFormat("yyyy-mm-dd", lokal).parse(stringFormat);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            valueToString = sdfNewValue.format(dateValue);
            value = valueToString;
        }
        super.setValue(value);
    }
}
