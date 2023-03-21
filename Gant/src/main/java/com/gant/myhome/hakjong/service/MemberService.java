package com.gant.myhome.hakjong.service;

import java.util.List;

import com.gant.myhome.hakjong.domain.Members;

public interface MemberService {

	public int idCheck(String id);
	 
	public int insert(Members m);

	public int passUpdate(Members m);
	
	public String findIdCheck(String name, String email);
	
	public String findPassCheck(String id, String name, String email);

	public int getMembersCount(String searchfield, String searchword);

	public List<Members> getMembersList(String searchfield, String searchword, int page, int limit);

	public String isAdminHuman(String id);

	public String selectByDname(String department);

	public Members getMemberInfo(String id);

	

	
}
