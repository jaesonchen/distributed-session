package com.asiainfo.dsession.service;

import java.util.Map;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月29日  上午9:16:03
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public interface DistributedSessionService {

	Map<String, String> getSessionContext(String sessionId);

	void removeSession(String sessionId);

	void setSessionAttribute(String sessionId, String key, String value);

	void removeSessionAttribute(String sessionId, String key);

	String getSessionAttribute(String sessionId, String key);
}
