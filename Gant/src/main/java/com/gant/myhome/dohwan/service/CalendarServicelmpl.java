package com.gant.myhome.dohwan.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gant.myhome.dohwan.domain.Calendar;
import com.gant.myhome.dohwan.mybatis.mapper.CalendarMapper;



@Service
public class CalendarServicelmpl implements CalendarService {

	private CalendarMapper dao;
	
	
	@Autowired
	public CalendarServicelmpl(CalendarMapper dao) {
		this.dao = dao;
	}
	
	@Override
	public int add(Calendar c) {  
		
		String admin = dao.getadminid(c.getName());
		
		return dao.add(c, admin);
	}
	
	public String getadminid(String name) {
		return dao.getadminid(name);
	}

	@Override
	public List<Calendar> getCalList() {
		
		return dao.getCalList();
	}

	@Override
	public int update(Calendar c) {
		return dao.update(c);
	}

	
	@Override
	public int caldelete(String id) {
		return dao.caldelete(id);
		
	}



	

}
