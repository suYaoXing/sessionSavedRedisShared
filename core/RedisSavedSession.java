package com.suyaoxing.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.nubia.oa.util.CookieUtil;

public class RedisSavedSession implements HttpSession{
	
	private String sessionId = "";
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
    private	Map<String,Object> sessionMap = null;
	
	private HttpSession session;
	
	private RedisSessionOperation operation = new RedisSessionOperation();
	
	public RedisSavedSession()
	{
		
	}
	
	public RedisSavedSession(HttpSession session)
	{
		this.session = session;
	}
	
	public RedisSavedSession(String sessionId,HttpSession session)
	{
		this(session);
		this.sessionId = sessionId;
	}
	
	public RedisSavedSession(String sessionId,HttpSession session,
			HttpServletRequest request,HttpServletResponse response)
	{
		this(sessionId,session);
		this.request = request;
		this.response = response;
	}
	
	private Map<String, Object> getSessionMap() {
        if (this.sessionMap == null) {
            this.sessionMap = operation.getSessionMap(this.getId());
        }
        return this.sessionMap;
    }
	
	@Override
	public Object getAttribute(String key) {
		// TODO Auto-generated method stub
		this.getSessionMap().get(key);	
		return sessionMap.get(key);
	}

	@Override
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		 return (new Enumerator(getSessionMap().keySet(), true));
	}

	@Override
	public long getCreationTime() {
		// TODO Auto-generated method stub
		return this.session.getCreationTime();
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		//return CookieUtil.getSessionValue(request, "JSESSIONID");
		return this.sessionId;
	}

	@Override
	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return this.session.getLastAccessedTime();
	}

	@Override
	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return this.session.getMaxInactiveInterval();
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return this.session.getServletContext();
	}

	@SuppressWarnings("deprecation")
	@Override
	public HttpSessionContext getSessionContext() {
		// TODO Auto-generated method stub
		return this.getSessionContext();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object getValue(String name) {
		// TODO Auto-generated method stub
		return this.session.getValue(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public String[] getValueNames() {
		// TODO Auto-generated method stub
		return this.session.getValueNames();
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		this.sessionMap.clear();
		operation.removeSessionMap(this.getId());
		CookieUtil.removeCookieValue(request, response, "SUYAOXINGJSESSIONID");
		
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return this.session.isNew();
	}

	@Override
	public void putValue(String key, Object object) {
		// TODO Auto-generated method stub
		this.session.putValue(key, object);
	}

	@Override
	public void removeAttribute(String key) {
		// TODO Auto-generated method stub
		this.getSessionMap().remove(key);
		operation.saveSession(this.getId(), sessionMap);
	}

	@Override
	public void removeValue(String name) {
		// TODO Auto-generated method stub
		this.session.removeValue(name);
		
	}

	@Override
	public void setAttribute(String key, Object object) {
		// TODO Auto-generated method stub
		this.getSessionMap().put(key,object);
		operation.saveSession(this.getId(),sessionMap);
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		// TODO Auto-generated method stub
		this.session.setMaxInactiveInterval(interval);
		
	}
	
	   class Enumerator<E> implements Enumeration <E> 
	   {		   
		   private Iterator<E> iterator = null;
		   
		   public Enumerator(Collection<E> collection) 
		   {
			   this(collection.iterator());
		   }
		   
		   public Enumerator(Collection<E> collection, boolean clone) 
		   {
			   this(collection.iterator(), clone);
		   }
		   
		   public Enumerator(Iterator<E> iterator) 
		   {
			   super();
			   this.iterator = iterator;
		   }
		   
		   public Enumerator(Iterator<E> iterator, boolean clone) 
		   {
			   super();
			   if (!clone) 
			   {
				   this.iterator = iterator;
			   }
			   else 
			   {
				   List<E> list = new ArrayList<E>();
				   while (iterator.hasNext()) 
				   {
					   list.add(iterator.next());
				   }
				   this.iterator = list.iterator();
			   }
		   }
		   
		   public Enumerator(Map<String, E> map) 
		   {
			   this(map.values().iterator());
		   }
		   
		   public Enumerator(Map<String, E> map, boolean clone) 
		   {
			   this(map.values().iterator(), clone);
		   }
		   
		   public boolean hasMoreElements() 
		   {
			   return (iterator.hasNext());
		   }
		   
		   public E nextElement() throws NoSuchElementException
		   {
			   return (iterator.next());
		   }
		} 
}
