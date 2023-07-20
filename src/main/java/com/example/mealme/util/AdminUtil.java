package com.example.mealme.util;

import javax.servlet.http.HttpServletRequest;

public class AdminUtil {

    // 세션체크
    public static Long sessionCheck(HttpServletRequest req) {
        Long adminNumber = (Long)req.getSession().getAttribute("adminNumber");
        if(adminNumber == null) {
            return 0L;
        }else return adminNumber;
    }
}
