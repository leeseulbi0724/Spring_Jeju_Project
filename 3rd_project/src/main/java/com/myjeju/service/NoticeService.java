package com.myjeju.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.myjeju.vo.NoticeVO;


public interface NoticeService {
	
	ArrayList<NoticeVO> getNoticeList();
	 int getNoticeCount(String nid);

}
