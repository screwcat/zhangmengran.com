package com.zw.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.zw.test.bean.Person;
import com.zw.test.service.PersonService;

@Controller
public class PersonController {
	@Autowired
	private PersonService personService;

	@RequestMapping("/person")
	@ResponseBody
	public String getPerson() {
		List<Person> persons = personService.getAllPerson();
		return persons.get(0).getUser_id();
	}
}
