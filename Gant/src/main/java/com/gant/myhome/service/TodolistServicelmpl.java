package com.gant.myhome.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gant.myhome.domain.Todolist;
import com.gant.myhome.mybatis.mapper.TodolistMapper;



@Service
public class TodolistServicelmpl implements TodolistService {

	private TodolistMapper dao;
	
	
	@Autowired
	public TodolistServicelmpl(TodolistMapper dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Todolist> getTodolist(){
		
		return dao.getTodolist();
	}

	

}
