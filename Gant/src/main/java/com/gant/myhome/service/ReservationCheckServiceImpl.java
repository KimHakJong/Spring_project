package com.gant.myhome.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gant.myhome.domain.ReservationCheck;
import com.gant.myhome.mybatis.mapper.ReservationCheckMapper;

@Service
public class ReservationCheckServiceImpl implements ReservationCheckService {
	
	private ReservationCheckMapper dao;

	@Autowired
	public ReservationCheckServiceImpl(ReservationCheckMapper dao) {
		this.dao = dao;
	}


	@Override
	public List<ReservationCheck> getTime(Map<String, String> parameter_map) {
		return dao.getTime(parameter_map);
	}
	
}