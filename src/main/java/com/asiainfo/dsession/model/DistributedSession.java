package com.asiainfo.dsession.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Session实例的元数据
 * 
 * @author       zq
 * @date         2017年8月28日  下午2:49:30
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class DistributedSession implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**Session实例的ID*/
	private String id;
	/**session的创建时间*/
	private Long createTime;
	/**session的最后一次访问时间，每次调用Request.getSession方法时都会去更新这个值。用来计算当前Session是否超时*/
	private Long lastAccessTime;
	/**session内容体*/
	private Map<String, String> sessionContext = new HashMap<>();
	
	public DistributedSession() {
		this.createTime = System.currentTimeMillis();
		this.lastAccessTime = this.createTime;
	}
	public DistributedSession(String id) {
		this();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public Map<String, String> getSessionContext() {
		return sessionContext;
	}
	public void setSessionContext(Map<String, String> sessionContext) {
		this.sessionContext = sessionContext;
	}
	@Override
	public String toString() {
		return "DistributedSession [id=" + id + ", createTime=" + createTime + ", lastAccessTime=" + lastAccessTime
				+ ", sessionContext=" + sessionContext + "]";
	}
}
