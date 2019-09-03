package com.zw.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.test.bean.Tuser;
import com.zw.test.service.TuserService;

@Controller
public class TuserController {
	@Autowired
	private TuserService tuerService;
 
	@RequestMapping("/tuser")
	@ResponseBody
	public String getUser() {
		List<Tuser> users = tuerService.getAllUser();
		return users.get(0).getUser_name();
	}
}
