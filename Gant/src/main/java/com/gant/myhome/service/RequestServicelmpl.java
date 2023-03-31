package com.gant.myhome.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gant.myhome.domain.Members;
import com.gant.myhome.mybatis.mapper.RequestMapper;


@Service
public class RequestServicelmpl implements RequestService {


	private RequestMapper dao;
		
	@Autowired
	public RequestServicelmpl(RequestMapper dao) {
		this.dao = dao;
	}
    
	
	
	//참조자 명단 리스트
	@Override
	public List<Members> getMembersList() {
		return dao.getMembersList();
		
	}


    // 참조자 명단 갯수
	@Override
	public int getMembersCount() {
		return dao.getMembersCount();
	}


	@Override
	public List<Members> MemberSearchList(String search_name) {
		return  dao.MemberSearchList(search_name);
	}

    
	
	


	
}
