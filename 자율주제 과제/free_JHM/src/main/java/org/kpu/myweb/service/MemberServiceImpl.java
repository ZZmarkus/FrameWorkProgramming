package org.kpu.myweb.service;

import java.util.List;

import org.kpu.myweb.domain.ReservVO;
import org.kpu.myweb.domain.StudentVO;
import org.kpu.myweb.persistence.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;

	public StudentVO readMember(String id) throws Exception {
		return memberDAO.read(id);
	}
	
	public ReservVO readMyReserv(String id) throws Exception {
		return memberDAO.ReservRead(id);
	}
	
	public List<StudentVO> readMemberList() throws Exception{
		return memberDAO.readList();
	}
	
	public void addMember(StudentVO student) throws Exception {
		memberDAO.add(student);
	}
	
	public void deleteMember(String id) throws Exception {
		memberDAO.delete(id);
	}
	
	public void updateMember(ReservVO student) throws Exception {
		memberDAO.update(student);
	}

	public void addReservation(ReservVO reserv) throws Exception {
		memberDAO.addreservation(reserv);
	}
	
	public List<ReservVO> readReservList() throws Exception{
		return memberDAO.readReservList();
	}
}
