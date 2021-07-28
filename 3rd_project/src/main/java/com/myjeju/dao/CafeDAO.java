package com.myjeju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.CafeReviewVO;
import com.myjeju.vo.CafeVO;
import com.myjeju.vo.HeartVO;

@Repository
public class CafeDAO extends DBConn {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static String namespace = "mapper.cafe";
	
	//Select --> 전체 리스트
	public ArrayList<CafeVO> getList(){
		List<CafeVO> list = sqlSession.selectList(namespace+".map_list");
		return (ArrayList<CafeVO>)list;
	}
	
	//카페 전체 리스트
	public ArrayList<CafeVO> getCafeList(){
		List<CafeVO> list = sqlSession.selectList(namespace+".cafe_list");
		return (ArrayList<CafeVO>)list;
	}
	
	//카페 전체 리스트
	public ArrayList<CafeVO> getCafeList(int startnum, int endnum){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(endnum));
		
		List<CafeVO> list = sqlSession.selectList(namespace+".ajax_cafe_list_num",param);
		
		return (ArrayList<CafeVO>)list;
	}
	
	//카페 전체 리스트
	public ArrayList<CafeVO> getCafeList(int startnum, int end, String search, String search_text){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(end));
		param.put("search", search);
		param.put("search_text", search_text);
		
		List<CafeVO> list = sqlSession.selectList(namespace+".ajax_cafe_list_search",param);
		
		return (ArrayList<CafeVO>)list;
	}
	
	//카페 상위3
	public ArrayList<CafeVO> getCafeListTop3(){
		List<CafeVO> list = sqlSession.selectList(namespace+".cafe_top3");
		return (ArrayList<CafeVO>)list;
	}
	
	//카페 상세 정보
	public CafeVO getCafeDetail(String caid) {
		return sqlSession.selectOne(namespace+".cafe_detail", caid);
	}
	
	
	//리뷰 쓰기
	public boolean getInsertResult(CafeReviewVO vo) {
		boolean result = false;
		
		int value = sqlSession.insert(namespace+".insert_review", vo);
		if(value != 0) result = true;
		
		return result;
	}
	
	//리뷰 리스트
	public ArrayList<CafeReviewVO> getCafeReview(String caid){
		List<CafeReviewVO> list = sqlSession.selectList(namespace+".review_list", caid);
		return (ArrayList<CafeReviewVO>)list;
	}
	
	//리뷰 리스트
	public ArrayList<CafeReviewVO> getCafeReview(String caid, int startnum, int endnum){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("caid", caid);
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(endnum));
		
		List<CafeReviewVO> list = sqlSession.selectList(namespace+".ajax_review_list",param);
		
		return (ArrayList<CafeReviewVO>)list;
	}
	
	//리뷰 삭제
	public int getCafeReviewDelete(String reid) {
		return sqlSession.delete(namespace+".delete_review", reid);
	}
	
	//별점 평균 업데이트
	public boolean getStarAvgUpdate(String caid) {
		boolean result = false;
		int value = sqlSession.update(namespace+".star_avg_update", caid);
		if(value != 0) result = true;
		return result;
	}
	
	//리뷰 카운트 업데이트
	public boolean getReviewCountUpdate(String caid) {
		boolean result =false;
		int value = sqlSession.update(namespace+".review_count_update", caid);
		if(value != 0) result =true;
		return result;
	}
	
	
	//Update ---> 하트 업데이트
	public int getUpdateHeart(String caid) {
		return sqlSession.update(namespace+".updateheart", caid);
	}
	
	//Update ---> 하트 업데이트 마이너스
	public int getUpdateMinusHeart(String caid) {
		return sqlSession.update(namespace+".updateminusheart", caid);
	}
	
	//하트 테이블 추가
	public int getHeartPlus(HeartVO vo) {
		return sqlSession.insert(namespace+".heart_plus", vo);
	}
	
	//하트 테이블 삭제
	public int getHeartMinus(HeartVO vo) {
		return sqlSession.delete(namespace+".heart_minus", vo);
	}
	
	//하트정보가져오기
	public int getHeartInfoResult(HeartVO vo) {
		return sqlSession.selectOne(namespace+".hearrt_info_result", vo);
	}
}
