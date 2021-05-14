package org.kpu.di.service;

import org.kpu.di.domain.StudentVO;
import org.kpu.di.persistence.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//spring bean이라고 annotation을 붙여놓았다.
@Component
public class MemberServiceImpl implements MemberService {

	//memberDAO가 필요하다고 의존관계를 등록 해 놓았다.
	@Autowired
	private MemberDAO memberDAO;

	public StudentVO readMember(String id) throws Exception {
		return memberDAO.read(id);
	}
	
	public void addMember(StudentVO student) throws Exception {
		memberDAO.add(student);
	}

}
