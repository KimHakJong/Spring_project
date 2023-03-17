package com.gant.myhome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gant.myhome.domain.Comment;
import com.gant.myhome.service.CommentService;


//@Controller
@RestController // @Controller+@ResponseBody
@RequestMapping(value = "/comment")//http://localhost:8088/myhome4/comment/ 로 시작하는 주소 맴핑
public class CommentController {
	
	
	private CommentService commentService ;
		
	@Autowired
	public CommentController(CommentService commentService) {
	this.commentService = commentService;
	}
	
	
	//@ResponseBody :각 메서드의 실행 결과는 JSON으로 변환되어 HTTP Respons BODY에 설정됩니다.
	//@ResponseBody
	@PostMapping(value = "/list")
	public Map<String,Object> CommentList(int board_num , int page){
		List<Comment> list = commentService.getCommentList(board_num, page);
		int listcount = commentService.getListCount(board_num);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list",list);
		map.put("listcount",listcount);
	    return map;	
	}
	
	
	
	@PostMapping(value = "/add")
	public int add(Comment comment){
		int result = commentService.commentsInsert(comment);		
		return result;
	}

    
	
	@PostMapping(value = "/update")
	public int Update(Comment comment){
		int result = commentService.commentsUpdate(comment);		
		return result;
	} 

	@PostMapping(value = "/delete")
	public int Delete(int num){
		int result = commentService.commentsDelete(num);		
		return result;
	} 

	
}
