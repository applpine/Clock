package com.example.clock.util;

import java.time.LocalDate;

public class DateUtil {
    public static LocalDate getCurrentDate() {
        LocalDate today = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            today = LocalDate.now();
        }
     //   System.out.println("Today's Local date : " + today);
        return today;
    }
}
