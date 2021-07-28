package com.myjeju.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.StorevVO;

@Repository
public class StorevDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static String namespace = "mapper.store";
	
	//스토어 상품평 쓰기
	public boolean getInsertResult(StorevVO vo) {
		boolean result = false;
		int value = sqlSession.insert(namespace + ".storeReviewInsert", vo);
		if(value != 0) result = true;
		return result;
	}
	
	//스토어 상품평리스트 가져오기
	public ArrayList<StorevVO> getStoreReview(String sid) {
		List<StorevVO> list = sqlSession.selectList(namespace + ".StoreReviewList", sid);
		return (ArrayList<StorevVO>) list;
	}

	// 스토어 상품평 하나만 가져오기 
	public StorevVO getStoreReviewOne(String srid) {
		return sqlSession.selectOne(namespace + ".reviewOne", srid);
	}
	
	// 스토어 상품평 삭제
	public int getReviewDelete(String srid) {
		return sqlSession.delete(namespace + ".reviewDelete", srid);
	}
	
	// 스토어 상품평 수정
	public int getReviewUpdate(StorevVO vo) {
		return sqlSession.update(namespace + ".reviewUpdate", vo);
	}
	
	// 스토어 상품평 하나만
	public int getReviewCount(String sid) {
		return sqlSession.selectOne(namespace + ".reviewCount", sid);
	}
	
	// 스토어 상품평 별점 평균
	public int getReviewAvg(String sid) {
		return sqlSession.selectOne(namespace + ".reviewAvg", sid);
	}
	
	public boolean getReviewAvgUpdate(String sid) {
		boolean result = false;
		int value = sqlSession.update(namespace + ".review_avg_update", sid);
		if(value != 0) result = true;
		return result;
	}
}
