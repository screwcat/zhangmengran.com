package com.zw.test.service;

import java.util.List;

import com.zw.test.bean.Person;

public interface PersonService {
	List<Person> getAllPerson();
	List<?> getAllPersonList();
}
