package com.gant.myhome.controller;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gant.myhome.domain.MailVO;
import com.gant.myhome.domain.Member;
import com.gant.myhome.service.MemberService;
import com.gant.myhome.task.SendMail;


@Controller
@RequestMapping(value = "/member")//http://localhost:8088/myhome4/member/ 로 시작하는 주소 맴핑
public class MemberController {
	//import org.slf4j.Logger
	//import org.slf4j.LoggerFactory
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MemberService  memberservice;
	private PasswordEncoder passwordEncoder;
	private SendMail sendMail;
		
	@Autowired
	public MemberController(MemberService memberservice , PasswordEncoder passwordEncoder,SendMail sendMail) {
	this.memberservice = memberservice;
	this.passwordEncoder = passwordEncoder;
	this.sendMail = sendMail ;
	}
	
	
	/*
	<security:remember-me> 설정 후
    로그인 유지를 위한 쿠키의 값 수정
	 */
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv,
			                  @CookieValue(value="remember-me" , required = false) Cookie readCookie,
			                  HttpSession session,
			                  Principal userPrincipal
			                  ) {
		if(readCookie != null) {
			logger.info("저장된 아이디: " + userPrincipal.getName()); //principal.getName() :로그인한 아이디
			mv.setViewName("redirect:/board/list");
		}else {
			mv.setViewName("member/member_loginForm");
			mv.addObject("loginfail" , session.getAttribute("loginfail")); //세션에 저장된 값을 한 번 만 실행될 수 있도록 mv에 저장하고
			session.removeAttribute("loginfail"); // 세션의 값은 제거합니다.
		}

		return mv;
	}
	
	
	//http://localhost:8088/myhome4/member/join
	//회원가입 페이지로 이동
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String join() {
		return "member/member_joinForm";
	}
	
	
	//회원가입 폼에서 아이디 검사
	@RequestMapping(value = "/idcheck" , method = RequestMethod.GET)
	public void idcheck(@RequestParam("id") String id,
			              HttpServletResponse response) throws Exception {		
		int result = memberservice.isId(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
	
	   //회원가입 
		@RequestMapping(value = "/joinProcess" , method = RequestMethod.POST)
		public String joinProcess(Member member, 
				                        RedirectAttributes rattr,
				                        Model model,
				                        HttpServletRequest request)  {		
			
			//비밀번호 암호화 추가
			String encPassword = passwordEncoder.encode(member.getPassword());
			logger.info(encPassword);
			member.setPassword(encPassword);
		
			
			int result = memberservice.insert(member);
			//result = 0 ;
			/*
			 스프링에서 제공하는 RedirectAttributes는 기존의 Servlet에서 사용되던
			 response.sendRedirect()를 사용할 때와 동일한 용도로 사용합니다.
			 리다이렉트로 전송하면 파라미터를 전달하고자 할 때 addAttribute()나 addFlashAttribute()를 사용합니다.
			 예) response.sendRedirect("test?result = 1");
			    => rattr.addAttribute("result" , 1)
			 
			 */
			if(result == 1) {			
				MailVO vo = new MailVO();
				vo.setTo(member.getEmail());
				vo.setContent(member.getId() + "님 회원 가입을 축하드립니다.");
				sendMail.sendMail(vo);
				
				rattr.addFlashAttribute("result", "joinSuccess");
				return "redirect:login";
			}else {
				model.addAttribute("url",request.getRequestURL());
				model.addAttribute("message","회원 가입 실패");
				return "error/error";
			}
			
		}
		
		
		/*/로그인 처리
		@RequestMapping(value = "/loginProcess" , method = RequestMethod.POST)
		public String loginProcess(@RequestParam("id") String id,
				                 @RequestParam("password") String password,
				                 @RequestParam(value = "remember" ,defaultValue="", required = false) String remember,
				              HttpServletResponse response,
				              HttpSession session,
				              RedirectAttributes rattr) {
			int result = memberservice.isId(id,password);
			logger.info("결과 :" + result);
			
			if(result == 1) {
				//로그인 성공
				session.setAttribute("id", id);
				Cookie savecookie = new Cookie("saveid" , id);
				if(!remember.equals("")) {
					savecookie.setMaxAge(60*60);
					logger.info("쿠키저장 : 60 * 60" );
				}else {
					savecookie.setMaxAge(0);
					logger.info("쿠키저장 :0" );
				}
				
				//응답으로 addCookie를해줘야 Coookie 생성
				response.addCookie(savecookie);
				return "redirect:/board/list";
				
			}else {
				rattr.addFlashAttribute("result" , result);
				return "redirect:login";				
			}
		
		}
		*/
		
		
		//로그아웃
		@RequestMapping(value = "/logout" , method = RequestMethod.GET)
			public String logout(HttpSession session) {			
			session.invalidate();
			return "redirect:login";
		}
		
		
		//정보수정
		@RequestMapping(value = "/update" , method = RequestMethod.GET)
			public ModelAndView member_update(Principal principal,
					                          ModelAndView mv ) {			
			String id = principal.getName();
			
			if(id == null) {
				mv.setViewName("redirect:login");
				logger.info("id is null");
			}else {
				Member m  = memberservice.member_info(id);
				mv.addObject("memberinfo" , m);
				mv.setViewName("member/update");
			}
			
			return mv;
		}
		
		
		//회원정보 수정 
		@RequestMapping(value = "/updateProcess" , method = RequestMethod.POST)
		public String updateProcess(Member member,Model model, 
				                    RedirectAttributes rattr,
				                    HttpServletRequest request)  {		
			
			int result = memberservice.update(member);			
			if(result == 1) {
			  rattr.addFlashAttribute("result" , "updateSuccess");
			  return "redirect:/board/list";
			}else {
				model.addAttribute("url",request.getRequestURL());
				model.addAttribute("message","정보 수정 실패");
			return "error/error";
			}

		}
		
		
		//회원리스트
		@RequestMapping(value = "/list")
		public ModelAndView memberList(
				@RequestParam(value = "page",  defaultValue = "1" ,required = false) int page,
				@RequestParam(value = "limit", defaultValue = "3" ,required = false) int limit,
				ModelAndView mv,
				@RequestParam(value = "search_field", defaultValue = "-1" ,required = false) int index,
				@RequestParam(value = "search_word", defaultValue = "" ,required = false) String search_word
				) {
		    int listcount = memberservice.getSearchListCount(index, search_word); //리스트 수를 받아옴	
	        List<Member> list = memberservice.getSearchList(index, search_word, page, limit);		
		    
	        //총 페이지 수
			int maxpage = (listcount + limit - 1) / limit;
			
			//현재 페이지에 보여줄 시작 페이지 수 (1, 11 , 21 등....)
			int startpage = ((page - 1) / 10) * 10 + 1 ;
			
			// 현재 페이지에 보여줄 마지막 페이지 수 (10 , 20 , 30 등....)
			int endpage = startpage + 10 - 1 ;
			
			if(endpage > maxpage) {
				endpage = maxpage;
			}
					
			mv.setViewName("member/member_list");
			mv.addObject("page", page);
			mv.addObject("maxpage" , maxpage);
			mv.addObject("startpage" , startpage);
			mv.addObject("endpage" , endpage);
			mv.addObject("listcount" , listcount);
			mv.addObject("memberlist" , list);
			mv.addObject("search_field" , index);
			mv.addObject("search_word" , search_word);
		
		 return mv;
		}
		
		
		//회원 개인 정보
		@RequestMapping(value = "/info" , method = RequestMethod.GET)
		public ModelAndView member_info(@RequestParam("id") String id,
				                    ModelAndView mv,
				                    HttpServletRequest request) {
		Member m = memberservice.member_info(id);
		//m=null; //오류 확인하는 값
		if(m != null) {
			mv.setViewName("member/member_info");
			mv.addObject("memberInfo" , m);
		}else {
			mv.setViewName("error/error");
			mv.addObject("url",request.getRequestURL());
			mv.addObject("message","회원 가입 실패");
		}
			
		return mv;
		}
		
		
        // 회원정보 삭제 
		@RequestMapping(value = "/delete" , method = RequestMethod.GET)
		public String member_delete(String id) {
			memberservice.delete(id);
			return "redirect:list";	
		}
		
	
}
