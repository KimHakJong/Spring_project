package com.gant.myhome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gant.myhome.domain.ProjectCalendar;
import com.gant.myhome.service.ProjectCalendarService;


@Controller
@RequestMapping(value="/pcalendar")
// 

public class ProjectCalendarController {
   
   private static final Logger logger = LoggerFactory.getLogger(ProjectCalendarController.class);
   
   private ProjectCalendarService calservice;

      
   @Autowired
   public ProjectCalendarController(ProjectCalendarService calservice) {
	   this.calservice = calservice;

   }


   
   @RequestMapping(value="/cal", method=RequestMethod.GET)
   public ModelAndView CalList(ModelAndView mv, @RequestParam("p_no") String p_no) 
   {
		
       JSONArray jsonArr = new JSONArray();
       
       Map<String, Object> obj = new HashMap<>();
       
       p_no = p_no.replaceAll(",", "");
       
       List<ProjectCalendar> list = calservice.getCalList(p_no);  
	 	  
	   
	   System.out.println("todolist에서 받아온 p_no : " + p_no);
	      
	   for(ProjectCalendar c : list) {
		   	   		   
		   JSONObject jsonObj = new JSONObject();
		   obj.put("title", c.getTitle());
		   obj.put("p_no", c.getP_no());
		   obj.put("id", c.getId());
		   obj.put("name", c.getName());
		   obj.put("start", c.getStartday());
		   obj.put("end", c.getEndday());
		   obj.put("allDay", true);
		   
		   jsonObj = new JSONObject(obj);
		   jsonArr.add(jsonObj);

	   }
	   System.out.println(jsonArr);
	   
	   
	   mv.addObject("event", jsonArr);
	   mv.addObject("p_no", p_no);
	   
	   mv.setViewName("/todolist/calendar2");
	   
	   System.out.println(mv);  
	   
	   return mv;

	}
   
   @RequestMapping(value="/add", method=RequestMethod.POST)//name이 loginid
   public String addCal( @RequestParam("start") String startday,
		   			@RequestParam("p_no") String p_no,
		   			@RequestParam("end") String endday,
   					@RequestParam("id") String id,
   					@RequestParam("name") String name,
   					@RequestParam("title") String title) {
	   
	   ProjectCalendar c = new ProjectCalendar();
	   
	   
	   
	   c.setName(name);
	   c.setId(id);
	   c.setP_no(p_no);
	   c.setStartday(startday);
	   c.setEndday(endday);
	   c.setTitle(title);
	   

	   
	   calservice.add(c);
	   
	   System.out.println("일정 추가 메소드 호출함");
	   
	   return "redirect:cal";
	   
   }
   		
   @ResponseBody
   @RequestMapping(value="/getadmin")
   
   public String getadmin2(@RequestParam("id") String id) {

	   String admin = calservice.getadminid(id);

	   
	   return admin;
   }
   
   @ResponseBody
   @RequestMapping(value="/gethost")
   public String gethostid(@RequestParam("p_no") String p_no) {

	   System.out.println(p_no);
	   String host = calservice.gethostid(p_no);

	   
	   return host;
   }
   
   @RequestMapping(value="/update", method=RequestMethod.POST)
   public String updateCal( @RequestParam("start") String startday, 
		   			@RequestParam("p_no") String p_no,
		   			@RequestParam("end") String endday,
   					@RequestParam("id") String id,
   					@RequestParam("title") String title) {
	   
	   System.out.println("수정 메소드 시작");

	   ProjectCalendar c = new ProjectCalendar();
	   	   
	   c.setId(id);
	   c.setStartday(startday);
	   c.setEndday(endday);
	   c.setTitle(title);
	   
	   
	   
	   int result = 0;
	   result = calservice.update(c);
	   
	   if(result != 0)
		   System.out.println("수정 성공");
	   
	   return "redirect:cal";
	   
   }
   
   @RequestMapping(value="/delete", method=RequestMethod.POST)
   public String delcal( @RequestParam("id") String id,
		   			@RequestParam("p_no") String p_no,
   					@RequestParam("title") String title) {
	   
	   
	   ProjectCalendar c = new ProjectCalendar();
	   	   
	   c.setId(id);
	   c.setTitle(title);

	   
	   int result = 0;
	   result = calservice.caldelete(id);
	   
	   if(result != 0)
		   System.out.println("삭제 성공");
	   
	   return "redirect:cal";
	   
   }
   
}   
   

   
