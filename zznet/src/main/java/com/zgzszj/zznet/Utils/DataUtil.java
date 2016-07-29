package com.zgzszj.zznet.Utils;



import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tony on 2016/7/29.
 */
public class DataUtil{
        /**
         *  获取当前时间
         */
        public static String getCurrentDate(){
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            return sDateFormat.format(date);
        }

        /**
         *  获取当前时间
         */
        public static String getSpecialDate(){
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
            Date date = new Date();
            return sDateFormat.format(date);
        }
}
