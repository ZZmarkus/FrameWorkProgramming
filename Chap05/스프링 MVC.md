
## 📌 5장 스프링 MVC
- 스프링 MVC 설계 : 프론트 컨트롤러 패턴에 기초한 MVC 프레임워크
- 약한 결합도로 구성되어 유연하고 확장하기 쉬움
- 다양한 서드파티 라이브러리 연계를 지원
- 애노테이션의 도입으로 스프링 MVC 보급이 확대됨

- Model1 방식 : JSP만 사용하여 개발하거나 Java bean을 포함하여 개발하는 방식
- Model2 방식 : Model - View - Controller로 분리
- 프론트 컨트롤러 패턴 : 클라이언트 요청을 별도의 프롵느 컨트롤러에 집중
- 프론트 컨트롤러 실행 프로세스
    1. DispatcherSerlet이 HTTP 요청을 받음
    2. DispatcherServlet은 서브 Controller로 HTTP요청 위임
    3. 서브 Controller는 클라이언트의 요청 처리를 위해 DAO 객체를 호출
    4. DAO 객체는 리소스를 엑세스하여 Model 객체를 생성 후 요청 결과 리턴
    5. DispatcherServlet은 처리 결과에 적합한 뷰에 화면 처리 요청
    6. 선택된 뷰는 화면에 모델 객체를 가져와 화면 처리
    7. HTTP응답

- MVC의 주요 구성요소
    - DispatcherServlet : 프론트 컨트롤러를 담당하고, 클라이언트의 요청을 받아서 Controller에게 클라이언트의 요청을 전달하고, 리턴 결과값을 View에 전달하여 알맞은 응답을 생성한다.

- HandlerMapping : URL과 요청 정보를 기준으로 어떤 컨트롤러를 실행할지 결정하는 객체로 애노테이션을 이용할 때에는 mvn:annotation-driven태그를 설정해야 한다.

- Controller : 클라이언트의 요청을 처리한 뒤 그 결과를 DispatcherServlet에게 알려준다.

- Model : 컨트롤러가 뷰에게 넘겨줄 데이터를 저장하기 위한 객체

- ViewResolver : Controller가 리턴한 뷰 이름을 기반으로 Controller처리 결과를 생성할 뷰를 결정한다.

- View : Controller의 처리 결과 화면을 생성한다.

- 스프링 스테리오타입 애노테이션
    - @Component : 일반적인 컴포넌트
    - @Repository : Persistence 계층 컴포넌트
    - @Service : Business(Service) 계층 컴포넌트
    - @Controller : Presentation 계층 컴포넌트
    - @RestController : @Controller + @ResponseBody

- 라이브러리 설정
    - spring-webmvc를 설정하면 스프링 웹과 기타 스프링 프레임워크 의존모듈(spring-context)에 대한 관계도 함께 처리
    - 스프링 MVC는 Bean Validation 구조체(hibernate-validator)를 이용해 자바 빈(VO)값의 유효성을 애노테이션을 통해 검중
    - pom.xml
    ```
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-webmvc</artifactId>
       <version>${org.springframework-version}</version>
    </dependency>
    <dependency>
       <groupId>org.hibernate</groupId>
       <artifactId>hibernate-validator</artifactId>
       <version>6.1.2.Final</version>
    </dependency>
    ```

- ContextLoadListner클래스
    - 서비스 계층 이하의 빈(@Service, @Repository 등)을 등록하기 위한 클래스

- DispatcherServlet 클래스
    - 컨트롤러(@Controller, @Component)빈을 등록하기 위한 클래스

- ContextLoadListner
```
<context-param>
   <param-name>contextConfigLocation</param-name>
   <!-- ContextLoaderListner 설정 파일 -->
   <param-value>/WEB-INF/spring/root-context.xml</param-value>

<listener>
   <!-- ContextLoaderListener 등록 -->
   <listener-class>org.springframework.web.context.ContextloaderListener
   </listener-class>
</listener>
</context-param>
```

- DispatcherServlet
```
<servlet>
   <!-- 프론트 컨트롤러(DispatcherServlet)를 서블릿 컨테이너에 등록 -->
   <servlet-name>appServlet</servlet-name>
   <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
   <init-param>
      <param-name>contextConfigLocation</param-name>
      <!-- 프론트컨트롤러 설정파일  -->
      <param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
   </init-param>
   <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
   <servlet-name>appServlet</servlet-name>
   <url-pattern>/</url-pattern>
</servlet-mapping>
```

- 컨트롤러 기본 구조
    - 모든 컨트롤 클래스에넌 @Controller 애노테이션을 설정
    - 메소드 별로는 @Requestmapping 애노테이션을 사용하여 URL매핑을 설정
    - @RequestParam은 HTTP요청 파라미터를 메소드의 파리미터로 전달받을 때 사용한다.
    - 매소드의 반환값으로는 View의 이름을 반환한다 ex)memberList.jsp

- 컨트롤러 등록
    - DispatcherServlet 클래스의 설정파일인 servlet-context.xml에서 컨트롤러 등록
    - DispatcherServlet 클래스 설정
    1. <annotation-driven/> : 패키지 내부에서 찾은 빈(컨트롤러)과 URI를 맵핑
    2. <context:component-scan base-package="org.kpu.web.controller"/> : base-package 내부의 클래스에서 @Controller지정된 컨트롤러를 검색하여 빈으로 등록

- @PathVariable 적용 변수로 전달
```
@RequestMapping(value="/try/{msg}", method = RequestMethod.GET)
public String getUserTest(@PathVariable("msg") String msg){
.....
}
```

- @RequestParam 적용 변수로 전달
```
@RequestMapping(value="/tryA}", method = RequestMethod.GET)
public String getUserTest(@RequestParam("msg") String msg){
.....
}
```

- @ModelAttribute 적용 변수로 전달
```
@RequestMapping(value="/tryB}", method = RequestMethod.GET)
public String getUserTest(@ModelAttribute("msg") String msg){
.....
}
```

- @RequestMapping(value={"/tryC", "/tryD"})
    - 배열 형태의 값을 지정할 수 있다.
    - tryC, tryD 양쪽 URL에 대응하는 메서드를 정의할 수 있다.
    - 요청 파라미터 값을 @ModelAttribute적용 변수로 전달한다.
```
@RequestMapping(value={"/tryC", "/tryD"}, method = RequestMethod.GET)
public String getUserTest(@ModelAttribute("msg") String msg){
.....
}
```

- Rest아키텍처
    - Rest(Representational State Transfer)는 클라이언트와 서버 사이에 데이터 연동 애플리케이션을 위한 아키텍처 스타일
    - 웹 상의 정보를 리소스로 파악하고 그 식별자로서 URI를 할당해 고유하게 주소를 지정하는 방법

- Rest컨트롤러를 위한 라이브러리 설정
    - 리소스 형식을 JSON으로 사용
    - jackson-databind를 사용하면 JSON과 자바빈즈를 서로 교환할 수 있음
    ```
    <dependency>
       <groupId>com.fasterxml.jackson.core</groupId>
       <artifactId>jackson-databind</artifactId>
       <version>2.9.8</version>
    </dependency>
    ```

- @RestController : REST API를 제공하는 컨트롤러를 의미하고 @Controller와 @ResponseBody의 의미를 합침

- @RequestBody : 컨트롤러 매서드 매개변수에 @RequestBody가 애노테이션된 경우, 스프링은 요청된 HTTP request body를 해당 매개변수에 바인딩 한다.

- @ResponseBody : 컨트롤러 메소드가 @ResponseBody로 애노테이션 된 경우, 스프링은 반환값을 나가는 HTTP response body에 바인딩한다.

- RsponseEntity : 전체 HTTP응답을 나타내고 statusCode, headers, body 3가지 속성값을 지정할 수있다.

- XML연동 REST 컨트롤러
    - jaxb2RootElementHttpMessageConverter
    - XML 형식의 HTTP 메시지를 JAXB(Java Architecture for XML Binding)로 변환
    - JAXB는 JAVA 오브젝트와 XML 문서와 상호교환하는 자바의 표준 사양
    1. Servlet-context.xml
    ```
    <annotation-driven validator="validator">
       <message-converters>
          <beans:bean class="org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter"/> 
       </message-converters>
    </annotation-driven>
    ```

    2. root-context.xml
    ```
    <bean id="validator" 
    class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
    </bean>
    ```
- Spring MVC에서의 예외처리
    - 컨트롤러 별로 예외처리
        1. 컨트롤러의 매소드에서 예외가 발생했을 때의 처리를 정의
        2. 별도의 예외처리 메소드를 정의하고 그 매소드에 @ExceptionHandler 애노테이션 설정

    - 하나의 웹 애플리케이션 안에서 공통된 예외처리
        1. 복수의 컨트롤러에서 사용할 수 있는 공통된 예외 처리 클래스를 정의
        2. 공통된 예외 처리 클래스를 정의하고 그 클래스어 @ControllerAdvice애노테이션을 설정
