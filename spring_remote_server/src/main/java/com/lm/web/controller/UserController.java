package com.lm.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lm.core.entity.User;
import com.lm.core.service.UserRmiService;
import com.lm.web.vo.BaseMapVo;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserRmiService userService;
	
	@RequestMapping(value="/showUser")
	public String showUser(ModelMap modelMap,HttpServletRequest request){
		List<User> users = userService.getUserByAcount("root", "root");
		modelMap.addAttribute("user", users.get(0));
		
		return "/view/showUser";
	}
	
	@RequestMapping(value="/getUsers")
	@ResponseBody
	public BaseMapVo getUsers(){
		BaseMapVo vo = new BaseMapVo();
		List<User> users = userService.getUserByAcount("root", "root");
		vo.addData("user", users);
		return vo;
	}
	
	@RequestMapping(value="/httpClientTest")
	@ResponseBody
	public BaseMapVo httpClientTest(String name, String password){
		BaseMapVo vo = new BaseMapVo();
		List<User> users = userService.getUserByAcount(name, password);
		vo.addData("user", users);
		vo.setRslt("success");
		return vo;
	}
}
