package com.asiainfo.dsession.dao;

import javax.annotation.PostConstruct;

import org.I0Itec.zkclient.DataUpdater;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.asiainfo.dsession.model.DistributedSession;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月29日  上午9:22:34
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Repository
public class DistributedSessionDaoImpl implements DistributedSessionDao {

	protected ZkClient client;
	@Value("${env.distributedsession.zookeeper.url}")
	protected String zookeeperURL;
	@Value("${env.distributedsession.zookeeper.root}")
	protected String root;

	@PostConstruct
	public void init() {
		this.client = new ZkClient(zookeeperURL);
		if (!this.client.exists(root)) {
			this.client.createPersistent(root);
		}
	}

	/* 
	 * @Description: TODO
	 * @param sessionId
	 * @param session
	 * @see com.asiainfo.dsession.dao.DistributedSessionDao#saveSession(java.lang.String, com.asiainfo.dsession.model.DistributedSession)
	 */
	@Override
	public void saveSession(String sessionId, DistributedSession session) {
		
		if (this.client.exists(getSessionPath(sessionId))) {
			this.updateSession(sessionId, session);
		} else {
			this.addSession(sessionId, session);
		}
	}
	
	/* 
	 * @Description: TODO
	 * @param sessionId
	 * @return
	 * @see com.asiainfo.zookeeper.session.dao.DistributedSessionDao#getSession(java.lang.String)
	 */
	@Override
	public DistributedSession getSession(String sessionId) {
		return this.client.readData(getSessionPath(sessionId), true);
	}

	/* 
	 * @Description: TODO
	 * @param sessionId
	 * @see com.asiainfo.zookeeper.session.dao.DistributedSessionDao#delSession(java.lang.String)
	 */
	@Override
	public void delSession(String sessionId) {
		if (this.client.exists(getSessionPath(sessionId))) {
			this.client.delete(getSessionPath(sessionId));
		}
	}

	/*
	 * 创建节点
	 */
	protected void addSession(String sessionId, DistributedSession session) {
		this.client.createPersistent(getSessionPath(sessionId), session);
	}

	/* 
	 * 更新节点数据
	 */
	protected void updateSession(String sessionId, final DistributedSession session) {
		
		this.client.updateDataSerialized(getSessionPath(sessionId), new DataUpdater<DistributedSession>() {
			@Override
			public DistributedSession update(DistributedSession currentData) {
				return session;
			}
		});
	}
	
	/*
	 * 生成zk节点路径
	 */
	protected String getSessionPath(String sessionId) {
		return this.root + "/" + sessionId;
	}
}
