package br.com.casadocodigo.loja.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class DataHelper {

    private GregorianCalendar calendar;
    private SimpleDateFormat formatter;
    private boolean dataValida;


    public DataHelper(String data) {
        try {
            this.dataValida = true;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            this.calendar = new GregorianCalendar();
            this.calendar.setTime(formatter.parse(data));
        } catch (ParseException e) {
            this.dataValida = false;
        }
    }

    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public boolean isDataValida() {
        return dataValida;
    }
}