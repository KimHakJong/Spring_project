package com.gant.myhome.rojoon.board.service;

import java.util.List;

import com.gant.myhome.rojoon.board.domain.Board;



public interface BoardService {
    
	//전체 글 갯수 구하기
	public int getListCount();
	
	//글 목록 보기 (공지사항이 위로간다.)
	public List<Board> getBoardList(int page, int limit);
    
	//검색 글 갯수
	public int getSearchListCount(String search_name);
    
	//검색 리스트
	public List<Board> getSearchBoardList(int page, int limit, String search_name);
    
	//공지 게시글 수
	public int getNoticeCount();
   
	//글 등록하기
	public void insertBoard(Board board);
    
	//admin 계정 확인 
	public String getadmindate(String id);
	

//	//글 내용 보기
//	public Board getDetail(int num);
//	
//	//글 답변
//	public int boardReply(Board board);
//	
//	//답변 수정
//	public int boardReplyUpdate(Board board);
//	
//	//글 수정
//	public int boardModify(Board modifyboard);
//	
//	//글 삭제
//	public int boardDelete(int num);
//	
//	//조회수 업데이트
//	public int setReadCountUpdate(int num);
//	
//	//글쓴이인지 확인
//	public boolean isBoardWriter(int num, String pass);
//	

//    
//	
//	// 삭제 파일 리스트 
//	public List<String> getdeleteFileList();
//    
//	//파일 삭제 
//	public void deleteFileList(String filename);
 
	
	
}
