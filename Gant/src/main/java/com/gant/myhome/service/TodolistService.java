package com.gant.myhome.service;



import java.util.List;

import com.gant.myhome.domain.Todolist;


public interface TodolistService {
	
	//
		
	public String get_id(int p_no);
	public String get_name(int p_no);
	
	public int getListCount(int p_no);
	public int getSendListCount(int p_no, String id);
	   
	public List<Todolist> getTodolist(int page, int limi, int p_no, String id);
	
	
	 
	public void insertBoard(Todolist todolist);
	
	public List<Todolist> getTodolist2(int p_no);
	
	public List<Todolist> getTodolist3(int page, int limi, int p_no, int board_num);
	
	
	public Todolist getDetail(int num);
	
	public int boardDelete(int num);
		 
	/*

	   
	   public Board getDetail(int num);
	   
	   public int boardReply(Board board);
	   
	   public int boardModify(Board modifyboard);
	   
	   
	   
	   public int setReadCountUpdate(int num);
	   
	   public boolean isBoardWriter(int num,String pass);
	   
	   public void insertBoard(Board board);

	   public int boardReplyUpdate(Board board);

	
	public void deleteFileList(String filename);
	
	public List<String> getDeleteFileList();
	 */

	
}
