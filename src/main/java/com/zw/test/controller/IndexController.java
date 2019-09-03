package com.zw.test.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zw.test.service.PersonService;

@Controller
public class IndexController {
	@Autowired
	private PersonService personService;

	@RequestMapping("/")
	public ModelAndView getIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index1.jsp");
		mav.addObject("time", new Date());
		mav.getModel().put("name", "一地在要工花木成畦手自栽agadf");
		List<?> persons = personService.getAllPersonList();
		mav.addObject("persons", persons);
		return mav;
	}

	@RequestMapping("/nihao")
	@ResponseBody
	public ModelAndView getPerson() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index2.jsp");
		return mav;
	}
}
