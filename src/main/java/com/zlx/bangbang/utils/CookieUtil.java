package com.zlx.bangbang.utils;

import com.sun.xml.internal.bind.v2.runtime.output.IndentingUTF8XmlOutput;
import com.zlx.bangbang.constants.Constant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class CookieUtil {
    public static void setCookie(HttpServletResponse response, String key, String value, Integer expire) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setMaxAge(expire);
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }
        Cookie cookie;
        try {
            cookie = Arrays.stream(cookies).filter(c -> c.getName().equals(Constant.TOKEN)).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return cookie;
    }

    /**
     * 生成cookie
     */
    public static String tokenGenerate() {
        return UUID.randomUUID().toString();
    }
}
