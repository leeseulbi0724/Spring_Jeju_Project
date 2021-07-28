package com.myjeju.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjeju.dao.CafeDAO;
import com.myjeju.vo.CafeReviewVO;
import com.myjeju.vo.CafeVO;
import com.myjeju.vo.HeartVO;

@Service("cafeService")
public class CafeServiceImpl implements CafeService{
	
	@Autowired
	private CafeDAO cafeDAO;
	
	@Override
	public ArrayList<CafeVO> getList(){
		return cafeDAO.getList();
	}
	
	@Override
	public ArrayList<CafeVO> getCafeList(){
		return cafeDAO.getCafeList();
	}

	@Override
	public ArrayList<CafeVO> getCafeList(int startnum, int endnum){
		return cafeDAO.getCafeList(startnum, endnum);
	}
	
	@Override
	public ArrayList<CafeVO> getCafeList(int startnum, int end, String search, String search_text){
		return cafeDAO.getCafeList(startnum, end, search, search_text);
	}
	
	@Override
	public ArrayList<CafeVO> getCafeListTop3(){
		return cafeDAO.getCafeListTop3();
	}
	
	@Override
	public CafeVO getCafeDetail(String caid) {
		return cafeDAO.getCafeDetail(caid);
	}
	
	
	@Override
	public boolean getInsertResult(CafeReviewVO vo) {
		return cafeDAO.getInsertResult(vo);
	}
	
	@Override
	public ArrayList<CafeReviewVO> getCafeReview(String caid){
		return cafeDAO.getCafeReview(caid);
	}
	
	@Override
	public ArrayList<CafeReviewVO> getCafeReview(String caid, int startnum, int endnum){
		return cafeDAO.getCafeReview(caid, startnum, endnum);
	}
	
	@Override
	public boolean getCafeReviewDelete(String reid) {
		int value = cafeDAO.getCafeReviewDelete(reid);
		
		boolean result = false;
		if(value != 0) result = true;
		
		return result;
	}
	
	@Override
	public boolean getStarAvgUpdate(String caid) {
		return cafeDAO.getStarAvgUpdate(caid);
	}
	
	@Override
	public boolean getReviewCountUpdate(String caid) {
		return cafeDAO.getReviewCountUpdate(caid);
	}
	
	@Override
	public boolean getUpdateHeart(String caid) {
		int val = cafeDAO.getUpdateHeart(caid);
		boolean result = false;
		if ( val!= 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean getUpdateMinusHeart(String caid) {
		int val = cafeDAO.getUpdateMinusHeart(caid);
		boolean result = false;
		if ( val!= 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public int getHeartInfoResult(HeartVO vo) {
		return cafeDAO.getHeartInfoResult(vo);
	}
	
	@Override
	public boolean getHeartMinus(HeartVO vo) {
		int val = cafeDAO.getHeartMinus(vo);
		boolean result = false;
		if ( val!= 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean getHeartPlus(HeartVO vo) {
		int val = cafeDAO.getHeartPlus(vo);
		boolean result = false;
		if ( val!= 0) {
			result = true;
		}
		return result;
	}
}
