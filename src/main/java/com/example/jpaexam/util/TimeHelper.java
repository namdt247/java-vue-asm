package com.example.jpaexam.util;

import java.util.Calendar;
import java.util.Date;

public class TimeHelper {
    public static Date addDaysToCurrentTime(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }
}
