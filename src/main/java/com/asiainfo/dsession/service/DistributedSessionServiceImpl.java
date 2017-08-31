package com.asiainfo.dsession.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.dsession.dao.DistributedSessionDao;
import com.asiainfo.dsession.model.DistributedSession;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月29日  上午9:34:22
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Service
public class DistributedSessionServiceImpl implements DistributedSessionService {

	static final Long SESSION_TIMEOUT = 30L * 60 * 1000;
	
	@Autowired
	protected DistributedSessionDao sessionDao;
	
	/* 
	 * @Description: TODO
	 * @param sessionId
	 * @return
	 * @see com.asiainfo.zookeeper.session.service.DistributedSessionService#getSessionContext(java.lang.String)
	 */
	@Override
	public Map<String, String> getSessionContext(String sessionId) {

		DistributedSession session = this.sessionDao.getSession(sessionId);
		if (null == session) {
			return null;
		}
		if ((System.currentTimeMillis() - session.getLastAccessTime()) >= SESSION_TIMEOUT) {
			this.sessionDao.delSession(sessionId);
			return null;
		}
		session.setLastAccessTime(System.currentTimeMillis());
		this.sessionDao.saveSession(sessionId, session);
		return session.getSessionContext();
	}

	/* 
	 * @Description: TODO
	 * @param sessionId
	 * @see com.asiainfo.zookeeper.session.service.DistributedSessionService#removeSession(java.lang.String)
	 */
	@Override
	public void removeSession(String sessionId) {
		this.sessionDao.delSession(sessionId);
	}

	/* 
	 * @Description: TODO
	 * @param sessionId
	 * @param key
	 * @param value
	 * @see com.asiainfo.zookeeper.session.service.DistributedSessionService#setSessionAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setSessionAttribute(String sessionId, String key, String value) {
		
		DistributedSession session = this.sessionDao.getSession(sessionId);
		if (null == session) {
			session = new DistributedSession(sessionId);
		}
		session.getSessionContext().put(key, value);
		session.setLastAccessTime(System.currentTimeMillis());
		this.sessionDao.saveSession(sessionId, session);
	}

	/* 
	 * @Description: TODO
	 * @param sessionId
	 * @param key
	 * @see com.asiainfo.zookeeper.session.service.DistributedSessionService#removeSessionAttribute(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeSessionAttribute(String sessionId, String key) {
		
		DistributedSession session = this.sessionDao.getSession(sessionId);
		if (null == session) {
			session = new DistributedSession(sessionId);
		}
		session.getSessionContext().remove(key);
		session.setLastAccessTime(System.currentTimeMillis());
		this.sessionDao.saveSession(sessionId, session);
	}

	/* 
	 * @Description: TODO
	 * @param sessionId
	 * @param key
	 * @return
	 * @see com.asiainfo.zookeeper.session.service.DistributedSessionService#getSessionAttribute(java.lang.String, java.lang.String)
	 */
	@Override
	public String getSessionAttribute(String sessionId, String key) {
		
		DistributedSession session = this.sessionDao.getSession(sessionId);
		if (null == session) {
			session = new DistributedSession(sessionId);
		}
		session.setLastAccessTime(System.currentTimeMillis());
		this.sessionDao.saveSession(sessionId, session);
		return session.getSessionContext().get(key);
	}
}
