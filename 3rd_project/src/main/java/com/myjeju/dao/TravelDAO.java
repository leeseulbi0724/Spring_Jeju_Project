package com.myjeju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.CarSpotVO;
import com.myjeju.vo.HeartVO;
import com.myjeju.vo.PhotoSpotVO;
import com.myjeju.vo.TravelReviewVO;
import com.myjeju.vo.TravelVO;

@Repository
public class TravelDAO extends DBConn {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static String namespace = "mapper.travel";
	
	//Select --> 전체 리스트
	public ArrayList<TravelVO> getList(){
		List<TravelVO> list = sqlSession.selectList(namespace+".map_list");
		return (ArrayList<TravelVO>)list;
	}
		
	//여행지 전체 리스트
	public ArrayList<TravelVO> getTravelList(){
		List<TravelVO> list = sqlSession.selectList(namespace+".travel_list");
		return (ArrayList<TravelVO>)list;
	}
	
	//여행지 전체 리스트
	public ArrayList<TravelVO> getTravelList(int startnum, int endnum){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(endnum));
		
		List<TravelVO> list = sqlSession.selectList(namespace+".ajax_travel_list_num",param);
		
		return (ArrayList<TravelVO>)list;
	}
	
	//여행지 전체 리스트
	public ArrayList<TravelVO> getTravelList(int startnum, int end, String search, String search_text){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(end));
		param.put("search", search);
		param.put("search_text", search_text);
		
		List<TravelVO> list = sqlSession.selectList(namespace+".ajax_travel_list_search",param);
		
		return (ArrayList<TravelVO>)list;
	}
	
	//여행지 상위3
	public ArrayList<TravelVO> getTravelListTop3(){
		List<TravelVO> list = sqlSession.selectList(namespace+".travel_top3");
		return (ArrayList<TravelVO>)list;
	}
	
	//여행지 상세 정보
	public TravelVO getTravelDetail(String tid) {
		return sqlSession.selectOne(namespace+".travel_detail", tid);
	}
	
	//포토 스팟 
	public PhotoSpotVO getPhotoSpot(String tid) {
		return sqlSession.selectOne(namespace+".photo_spot", tid);
	}
	
	//차박 스팟
	public CarSpotVO getCarSpot(String tid) {
		return sqlSession.selectOne(namespace+".car_spot", tid);
	}
	
	//리뷰 쓰기
	public boolean getInsertResult(TravelReviewVO vo) {
		boolean result = false;
		
		int value = sqlSession.insert(namespace+".insert_review", vo);
		if(value != 0) result = true;
		
		return result;
	}
	
	//리뷰 리스트
	public ArrayList<TravelReviewVO> getTravelReview(String tid){
		List<TravelReviewVO> list = sqlSession.selectList(namespace+".review_list", tid);
		return (ArrayList<TravelReviewVO>)list;
	}
	
	
	//리뷰 리스트
	public ArrayList<TravelReviewVO> getTravelReview(String tid, int startnum, int endnum){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("tid", tid);
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(endnum));
		
		List<TravelReviewVO> list = sqlSession.selectList(namespace+".ajax_review_list",param);
		
		return (ArrayList<TravelReviewVO>)list;
	}
	
	//리뷰 삭제
	public int getTravelReviewDelete(String reid) {
		return sqlSession.delete(namespace+".delete_review", reid);
	}
	
	//리뷰 카운트 
	public int getReviewCount(String tid) {
		return sqlSession.selectOne(namespace+".review_count", tid);
	}
	
	//별점 평균
	public int getAvgStar(String tid) {
		return sqlSession.selectOne(namespace+".avg_star", tid);
	}
	
	//별점 평균 업데이트
	public boolean getStarAvgUpdate(String tid) {
		boolean result = false;
		int value = sqlSession.update(namespace+".star_avg_update", tid);
		if(value != 0) result = true;
		return result;
	}
	
	//리뷰 카운트 업데이트
	public boolean getReviewCountUpdate(String tid) {
		boolean result =false;
		int value = sqlSession.update(namespace+".review_count_update", tid);
		if(value != 0) result =true;
		return result;
	}
	
	
	//Update ---> 하트 업데이트
	public int getUpdateHeart(String tid) {
		return sqlSession.update(namespace+".updateheart", tid);
	}
	
	//Update ---> 하트 업데이트 마이너스
	public int getUpdateMinusHeart(String tid) {
		return sqlSession.update(namespace+".updateminusheart", tid);
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
