package com.myjeju.service;

import java.sql.Date;

import com.myjeju.vo.MemberVO;
import com.myjeju.vo.SessionVO;

public interface MemberService {
	
	boolean getJoinResult(MemberVO vo);
	boolean getLoginResult(MemberVO vo);
	String getIdSearchResult(MemberVO vo);
	String getPassSearchResult(MemberVO vo);
	boolean getPassUpdateResult(MemberVO vo);
	int getIdCheckResult(String id);
	

}
