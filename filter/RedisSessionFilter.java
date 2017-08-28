package com.suyaoxing.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.nubia.oa.util.CookieUtil;
import com.nubia.oa.util.StringUtil;
import com.suyaoxing.core.SessionHttpServletRequestWrapper;;
public class RedisSessionFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("到了过滤器");
		 String sessionId = CookieUtil.getCookieValue(request, "SUYAOXINGJSESSIONID");
	        if(StringUtil.isEmpty(sessionId) || sessionId.length() != 36){
	            sessionId = StringUtil.getUuid();
	            CookieUtil.setCookie(request, response, "SUYAOXINGJSESSIONID", sessionId, 60 * 60); 
	        }

	        //交给自定义的HttpServletRequestWrapper处理
	        filterChain.doFilter(new SessionHttpServletRequestWrapper(sessionId, request, response), response);
		
	}

}
