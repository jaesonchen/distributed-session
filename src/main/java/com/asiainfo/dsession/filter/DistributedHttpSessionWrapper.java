package com.asiainfo.dsession.filter;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.dsession.DistributedContextContainer;
import com.asiainfo.dsession.SessionEnumerator;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月29日  上午11:06:54
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class DistributedHttpSessionWrapper extends HttpSessionWrapper {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	String sessionId;
	
	/**
	 * DistributedHttpSessionWrapper实例化方法
	 * 
	 * @param session
	 */
	public DistributedHttpSessionWrapper(String sessionId, HttpSession session) {
		super(session);
		this.sessionId = sessionId;
	}

	public Object getAttribute(String key) {
		return DistributedContextContainer.getSessionService().getSessionAttribute(sessionId, key);
	}

	public Enumeration<String> getAttributeNames() {
		
		Map<String, String> session = DistributedContextContainer.getSessionService().getSessionContext(sessionId);
		return (new SessionEnumerator<String>(session.keySet(), true));
	}

	public void invalidate() {
		DistributedContextContainer.getSessionService().removeSession(sessionId);
	}

	public void removeAttribute(String key) {
		DistributedContextContainer.getSessionService().removeSessionAttribute(sessionId, key);
	}

	public void setAttribute(String key, Object value) {
		if (value instanceof String) {
			DistributedContextContainer.getSessionService().setSessionAttribute(sessionId, key, (String) value);
		} else {
			logger.warn("session unsupport not serializable string." + "[key="
					+ key + "]" + "[value=" + value + "]");
		}
	}
	
	@Override
	public String getId() {
		return this.sessionId;
	}
}
