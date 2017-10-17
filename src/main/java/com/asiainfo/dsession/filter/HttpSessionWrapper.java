package com.asiainfo.dsession.filter;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月29日  上午11:00:22
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@SuppressWarnings("deprecation")
public class HttpSessionWrapper implements HttpSession {

	HttpSession session;

	public HttpSessionWrapper(HttpSession session) {
		this.session = session;
	}
	
	/* 
	 * @Description: TODO
	 * @return
	 * @see javax.servlet.http.HttpSession#getCreationTime()
	 */
	@Override
	public long getCreationTime() {
		return this.session.getCreationTime();
	}

	/* 
	 * @Description: TODO
	 * @return
	 * @see javax.servlet.http.HttpSession#getId()
	 */
	@Override
	public String getId() {
		return this.session.getId();
	}

	/* 
	 * @Description: TODO
	 * @return
	 * @see javax.servlet.http.HttpSession#getLastAccessedTime()
	 */
	@Override
	public long getLastAccessedTime() {
		return this.session.getLastAccessedTime();
	}

	/* 
	 * @Description: TODO
	 * @return
	 * @see javax.servlet.http.HttpSession#getServletContext()
	 */
	@Override
	public ServletContext getServletContext() {
		return this.session.getServletContext();
	}

	/* 
	 * @Description: TODO
	 * @param interval
	 * @see javax.servlet.http.HttpSession#setMaxInactiveInterval(int)
	 */
	@Override
	public void setMaxInactiveInterval(int interval) {
		this.session.setMaxInactiveInterval(interval);
	}

	/* 
	 * @Description: TODO
	 * @return
	 * @see javax.servlet.http.HttpSession#getMaxInactiveInterval()
	 */
	@Override
	public int getMaxInactiveInterval() {
		return this.session.getMaxInactiveInterval();
	}

	/* 
	 * @Description: TODO
	 * @return
	 * @deprecated
	 * @see javax.servlet.http.HttpSession#getSessionContext()
	 */
	@Override
	@Deprecated
	public HttpSessionContext getSessionContext() {
		return null;
	}

	/* 
	 * @Description: TODO
	 * @param name
	 * @return
	 * @see javax.servlet.http.HttpSession#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String name) {
		return this.session.getAttribute(name);
	}

	/* 
	 * @Description: TODO
	 * @param name
	 * @return
	 * @deprecated
	 * @see javax.servlet.http.HttpSession#getValue(java.lang.String)
	 */
	@Override
	@Deprecated
	public Object getValue(String name) {
		return this.getAttribute(name); }

	/* 
	 * @Description: TODO
	 * @return
	 * @see javax.servlet.http.HttpSession#getAttributeNames()
	 */
	@Override
	public Enumeration<String> getAttributeNames() {
		return this.session.getAttributeNames();
	}

	/* 
	 * @Description: TODO
	 * @return
	 * @deprecated
	 * @see javax.servlet.http.HttpSession#getValueNames()
	 */
	@Override
	@Deprecated
	public String[] getValueNames() {
		return null;
	}

	/* 
	 * @Description: TODO
	 * @param name
	 * @param value
	 * @see javax.servlet.http.HttpSession#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String name, Object value) {
		this.session.setAttribute(name, value);
	}

	/* 
	 * @Description: TODO
	 * @param name
	 * @param value
	 * @deprecated
	 * @see javax.servlet.http.HttpSession#putValue(java.lang.String, java.lang.Object)
	 */
	@Override
	@Deprecated
	public void putValue(String name, Object value) {
		this.setAttribute(name, value);
	}

	/* 
	 * @Description: TODO
	 * @param name
	 * @see javax.servlet.http.HttpSession#removeAttribute(java.lang.String)
	 */
	@Override
	public void removeAttribute(String name) {
		this.session.removeAttribute(name);
	}

	/* 
	 * @Description: TODO
	 * @param name
	 * @deprecated
	 * @see javax.servlet.http.HttpSession#removeValue(java.lang.String)
	 */
	@Override
	public void removeValue(String name) {
		this.removeAttribute(name);
	}

	/* 
	 * @Description: TODO
	 * @see javax.servlet.http.HttpSession#invalidate()
	 */
	@Override
	public void invalidate() {
		this.session.invalidate();
	}

	/* 
	 * @Description: TODO
	 * @return
	 * @see javax.servlet.http.HttpSession#isNew()
	 */
	@Override
	public boolean isNew() {
		return this.session.isNew();
	}
}
