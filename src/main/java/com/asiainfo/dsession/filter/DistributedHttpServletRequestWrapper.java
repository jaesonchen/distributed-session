package com.asiainfo.dsession.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月29日  上午11:15:56
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class DistributedHttpServletRequestWrapper extends HttpServletRequestWrapper {

	String sessionId;
	
	/**
	 * DistributedHttpServletRequestWrapper实例化方法
	 * 
	 * @param request
	 */
	public DistributedHttpServletRequestWrapper(String sessionId, HttpServletRequest request) {
		super(request);
		this.sessionId = sessionId;
	}

	public HttpSession getSession(boolean create) {
		return new DistributedHttpSessionWrapper(this.sessionId, super.getSession(create));
	}

	public HttpSession getSession() {
		return new DistributedHttpSessionWrapper(this.sessionId, super.getSession());
	}
}
