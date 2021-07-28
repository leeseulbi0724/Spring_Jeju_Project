package com.myjeju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.HDetailVO;
import com.myjeju.vo.HeartVO;
import com.myjeju.vo.HouseVO;
import com.myjeju.vo.HouseReviewVO;


@Repository
public class HouseDAO extends DBConn {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static String namespace = "mapper.house";
	
	//숙소 전체리스트
	public ArrayList<HouseVO> getHouseList(){
		List<HouseVO> list = sqlSession.selectList(namespace+".house_list");
		return (ArrayList<HouseVO>)list;
	}
	
	//숙소 전체 리스트
	public ArrayList<HouseVO> getHouseList(int startnum, int endnum){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(endnum));
		
		List<HouseVO> list = sqlSession.selectList(namespace+".ajax_house_list_num",param);
		
		return (ArrayList<HouseVO>)list;
	}
	
	//숙소 전체 리스트
	public ArrayList<HouseVO> getHouseList(int startnum, int end, String search, String search_text){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(end));
		param.put("search", search);
		param.put("search_text", search_text);
		
		List<HouseVO> list = sqlSession.selectList(namespace+".ajax_house_list_search",param);
		
		return (ArrayList<HouseVO>)list;
	}
	
	//숙소 상위3개
	public ArrayList<HouseVO> getHouseListTop3(){
		List<HouseVO> list = sqlSession.selectList(namespace+".house_top3");
		return (ArrayList<HouseVO>)list;
	}
	
	//숙소 상세 정보
	public HouseVO getHouseDetail(String hid) {
		return sqlSession.selectOne(namespace+".house_detail", hid);
	}
	
	//숙소 객실 정보
	public ArrayList<HDetailVO> getHDetail(String hid){
		List<HDetailVO> list = sqlSession.selectList(namespace+".room_detail",hid);
		return (ArrayList<HDetailVO>)list;
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
	
	//리뷰 쓰기
	public boolean getInsertResult(HouseReviewVO vo) {
		boolean result = false;
		
		int value = sqlSession.insert(namespace+".insert_review", vo);
		if(value != 0) result = true;
		
		return result;
	}
	
	//리뷰 리스트
	public ArrayList<HouseReviewVO> getHouseReview(String hid){
		List<HouseReviewVO> list = sqlSession.selectList(namespace+".review_list", hid);
		return (ArrayList<HouseReviewVO>)list;
	}
	
	
	//리뷰 리스트
	public ArrayList<HouseReviewVO> getHouseReview(String hid, int startnum, int endnum){
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("hid", hid);
		param.put("start", String.valueOf(startnum));
		param.put("end", String.valueOf(endnum));
		
		List<HouseReviewVO> list = sqlSession.selectList(namespace+".ajax_review_list",param);
		
		return (ArrayList<HouseReviewVO>)list;
	}
	
	
	//리뷰 삭제
	public int getHouseReviewDelete(String reid) {
		return sqlSession.delete(namespace+".delete_review", reid);
	}
	
	//별점 평균 업데이트
	public boolean getStarAvgUpdate(String hid) {
		boolean result = false;
		int value = sqlSession.update(namespace+".star_avg_update", hid);
		if(value != 0) result = true;
		return result;
	}
	
	//리뷰 카운트 업데이트
	public boolean getReviewCountUpdate(String hid) {
		boolean result =false;
		int value = sqlSession.update(namespace+".review_count_update", hid);
		if(value != 0) result =true;
		return result;
	}
	
}
