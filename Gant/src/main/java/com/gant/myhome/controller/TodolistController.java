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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gant.myhome.service.TodolistService;


@Controller
@RequestMapping(value="/todolist")
//http://localhost:8088/gant/todolist/ 로 시작하는 주소 

public class TodolistController {
   
   private static final Logger logger = LoggerFactory.getLogger(TodolistController.class);
   
   private TodolistService todolistservice;

      
   @Autowired
   public TodolistController(TodolistService todolistservice) {
	   this.todolistservice = todolistservice;

   }
   
   @RequestMapping(value="/receive", method=RequestMethod.GET)
   public ModelAndView Todolist(ModelAndView mv, 
		   						@RequestParam("p_no") int p_no, 
		   						@RequestParam(value="p_mids", required = false) String p_id,
		   						@RequestParam(value="p_mnames", required = false) String p_name)  
   {
		/*
       JSONArray jsonArr = new JSONArray();
       
       Map<String, Object> obj = new HashMap<>();
              
	   List<Calendar> list = calservice.getCalList();  
	 	   
	      
	   for(Calendar c : list) {
		   	   		   
		   JSONObject jsonObj = new JSONObject();
		   obj.put("title", c.getTitle());
		   obj.put("id", c.getId());
		   obj.put("name", c.getName());
		   obj.put("start", c.getStartday());
		   obj.put("end", c.getEndday());
		   obj.put("allDay", true);
		   
		   jsonObj = new JSONObject(obj);
		   jsonArr.add(jsonObj);

	   }
	   System.out.println(jsonArr);
	   */
	   Map<String, Object> obj = new HashMap<>();
	   
	   obj.put("p_no", p_no);
	   obj.put("p_id", p_id);
	   obj.put("p_name", p_name);
	   
	   System.out.println(p_no);
	   System.out.println(p_id);
	   System.out.println(p_name);
	   System.out.println("obj 결과" + obj);

	   mv.addObject("p_no", p_no);
	   mv.addObject("p_id", p_id);
	   mv.addObject("p_name", p_name);
	   
	   System.out.println("mv 결과" + mv);
	   

	   mv.setViewName("/todolist/todolist");
	   	   
	   return mv;

	}
   /*
   @RequestMapping(value="/add", method=RequestMethod.POST)//name이 loginid
   public String addCal( @RequestParam("start") String startday, 
		   			@RequestParam("end") String endday,
   					@RequestParam("id") String id,
   					@RequestParam("name") String name,
   					@RequestParam("title") String title) {
	   
	   Calendar c = new Calendar();
	   
	   
	   
	   c.setName(name);
	   c.setId(id);
	   c.setStartday(startday);
	   c.setEndday(endday);
	   c.setTitle(title);
	   

	   
	   calservice.add(c);
	   
	   System.out.println("일정 추가 메소드 호출함");
	   
	   return "redirect:list";
	   
   }
   		
   @ResponseBody
   @RequestMapping(value="/getadmin")
   //name이 loginid
   public String getadmin2(@RequestParam("id") String id) {

	   String admin = calservice.getadminid(id);

	   
	   return admin;
   }
   
   @RequestMapping(value="/update", method=RequestMethod.POST)
   public String updateCal( @RequestParam("start") String startday, 
		   			@RequestParam("end") String endday,
   					@RequestParam("id") String id,
   					@RequestParam("title") String title) {
	   
	   System.out.println("수정 메소드 시작");

	   Calendar c = new Calendar();
	   	   
	   c.setId(id);
	   c.setStartday(startday);
	   c.setEndday(endday);
	   c.setTitle(title);
	   
	   
	   
	   int result = 0;
	   result = calservice.update(c);
	   
	   if(result != 0)
		   System.out.println("수정 성공");
	   
	   return "redirect:list";
	   
   }
   
   @RequestMapping(value="/delete", method=RequestMethod.POST)
   public String delcal( @RequestParam("id") String id,
   					@RequestParam("title") String title) {
	   
	   
	   Calendar c = new Calendar();
	   	   
	   c.setId(id);
	   c.setTitle(title);

	   
	   int result = 0;
	   result = calservice.caldelete(id);
	   
	   if(result != 0)
		   System.out.println("삭제 성공");
	   
	   return "redirect:list";
	   
   }*/
   
   
   

   
}