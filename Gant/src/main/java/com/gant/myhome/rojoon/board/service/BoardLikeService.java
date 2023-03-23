package com.gant.myhome.rojoon.board.service;

public interface BoardLikeService {
    
    
	//좋아요 테이블 유무 확인
	public int selectLike(String id, int board_num);
    
	//좋아요 테이블 생성
	public void insertLike(String id, int board_num);
    
	//id와 테이블번호에 해당하는 like_check 값을 가져온다.
	public String selectLikeCheck(String id, int board_num);
		
}
