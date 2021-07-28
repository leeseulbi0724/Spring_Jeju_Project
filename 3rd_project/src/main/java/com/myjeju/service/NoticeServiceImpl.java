package com.myjeju.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjeju.dao.CommunityDAO;
import com.myjeju.vo.NoticeVO;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private CommunityDAO communityDAO;
	
	@Override
	public ArrayList<NoticeVO> getNoticeList() {
		return communityDAO.getNoticeList();
		
	}
	
	@Override
	public  int getNoticeCount(String nid) {
		return communityDAO.getNoticeCount(nid);
	}

}
