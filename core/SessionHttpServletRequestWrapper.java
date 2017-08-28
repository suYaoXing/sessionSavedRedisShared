package com.suyaoxing.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.nubia.oa.util.CookieUtil;

public class SessionHttpServletRequestWrapper extends HttpServletRequestWrapper{
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private HttpSession session;
	
    private String sessionId = "";
	

    public SessionHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public SessionHttpServletRequestWrapper(String sessionId, HttpServletRequest request) {
        super(request);
        this.sessionId = sessionId;
    }

    public SessionHttpServletRequestWrapper(String sessionId, HttpServletRequest request,
            HttpServletResponse response) {
        super(request);
        this.request = request;
        this.response = response;
        this.sessionId = sessionId;
        if (this.session == null) {
            this.session = new RedisSavedSession(sessionId, super.getSession(false),
                    request, response);
        }
    }

	
    @Override
    public HttpSession getSession(boolean create) {
        if (this.session == null) {
            if (create) {
                this.session = new RedisSavedSession(this.sessionId,
                        super.getSession(create), this.request, this.response);
                return this.session;
            } else {
                return null;
            }
        }
        return this.session;
    }

    @Override
    public HttpSession getSession() {
        if (this.session == null) {
            this.session = new RedisSavedSession(this.sessionId, super.getSession(),
                    this.request, this.response);
        }
        return this.session;
    }
         
}
