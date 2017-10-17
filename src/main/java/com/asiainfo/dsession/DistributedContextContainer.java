package com.asiainfo.dsession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.asiainfo.dsession.service.DistributedSessionService;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月29日  上午11:10:44
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Component
public class DistributedContextContainer implements ApplicationContextAware {

	private static ApplicationContext applicationcontext;
	
	/* 
	 * @Description: TODO
	 * @param applicationContext
	 * @throws BeansException
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationcontext = context;
	}

	/**
	 * @Description: TODO
	 * 
	 * @return
	 */
	public static final DistributedSessionService getSessionService() {
		return applicationcontext.getBean(DistributedSessionService.class);
	}
}
