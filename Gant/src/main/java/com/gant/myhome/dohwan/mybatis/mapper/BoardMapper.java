package com.gant.myhome.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gant.myhome.domain.Board;

/*
 Mapper 인터페이스란 매퍼 파일에 기재된 SQL을 호출하기 위한 인터페이스 입니다.
 Mybatis-Spring은 Mapper 인터페이스를 이용해서 실제 SQL 처리가 되는 클래스를 자동으로 생성합니다. 
 */

@Mapper
public interface BoardMapper {
	    
	    //글 갯수 구하기
		public int getListCount();
		
		//글 목록 보기
		public List<Board> getBoardList(HashMap<String,Integer> map);
		
		//글 내용 보기
		public Board getDetail(int num);
		
		//글 답변
		public int boardReply(Board board);
		
		//글 수정
		public int boardModify(Board modifyboard);
		
		//글 삭제
		public int boardDelete(Board board);
		
		//조회수 업데이트
		public int setReadCountUpdate(int num);
		
		//글쓴이인지 확인
		public Board isBoardWriter(HashMap<String,Object> map);
		
		//글 등록하기
		public void insertBoard(Board board);
		
		//BOARD_RE_SEQ값 수정
		public int boardReplyUpdate(Board board);
		
		// 삭제 파일 리스트 
		public List<String> getDeleteFileList();
	    
		//파일 삭제 
		public void deleteFileList(String filename);
	 
		
		
}