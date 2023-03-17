package com.gant.myhome.common;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/* 호출되는 비즈니스 메서드의 정보를 JoinPoint 인터페이스로 알 수 있습니다.
 1.Signature getSignature() : 호출한 메서드에 대한 정보를 구합니다. 
 2.Object getTarget() : 호출한 비즈니스 메서드를 포함하느 비즈니스 객체를 구합니다.
 3.getClass().getName(): 클래스 이름을 구합니다.
 4.proceedion.getSignature().getName() : 호출되는 메서드 이름을 구합니다.
 */

//공통으로 처리할 로직을 BeforeAdvice 클래스에 beforeLog()메서드로 구현합니다.
//Advice : 횡단 관심에 해당하는 공통 기능을 의미하며 독립된 클래스의 메서드로 작성됩니다.
// Advice 클래스는 스프링 설정 파일에서 <bean>으로 등록하거나 @Service 애노테이션을 이용합니다.

//@Service
//@Aspect //@Aspect가 설정된 클래스는 Pointcut과 Advice를 결합하는 설정이 있어야 합니다.
public class BeforeAdvice2 {
	
	private static final Logger logger = LoggerFactory.getLogger(BeforeAdvice2.class);
    
	@Before("execution(* com.gant.myhome..*lmpl.get*(..))")
	public void beforeLog(JoinPoint proceeding) {
		logger.info("===============================");
		logger.info("[BeforeAdvice] : 비지니스 로직 수행 전 동작합니다.");
		logger.info("[BeforeAdvice] : "
				+ proceeding.getTarget().getClass().getName()
				+"의 " + proceeding.getSignature().getName() + "() 호출합니다.");
		logger.info("===============================");
	}
	
}
