package com.example.urlshortner.util;

public class Base62 {

    private static final String CHAR_SET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(long value) {
        if (value == 0) return "0";

        StringBuilder sb = new StringBuilder();

        while (value > 0) {
            int remainder = (int)(value % 62);
            sb.append(CHAR_SET.charAt(remainder));
            value /= 62;
        }

        return sb.reverse().toString();
    }
}