package com.gant.myhome.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gant.myhome.domain.Members;
import com.gant.myhome.domain.ReservationCheck;
import com.gant.myhome.domain.ReservationItem;
import com.gant.myhome.service.MemberService;
import com.gant.myhome.service.ReservationCheckService;
import com.gant.myhome.service.ReservationCountService;
import com.gant.myhome.service.ReservationItemService;
import com.gant.myhome.service.ReservationService;

@Controller
@RequestMapping(value="/reserve")
public class ReservationController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	private MemberService memberservice;
	private ReservationService reservationservice;
	private ReservationCheckService reservationcheckservice;
	private ReservationItemService reservationitemservice;
	private ReservationCountService reservationcountservice;
	
	@Autowired
	public ReservationController(MemberService memberservice, ReservationService reservationservice, 
						ReservationCheckService reservationcheckservice, ReservationItemService reservationitemservice,
						ReservationCountService reservationcountservice) {
		this.memberservice = memberservice;
		this.reservationservice = reservationservice;
		this.reservationcheckservice = reservationcheckservice;
		this.reservationitemservice = reservationitemservice;
		this.reservationcountservice = reservationcountservice;
	}
	
	@GetMapping(value="/main")
	public ModelAndView reserveMain(Principal userPrincipal, ModelAndView mv) {
		//관리자인지아닌지 구별하여 자원추가버튼 활성화
		String id = userPrincipal.getName();
		Members members = memberservice.getMemberInfo(id);
		mv.addObject("admin",members.getAdmin());
		mv.setViewName("reserve/reserveMain");
		
		//자원종류, 자원명 불러옴 (예약화면 처음 불러올 때: 자원많은 종류가 표시)
		List<String> types = reservationitemservice.getTypeList();
		String first_type = types.get(0);
		List<String> resources_by_type = reservationitemservice.getResourcesByType(first_type);
		
		mv.addObject("types",types);
		mv.addObject("resources_by_type",resources_by_type);
		
		return mv;
	}
	
	
	@ResponseBody
	@PostMapping(value="/loadResource_ajax")
	public List<String> loadResource_ajax(String type){
		List<String> resources_by_type = reservationitemservice.getResourcesByType(type);
		
		return resources_by_type;
	}
	
	@ResponseBody
	@PostMapping(value="/loadTime_ajax")
	public List<ReservationCheck> loadTime_ajax(String resource_name, String day){
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("resource_name", resource_name);
		map.put("day", day);
		
		//자원명과 날짜에 해당하는 예약된 시간을 list에 담는다.
		List<ReservationCheck> list = reservationcheckservice.getTime(map);
		return list;
	}
	
	@GetMapping(value="/insert")
	public String reserveInsert() {
		return "reserve/reserveInsert";
	}
	
	@PostMapping(value="/addResource")
	public String addResource(ReservationItem RItem, RedirectAttributes rattr, Model model) {
		int result = reservationitemservice.insert(RItem);
		if(result==1) {
			rattr.addFlashAttribute("add_item",result);
			return "redirect:main";
		}else {
			model.addAttribute("message","자원등록 실패");
			return "error/error";
		}
	}
}
