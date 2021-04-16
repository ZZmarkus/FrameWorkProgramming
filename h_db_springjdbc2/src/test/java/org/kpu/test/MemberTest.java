package org.kpu.test;
import java.sql.Connection;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kpu.di.domain.StudentVO;
import org.kpu.di.persistence.MemberDAO;
import org.kpu.di.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/applicationContext.xml")

public class MemberTest {
	@Autowired
	MemberService memberService;
	
	@Test
	public void testReadMember() throws Exception{
		StudentVO member = memberService.readMember("hansol");
		System.out.println(member);
	}
}
