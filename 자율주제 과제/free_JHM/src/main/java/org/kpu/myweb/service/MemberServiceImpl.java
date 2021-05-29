package org.kpu.myweb.service;

import java.util.List;

import org.kpu.myweb.domain.ReservVO;
import org.kpu.myweb.domain.StudentVO;
import org.kpu.myweb.persistence.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional ( propagation=Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE, timeout=10 )
	public void updateMemberList1(ReservVO res1) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.Transactionupdate(res1);
//		memberDAO.Transactionupdate(res2);
	}
	
//	@Transactional ( propagation=Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE, timeout=10 )
//	public void updateMemberList2(ReservVO res2) throws Exception {
//		// TODO Auto-generated method stub
//		memberDAO.Transactionupdate(res2);
//	}


}
