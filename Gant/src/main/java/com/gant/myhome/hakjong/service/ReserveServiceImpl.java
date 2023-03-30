package com.gant.myhome.hakjong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gant.myhome.hakjong.mybatis.mapper.ReserveMapper;

@Service
public class ReserveServiceImpl implements ReserveService {
	
	private ReserveMapper dao;

	@Autowired
	public ReserveServiceImpl(ReserveMapper dao) {
		this.dao = dao;
	}
	
	
}
