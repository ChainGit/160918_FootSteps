package com.tgweb.ssh.utils;

import org.apache.struts2.util.StrutsTypeConverter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class MyDateConverter extends StrutsTypeConverter {

    private DateFormat dateFormat;

    {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        Date date = null;
        if (toClass == Date.class) {
            try {
                java.util.Date d = (java.util.Date) dateFormat.parse(values[0]);
                date = new Date(d.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("convertFromString: " + date);
        return date;
    }

    @Override
    public String convertToString(Map context, Object o) {
        String date = null;
        if (o instanceof Date) {
            date = dateFormat.format((Date) o);
        }
        System.out.println("convertToString: " + date);
        return date;
    }
}
