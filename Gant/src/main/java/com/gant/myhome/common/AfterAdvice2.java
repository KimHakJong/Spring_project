package com.gant.myhome.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//Advice : 횡단 관심에 해당하는 공통 기능을 의미하며 독립된 클래스의 메서드로 작성됩니다.
// Advice 클래스는 스프링 설정 파일에서 <bean>으로 등록하거나 @Service 애노테이션을 이용합니다.
//@Aspect가 설정된 클래스는 Pointcut과 Advice를 결합하는 설정이 있어야 합니다.
//@Service
//@Aspect
public class AfterAdvice2 {
	
	private static final Logger logger = LoggerFactory.getLogger(AfterAdvice2.class);
    
	@After("execution(* com.gant.myhome..*lmpl.get*(..))")
	public void afterLog(JoinPoint proceeding) {
		logger.info("===============================");
		logger.info("[AfterAdvice] : 비지니스 로직 수행 후 동작합니다.");
		logger.info("[AfterAdvice] : "
				+ proceeding.getTarget().getClass().getName()
				+"의 " + proceeding.getSignature().getName() + "() 호출합니다.");
		logger.info("===============================");
	}
	
}
