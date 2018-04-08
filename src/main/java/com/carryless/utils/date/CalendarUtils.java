package com.carryless.utils.date;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
    private CalendarUtils(){};

    /**
     * 获取指定日期所在星期的第一天
     * @param date 指定时间
     * @return 指定时间所在星期的第一天
     */
    public static Date getFirstDateByWeek(Date date){
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int first_day_of_week = now.get(Calendar.DATE) + 2 - today; // 星期一
        now.set(now.DATE, first_day_of_week);
        return now.getTime();
    }
}
