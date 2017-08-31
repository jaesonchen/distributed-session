package com.asiainfo.dsession.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月30日  下午4:53:37
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@RestController
public class DistributedSessionController {

	@RequestMapping(value = "/login/{userId}")
	public String distributedSession(@PathVariable("userId") String userId, HttpServletRequest request) {
		
		System.out.println("query param: userId=" + userId);
		HttpSession session = request.getSession(false);
		String loginId = (null == session) ? null : (String) session.getAttribute("userId");
		if (StringUtils.isNotEmpty(loginId)) {
			System.out.println("get userId from session, userId=" + loginId);
		} else {
			System.out.println("set userId to session, userId=" + userId);
			session.setAttribute("userId", userId);
		}
		return "success";
	}
}
