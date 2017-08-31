package com.asiainfo.dsession.dao;

import com.asiainfo.dsession.model.DistributedSession;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月29日  上午9:20:53
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public interface DistributedSessionDao {
	
	/**
	 * @Description: 保存session数据
	 * 
	 * @param sessionId
	 * @param session
	 */
	void saveSession(String sessionId, DistributedSession session);
	
	/**
	 * @Description: 获取session数据
	 * 
	 * @param sessionId
	 * @return
	 */
	DistributedSession getSession(String sessionId);

	/**
	 * @Description: 删除session数据
	 * 
	 * @param sessionId
	 */
	void delSession(String sessionId);
}
