package com.gant.myhome.rojoon.board.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gant.myhome.rojoon.board.mapper.BoardLikeMapper;


@Service
public class BoardLikeServicelmpl implements BoardLikeService {

	private BoardLikeMapper dao;
		
	@Autowired
	public BoardLikeServicelmpl(BoardLikeMapper dao) {
		this.dao = dao;
	}
    
	
	@Override
	public int selectLike(String id, int board_num) {
		int result =0; // id와 테이블번호에 해당 테이블이 없습니다.
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("board_num",board_num);
		String selectId = dao.selectLike(map);		
		if(selectId != null && selectId != "") {
			result = 1;
		}		
		return result;
	}
    
	@Override
	public void insertLike(String id, int board_num) {	
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("board_num",board_num);
		dao.insertLike(map);
	}

	@Override
	public String selectLikeCheck(String id, int board_num) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("board_num",board_num);
		return dao.selectLikeCheck(map);
	}
	


	
}
