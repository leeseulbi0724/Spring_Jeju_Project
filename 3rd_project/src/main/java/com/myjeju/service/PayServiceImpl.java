package com.myjeju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjeju.dao.PayDAO;
import com.myjeju.vo.BasketVO;
import com.myjeju.vo.MemberVO;

@Service("PayService")
public class PayServiceImpl implements PayService {

	@Autowired
	private PayDAO payDAO;
	
	
	@Override
	public boolean getOrderResult(BasketVO vo) {
		int val = payDAO.getOrderResult(vo);
		boolean result = false;
		if (val !=0) {
			result = true;
		}
		return result;
	}	
	
	@Override
	public boolean getPointInsert(MemberVO vo) {
		int val = payDAO.getPointInsert(vo);
		boolean result = false;
		if (val !=0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean getPointDelete(MemberVO vo) {
		int val = payDAO.getPointDelete(vo);
		boolean result = false;
		if (val !=0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean getPointPlus(MemberVO vo) {
		int val = payDAO.getPointPlus(vo);
		boolean result = false;
		if (val !=0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean getPointMinus(MemberVO vo) {
		int val = payDAO.getPointMinus(vo);
		boolean result = false;
		if (val !=0) {
			result = true;
		}
		return result;
	}
}
