## 📌 8장 스프링 Transaction

- 트랜잭션
	- 전체 로직이 모두 다 반영되거나 모두 반영되지 않는 논리적인 작업의 묶음(All or Nothing)
	- 작업이 완료되는것을 커밋(commit)이라고 하고, 작업이 취소되는 것을 롤백(rollback)이라고 한다.

- 트랜잭션의 기본 원칙(ACID)
	- 원자성(Atomicity)
		- 한 트랜잭션 내의 모든 연산들이 완전히 수행되거나 전혀 수행되지 않음
	- 일관성(Consistency)
		- 어떤 트랜잭션이 수행되기 전에 데이터베이스가 일관된 상태를 가졌다면 트랜잭션이 수행된 후에 데이터베이스는 또 다른 일관된 상태를 가짐(제약조건 등)
	- 고립성(Isolation)
		- 한 트랜잭션이 데이터를 갱신하는 동안 이 트랜잭션이 완료되기 전에는 갱신중인 데이터를 다른 트랜잭션에서 접근할 수 없음
	- 지속성(Durability)
		- 일단 한 트랜잭션이 완료되면 데이터베이스에 반영한 수행 결과는 영구적이어야 한다.

- 트랜잭션의 경계
	- 프레젠테이션 층과 비즈니스 층 사이에 놓여지는 것이 전통적인 방식
	- 프레젠테이션 층에 공개된 서비스의 메서드가 트랜잭션의 시작과 종료

- AOP를 이용한 트랜잭션 처리
	- AOP로 서비스에 트랜잭션 처리를 구현한 어드바이스를 적용함으로써 서비스 내부를 수정하지 않고 트랜잭션을 처리
	- 트랜잭션 처리는 트랜잭션 관리자와 트랜잭션 어드바이스를 이용

- 트랜잭션 관리자
	- 데이터베이스 연동 기술에 상관없이 동일한 방식으로 트랜잭션 관리기능을 제공
	- 트랜잭션 처리의 중심이 되는 인터페이스는 PlatfromTransactionManager
	- 스프링을 이용해서 트랜잭션을 처리하기 위해서는 DB연동기술에 적합한 트랜잭션 관리자를 등록

- 트랜잭션 구현 클래스
	- 트랜잭션 시작과 종료, 롤백 처리를 비롯해 트랜잭션의 정의 정보를 설정
	- 트랜잭션의 정보
		- 전파(Propagation)속성, 독립성(Isolation)수준
		- 롤백(rollback)의 대상 예외, 커밋(Commit)의 대상 예외
		- 읽기(read-only) 전용, 타임아웃(timeout)

	클래스명 | 설명 |
	--- | --- |
	DataSourceTransactionManager | JDBC 및 MyBatis등의 JDBC 기반의 라이브러리로 접근
	HibernateTransactionManager | 하이버네이트를 이용한 접근
	JpaTransactionManager | JPA로 DB접근
	JtaTransactionManager | JTA로 DB접근
	WebLogicJtaTransactionManager | 웹로직 애플리케이션 서버의 JTA에서 트랜잭션 처리 관리
	WebSphereUowTransactionManager | 웹스피오 애플리케이션 서버의 JTA에서 트랜잭션 처리 관리

- 기본적인 트랜잭션 관리자 설정
```
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"></property>
</bean>
```

- 트랜잭션 정의 정보
	- 전파(propagation) 속성 : 기존 트랜잭션이 있는 상태에서 새로운 트랜잭션을 별도로 생성하거나 기존 트랜잭션에 포함시켜 실행하도록 설정하기 위함
	- 독립성(isolation) 수준 : 트랜잭션 처리가 병행해서 실행될 때 각 트랜잭션의 독립성을 결정
	- 만료시간 : 트랜잭션이 취소되는 만료시간을 초 단위로 설정
	- 읽기 전용 상태 : 트랜잭션 내의 처리가 읽기 전용으로 설정
	- 롤백 대상 예외(Exception)
	- 커밋 대상 예외(Exception)

- @Transactional 애노테이선의 전파 속성
	- 2개 이상의 트랜잭션이 작동할 때, 기존 트랜잭션에 참여하는 방법을 결정하는 속성
	![Transaction_propagation](https://user-images.githubusercontent.com/51106028/121029275-6d49f880-c7e3-11eb-8f99-3db5451c2f20.PNG) 

	전파 속성 | 설명 |
	--- | --- |
	MANDATORY | 기존의 트랜잭션이 존재하면 포함되어 실행하고, 존재하지 않으면 예외 발생
	NESTED | 기존 트랜잭션이 존재하면 포함되어 실행, 없으면 새로운 트랜잭션 생성 (REQUIRED와 동일, 차이점은 SAVEPOINT를 지정한 시점까지 부분 롤백이 가능)
	SUPPORTS | 기존 트랜잭션이 존재하면 포함해서 실행하고, 없으면 없이 실행
	REQUIRED | 기존 트랜잭션이 있으면 해당 트랜잭션에 포함하고, 없으면 새로운 트랜잭션 생성
	NOT_SUPPORTED | 기존 트랜잭션이 존재하든 안하든 실행
	NEVER | 기존 트랜잭션이 존재하면 예외발생, 없으면 없이 실행
	REQUIRED_NEW | 항상 새로운 트랜잭션 실행, 기존 트랜잭션이 존재하면 일시 중지하고 새로운 트랜잭션이 완료된 후에 기존 트랜잭션 실행

- @Transactional 애노테이션의 격리(Isolation)수준 속성
	- 여러개의 트랜잭션이 동시에 실행될 때 어떻게 데이터 무결성을 보장할지에 대한 결정이 필요하고, 이 결정을 격리 수준 이라고 한다.
	
	격리 속성 | 설명 |
	--- | --- |
	DEFAULT | DB설정을 따름, 기본 격리 수준(기본설정)
	READ_UNCOMMITED | 다른 트랜잭션에 의해 커밋되지 않은 데이터를 읽을 수 있다.
	READ_COMMITED | 다른 트랜잭션에 의해 커밋된 데이터를 읽을 수 있다.
	REPEATABLE_READ | 트랜잭션 내에서 동일 필드에 대해 처음 읽어 온 데이터와 두 번째 읽어온 데이터가 동일한 값을 갖도록 보장한다.(다중접근의 경우)
	SERIALIZABLE | 가장 높은 격리수준, 동일한 데이터에 대해 동시에 2개 이상의 트랜잭션이 수행될 수 없다.

- AOP를 이용한 스프링의 트랜잭션 처리 방식을 다음 용어를 이용하여 설명하시오. (트랜잭션 관리자, PlatformTransactionManager, MyBatis, DataSourceTransactionManager, Propagation, Isolation)
	- 트랜잭션 처리는 트랜잭션 관리자와 트랜잭션 어드바이스를 사용하는데, 트랜잭션 관리자란 데이터베이스 연동기술에 상관없이 동일한 방식으로 트랜잭션 관리기능을 제공해 주고 이때 트랜젝션 처리에 중심이 되는 인터페이스 이름이 PlatformTransactionManager이다. 트랜잭션을 구현하는 클래스는 다양하게 존재하는데 그 중 JDBC 및 Mybatis등의 JDBC기반의 라이브러리로 접근하는 클래스는 DataSourceTransactionManager 클래스이다. 트랜잭션을 정의할 때 들어가야할 정보들이 있는데 그 속성에는 Propagation속성과 Isolation속성이 있다. Propagation속성은 2개 이상의 트랜잭션이 작동할 때 기존 트랜잭션에 참여하는 방법을 결정하는 속성이고, Isolation속성은 트랜잭션 처리가 병행해서 실행될 때 각 트랜잭션의 독립성을 결정하여 무결성을 어떻게 보장할지 결정하는 속성이다.

- 선언적 트랜잭션 처리 (XML 설정)
	- <tx:advice> 태그를 이용하여 어드바이스 설정
	- <aop:config> 태그를 이용하여 AOP 설정을 통해 트랜잭션 적용
	```
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>

	<tx:advice id="txAdvice" transaction-manager="transactionManager">
		<tx:attributes>
			<tx:method name="*" propagation="REQUIRED"/>
		</tx:attributes>

	<aop:config>
		<aop:pointcut id="txPointcut" expression"execution(* updateMemberList(..)"/>
		<aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/>
	</aop:config>
	```

- 선언적 트랜잭션 처리 (Annotation 설정)
	- @Transactional 애노테이션을 통해 트랜잭션 처리가 필요한 클래스와 메서드에 설정 가능
	```
	@Transactional
	public void updateMemberList(StudentVO student, StudentVO student) throws Exception{------------------}

- @Transactional 우선순위
	- 매서드의 @Transactional 설정이 가장 우선순위가 높다.
	- 클래스의 @Transactional 설정은 메소드보다 우선순위가 낮다.
	- 인터페이스의 @Transactional 설정이 가장 우선순위가 낮다.