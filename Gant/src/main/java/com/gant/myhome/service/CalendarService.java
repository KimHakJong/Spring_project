package com.gant.myhome.dohwan.service;



import java.util.List;
import java.util.Map;

import com.gant.myhome.dohwan.domain.Calendar;


public interface CalendarService {
	
	public int add(Calendar c);
		
	public List<Calendar> getCalList();
		   
	
		   
	public String getadminid(String id);
			
	public int update(Calendar c);
	
	public int caldelete(String id);
	
}
