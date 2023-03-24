package com.gant.myhome.hakjong.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gant.myhome.hakjong.domain.Members;
import com.gant.myhome.hakjong.service.MemberService;
import com.gant.myhome.hakjong.service.ProjectMainService;

@Controller
@RequestMapping(value="/pmain")
public class ProjectMainController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectMainController.class);
	
	private ProjectMainService projectmainservice;
	private MemberService memberservice;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public ProjectMainController(ProjectMainService projectmainservice, MemberService memberservice, PasswordEncoder passwordEncoder) {
		this.projectmainservice = projectmainservice;
		this.memberservice = memberservice;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping(value="/view")
	public ModelAndView mainView(Principal principal, ModelAndView mv, HttpSession session) {
			
			String id = principal.getName();
			Members m = memberservice.getMemberInfo(id);
				
			session.setAttribute("profileimg", m.getProfileimg());
			session.setAttribute("name", m.getName());
			mv.setViewName("pmain/view");
			return mv;
	}
	
}
