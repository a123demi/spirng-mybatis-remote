package com.lm.web.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lm.core.service.UserHttpService;
import com.lm.core.service.UserRmiService;
import com.lm.core.service.UserWsService;
import com.lm.core.util.ApplicationContextUtil;
import com.lm.core.util.HttpClientUtil;
import com.lm.core.util.MyThreadPool;
import com.lm.web.vo.BaseMapVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private MyThreadPool myThreadPool;
	
	@RequestMapping(value = "/rmiTest")
	@ResponseBody
	public BaseMapVo rmiTest(String name, String password) {
		BaseMapVo vo = new BaseMapVo();
		System.out.println("rmi客户端开始调用");
		UserRmiService rmi = (UserRmiService) ApplicationContextUtil.getInstance().getBean("clientRmiTest");
		rmi.getUserByAcount("张三", ":张三的密码");
		System.out.println("rmi客户端调用结束");
		vo.setRslt("sucess");
		return vo;
	}
	
	@RequestMapping(value = "/httpInvokerTest")
	@ResponseBody
	public BaseMapVo httpInvokerTest(String name, String password) {
		BaseMapVo vo = new BaseMapVo();
		long startDate = Calendar.getInstance().getTimeInMillis();
		System.out.println("httpInvoker客户端开始调用" + startDate);
		UserHttpService rmi = (UserHttpService) ApplicationContextUtil.getInstance().getBean("httpInvokerProxy");
		rmi.getUserByAcount("张三", ":张三的密码");
		System.out.println("httpInvoker客户端调用结束" +  (Calendar.getInstance().getTimeInMillis()-startDate));
		vo.setRslt("sucess");
		return vo;
	}
	
	@RequestMapping(value = "/httpClientTest")
	@ResponseBody
	public BaseMapVo httpClientTest(String name, String password) {
		BaseMapVo vo = new BaseMapVo();
		long startDate = Calendar.getInstance().getTimeInMillis();
		System.out.println("httpInvoker客户端开始调用" + startDate);
		String path="http://127.0.0.1:8080/spring_remote_server/user/httpClientTest";
		HttpClientUtil httpClientUtil = new  HttpClientUtil(name, password, path);
//		new Thread(httpClientUtil).start();
		myThreadPool.execute(httpClientUtil);
//		UserHttpService rmi = (UserHttpService) ApplicationContextUtil.getInstance().getBean("httpInvokerProxy");
//		rmi.getUserByAcount("张三", ":张三的密码");
		System.out.println("httpInvoker客户端调用结束" +  (Calendar.getInstance().getTimeInMillis()-startDate));
		vo.setRslt("sucess");
		return vo;
	}
	
	@RequestMapping(value = "/wsTest")
	@ResponseBody
	public BaseMapVo wsTest(String name, String password) {
		BaseMapVo vo = new BaseMapVo();
		long startDate = Calendar.getInstance().getTimeInMillis();
		System.out.println("wsTest客户端开始调用" + startDate);
		UserWsService ws = (UserWsService) ApplicationContextUtil.getInstance().getBean("userWsService");
		ws.getUserByAcount("张三", ":张三的密码");
		System.out.println("wsTest客户端调用结束" +  (Calendar.getInstance().getTimeInMillis()-startDate));
		vo.setRslt("sucess");
		return vo;
	}
}
