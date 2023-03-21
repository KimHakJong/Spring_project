package com.gant.myhome.hakjong.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gant.myhome.hakjong.mybatis.mapper.ProjectMainMapper;

@Service
public class ProjectMainServiceImpl implements ProjectMainService {

	private ProjectMainMapper dao;
	private PasswordEncoder passwordEncoder;
	
	

}
