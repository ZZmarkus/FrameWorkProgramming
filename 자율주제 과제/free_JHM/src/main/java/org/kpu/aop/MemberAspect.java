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
import org.kpu.myweb.domain.ReservVO;
import org.kpu.myweb.domain.StudentVO;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MemberAspect {
	
	   @Before("execution(* update(..))")
	   public void beforeMethod(JoinPoint jp) {
	        System.out.println("[BeforeMethod] : 메소드 호출 전");
	        Signature sig = jp.getSignature();
	        System.out.println(" 메소드 이름:" + sig.getName());
	        Object[] obj = jp.getArgs();
	        System.out.println(" 인수 값:" + obj[0]);
	        Object target = jp.getTarget();
	        System.out.println(" 객체 값:" + target);
	   }
	    @AfterReturning(value = "execution(* update(..))", returning = "member")
	    public void afterReturningMethod(JoinPoint jp, ReservVO member) {
	    	System.out.println("[afterReturningMethod] : 메소드 호출 후");
	        Signature sig = jp.getSignature();
	        System.out.println(" 메소드 이름:" + sig.getName());
	        Object[] obj = jp.getArgs();
	        System.out.println(" 인수 값:" + obj[0]);
	        Object target = jp.getTarget();
	        System.out.println(" 객체 값:" + target);
	        System.out.println(" 객체 값:" + member.getResid());
	    }
	    
//	    @Around("execution(* update(..))")
////    	@Around("within(org.kpu.di.service.MemberServiceImpl)")
//	    public ReservVO aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
//	        System.out.println("[AroundMethod Before] : 메소드 호출 전");
//	        Signature sig = pjp.getSignature();
//	        System.out.println(" 메소드 이름:" + sig.getName());
//	        ReservVO member = (ReservVO) pjp.proceed();
//	        System.out.println("[AroundMethod after] : 메소드 호출 후");
//	        System.out.println(" 메소드 이름:" + sig.getName());
//	        System.out.println(" 수정 내용 : " + "[id = " + member.getResid()+"]" + "[resname = " + member.getResname()+"]" +  
//	        		"[resdate = " + member.getResdate()+"]" + "[resusername = " + member.getResusername()+"]" + 
//	        		"[resusermobile = " + member.getResusermobile()+"]" + "[resuseremail = " + member.getResuseremail()+"]");
//	        return member;
//	    }
}
