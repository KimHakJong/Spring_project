package com.gant.myhome.service;

import java.util.List;

import com.gant.myhome.domain.Members;

public interface RequestService {
    
	// 참조자 명단 리스트
	List<Members> getMembersList();
    
	//참조자 명단 갯수
	int getMembersCount();
    
	//검색 참조자 명단 리스트
	List<Members> MemberSearchList(String trim);
    
	

}
