package com.gant.myhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gant.myhome.mybatis.mapper.ReservationMapper;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	private ReservationMapper dao;

	@Autowired
	public ReservationServiceImpl(ReservationMapper dao) {
		this.dao = dao;
	}
	
	
}
