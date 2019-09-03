package com.zw.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.test.bean.Tuser;
import com.zw.test.mapper.TuserMapper;
import com.zw.test.service.TuserService;

@Service("tuserService")
public class TuserServiceImpl implements TuserService {
	@Autowired
	private TuserMapper tuserMapper;
 
	@Override
	public List<Tuser> getAllUser() {
		return tuserMapper.getAllUser();
	}
 
}
