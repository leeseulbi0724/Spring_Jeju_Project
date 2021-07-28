package com.myjeju.service;

import java.util.ArrayList;

import com.myjeju.vo.CarSpotVO;
import com.myjeju.vo.HeartVO;
import com.myjeju.vo.PhotoSpotVO;
import com.myjeju.vo.TravelReviewVO;
import com.myjeju.vo.TravelVO;

public interface TravelService {
	ArrayList<TravelVO> getList();
	ArrayList<TravelVO> getTravelList();
	ArrayList<TravelVO> getTravelList(int startnum, int endnum);
	ArrayList<TravelVO> getTravelList(int startnum, int end, String search, String search_text);
	ArrayList<TravelVO> getTravelListTop3();
	
	int getHeartInfoResult(HeartVO vo);
	boolean getHeartMinus(HeartVO vo);
	boolean getUpdateHeart(String tid);
	boolean getUpdateMinusHeart(String tid);
	boolean getHeartPlus(HeartVO vo);
	
	TravelVO getTravelDetail(String tid);
	PhotoSpotVO getPhotoSpot(String tid);
	CarSpotVO getCarSpot(String tid);
	
	boolean getInsertResult(TravelReviewVO vo);
	ArrayList<TravelReviewVO> getTravelReview(String tid);
	ArrayList<TravelReviewVO> getTravelReview(String tid, int startnum, int endnum);
	boolean getTravelReviewDelete(String reid);
	int getReviewCount(String tid);
	int getAvgStar(String tid);
	boolean getStarAvgUpdate(String tid);
	boolean getReviewCountUpdate(String tid);
}
