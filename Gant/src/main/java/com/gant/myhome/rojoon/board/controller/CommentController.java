package com.gant.myhome.rojoon.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gant.myhome.rojoon.board.domain.Comment;
import com.gant.myhome.rojoon.board.service.CommentService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;





//@Controller
@RestController // @Controller+@ResponseBody
@RequestMapping(value = "/comment")//http://localhost:8088/myhome4/comment/ 로 시작하는 주소 맴핑
public class CommentController {
	
	
	private CommentService commentService ;
		
	@Autowired
	public CommentController(CommentService commentService) {
	this.commentService = commentService;
	}
	
	//response에 PrintWriter를 사용하여 스크립트를 사기위한 메서드
	public static void init(HttpServletResponse response) {
	       response.setContentType("text/html; charset=utf-8");
	       response.setCharacterEncoding("utf-8");
	}
	
	
	//@ResponseBody :각 메서드의 실행 결과는 JSON으로 변환되어 HTTP Respons BODY에 설정됩니다.
	//@ResponseBody
	@PostMapping(value = "/list")
	public Map<String, Object> CommentList(
			int comment_board_num ,
			int state, // state == 1 -> 댓글 등록순  ,state == 2 -> 댓글 최신순
			HttpServletResponse response
			) throws IOException{
		
		// comment_board_num에 해당하는 댓글 갯수
		int listcount = commentService.getListCount(comment_board_num);
	    	

		List<Comment> list = commentService.getCommentList(comment_board_num,state);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("boardlist",list);
		map.put("listcount",listcount);
		return map;	
	}
	
	
	
	@PostMapping(value = "/add")
	public String add(Comment comment,
			   HttpServletResponse response) throws IOException{
		
		int result = commentService.commentsInsert(comment);		
		response.getWriter().print(result);
		
		return null;
	}
    
	@PostMapping(value = "/reply")
	public String Update(Comment comment,
			   HttpServletResponse response) throws IOException{
		int result = commentService.commentsreply(comment);		
		response.getWriter().print(result);
		return null;
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
