package com.gant.myhome.rojoon.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gant.myhome.rojoon.board.domain.Comment;
import com.gant.myhome.rojoon.board.mapper.CommentMapper;


@Service
public class CommentServicelmpl implements CommentService {

	private CommentMapper dao;
	
	@Autowired
	public CommentServicelmpl(CommentMapper dao) {
		this.dao = dao;
	}

	@Override
	public int getListCount(int board_num) {
		return dao.getListCount(board_num);
	}

	@Override
	public List<Comment> getCommentList(int board_num, int page) {
		int startrow = 1;
		int endrow = page*3;
		
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("board_num", board_num);
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getCommentList(map);
	}

	@Override
	public int commentsInsert(Comment c) {
		return dao.commentsInsert(c);
	}
	
	
	@Override
	public int commentsUpdate(Comment co) {
		return dao.commentsUpdate(co);
	}

	@Override
	public int commentsDelete(int num) {
		return dao.commentsDelete(num);
	}

	

	
	
	
	
}
