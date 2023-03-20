package com.gant.myhome.hakjong.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gant.myhome.hakjong.domain.Members;
import com.gant.myhome.hakjong.mybatis.mapper.MembersMapper;

@Service
public class MemberServicelmpl implements MemberService {

	private MembersMapper dao;
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public MemberServicelmpl(MembersMapper dao, PasswordEncoder passwordEncoder) {
		this.dao = dao;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public int idCheck(String id) {
		Members member = dao.idCheck(id);
		return (member==null) ? 0 : 1 ;  //0은 아이디가 존재하지 않는경우
	                                      //1은 아이디가 존재하는 경우
	} 

	@Override
	public int insert(Members m) {  
		return dao.insert(m);
	}

	@Override
	public String findIdCheck(String name, String email) {
		Members m = dao.findIdCheck(name);
		if(m==null) { // 이름에 대한 정보가 없음
			return "";
		}else {
			if (m.getEmail().equals(email)) {
				return m.getId();
			}else {
				return "noemail";
			}
		}
	}
	
	@Override
	public String findPassCheck(String id, String name, String email) {
		Members m = dao.findPassCheck(id);
		if(m.getName().equals(name) && m.getEmail().equals(email)) {
			return m.getPassword();
		}else if (m.getName().equals(name) && !m.getEmail().equals(email)) {
			return "noemail";
		}else {
			return "noname";
		}
	}

	@Override
	public int getMembersCount(String searchfield, String searchword) {
		HashMap<String,String> map = new HashMap<String,String>();
		if(!searchfield.equals("")) {
			map.put("searchfield", searchfield);
			map.put("searchword", "%"+searchword+"%");
		}
		
		return dao.getMembersCount(map);
	}

	@Override
	public List<Members> getMembersList(String searchfield, String searchword, int page, int limit) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		int startrow = (page-1)*limit+1;
		int endrow = startrow+limit-1;
		
		if(!searchfield.equals("")) {
			map.put("searchfield", searchfield);
			map.put("searchword", searchword);
		}
		
		map.put("start", startrow);
		map.put("end", endrow);
		
		return dao.getMembersList(map);
	}

	@Override
	public String isAdminHuman(String id) {
		Members m = dao.isAdminHuman(id);
		if(m.getAdmin().equals("true") || m.getDepartment().equals("인사부")) {
			return "true";
		}else {
			return "false";
		}
	}

	@Override
	public String selectByDname(String department) {
		List<String> list = dao.selectByDname(department);
		
		String names ="";
		for(String name : list) {
			names += name + ",";
		}
		return names;
	}

	

}
