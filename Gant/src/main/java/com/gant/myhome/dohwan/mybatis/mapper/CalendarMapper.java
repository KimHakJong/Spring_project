package com.gant.myhome.dohwan.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gant.myhome.dohwan.domain.Calendar;

@Mapper
public interface CalendarMapper {

		
		
		public List<Calendar> getCalList();
			   
		public int caldelete(String id);
			   
		public String getadminid(String name);
				
		public int update(Calendar c);

		public int add(Calendar c, String admin);




}
