package com.myjeju.service;

import java.util.ArrayList;
import java.util.List;

import com.myjeju.vo.BasketVO;
import com.myjeju.vo.StoreVO;
import com.myjeju.vo.StorevVO;

public interface StoreService {

	ArrayList<StoreVO> getBestList();
	ArrayList<StoreVO> getEatList();
	ArrayList<StoreVO> getEatList2();
	ArrayList<StoreVO> getSouveList();
	ArrayList<StoreVO> getSouveList2();
	ArrayList<StoreVO> getEtcList();
	ArrayList<StoreVO> getEtcList2();
	StoreVO getContent(String sid);
	
	ArrayList<List<BasketVO>> getBuyContent(String[] list, String id);
	ArrayList<BasketVO> getBuyContent(String sid, String id);
	
	
	//ªÛ«∞∆Ú
	boolean getInsertResult(StorevVO vo);
	ArrayList<StorevVO> getStoreReview(String sid);
	/*
	 * StorevVO getStoreReviewOne(String srid); boolean
	 * getStoreReviewUpdate(StorevVO vo);
	 */
	
	StorevVO getStoreReviewOne(String srid);
	boolean getReviewUpdate(StorevVO vo);
	boolean getReviewDelete(String srid);
	int getReviewCount(String sid);
	int getReviewAvg(String sid);
	boolean getReviewAvgUpdate(String sid);
	
	
	boolean getOrderResult(BasketVO vo);
}
