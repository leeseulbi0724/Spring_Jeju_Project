package com.myjeju.service;

import java.util.ArrayList;

import com.myjeju.vo.StofaqVO;

public interface StofaqService {

	ArrayList<StofaqVO> getList(String sid);
	boolean getInsertResult(StofaqVO vo);
	boolean getReplyResult(StofaqVO vo);
	StofaqVO getStid(String sid);
	
	boolean getFaqDelete(String st_id);
	
}
