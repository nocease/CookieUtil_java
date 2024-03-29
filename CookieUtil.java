package cn.nocease.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

public class CookieUtil {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieUtil(HttpServletRequest request, HttpServletResponse response) {
        this.response = response;
        this.request = request;
    }

    //设置cookie
    public void setCookie(String key, String value, int day) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * day);
        response.addCookie(cookie);
    }

    //获取cooike
    public String getCookie(String key) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    value = URLDecoder.decode(cookie.getValue(), "utf-8");
                }
            }
        } catch (Exception e) {
            value = null;
        }
        return value;
    }

    //删除cookie
    public void delCookie(String key) {
        Cookie cookie = new Cookie(key, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
