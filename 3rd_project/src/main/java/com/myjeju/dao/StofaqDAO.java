package com.myjeju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.StofaqVO;

@Repository
public class StofaqDAO extends DBConn {
	//Store_faq_DAO : 스토어 상품문의 - 문의 DAO
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.stofaq";
	
	//SELECT --> 스토어 상품문의 (문의랑 답변 불러오기) 화면
	public ArrayList<StofaqVO> getList(String sid) {
		
		List<StofaqVO> list = sqlSession.selectList(namespace + ".faqlist", sid);
		
		return (ArrayList<StofaqVO>) list;
	}
	

	//INSERT --> 스토어 상품문의 - ""문의"" 등록
	public boolean getInsertResult(StofaqVO vo) {
		boolean result = false;
		int value = sqlSession.insert(namespace + ".faqinsert", vo);
		if(value != 0) result = true;
		
		return result;
	}
	
	//INSERT --> 스토어 상품문의 - ""답변"" 등록
	public boolean getReplyResult(StofaqVO vo) {
		boolean result = false;
		int value = sqlSession.update(namespace + ".replylist", vo);
		if(value != 0) result = true;
		
		return result;
	}
	
	
	//SELECT --> st_id 받아오는 법
	public StofaqVO getStid(String sid) {
		return sqlSession.selectOne(namespace + ".getSt_id", sid);
	}
	
	// 스토어 상품문의 삭제
		public int getFaqDelete(String st_id) {
			return sqlSession.delete(namespace + ".faqDelete", st_id);
		}
	
}
