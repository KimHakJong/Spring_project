package com.gant.myhome.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gant.myhome.domain.Todolist;

@Mapper
public interface TodolistMapper {
	
		
	
	public List<Todolist> getTodolist(HashMap<String, Integer> map);


	public String get_id(int p_no);

	public String get_name(int p_no);
	
	public int getListCount();
	
	



	/*
	 * 	
	
	
	public Board getDetail(int num);
	
	public int boardReply(Board board);
	
	public int boardModify(Board modifyboard);
	
	public int boardDelete(Board board);
	
	public int setReadCountUpdate(int num);
	
	public Board isBoardWriter(HashMap<String, Object> map);
	
	public void insertBoard(Board board);
	
	public int boardReplyUpdate(Board board);
	
	public List<String> getDeleteFileList();
	
	public void deleteFileList(String filename);
	 */

}
