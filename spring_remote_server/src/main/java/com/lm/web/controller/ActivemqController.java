package com.lm.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lm.core.entity.User;
import com.lm.core.service.impl.sender.QueueSender;
import com.lm.core.service.impl.sender.TopicSender;

@Controller
@RequestMapping("/activemq")
public class ActivemqController {
	@Resource
	QueueSender queueSender;
	@Resource
	TopicSender topicSender;

	@RequestMapping(value="/activemq")
	public String showUser(ModelMap modelMap,HttpServletRequest request){
		
		return "/activemq";
	}
	/**
	 * 发送消息到队列
	 * 
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("queueSender")
	public String queueSender(@RequestParam("message") String message) {
		String opt = "";
		try {
			queueSender.send("test.queue", message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}

	/**
	 * 发送消息到主题
	 * 
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("topicSender")
	public String topicSender(@RequestParam("message") String message) {
		String opt = "";
		try {
			topicSender.send("test.topic", message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
}