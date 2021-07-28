package com.myjeju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.FoodReviewVO;
import com.myjeju.vo.FoodVO;
import com.myjeju.vo.HeartVO;

@Repository
public class FoodDAO extends DBConn {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static String namespace = "mapper.food";
	
	//Select --> 전체 리스트
	public ArrayList<FoodVO> getList(){
		List<FoodVO> list = sqlSession.selectList(namespace+".map_list");
		return (ArrayList<FoodVO>)list;
	}
	
	//음식점 전체 리스트
	public ArrayList<FoodVO> getFoodList(){
		List<FoodVO> list = sqlSession.selectList(namespace+".food_list");
		return (ArrayList<FoodVO>)list;
	}
	
	//음식점 전체 리스트
	public ArrayList<FoodVO> getFoodList(int startnum, int endnum){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(endnum));
		
		List<FoodVO> list = sqlSession.selectList(namespace+".ajax_food_list_num",param);
		
		return (ArrayList<FoodVO>)list;
	}
	
	//음식점 전체 리스트
	public ArrayList<FoodVO> getFoodList(int startnum, int end, String search, String search_text){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(end));
		param.put("search", search);
		param.put("search_text", search_text);
		
		List<FoodVO> list = sqlSession.selectList(namespace+".ajax_food_list_search",param);
		
		return (ArrayList<FoodVO>)list;
	}
	
	
	//음식점 상위3
	public ArrayList<FoodVO> getFoodListTop3(){
		List<FoodVO> list = sqlSession.selectList(namespace+".food_top3");
		return (ArrayList<FoodVO>)list;
	}

	//음식점 상세 정보
	public FoodVO getFoodDetail(String fid) {
		return sqlSession.selectOne(namespace+".food_detail", fid);
	}
	
	
	//리뷰 쓰기
	public boolean getInsertResult(FoodReviewVO vo) {
		boolean result = false;
		
		int value = sqlSession.insert(namespace+".insert_review", vo);
		if(value != 0) result = true;
		
		return result;
	}
	
	//리뷰 리스트
	public ArrayList<FoodReviewVO> getFoodReview(String fid){
		List<FoodReviewVO> list = sqlSession.selectList(namespace+".review_list", fid);
		return (ArrayList<FoodReviewVO>)list;
	}
	
	//리뷰 리스트
	public ArrayList<FoodReviewVO> getFoodReview(String fid, int startnum, int endnum){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("fid", fid);
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(endnum));
		
		List<FoodReviewVO> list = sqlSession.selectList(namespace+".ajax_review_list",param);
		
		return (ArrayList<FoodReviewVO>)list;
	}
	
	//리뷰 삭제
	public int getFoodReviewDelete(String reid) {
		return sqlSession.delete(namespace+".delete_review", reid);
	}
	
	//별점 평균 업데이트
	public boolean getStarAvgUpdate(String fid) {
		boolean result = false;
		int value = sqlSession.update(namespace+".star_avg_update", fid);
		if(value != 0) result = true;
		return result;
	}
	
	//리뷰 카운트 업데이트
	public boolean getReviewCountUpdate(String fid) {
		boolean result =false;
		int value = sqlSession.update(namespace+".review_count_update", fid);
		if(value != 0) result =true;
		return result;
	}
	
	//Update ---> 하트 업데이트
	public int getUpdateHeart(String hid) {
		return sqlSession.update(namespace+".updateheart", hid);
	}
	
	//Update ---> 하트 업데이트 마이너스
	public int getUpdateMinusHeart(String hid) {
		return sqlSession.update(namespace+".updateminusheart", hid);
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
