## 📌 9장 스프링 Security

- 인터셉터
	- Spring MVC의 인터셉터는 Controller의 호출 이전과 이후에 추가적인 기능을 할 수 있는 구조를 제공한다.
	- 기능상으로는 AOP의 기능과 유사하다.
	- 구조상으로는 서블릿/JSP에서 사용하는 Filter와 유사하다.

- 필터(Filter)와 인터셉터(Interceptor)
	- 영역제어 측면에서의 차이점
		- 인터셉터는 스프링 빈으로 등록된 컨트롤러나 서비스 객체를 주입받는다.
![9장](https://user-images.githubusercontent.com/51106028/121062026-8f517400-c7ff-11eb-9717-7a44e1320e8b.PNG)

- Filter와 인터셉터의 차이점
	- DIspatcherServlet의 뒤에있냐 앞에있냐 차이
	- 이유는 디스패처 서블렛 앞에있으면 컨트롤러나 서비스 객체를 주입받지 못하여 Context 접근이 어렵지만 DispatcherServlet 뒤에 있으면 컨트롤러나 서비스 객체를 주입받아서 Context내의 객체를 모두 사용할 수 있다.

- AOP와 Interceptor 구현차이
	- 차이는 Interceptor 파라미터가 HttpservletRequest, HttpServletResponse라는 점
		- AOP는 (JoinPoint, preceedingJoinPoint)등의 파라미터를 활용하는 방식
		- AOP는 특정 객체 동작의 사전, 사후 처리를 활용
	- Interceptor는 DispatcherServlet이 Controller를 호출하기 전과 후에 요청과 응답을 참조하거나 적용할 수 있는 일종의 서블릿 필터
		- HandlerInterceptorAddapter는 HandlerInterceptor 인터페이스를 구현한 추상클래스
		- HandlerInterceptorAdapter 추상 메소들을 구현하여 컨트롤러의 사전, 사후를 처리

- HandlerInterceptor 인터페이스를 구현
	- 컨트롤러 실행 전 : preHandle(request, response, handler)
	- 컨트롤러 실행 후 : postHandle(request, response, handler, modelAndView)
	- 뷰를 실행한 이후 : afterCompletion(request, response, handler, exception)

- HandlerInterceptor
	- 요청 경로마다 접근 제어를 다르게 처리하고자 한다. 즉, 특정 컨트롤러에 종속되지 않고 다양한 컨트롤러에 공통적으로 적용한다.

- Spring AOP기능과 HandlerInterceptor의 차이
	- HandlerInterceptorAdapter클래스는 HandlerInterceptor인터페으스를 구현
![9장](https://user-images.githubusercontent.com/51106028/121062721-6b426280-c800-11eb-80a1-1cf9d7329571.PNG)

- 컨트롤러(핸들러) 실행 전 메소드
	- preHandle(request, response, handler)
	- 컨트롤러(핸들러) 객체를 실행하기 전에 필요한 기능을 구현할 때 사용
	- 리턴타입이 boolean인데, false를 리턴하면 컨트롤러를 실행하지 않는다.

- 컨트롤러(핸들러) 실행 후 메소드
	- postHandle(request, response, handler, modelAndView)
	- 컨트롤러 객체가 정상 실행된 후(화면처리 전)에 필요한 기능을 구현할 때 사용
- 뷰를 실행한 이후 메소드
	- afterCompletion(request, response, handler, exception)
	- 클라이언트에 뷰를 전송한 뒤(화면처리 후) 실행된다.

- 인터셉터의 request, response활용
	- preHandle()의 Object 파라미터
		- 현재 실행하려고 하는 메소드 자체를 의미
		- 리턴값을 false값으로 리턴할 경우 다음 모듈은 실행되지 않는다.
	- postHandle()을 이용해서 추가적인 작업
		- 컨트롤러의 메소드는 실행되었고, 아직 결과가 뷰에 처리되지 않은 상태이므로 추가적인 데이터 로직을 넣을 수 있음

- 인터셉터(SimpleInterceptor)설정
	- servlet-context.xml설정
		```
		<beans:bean id="simpleInterceptor" class="org.kpu.interweb.interceptor.SimpleInterceptor">
		</beans:bean>
		<interceptors>
				<interceptor>
				<mapping path="/doInterA"/>
				<mapping path="/doInterB"/>
				<beans:ref 
bean="simpleInterceptor"/>
			</interceptor>
		</interceptors>
		```	
	- <interceptors> : HandlerInterceptor 설정과 경로 설정을 함께 설정
		- 태그 내부에 설정한 빈 객체를 핸들러 인터셉터로 사용
	- <interceptor> : 특정 요청경로에 대해 특정 핸들러 인터셉터를 적용
		- <mapping> : 경로지정, "*"와 같은 패턴적용 가능

- SimpleInterceptor 테스트를 위한 컨트롤러
	- HomeController.java 수정
	```
	@RequestMapping(value="/doInterA", method=RequestMethod.GET)
	public String InterA(Locale locale, Model model){
		logger.info("doInterA");
		return "home";
	}
	@RequestMapping(value="/doInterB", method=RequestMethod.GET)
	public String InterB(Locale locale, Model model){
		logger.info("doInterB");
		return "home";
	}
	```

- 애플리케이션 보안 기능을 구현할 때 사용하는 프레임워크
- 기본적인 보안기능
	- 인증 : 애플리케이션에 접근하는 사용자를 특정하는 기능
	- 인가 : 특정한 사용자에 대해 정보와 기능의 접근을 제한하는 기능
- 강화된 보안기능
	- 세션 관리 : 세션 라이프사이클 관리
	- CSRF(Cross-Site Request Forgery)방지 : 크로스 사이트 요청변조 공격으로부터 사용자 보호
	- 브라우저 보안기능 연계 : 브라우저 기능을 악용한 공격에서 사용자 보호

- 스프링 시큐리티 특징
	- 인증, 인가 기능 제공
	- 인증, 인가 구현을 위한 다양한 필터 클래스 제공
	- XML 파일에서 데이터베이스 리소스로부터 인증, 인가 정보 취득 가능
	- HTTP basic 인증, HTML 폼 인증 지원
	- 인가 정보에 기반한 화면제어를 위한 JSP 태그라이브러리 제공
	- 메서드 호출에 대한 접근제어에 AOP 사용가능
	- 시큐리티 공격 방어기능 제공

- 스프링 시큐리티는 spring-security-oauth2 라이브러리 제공
	- OAuth 2.0를 사용한 API 인가 기능 구현 가능
	- OAuth 2.0은 하나의 ID로 여러 사이트에 로그인 할 수 있는 체계

- POM.xml에 등록할 스프링 시큐리티 라이브러리 구성
	모듈 | 설명 |
	--- | --- |
	spring-security-core | 인증 인가 기능을 구현하기 위한 핵심적인 컴포넌트
	spring-security-web | 웹 애플리케이션 보안 기능을 구현하기 위한 컴포넌트
	spring-security-config | 각 모듈에서 제공하는 컴포넌트 설정을 구현하기 위한 컴포넌트
	spring-security-taglibs | 인증 및 인가 정보를 사용하기 위한 JSP 태그 라이브러리로 구성

- web.xml에 등록할 시큐리티 주요 컴포넌트
	모듈 | 설명 |
	--- | --- |
	FilterChainProxy | 프레임워크의 진입점 역할을 하는 서블릿 필터 클래스로 전체 흐름을 제어하고 보안 기능과 같은 추가기능을 필터에 위임하는 방식으로 동작
	Httpfirewall | 방화벽 기능을 추가하기 위한 인터페이스, 인가되지 않은 요청을 차단하는 역할을 수행
	SecurityFilterChain | FilterChainProxy가 받은 요청에 적용할 보안 필터 목록을 관리하기 위한 인터페이스
	Security Filter | 보안기능을 제공하는 서블릿 필터 클래스(인증, 인가 등)

- 스프링 시큐리티 인증 필터
	- 인증 필터는 인증 처리방식에 대한 구현을 제공하는 서블릿 필터
- 인증 처리 수행을 위한 인터페이스
	인터페이스 | 설명 |
	--- | --- |
	AuthenticationManager | 인증 처리를 수행하기 위한 인터페이스, 실제 인증 처리는 AuthenticationProvider에게 위임하고 반환되는 처리 결과를 처리하는 구조로 동작
	AuthenticationProvider | 인증 처리기능을 구현하기 위한 인터페이스, 스프링 시큐리티는 인증 방식별로 다양한 구현 클래스를 제공