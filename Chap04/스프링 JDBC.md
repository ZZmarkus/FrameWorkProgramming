
## 📌 4장 스프링 JDBC
- 데이터 액세스 층의 역할
    - 데이터 액세스 처리를 비즈니스 로직 층에서 분리하는 것

- DAO(Data Access Object)
    - 데이터 액세스 처리에 특화된 오브젝트

- 데이터 소스
    - 데이터 액세스 기술 종류와 상관없이 데이터베이스 접속을 관리해주는 인터페이스

- 데이터 소스 구현 (Pom.xml)
```
<dependency>
   <groupId>commons-dbcp</groupId>
   <artifactId>commons-dbcp</artifactId>
   <version>1.4</version>
</dependency>
```

- 서드 파티가 제공하는 데이터 소스(applicationContext.xml)
```
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
   <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
   <property name="url" value="jdbc:mysql://127.0.0.1:3306/springdb?allowPublicKeyRetrieval=true&amp;useUnicode=true&amp;characterEncoding=utf8&amp; useSSL=false&amp;serverTimezone=UTC" />
   <property name="username" value="spring" />
   <property name="password" value="passwd" />
   <property name="maxActive" value="5" />
</bean>
```
- JDBC의 문제점
    - 대량의 소스코드를 기술
    - 다양한 에러 원인을 파악하기 위한 코딩이 필요
    - 데이터베이스 제품마다 에러 코드가 달라서 코드의 일관성 유지가 어려움

- 스프링 JDBC
    - JDBC를 래핑한 API를 제공해 소스코드를 단순화

- SELECT문 (취득 결과가 레코드 건수 또는 특정 컬럼만 취득할 경우)
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

- SELECT문 (취득 결과가 한 레코드 값을 취득할 경우)
    1. queryForMap 메소드 : 한 레코드 값을 Map(컬럼 이름을 키로 값을 저장)데이터로
    ```
    Map<String, Object> student = jdbcTemplate.queryForMap("SELECT * FROM STUDENT WHERE id=?", id);
    String name = (String)student.get("username");
    ```

    2. queryForList 메소드 : 여러 레코드 값을 Map데이터로
    ```
    List<Map<String, Object>> studentList = jdbcTemplate.queryForMap("SELECT * FROM STUDENT");
    ```

- SELECT문 (도메인으로 변환할 경우)
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

- SELECT문 (도메인으로 변환할 경우)
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
    ```
- INSERT / UPDATE / DELETE문
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

- Junit을 이용한 스프링 테스트
    - 스프링 프레임워크에서 만든 클래스(@Controller, @Service, @Repository, @Componet등이 붙은 클래스)를 테스트하는 모듈
    - 단위테스트, 통합테스트를 지원하기 위한 매커니즘이나 편리한 기능을 제공한다.
