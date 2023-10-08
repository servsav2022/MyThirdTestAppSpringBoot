package com.servsav.mythirdtestappspringboot.util;

import com.servsav.mythirdtestappspringboot.model.Request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DifferenceTimeCalculator {


    public static String differenceCalculate (Request request) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            //Время, которое пришло из реквеста
            Date date1 = dateFormat.parse(request.getSystemTime());
            ///текущее время
            Date date2 = dateFormat.parse(DateTimeUtil.getCustomFormat().format(new Date()));

            // разница между датами в секундах миллисекундах
            long timeDifferenceInMillis = date2.getTime() - date1.getTime();
            SimpleDateFormat resultFormat = new SimpleDateFormat("ss.SSS");
            String timeDifferenceFormatted = resultFormat.format(new Date(timeDifferenceInMillis));


        return timeDifferenceFormatted;
    }
}
