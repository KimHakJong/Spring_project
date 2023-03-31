package com.gant.myhome.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.gant.myhome.domain.Members;

import com.gant.myhome.service.RequestService;


@Controller
@RequestMapping(value = "/request")//http://localhost:8088/myhome4/board/ 로 시작하는 주소 맴핑
public class RequestController {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
	
	private RequestService requestservice;
	
	@Autowired
	public RequestController(RequestService requestservice) {
		this.requestservice = requestservice;
	}

	
	@RequestMapping(value = "/getMian" , method = RequestMethod.GET)
	public ModelAndView getmain(
			Principal principal,
			ModelAndView mv)  {
		
	
		
		mv.setViewName("request/getmain");
		return mv;
	}
    

	@RequestMapping(value = "/writeOvertime" , method = RequestMethod.GET)
	public ModelAndView writeOvertime(
			ModelAndView mv
			)  {
		
		int membercount = requestservice.getMembersCount();
		List<Members> memberlist = requestservice.getMembersList();
		
	
		mv.setViewName("request/overtime");
		mv.addObject("memberlist",memberlist);
		mv.addObject("membercount",membercount);
		return mv;
	}
	
	
	@RequestMapping(value = "/writeVacation" , method = RequestMethod.GET)
	public ModelAndView writeVacation(
			ModelAndView mv
			)  {
		
		int membercount = requestservice.getMembersCount();
		List<Members> memberlist = requestservice.getMembersList();
		
	
		mv.setViewName("request/vacation");
		mv.addObject("memberlist",memberlist);
		mv.addObject("membercount",membercount);
		return mv;
	}
	
	//참조자 명단 검색 시 ajax
	@ResponseBody
	@RequestMapping(value = "/searchMemberList" , method = RequestMethod.GET)
	public List<Members> searchMemberList(
			ModelAndView mv,
			@RequestParam(value = "name", defaultValue = "", required = false) String search_name
			)  {
		
		List<Members> memberlist = requestservice.MemberSearchList(search_name.trim());
		   
		return memberlist;
	}
	
	
	
	
	
}
