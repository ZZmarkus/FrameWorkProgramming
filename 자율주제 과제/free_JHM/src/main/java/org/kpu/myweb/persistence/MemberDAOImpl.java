package org.kpu.myweb.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kpu.myweb.domain.ReservVO;
import org.kpu.myweb.domain.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.myweb.mapper.StudentMapper";

	public StudentVO read(String id) throws Exception {
	
		StudentVO vo = sqlSession.selectOne(namespace+".selectByid", id);
		return vo;   
	}
	
	public ReservVO ReservRead(String id) throws Exception {
		
		ReservVO vo = sqlSession.selectOne(namespace+".selectMyres", id);
		return vo;   
	}
 
	public List<StudentVO> readList() throws Exception {
		List<StudentVO> studentlist = new ArrayList<StudentVO>();
		studentlist = sqlSession.selectList(namespace + ".selectAll");
		return studentlist;
	}
	
	public void add(StudentVO vo) throws Exception {
		sqlSession.insert(namespace + ".insert", vo);
	}

	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace + ".delete", id);
	}

	public void update(ReservVO student) throws Exception {
		// TODO Auto-generated method stub
	    sqlSession.update(namespace + ".update", student);
	}
	
	public void addreservation(ReservVO vo) throws Exception {
		sqlSession.insert(namespace + ".reservation", vo);
	}

	public List<ReservVO> readReservList() throws Exception {
		List<ReservVO> reservlist = new ArrayList<ReservVO>();
		reservlist = sqlSession.selectList(namespace + ".selectReservation");
		return reservlist;
	}
	
	public void Transactionupdate(ReservVO reserv) throws Exception{
	    sqlSession.insert(namespace + ".insertTransaction", reserv);
	}
}
