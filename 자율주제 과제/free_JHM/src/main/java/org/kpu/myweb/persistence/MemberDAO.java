package org.kpu.myweb.persistence;

import java.util.List;

import org.kpu.myweb.domain.ReservVO;
import org.kpu.myweb.domain.StudentVO;

public interface MemberDAO {

	public void add(StudentVO student) throws Exception;
	public List<StudentVO> readList() throws Exception;
	public StudentVO read(String id) throws Exception;
	public void delete(String id) throws Exception;
	public void update(ReservVO student) throws Exception;
	
	public void addreservation(ReservVO reserv) throws Exception;
	public ReservVO ReservRead(String id) throws Exception;
	public List<ReservVO> readReservList() throws Exception;
}
