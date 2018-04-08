package com.carryless.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private DateUtils(){}

    /**
     * 根据传入的时间格式（eg:"yyyy-MM-dd"）,得到对应格式的当天时间
     * @param fromatType SimpleDateFormate的formate类型
     * @return
     */
    public static String getToday(String fromatType){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(fromatType);
        return sdf.format(now);
    }
}
