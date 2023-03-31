package com.gant.myhome.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gant.myhome.domain.Todolist;

@Mapper
public interface TodolistMapper {
	
		
	

	public List<Todolist> getTodolist();


}
