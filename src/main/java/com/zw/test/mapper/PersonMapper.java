package com.zw.test.mapper;

import java.util.List;

import com.zw.test.bean.Person;

public interface PersonMapper {
	List<Person> getAllPerson();
	List<?>getAllPersonList();
}
