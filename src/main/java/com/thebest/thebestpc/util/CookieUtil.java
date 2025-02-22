package com.thebest.thebestpc.util;

import java.util.Base64;
import java.util.logging.Logger;

public class CookieUtil {

    private static final Logger logger = Logger.getLogger(CookieUtil.class.getName());

    public static String encode(String value) {
        return Base64.getUrlEncoder().encodeToString(value.getBytes());
    }

    public static String decodeValue(String encodedValue) {
        return new String(Base64.getUrlDecoder().decode(encodedValue));
    }
}
