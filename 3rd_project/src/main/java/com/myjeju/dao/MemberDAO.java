package com.myjeju.dao;

import java.sql.Date;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.MemberVO;
import com.myjeju.vo.SessionVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.member";
	
	//회원가입
	public int getJoinResult(MemberVO vo) {
		return sqlSession.insert(namespace+".join", vo);
	}
	
	//로그인
	public int getLoginResult(MemberVO vo) {
		return sqlSession.selectOne(namespace+".login", vo);
	}
	
	//아이디 찾기
	public String getIdSearchResult(MemberVO vo) {
		return sqlSession.selectOne(namespace+".id_search", vo);
	}
	
	//패스워드 찾기
	public String getPassSearchResult(MemberVO vo) {
		return sqlSession.selectOne(namespace+".pass_search", vo);
	}
	
	//패스워드 변경
	public int getPassUpdateResult(MemberVO vo) {
		return sqlSession.update(namespace+".pass_update", vo);
	}
	
	//아이디 중복확인
	public int getIdCheckResult(String id) {
		return sqlSession.selectOne(namespace+".id_check", id);
	}
	
	

	
}
