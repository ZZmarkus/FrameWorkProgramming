## --프프 1장--
● 일반적인 웹 시스템
- 정적 컨텐츠(HTML) : 웹 브라우저가 웹 서버로부터 저장된 HTML을 읽어와 표현
- 동적 컨텐츠(JSP) : 웹 브라우저가 웹 서버에 동적 페이지를 요청하면 이 요청을 애플리케이션 서버가 실행하고, 처리결과(데이터 베이스 조회 등)를 브라우저가 HTML형식으로 받아서 표현

● 웹 애플리케이션
- Spring : DixAOP컨테이너, 경량 컨테이너, POJO객체, 웹과 클라우드 모바일 등 다양한 자바 기반의 애플리케이션을 만들기 위한 프레임워크

● Spring Framework : 2003년 Johnson이 개발, 자바 플랫폼을 위한 오픈소스 애플리케이션 프레임워크, POJO(Plain Old Java Object) 지원을 통해 기존 EJB의 문제점인 복잡성을 줄임

● 아키텍처 중요성
- 서버 애플리케이션 특징에 적합한 구조가 필요 : 유연하게 대응할 수 있는 개발 효율성인 구조는 서버 애플리케이션의 수명을 유지

● 웹 애플리케이션 아키텍처
- 물리층인 티어(Tier)와 논리층인 레이어(Layer)로 구분

● Tier
- 프레젠테이션 층 : 사용자 인터페이스와 컨트롤러 제공(Controller)
- 비즈니스 로직 층 : 비즈니스 로직을 제공(Service)
- 데이터 엑세스 층 : 데이터 엑세스를 추상화(DAO:Data Access Object)

● Layer
- 컨트롤러 : 페이지 화면 전환 또는 동작 제어(프레젠테이션 레이어)
- 서비스 : 유스케이스로 표현되는 특정 업무 처리, 트랜젝션 기점(비즈니스 로직 레이어)
- 도메인 : 서비스로부터 기능을 실행하는데 사용되는 고객 또는 주문같은 클래스의 집합 (비즈니스 로직 레이어)
- DAO : 데이터 엑세스 레이어

● 오목형 레이어
- 표현층 또는 데이터 엑세스 층이 변경되어도 비즈니스 로직층에는 영향을 최소화
- 레이어 층 도입과 함께 결합 부분에 약한 결합 설계 구현(인터페이스 도입)필요

● 프레젠테이션 층의 역할
- 사용자 인터페이스와 컨트롤러 제공
- 컨트롤러는 사용자 인터페이스를 통해 사용자의 입력을 받아 적당한 로직을 호출하고, 그 결과를 사용자 인터페이스로 변환하는 작업

● 모델 1방식
- JSP만 구현 개발하거나, java bean을 포함하여 개발하는 방식

● 모델 2방식(MVC 2)
- 모델 : 뷰에 필요한 비즈니스 영역의 로직을 처리(Java Bean)
- 뷰 : 비즈니스 영역에 대한 프레젠테이션 뷰(결과화면)을 담당(JSP)
- 컨트롤러 : 사용자의 입력 처리와 화면의 흐름제어를 담당(Servlet)

● 비즈니스 로직
- 유즈케이스로 표현되는 특정 업무 처리를 위한 서비스와 도메인으로 구성

● 트랜잭션 스크립트 방식
- 도메인은 가능한 한 로직을 포함하지 않고 단순한 값만 저장
-> VO(Value Object), DTO(Data Transfer Object)

● 웹 애플리케이션의 문제
- 오브젝트 생명주기 : 서블릿 인스턴스의 호출로 인한 성능 저하 및 메모리 압박, HTTP Session / Request / Application 경우 객체 생명주기 관리 필요
- 부품화 문제 : 인터페이스 구현과 비의존 구현하기 위해서는 고도의 기술이 필요, 팩토리 매서드는 개발자가 new를 사용하지 않고 인스턴스화를 구현
- 기술 은닉과 부적절한 기술 은닉 : 여러 클래스에 걸쳐 존재하는 예외처리, 로깅, 트랜잭션은 프로그램 가독성을 훼손하고, 부품화와 테스팅에 비 효과적

● 웹 애플리케이션 문제를 Spring Frame Work로 해결
- 오브젝트 생명주기 : DI컨테이너 Dependency Injection으로 해결
- 부품화 문제 : DI컨테이너 Dependency Injection으로 해결
- 기술 은닉과 부적절한 기술 은닉 문제 : AOP Aspect Oriented Programming으로 해결

● Spring Framework의 주요 특징
- POJO(Pain Old Java Object)관리 : 특정한 인터페이스를 구현하거나 상속 받을 필요 없는 가벼운 객체를 관리
- IoC 경량 컨테이너 : 필요에 따라 스프링 프레임워크에서 사용자의 코드를 호출, 일반 오브젝트의 생애 주기 관리나 오브젝트 간의 이존관계를 해결하는 아키텍쳐를 구현

- DIxAOP(Dependency Injection, Aspect Oriented Programming)지원
-> DI : 각각의 계층이나 서비스들 간에 의존성이 존재할 경우 프레임워크가 서로 연결시켜줌(변경용이성, 확장성, 품질관리 용이)
-> AOP : 트랜잭션이나 로깅, 보안과 같이 여러 모듈에서 공통적으로 사용하는 기능의 경우 해당 기능을 분리하여 관리(프로그램 가독성, 기술 은닉)

- O/R매핑 프레임워크 지원 : 완성도가 높은 데이터베이스 처리 라이브러리와 연결할 수 있는 인터페이스 제공(Mybatis, Hibernate)
- 스프링 데이터를 통한 데이터 연동 기능 : 그래프DB, 다큐먼트DB, NoSQL 등의 데이터 저장소를 엑세스
- 스프링 배치 : 대량의 데이터를 일괄처리, 병행처리할 수 있는 템플릿 제공
- 스프링 시큐리티 : 인증, 인가 기능 제공

## --프프2장 메이븐 기초--
● Maven
- 프로젝트를 관리하는 도구, 빌드 자동화 기능과 프로젝트 관리 기능을 제공

● 프로젝트(라이브러리 관리)
- pom.xml 파일을 이용하여 프레적트 관련된 jar파일을 다운로드하고 관리

● 빌드 자동화
- 빌드 작업들을 간단하고 쉽게 그리고 일관성 있게 수행할 수 있는 통합 환경을 제공
- 빌드는 소스코드 파일을 실행 코드로 변환하여 베포하는 과정

● 프로젝트 관리기능
- 정형화된 프로젝트 디렉토리 구조관리(po,.xml) : 설정보다는 규범, Convention over Configuration(CoC)패러다임을 따름
- 의존성 관리기능 : 편리한 라이브러리 관리 기능(pom.xml, Repositorty) : 프로젝트 빌드에 필요한 라이브러리, 플러그인을 개발자 PC에 자동으로 다운로드
- 빌드 프로세스를 관리(pom.xml) : 플러그인 설정을 통해 빌드 자동화

● 의존관계(라이브러리) 설정(pom.xml)
- 프로젝트 당 한개의 pom.xml파일 관리
- 최상위 엘리먼트(root element) : project
- 3개의 필수 필드를 가진다
->groupId : 프로젝트 조직 고유 도메인 예) org.kpu
->artifactId : 프로젝트 명 예) myhomework
->version : 프로젝트 버전
- 프로젝트 의존관계의 라이브러리 관리 : dependency

● 프로젝트 빌드 설정
- 프로젝트 기본정보, 저장소, 프로퍼티, 디렉토리 구조
- 플로그인
- 골

● Maven Repository
- 메이븐 저장소는 프로젝트에 사용되는 프로젝트 jar파일, 라이브러리 jar파일들이 위치하며 3가지 타입이 있다.
1. 지역(local)저장소 : 메이븐을 빌드할 때 다운로드하는 라이브러리, 플로기은을 관리하는 저장소
2. 중앙(central)저장소 : 오픈 소스 라이브러리, 메이븐 플러그인, 메이븐 아키타입을 관리하는 저장소
3. 원격(remote)저장소 : 중앙저장소에 존재하지 않는 라이브러리를 관리하기 위하여 별도의 메이븐 저장소를 설치해 관리하는것이 가능

● 메이븐 의존성 검색 절차
1. 지역 저장소를 검색한다. 찾는 라이브러리가 없을 경우 2단계로 넘어간다.
2. 중앙 저장소를 검색한다. 찾은 라이브러리는 지역 저장소에 저장한다. 만약 찾는 라이브러리가 없을 경우 3단계로 넘어간다. 원격 저장소가 없을경우 에러를 발생하고 종료한다.
3. 원격 저장소를 검색한다. 찾은 라이브러리는 지역 저장소에 저장한다. 찾는 라이브러리가 없는경우 에러를 발생하고 종료한다.
```
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
- 이렇게 작성된 코드가 있고 중앙 저장소에서 찾을 경우 groupId/artifactId/version에 존재하는 파일명 : ${artifactId}-${version}.jar 최종적으로 http://repo1.maven.org/maven2/junit/juint/3.8/junit-3.8.jar 파일을 다운로드한

● 위의 방법으로 할 때 중앙 저장소에서 관리하는 라이브러리의 수가 한두개가 아니라 이렇게 추가하는 것에는 한계가 있기 때문에 검색이 필요하다.
- http://mvnrepository.com/

● 의존 라이브러리 적용 스코프(의존 라이브러리를 적용할 수 있는 시점을 제한할 수있다.)
- compile : 스코프를 설정하지 않았을 때의 기본 스코프
- provided : 컴파일시에는 직접 의존성을 참조하고 런타임시에는 다른 환경에서 의존성을 제공받는다.(해당 컨테이너의 서블릿 API)
- runtime : 컴파일 시에는 사용되지 않지만 애플리케이션을 실행할 때 사용되는 라이브러리일 경우 설정한다.
- test : 테스트하는 시점에만 사용하는 라이브러리에 대한 스코프를 설정할 때 사용한다. (Junit)
- system : provided와 비슷하다. 단지 우리가 직접 jar파일을 제공해야 한다.
- import : 다른 POM설정 파일에 정의되어 있는 의존 관계 설정을 현재 프로젝트로 가져온다. 이 범위는 <dependencyManagement/>엘리먼트에서만 사용 가능하다.

● 메이븐은 소프트웨어 빌드를 위한 공통 인터페이스를 제공하는 프레임워크
- 플러그인 설정을 통해 기능을 위임
- 빌드단계(컴파일, 테스트, 패키징, 배포)들을 빌드 라이프 사이클이라고 한다.
- 각 빌드 단계에서 수행되는 작업을 골(Goal)이라고 한다.
- 실제 골은 그 단게에 연결된 플러그인(Plugin)에 의해 실행된다.

● 메이븐 빌드 라이프사이클
- Maven은 기본, clean, site 3개의 라이프사이클이 있다.
- 기본 : 여러 단계의 페이지로 나뉘어져 있으며, 각 페이지는 의존관계를 갖는다. Compile, test, package deploy 순서로 진행된다.
- clean : clean페이지를 이용하여 이전 빌드에서 생성된 모든 파일들을 삭제한다.
- site : site, site-deploy페이지를 이용하여 생성된 문서들을 대상 사이트에 배포한다.

## --프프3장 DI--
● DI(Dependency Injection)
- 의존관계를 주입하는 것으로 오브젝트 간의 의존관계를 만들고 객체간의 결합도를 낮춘다. 스프링 프레임 워크에서는 런타임시 사용할 객체들의 의존관계를 부여한다.

● IoC(Inversion of Control)
- 역전제어, 즉 인스턴스를 제어하는 주도권이 역전된다는 의미이다. 역전제어는 컴포넌트를 구성하는 인스턴스 생성과 의존관계 연결을 개발자의 소스코드가 아닌 DI컨테이너가 대신해 주기 때문에 제어가 역전되었다고 정의한다.

● IoC컨테이너
- 인스턴스의 생명주기 관리 및 의존관계 주입을 처리한다.

● 일반적인 애플리케이션에서의 의존 관리
- new 연산자를 사용

● DI컨테이너를 활용한 애플리케이션
- new 연산자를 제거하고 인터페이스 기반의 컴포넌트화
- memberDao 인스턴스를 MemberService에 인젝션하여 의존관계를 주입한다.
- 인터페이스 기반의 컴포넌트화를 실현하려면 ProductService와 ProductDao를 인터페이스로 하고 그 구현클래스는 인터페이스 이름에 Impl을 덧붙인다.

● 스프링 빈
- 스프링 컨테이너가 관리하는 객체

● IoC컨테이너
- 스프링 빈의 생성, 관계, 조립, 생명주기를 관리하는 스프링 프레임워크의 핵심

● 스프링 컨테이너 종류
- BeanFactory(org.Springframework.beans.factory.BeanFactory)
- ApplicationContext(org.Springframework.context.ApplicationContext)

● BeanFactory
- 빈의 생성, 빈의 의존관계 관리 등의 DI의 기본 기능을 제공

● ApplicationContext
- 일반적인 스프링 컨테이너를 의미

● WebApplicationContext
- 2가지 종류의 WebApplicationContext(WAC)
1. ContextLoaderListner에 의해 생성되는 WAC
 - Persistence(DAO), Service관련 스프링 빈들을 등록
 - 웹 애플리케이션 전체에서 사용할 WAC객체 생성
 - root-context.xml파일에 설정
2. DispatcherServlet에 의해 생성되는 WAC
 - 컨트롤러와 같은 서블릿 관련 빈을 등록
 - 해당 서블릿 마다 사용할 WAC 객체 생성
 - servlet-context.xml파일에 설정

● web.xml에 ContextLoaderListner, DispactcherServlet를 사용하여 ApplicationContext 생성
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
● Dependency Injection
- xml기반 설정 : XML파일을 사용하는 <Bean>요소를 정의하는 방법
- Annotation 기반 설정 : @Component 애너테이션이 부여된 클래스를 DI컨테이너가 Bean으로 자동으로 등록하는 방법
- java기반 설정 : 자바클래스에 @Configuration애너테이션을, 매서드에 @Bean 애너테이션을 사용해 Bean을 등록하는 방법

● xml DI
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

● Annotation DI (context:annotation-config)
- 이 선언을 사용하면 BeanPostprocessor가 자동으로 등록되어 @component를 선언할 필요가 없다.

● JAVA DI
- xml문법 대신 자바 코드로 빈을 설정한다.
- 타사의 외부 라이브러리를 사용하여 DI하고자 할 경우에는 소스를 가지고 있지 않고 있기 때문에 애노테이션을 이용하는 방법은 불가능하다 -> XML로 설정
- 컨테이너 생성 클래스 : AnnotationConfigApplicationContext

● @Configuration
- 빈 설정 메타 정보를 담고 있는 클래스를 선언

● @Bean
- 클래스 내의 매서드를 정의하여 새로운 빈 객체를 정의할 때 사용

##--프프 4장 JDBC--
● 데이터 액세스 층의 역할
- 데이터 액세스 처리를 비즈니스 로직 층에서 분리하는 것

● DAO(Data Access Object)
- 데이터 액세스 처리에 특화된 오브젝트

● 데이터 소스
- 데이터 액세스 기술 종류와 상관없이 데이터베이스 접속을 관리해주는 인터페이스

● 데이터 소스 구현 (Pom.xml)
```
<dependency>
   <groupId>commons-dbcp</groupId>
   <artifactId>commons-dbcp</artifactId>
   <version>1.4</version>
</dependency>
```

● 서드 파티가 제공하는 데이터 소스(applicationContext.xml)
```
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
   <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
   <property name="url" value="jdbc:mysql://127.0.0.1:3306/springdb?allowPublicKeyRetrieval=true&amp;useUnicode=true&amp;characterEncoding=utf8&amp; useSSL=false&amp;serverTimezone=UTC" />
   <property name="username" value="spring" />
   <property name="password" value="passwd" />
   <property name="maxActive" value="5" />
</bean>
```
● JDBC의 문제점
- 대량의 소스코드를 기술
- 다양한 에러 원인을 파악하기 위한 코딩이 필요
- 데이터베이스 제품마다 에러 코드가 달라서 코드의 일관성 유지가 어려움

● 스프링 JDBC
- JDBC를 래핑한 API를 제공해 소스코드를 단순화

● SELECT문 (취득 결과가 레코드 건수 또는 특정 컬럼만 취득할 경우)
1. queryForObject 메소드 사용
- 제1인수 : SQL문자열
- 제2인수 : 반환형 클래스 오브젝트(int)
```
JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM STUDENT", Integer.class);
```

2. queryForObject 메소드 사용
- 제1인수 : SQL문자열
- 제2인수 : 반환형 클래스 오브젝트(String)
- 제3인수 : 파라미터 값
```
String name = jdbcTemplate.queryForObject("SELECT username FROM STUDENT WHERE id=?", String.class, id);
```

● SELECT문 (취득 결과가 한 레코드 값을 취득할 경우)
1. queryForMap 메소드 : 한 레코드 값을 Map(컬럼 이름을 키로 값을 저장)데이터로
```
Map<String, Object> student = jdbcTemplate.queryForMap("SELECT * FROM STUDENT WHERE id=?", id);
String name = (String)student.get("username");
```

2. queryForList 메소드 : 여러 레코드 값을 Map데이터로
```
List<Map<String, Object>> studentList = jdbcTemplate.queryForMap("SELECT * FROM STUDENT");
```

● SELECT문 (도메인으로 변환할 경우)
- queryForObject메소드와 query메소드를 이용한다.
- queryForObject 메소드 : 한 레코드를 가져올때
- 제1인수 : SELECT문
- 제2인수 : 도메인 자동변환을 위한 스프링 제공 클래스 BeanPropertyRowMapper
- 제3인수 : SELECT문의 파라미터
- BeanPropertyRowMapper를 사용할 경우 StudentVO의 프로퍼티 명과 테이블 컬럼 명이 같아야한다. 그렇지 않을 경우는 RowMapper 인터페이스를 구현해서 StudentVo로 변환처리 해야함
```
public StudentVO read(String id) throws Exception{
   studentVO vo = null;
   try{
      vo = jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE ID=?", new BeanPropertyRowMapper<StudentVO>(StudentVO.class), id);
   catch(EmptyResultDataAccessException e){
      return vo;
   }
   return vo;
}
```

● SELECT문 (도메인으로 변환할 경우)
- query메소드 : 여러 레코드를 가져올 때
- RowMapper 인터페이스를 구현한 익명 클래스를 정의
- 클래스내 mapRow()추상메소드를 정의
```
public List<StudentVO> readList() throws Exception {
   List<StudentVO> studentlist = jdbcTemplate.query("SELECT * FROM STUDENT", new RowMapper<StudentVO>() {
      public StudentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
         StudentVO vo = new StudentVO();
         vo.setId(rs.getString("ID"));
         vo.setPasswd(rs.getString("PASSWD"));
         vo.setUsername(rs.getString("USERNAME"));
         vo.setSnum(rs.getString("SNUM"));
         vo.setDepart(rs.getString("DEPART"));
         vo.setMobile(rs.getString("MOBILE"));
         vo.setEmail(rs.getString("EMAIL"));
         return vo;
      }
   }); 
return studentlist;
}

● INSERT / UPDATE / DELETE문
- update 메소드만을 사용
1. INSERT문
```
StudentVO vo;
jdbcTemplate.update("INSERT INTO STUDENT (ID, PASSWD, USERNAME, SNUM, DEPART, MOBILE, EMAIL) VALUES(?,?,?,?,?,?,?)", vo.getId(), vo.getPasswd(), vo.getUsername(), vo.getSnum(), vo.getMobile(), vo.getEmail());
```

2. DELETE문
```
StudentVO vo;
jdbcTemplate.update("DELETE FROM STUDENT WHERE ID=?", vo.getId());
```

● Junit을 이용한 스프링 테스트
- 스프링 프레임워크에서 만든 클래스(@Controller, @Service, @Repository, @Componet등이 붙은 클래스)를 테스트하는 모듈
- 단위테스트, 통합테스트를 지원하기 위한 매커니즘이나 편리한 기능을 제공한다.