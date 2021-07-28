package com.myjeju.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.BasketVO;
import com.myjeju.vo.MemberVO;

@Repository
public class PayDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.pay";
	
	public int getOrderResult(BasketVO vo) {
		return sqlSession.insert(namespace+".order_result", vo);
	}
	
	public int getPointInsert(MemberVO vo) {
		return sqlSession.update(namespace+".point_insert", vo);		
	}
	
	public int getPointDelete(MemberVO vo) {
		return sqlSession.update(namespace+".point_delete", vo);
	}
	
	public int getPointPlus(MemberVO vo) {
		return sqlSession.insert(namespace+".point_plus", vo);
	}
	
	public int getPointMinus(MemberVO vo) {
		return sqlSession.insert(namespace+".point_minus", vo);
	}
}
