package org.kpu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.kpu.di.domain.StudentVO;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MemberAspect {
	
	   @Before("execution(* read(String))")
	   public void beforeMethod(JoinPoint jp) {
	        System.out.println("[BeforeMethod] : 메소드 호출 전");
	        Signature sig = jp.getSignature();
	        System.out.println(" 메소드 이름:" + sig.getName());
	        Object[] obj = jp.getArgs();
	        System.out.println(" 인수 값:" + obj[0]);
	   }
	    @After("execution(* read(String))")
	    public void afterMethod() {
	        System.out.println("[AfterMethod] : 메소드 호출 후");
	    }
	    @AfterReturning(value = "execution(* read(String))", returning = "member")
	    public void afterReturningMethod(JoinPoint jp, StudentVO member) {
	    	System.out.println("[afterReturningMethod] : 메소드 호출 후");
	        Signature sig = jp.getSignature();
	        System.out.println(" 메소드 이름:" + sig.getName());
	        Object[] obj = jp.getArgs();
	        System.out.println(" 인수 값:" + obj[0]);
	    }

	    @Around("execution(* read(String))")
//    	@Around("within(org.kpu.di.service.MemberServiceImpl)")
	    public StudentVO aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
	        System.out.println("[AroundMethod Before] : 메소드 호출 전");
	        Signature sig = pjp.getSignature();
	        System.out.println(" 메소드 이름:" + sig.getName());
	        StudentVO member = (StudentVO) pjp.proceed();
	        System.out.println("[AroundMethod after] : 메소드 호출 후");
	        System.out.println(" 메소드 이름:" + sig.getName());
	        return member;
	    }
	    @AfterThrowing(value = "execution(* read(String))", throwing = "ex")
	    public void afterThrowingMethod(Throwable ex) {
	        // 메소드 호출이 예외를 내보냈을 때 호출되는 Advice
	        System.out.println("[AfterThrowingMethod] : 예외 발생 후");
	        System.out.println("exception value = " + ex.toString());
	    }
}
