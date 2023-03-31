package com.gant.myhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gant.myhome.mybatis.mapper.ReservationCountMapper;

@Service
public class ReservationCountServiceImpl implements ReservationCountService {
	
	private ReservationCountMapper dao;

	@Autowired
	public ReservationCountServiceImpl(ReservationCountMapper dao) {
		this.dao = dao;
	}
	
	
}
