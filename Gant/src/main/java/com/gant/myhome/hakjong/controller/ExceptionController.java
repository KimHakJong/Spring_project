package com.gant.myhome.hakjong.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ExceptionController {
	
	private static final Logger logger
	               = LoggerFactory.getLogger(ExceptionController.class);
	
	//common메소드는 Exception 타입으로 처리하는 모드 예외를 처리하도록 설정
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e , HttpServletRequest request) {
		logger.info(e.toString());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/common");
		mav.addObject("exception" , e);
		mav.addObject("url" , request.getRequestURI());
		return mav;
	}
   
	
	//web.xml 설정 변경해야 합니다.
	//서버 start한 후 http://localhost:8088/myhome4/list.bo로 접속해보세요
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerError400(Exception e , HttpServletRequest request) {
		logger.info(e.toString());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/404");
		mav.addObject("exception" ,"404 오류 발생했습니다.");
		mav.addObject("url" , request.getRequestURI());
		return mav;

	}

	
	
	}
