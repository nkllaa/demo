package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String format(Date date,String str){
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        return sdf.format(date);
    }
    public static Date format(String str){
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

}
