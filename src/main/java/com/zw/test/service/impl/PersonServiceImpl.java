package com.zw.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.test.bean.Person;
import com.zw.test.mapper.PersonMapper;
import com.zw.test.service.PersonService;

@Service("PersonService")
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonMapper personMapper;

	@Override
	public List<Person> getAllPerson() {
		return personMapper.getAllPerson();
	}

	@Override
	public List<?> getAllPersonList() {
		return personMapper.getAllPersonList();
	}

}
