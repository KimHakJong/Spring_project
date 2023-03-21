package com.gant.myhome.hakjong.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gant.myhome.hakjong.domain.Members;
import com.gant.myhome.hakjong.service.MemberService;

@Controller
@RequestMapping(value="/small")
public class SmallController {
	
private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
	
	private MemberService  memberservice;
		
	@Autowired
	public SmallController(MemberService memberservice) {
	this.memberservice = memberservice;
	}
	
	@RequestMapping(value="/chat")
	public ModelAndView chatView(Principal userPrincipal, ModelAndView mv, HttpServletResponse response) throws Exception {
		
		String id = userPrincipal.getName();
		
		if(id==null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용해주세요');");
			out.println("window.close();");
			out.println("location.href='../member/login';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		Members member = memberservice.getMemberInfo(id); //채팅에 필요한 name,프로필사진값 가져옴
		
		mv.addObject("member",member);
		mv.setViewName("chat/chat");
		return mv;
	}
}
