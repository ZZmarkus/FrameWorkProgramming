package org.kpu.myweb.service;

import java.util.List;

import org.kpu.myweb.domain.ReservVO;
import org.kpu.myweb.domain.StudentVO;

public interface MemberService {
	
	public StudentVO readMember(String id) throws Exception;
	public List<StudentVO> readMemberList() throws Exception;
	public void addMember(StudentVO student) throws Exception;
	public void deleteMember(String id) throws Exception; 
	public void updateMember(ReservVO vo) throws Exception;
	
	public void addReservation(ReservVO reserv) throws Exception;
	public ReservVO readMyReserv(String id) throws Exception;
	public List<ReservVO> readReservList() throws Exception;
	
	public void updateMemberList1(ReservVO student1) throws Exception;
//	public void updateMemberList2(ReservVO student2) throws Exception;

}
