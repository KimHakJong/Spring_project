package com.gant.myhome.service;



import java.util.List;

import com.gant.myhome.domain.Todolist;


public interface TodolistService {
	
	//public int add(Todolist tdl);
		
	public String get_id(int p_no);
	public String get_name(int p_no);
	
	 public int getListCount();
	   
	 public List<Todolist> getTodolist(int page, int limit, int p_no);
	/*

	   
	   public Board getDetail(int num);
	   
	   public int boardReply(Board board);
	   
	   public int boardModify(Board modifyboard);
	   
	   public int boardDelete(int num);
	   
	   public int setReadCountUpdate(int num);
	   
	   public boolean isBoardWriter(int num,String pass);
	   
	   public void insertBoard(Board board);

	   public int boardReplyUpdate(Board board);

	
	public void deleteFileList(String filename);
	
	public List<String> getDeleteFileList();
	 */

	
}
