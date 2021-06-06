## 📌 6장 스프링 MyBatis
- MyBatis
	- SQL과 자바 객체를 매핑하는 사상에서 개발된 데이터베이스 접근용 프레임워크
	- SQL 기반으로 데이터베이스 접근을 수행하는 기존 방식과 큰 규모의 애플리케이션 개
발에서 발생하는 과제를 해결하는 구조

- MyBatis 장점
	- SQL의 체계적인 관리(설정 파일, 애노테이션)
		- 자바 Mapper 인터페이스를 통해 SQL 설정 파일과 연동
		- 비즈니스 로직에서 Mapper 인터페이스를 통해 SQL문 실행
	- 자바 객체와 SQL 입출력 값의 바인딩
	- 동적 SQL 조합

- MyBatis 주요 컴포넌트
	- MyBatis 설정 파일
	- SqlSessionFactoryBuilder : MyBatis 설정 파일을 바탕으로 SqlSessionFactory 생성
	- SqlSessionFactory : sqlSession 생성을 위한 컴포넌트
	- SqlSession : SQL 발행과 트랜잭션 관리
	- Mapper 인터페이스 : 매핑 파일과 SQL에 대응하는 자바 인터페이스
	- 매핑 파일 : SQL과 OR 매핑을 XML에 파일 기술
	- org.mybatis.spring.SqlSessionTemplate : sqlSession 인터페이스를 구현

-MyBatis 연동을 위한 라이브러리
	- spring-jdbc : 스프링이 제공하는 JDBC 래핑 모듈
	- MyBatis-Spring : 마이바티스가 제공하는 프레임워크 간의 연동 라이브러리
	- MyBatis : 마이바티스 프레임워크 모듈
	- 커넥션 풀 지원 라이브러리 : commons-dbcp
	- 사용할 데이터베이스 JDBC 라이브러리 : mysql-connector-java

- MyBatis 연동을 위한 스프링 의존 관계 설정 (root-context.xml)
	① 커넥션 풀을 지원하는 데이터 소스 빈을 등록한다. 
	② 스프링의 트랜잭션 관리자의 빈을 등록한다.
		- 스프링의 트랜잭션 제어를 사용하기 때문에 MyBatis의 트랜잭션 API는 사용하지 않음
	③ MyBatis의 SqlSessionFactory 빈을 등록한다.
	④ MyBatis-Spring의 SqlSessionTemplate 빈을 등록한다.

- 스프링 테스트 사용을 위한 라이브러리 등록
	```
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-test</artifactId>
		<version>${org.springframework-version}</version>
		<scope>test</scope>
	</dependency>
	```

- mvc-mybatis 예제 프로젝트 작업 순서
----------------------------------------------------------------------------------------------
	(1) 테이블 생성 : student 테이블
	(2) 스프링 MVC 프로젝트 생성(mvc-mybatis)
	(3) 프로젝트 라이브러리 설정(POM.xml)
	(4) 마이바티스 의존 관계 설정(root-context.xml)
	(5) 마이바티스 공통 설정(mybatis-config.xml)
	(6) 마이바티스 데이터베이스 연동 단위 테스트(DataSourceTest.java, MyBatisTest.java)
	(7) 도메인 객체 클래스 작성(studentVO.java)
	(8) DAO 인터페이스의 작성(MemberDAO.java)
	(9) DAO 클래스 구현 완료(MemberDAOImpl.xml)
	(10) XML Mapper 생성과 SQL 문 작성(studentMapper.xml)
	(11) XML Mapper를 인식하도록 root-context.xml 설정
	(12) DAO 클래스를 스프링 빈으로 등록(root.context.xml)
	(13) DAO 클래스 단위 테스트(MemberDAOTest.java)
	(14) Service 인터페이스 작성(MemberService.java)
	(15) Servcie 구현 클래스 작성(MemberServiceImpl.java)
	(16) Servcie 클래스를 스프링 빈으로 등록(root.context.xml)
	(17) 컨트롤러 작성 (MemberController.java)
	(18) 뷰 페이지 작성
	(19) 컨트롤러 스프링 빈으로 등록(servlet-context.xml)

- MyBatis를 이용하여 SQL 사용하는 방식 3가지
	- XML만을 이용하여 SQL문을 설정 : DAO에서 XML을 찾아서 실행하는 코드 작성
		- 장점 : SQL문이 별도의 XML 파일로 관리하여 유지보수에 적합
		- 단점 : 코드의 양이 많아짐
	- 애노테이션과 인터페이스만을 이용하여 SQL 문을 작성
		- 장점 : DAO 없이 개발 가능하여 생산성 증가
		- 단점 : 매번 수정시 컴파일 필요
	- 인터페이스와 XML로 작성된 SQL 문의 활용
		- 장점 : 간단한 SQL은 인터페이스, 복잡한 SQL은 XML로 처리하여 유연성 확보
		- 단점 : 2가지 방식의 혼재로 유지보수 작업에 부적합