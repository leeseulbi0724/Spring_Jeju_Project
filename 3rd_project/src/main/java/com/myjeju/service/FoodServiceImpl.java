package com.myjeju.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjeju.dao.FoodDAO;
import com.myjeju.vo.FoodReviewVO;
import com.myjeju.vo.FoodVO;
import com.myjeju.vo.HeartVO;

@Service("foodService")
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodDAO foodDAO;
	
	@Override
	public ArrayList<FoodVO> getList(){
		return foodDAO.getList();
	}
	
	@Override
	public ArrayList<FoodVO> getFoodList(){
		return foodDAO.getFoodList();
	}
	
	@Override
	public ArrayList<FoodVO> getFoodList(int startnum, int endnum){
		return foodDAO.getFoodList(startnum, endnum);
	}
	
	@Override
	public ArrayList<FoodVO> getFoodList(int startnum, int end, String search, String search_text){
		return foodDAO.getFoodList(startnum, end, search, search_text);
	}

	@Override
	public ArrayList<FoodVO> getFoodListTop3(){
		return foodDAO.getFoodListTop3();
	}
	
	@Override
	public FoodVO getFoodDetail(String fid) {
		return foodDAO.getFoodDetail(fid);
	}
	
	
	@Override
	public boolean getInsertResult(FoodReviewVO vo) {
		return foodDAO.getInsertResult(vo);
	}
	
	@Override
	public ArrayList<FoodReviewVO> getFoodReview(String fid){
		return foodDAO.getFoodReview(fid);
	}
	
	@Override
	public ArrayList<FoodReviewVO> getFoodReview(String fid, int startnum, int endnum){
		return foodDAO.getFoodReview(fid, startnum, endnum);
	}
	
	@Override
	public boolean getFoodReviewDelete(String reid) {
		int value = foodDAO.getFoodReviewDelete(reid);
		
		boolean result = false;
		if(value != 0) result = true;
		
		return result;
	}
	
	@Override
	public boolean getStarAvgUpdate(String fid) {
		return foodDAO.getStarAvgUpdate(fid);
	}
	
	@Override
	public boolean getReviewCountUpdate(String fid) {
		return foodDAO.getReviewCountUpdate(fid);
	}
	
	
	
	@Override
	public boolean getUpdateHeart(String fid) {
		int val = foodDAO.getUpdateHeart(fid);
		boolean result = false;
		if ( val!= 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean getUpdateMinusHeart(String fid) {
		int val = foodDAO.getUpdateMinusHeart(fid);
		boolean result = false;
		if ( val!= 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public int getHeartInfoResult(HeartVO vo) {
		return foodDAO.getHeartInfoResult(vo);
	}
	
	@Override
	public boolean getHeartMinus(HeartVO vo) {
		int val = foodDAO.getHeartMinus(vo);
		boolean result = false;
		if ( val!= 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean getHeartPlus(HeartVO vo) {
		int val = foodDAO.getHeartPlus(vo);
		boolean result = false;
		if ( val!= 0) {
			result = true;
		}
		return result;
	}
	
}
