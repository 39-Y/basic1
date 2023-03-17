package com.ll.basic1.Domain;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;

@AllArgsConstructor
@Component
@RequestScope
public class RQ {
    private HttpServletRequest rq;
    private HttpServletResponse rs;

    public void setCookie(String name, String value){
        if(name != null && value != null
            && name.trim().length()>0 && value.trim().length()>0)
            rs.addCookie(new Cookie(name, value));
    }
    public void setCookie(String name, Long value){
        setCookie(name, value+"");
    }

    public String getCookie(String name, String defaultValue){
        String value = defaultValue;
        Cookie[] cookies = rq.getCookies();
        if(cookies != null){
            value = Arrays.stream(cookies)
                    .filter(c->c.getName().equals(name))
                    .findFirst()
                    .map(c->c.getValue())
                    .orElse(defaultValue);
        }
        return value;
    }
    public long getCookieAsLong(String name, long defaultValue){
        long value = defaultValue;
        Cookie[] cookies = rq.getCookies();
        try{
            if(cookies != null){
                value = Arrays.stream(cookies)
                        .filter(c->c.getName().equals(name))
                        .findFirst()
                        .map(c->Long.parseLong(c.getValue()))
                        .orElse(defaultValue);
            }
        }catch(Exception e){
            value = defaultValue;
        }

        return value;
    }


    public void removeCookie(String name) {
        if (rq.getCookies() != null
                && name !=null
                && name.trim().length()>0) {
            Arrays.stream(rq.getCookies())
                    .filter(cookie -> cookie.getName().equals(name))
                    .forEach(cookie -> {
                        cookie.setMaxAge(0);
                        rs.addCookie(cookie);
                    });
        }
    }

    public void setSession(String name, String value){
        HttpSession session = rq.getSession();
        session.setAttribute(name, value);
    }

    public String getSession(String name, String defaultValue){
        String result =(String) rq.getSession().getAttribute(name);
        return result==null? defaultValue:result;
    }
    public long getSessionToLong(String name,long defaultValue){
        String result =(String) rq.getSession().getAttribute(name);
        long Lresult=defaultValue;
        try{
            Lresult = Long.parseLong(result);
        }catch (Exception e){
        }
        return Lresult;
    }

    public boolean removeSession(String name){
        HttpSession session = rq.getSession();
        if(session.getAttribute(name) == null)
            return false;
        session.removeAttribute(name);
        return  true;
    }

    public boolean isLogin(){
        HttpSession session = rq.getSession();
        return !getSession("logOn", "none").equals("none");
    }
}
