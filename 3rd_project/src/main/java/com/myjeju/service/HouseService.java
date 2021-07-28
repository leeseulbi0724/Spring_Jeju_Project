package com.myjeju.service;

import java.util.ArrayList;

import com.myjeju.vo.HDetailVO;
import com.myjeju.vo.HeartVO;
import com.myjeju.vo.HouseReviewVO;
import com.myjeju.vo.HouseVO;

public interface HouseService{
	ArrayList<HouseVO> getHouseList();
	ArrayList<HouseVO> getHouseList(int startnum, int endnum);
	ArrayList<HouseVO> getHouseList(int startnum, int end, String search, String search_text);
	ArrayList<HouseVO> getHouseListTop3();
	HouseVO getHouseDetail(String hid);
	ArrayList<HDetailVO> getHDetail(String hid);

	/*
	 *
	 */
	/*
	 *   boolean
	 * getHeartResult(HeartVO vo);
	 */
	int getHeartInfoResult(HeartVO vo);
	boolean getHeartMinus(HeartVO vo);
	boolean getUpdateHeart(String hid);
	boolean getUpdateMinusHeart(String hid);
	boolean getHeartPlus(HeartVO vo);
	
	boolean getInsertResult(HouseReviewVO vo);
	ArrayList<HouseReviewVO> getHouseReview(String hid);
	ArrayList<HouseReviewVO> getHouseReview(String hid, int startnum, int endnum);
	boolean getHouseReviewDelete(String reid);
	boolean getStarAvgUpdate(String hid);
	boolean getReviewCountUpdate(String hid);
}
