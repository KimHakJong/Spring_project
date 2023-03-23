package com.gant.myhome.dohwan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gant.myhome.dohwan.domain.Calendar;
import com.gant.myhome.dohwan.service.CalendarService;


@Controller
@RequestMapping(value = "/calendar")//http://localhost:8088/myhome4/calendar/ 로 시작하는 주소 
public class CalendarController {
   
   private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
   
   private CalendarService calservice;

      
   @Autowired
   public CalendarController(CalendarService calservice) {
	   this.calservice = calservice;

   }
   
   @RequestMapping(value="/list", method=RequestMethod.GET)
   public String CalList(Model mv) 
   {
		
	   List<Calendar> list = calservice.getCalList();  
		 
	   List<Map<String, Object>> event = new ArrayList<>();
	   
	      
	   for(Calendar c : list) {
		   Map<String, Object> obj = new HashMap<>();
		   		   
		   obj.put("title", c.getTitle());
		   obj.put("id", c.getId());
		   obj.put("name", c.getName());
		   obj.put("startday", c.getStart());
		   obj.put("endday", c.getEnd());
		   
		   String ad = calservice.getadminid(c.getId());
		   obj.put("admin",ad);
		   
		   event.add(obj);
		   		   
	   }
	   
	   mv.addAttribute("event", event);
	   
	   System.out.println(event);
	   
	   return "/calendar/calendar2";

	}

   
}