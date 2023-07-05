package com.example.mealme.util;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    // 암호화1
    public static String pwSha256(String PW) {
        String encrypted = "";
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(PW.getBytes());
            byte[] hash = sha256.digest();
            encrypted = bytesToHex(hash);
            System.out.println("Encrypted value: " + encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }

    // 비밀번호 암호화
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    // 카카오 반환값 조정(gender)
    public static String convertGender(String userGender) {
        if (userGender.equals("male")) {
            return "M";
        } else if (userGender.equals("female")) {
            return "F";
        } else {
            return "U";
        }
    }

    public static String convertBirth(String userBirth) {

        int currentYear = LocalDate.now().getYear();

        String dateString = currentYear + userBirth;

        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));

        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println(formattedDate);

        return formattedDate;
    }
}
