package com.gant.myhome.hakjong.mybatis.mapper;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gant.myhome.hakjong.domain.Members;

@Mapper
public interface ProjectMainMapper {

	public Members idCheck(String id);
	 
	public int insert(Members m);

	public int passUpdate(Members m);
	
	public int getMembersCount(HashMap<String, String> map);

	public List<Members> getMembersList(HashMap<String, Object> map);

	public Members isAdminHuman(String id);

	public List<String> selectByDname(String department);

	public Members findIdCheck(String name);

	public Members findPassCheck(String id);



	 
}
