package com.myjeju.service;

import com.myjeju.vo.BasketVO;
import com.myjeju.vo.MemberVO;

public interface PayService {
	
	boolean getOrderResult(BasketVO vo);
	boolean getPointInsert(MemberVO vo);
	boolean getPointDelete(MemberVO vo);
	boolean getPointPlus(MemberVO vo);
	boolean getPointMinus(MemberVO vo);

}
