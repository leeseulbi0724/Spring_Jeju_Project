package com.myjeju.service;

import java.util.ArrayList;

import com.myjeju.vo.CafeReviewVO;
import com.myjeju.vo.CafeVO;
import com.myjeju.vo.HeartVO;

public interface CafeService {
	ArrayList<CafeVO> getList();
	ArrayList<CafeVO> getCafeList();
	ArrayList<CafeVO> getCafeList(int startnum, int endnum);
	ArrayList<CafeVO> getCafeList(int startnum, int end, String search, String search_text);
	ArrayList<CafeVO> getCafeListTop3();
	CafeVO getCafeDetail(String caid);
	
	int getHeartInfoResult(HeartVO vo);
	boolean getHeartMinus(HeartVO vo);
	boolean getUpdateHeart(String caid);
	boolean getUpdateMinusHeart(String caid);
	boolean getHeartPlus(HeartVO vo);
	
	boolean getInsertResult(CafeReviewVO vo);
	ArrayList<CafeReviewVO> getCafeReview(String caid);
	ArrayList<CafeReviewVO> getCafeReview(String caid, int startnum, int endnum);
	boolean getCafeReviewDelete(String reid);
	boolean getStarAvgUpdate(String caid);
	boolean getReviewCountUpdate(String caid);
}
