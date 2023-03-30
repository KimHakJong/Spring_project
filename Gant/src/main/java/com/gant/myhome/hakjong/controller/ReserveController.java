package com.gant.myhome.hakjong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gant.myhome.hakjong.service.MemberService;
import com.gant.myhome.hakjong.service.ReserveService;

@Controller
@RequestMapping(value="/reserve")
public class ReserveController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	private ReserveService reserveservice;
	private MemberService memberservice;
	
	@Autowired
	public ReserveController(ReserveService reserveservice, MemberService memberservice) {
		this.reserveservice = reserveservice;
		this.memberservice = memberservice;
	}
	
	@GetMapping(value="/main")
	public ModelAndView reserveMain(ModelAndView mv) {
		mv.setViewName("reserve/reserveMain");
		return mv;
	}
}
