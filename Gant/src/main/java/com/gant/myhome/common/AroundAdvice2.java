package com.gant.myhome.common;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StopWatch;


//Advice : 횡단 관심에 해당하는 공통 기능을 의미하며 독립된 클래스의 메서드로 작성됩니다.
// Advice 클래스는 스프링 설정 파일에서 <bean>으로 등록하거나 @Service 애노테이션을 이용합니다.
//@Service
//@Aspect
public class AroundAdvice2 {
	
	private static final Logger logger = LoggerFactory.getLogger(AroundAdvice2.class);
	
	/*
	 1.ProceedingJoinPoint 인터페이스는 JoinPoint를 상속했기 때문에 JoinPoint가 가진 모든 메서드를 지원합니다.
	 2.Around Advice에서만 ProceedingJoinPoint을 매개변수로 사용하는데 이 곳에서 proceed()메서드가 필요하기 때문입니다.
	 3.Around Advice인 경우 비즈니스 메서드 실행 전과 후에 실행 되는 데 비즈니스 메서드를 호출하기 위해서는
	   ProceedingJoinPoint의 proceed()메서드가 필요합니다.
	   즉, 클라이언트의 요청을 가로챈 어드바이스는 클라이어트가 호출한 비즈니스 메서드(ServiceImpl의 get으로 시작하는 메서드)를
	   호출하기 위해 ProceedingJoinPoint객체를 매개 변수로 받아 proceed()메서드를 통해서
	   비즈니스 메서드를 호출할 수 있습니다.
	   proceed()메서드 실행 후 메소드의 반환값을 리턴해야 합니다.
	   
	 */
	@Around("execution(* com.gant.myhome..*lmpl.get*(..))")
	public Object aroundLog(ProceedingJoinPoint proceeding) throws Throwable {
	
		logger.info("===============================");
		logger.info("[Around Advice의 before] : 비지니스 메서드 수행 전 입니다.");
		logger.info("===============================");
		
		StopWatch sw = new StopWatch();
		sw.start();
		
		//이 코드의 이전과 이후에 공통 기능을 위한 코드를 위치 시키면 됩니다.
		// 대상 객체의 메서드 (예 - BoardServicelmpl.getListcount())를 호출)
		//proceeding.proceed() before after의 기준이된다.
		Object result = proceeding.proceed();
		
		//호출되는 메서드에 대한 정보를 구합니다.
		Signature sig = proceeding.getSignature();
		
		/*
		 * org.aspectj.lang.Signature 인터페이스는 호출되는 메서드와 관련된 정보를 제공합니다.
		 * String getName() : 메서드의 이름을 구한다.
		 */
		
		
		/*
		 * 1. proceeding.getTarget().getClass().getSimpleName() : Target클래스의 이름을 가져옵니다.
		 * 2. Object[] getArgs : 클라이언트가 메서드를 호출할 때 넘겨준 인사 목록을 Object배열로 리턴합니다.
		 * */
		
		// BoardServicelmpl.getBoardList([1,10])
		logger.info("===============================");
		logger.info("[Around Advice의 after]: " + proceeding.getTarget().getClass().getSimpleName() + "."
				 + sig.getName()+"(" + Arrays.toString(proceeding.getArgs()) + ")");
        
		
		//getBoardList() 메소드 수행 시간 : 16(ms) 초
		logger.info("[Around Advice의 after]: " + proceeding.getSignature().getName() + "() 메소드 수행시간 :"
				 + sw.getTotalTimeMillis()+ "(ms)초");
		
		//com.gant.myhome.service.BoardServicelmpl
		logger.info("[Around Advice의 after]:"
				+ proceeding.getTarget().getClass().getName());
	
	
		logger.info("proceeding.proceed() 실행 후 반환값:" + result);
		logger.info("===============================");
		
		return result;
	
	}
	
}
