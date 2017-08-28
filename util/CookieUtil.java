package com.suyaoxing.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    private static final String KEY = "jkdflsffff()kldkjapfdY=::$B+DUOWAN";

    private HttpServletRequest request;

    private HttpServletResponse response;

    private static String domain = "andy.com";

    public CookieUtil(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * ����cookie
     * @param request
     * @param response
     * @param name cookie����
     * @param value cookieֵ
     * @param seconds ����ʱ��(��λ��) -1����ر������ʱcookie������
     */
    public static void setCookie(HttpServletRequest request,
            HttpServletResponse response, String name, String value, int seconds) {
        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(value))
            return;
        Cookie cookie = new Cookie(name, value);
        //cookie.setDomain(domain);
        cookie.setMaxAge(seconds); 
        cookie.setPath("/");
        response.setHeader("P3P",
                "CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
        response.addCookie(cookie);
    }

    /**
     * ��ȥcookie�б����ֵ
     * @param name
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getCookieValue(String name)
            throws UnsupportedEncodingException {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equalsIgnoreCase(cookies[i].getName())) {
                    return cookies[i].getValue();
                }
            }
        }
        return "";
    }



    /**
     * ��ȡcookieֵ
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        try {
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (name.equalsIgnoreCase(cookies[i].getName())) {
                        return cookies[i].getValue();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * �Ƴ��ͻ��˵�cookie
     * @param request
     * @param response
     * @param name
     */
    public static void removeCookieValue(HttpServletRequest request,
            HttpServletResponse response, String name) {
        try {
            Cookie cookies[] = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (name.equalsIgnoreCase(cookie.getName())) {
                        cookie = new Cookie(name, null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        //cookie.setDomain(domain);
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}