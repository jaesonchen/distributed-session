package com.asiainfo.dsession.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月29日  上午10:05:08
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class DistributedSessionFilter implements Filter {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	final String sessionIdName = "D_SESSION_ID";
	protected String cookieDomain = "";
	protected String cookiePath = "/";
	protected List<String> excludeUrl = new ArrayList<String>();
	protected ThreadLocal<String> local = new ThreadLocal<>();
	
	/* 
	 * @Description: TODO
	 * @param filterConfig
	 * @throws ServletException
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.cookieDomain = filterConfig.getInitParameter("cookieDomain");
		if (this.cookieDomain == null) {
			this.cookieDomain = "";
		}
		this.cookiePath = filterConfig.getInitParameter("cookiePath");
		if (StringUtils.isEmpty(cookiePath)) {
			this.cookiePath = "/";
		}
		String excludeStr = filterConfig.getInitParameter("excludeUrl");
		if (StringUtils.isNotEmpty(excludeStr)) {
			this.excludeUrl = Arrays.asList(excludeStr.split(","));
		}
	}

	/* 
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String uri = request.getRequestURI();
		if (this.excludeUrl != null && this.isMatchExcludeUrl(uri)) {
			filterChain.doFilter(request, response);
			return;
		}
		this.local.set(StringUtils.isEmpty(this.cookieDomain) ? request.getServerName() : this.cookieDomain);
		String sessionId = this.getSessionId(request, response);
		filterChain.doFilter(new DistributedHttpServletRequestWrapper(sessionId, request), response);
	}

	/* 
	 * @Description: TODO
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		this.excludeUrl = null;
		this.cookieDomain = null;
		this.cookiePath = null;
	}
	
	protected boolean isMatchExcludeUrl(String uri) {
		
		if (StringUtils.isEmpty(uri)) {
			return false;
		}
		for (String regexUrl : this.excludeUrl) {
			if (uri.endsWith(regexUrl)) {
				return true;
			}
		}
		return false;
	}
	
	protected String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		
		Cookie cookies[] = request.getCookies();
		String sessionId = null;
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(sessionIdName)) {
					sessionId = cookie.getValue();
					break;
				}
			}
		}
		
		if (StringUtils.isEmpty(sessionId)) {
			sessionId = java.util.UUID.randomUUID().toString().replace("-", "");
			response.addHeader("Set-Cookie", sessionIdName + "=" + sessionId
					+ ";domain=" + this.local.get() + ";Path="
					+ this.cookiePath + ";HTTPOnly");
		}
		return sessionId;
	}
}
