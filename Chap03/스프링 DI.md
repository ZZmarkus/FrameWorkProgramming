## 📌 3장 스프링 DI
- DI(Dependency Injection)
    - 의존관계를 주입하는 것으로 오브젝트 간의 의존관계를 만들고 객체간의 결합도를 낮춘다. 스프링 프레임 워크에서는 런타임시 사용할 객체들의 의존관계를 부여한다.

- IoC(Inversion of Control)
    - 역전제어, 즉 인스턴스를 제어하는 주도권이 역전된다는 의미이다. 역전제어는 컴포넌트를 구성하는 인스턴스 생성과 의존관계 연결을 개발자의 소스코드가 아닌 DI컨테이너가 대신해 주기 때문에 제어가 역전되었다고 정의한다.

- IoC컨테이너
    - 인스턴스의 생명주기 관리 및 의존관계 주입을 처리한다.

- 일반적인 애플리케이션에서의 의존 관리
    - new 연산자를 사용

- DI컨테이너를 활용한 애플리케이션
    - new 연산자를 제거하고 인터페이스 기반의 컴포넌트화
    - memberDao 인스턴스를 MemberService에 인젝션하여 의존관계를 주입한다.
    - 인터페이스 기반의 컴포넌트화를 실현하려면 ProductService와 ProductDao를 인터페이스로 하고 그 구현클래스는 인터페이스 이름에 Impl을 덧붙인다.

- 스프링 빈
    - 스프링 컨테이너가 관리하는 객체

- IoC컨테이너
    - 스프링 빈의 생성, 관계, 조립, 생명주기를 관리하는 스프링 프레임워크의 핵심

- 스프링 컨테이너 종류
    - BeanFactory(org.Springframework.beans.factory.BeanFactory)
    - ApplicationContext(org.Springframework.context.ApplicationContext)

- BeanFactory
    - 빈의 생성, 빈의 의존관계 관리 등의 DI의 기본 기능을 제공

- ApplicationContext
    - 일반적인 스프링 컨테이너를 의미

- WebApplicationContext
    - 2가지 종류의 WebApplicationContext(WAC)
    1. ContextLoaderListner에 의해 생성되는 WAC
        - Persistence(DAO), Service관련 스프링 빈들을 등록
        - 웹 애플리케이션 전체에서 사용할 WAC객체 생성
        - root-context.xml파일에 설정
    2. DispatcherServlet에 의해 생성되는 WAC
        - 컨트롤러와 같은 서블릿 관련 빈을 등록
        - 해당 서블릿 마다 사용할 WAC 객체 생성
         - servlet-context.xml파일에 설정

- web.xml에 ContextLoaderListner, DispactcherServlet를 사용하여 ApplicationContext 생성
```
<context-param>
    <param-name>contextconfigLocation</param-name>
    <param-value>/WEB-INF/spring/root-context.xml</param-value>
</context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <servlet>
        <servlet-name>appServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextconfigLocation</param-name>
        <param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
    </servlet>
    ......
```
- Dependency Injection
    - xml기반 설정 : XML파일을 사용하는 <Bean>요소를 정의하는 방법
    - Annotation 기반 설정 : @Component 애너테이션이 부여된 클래스를 DI컨테이너가 Bean으로 자동으로 등록하는 방법
    - java기반 설정 : 자바클래스에 @Configuration애너테이션을, 매서드에 @Bean 애너테이션을 사용해 Bean을 등록하는 방법

- xml DI
```
<bean id="memberDAO"class="org.kpu.di.persistence.MemberDAOImpl">
</bean>
<!-- Constructor based Injection -->
<bean id="memberService"class="org.kpu.di.service.MemberServiceImpl">
   <constructor-arg ref="memberDAO"/>
</bean>
<!-- Setter based Injection
<bean id="memberService"class="org.kpu.di.service.MemberServiceImpl">
   <property name="memberDAO"ref="memberDAO"/>
</bean>
-->
```

- Annotation DI (context:annotation-config)
    - 이 선언을 사용하면 BeanPostprocessor가 자동으로 등록되어 @component를 선언할 필요가 없다.

- JAVA DI
    - xml문법 대신 자바 코드로 빈을 설정한다.
    - 타사의 외부 라이브러리를 사용하여 DI하고자 할 경우에는 소스를 가지고 있지 않고 있기 때문에 애노테이션을 이용하는 방법은 불가능하다 -> XML로 설정
    - 컨테이너 생성 클래스 : AnnotationConfigApplicationContext

- @Configuration
    - 빈 설정 메타 정보를 담고 있는 클래스를 선언

- @Bean
    - 클래스 내의 매서드를 정의하여 새로운 빈 객체를 정의할 때 사용
