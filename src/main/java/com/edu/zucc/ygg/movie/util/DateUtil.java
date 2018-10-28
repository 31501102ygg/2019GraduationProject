package com.edu.zucc.ygg.movie.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getNowDate(){
        Date date = new Date();
        return DateFormat.getDateInstance().format(date);
    }

    public static String getNowTime(){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        Date d= new Date();
        String str = sdf.format(d);
        return str;
    }

}
