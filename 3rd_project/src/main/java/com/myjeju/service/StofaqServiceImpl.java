package com.myjeju.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjeju.dao.StofaqDAO;
import com.myjeju.vo.StofaqVO;

@Service("stofaqService")
public class StofaqServiceImpl implements StofaqService {

	@Autowired
	private StofaqDAO stofaqDAO;
	
	@Override
	public ArrayList<StofaqVO> getList(String sid) {
		return stofaqDAO.getList(sid);
	}

	@Override
	public boolean getInsertResult(StofaqVO vo) {
		return stofaqDAO.getInsertResult(vo);
	}

	@Override
	public boolean getReplyResult(StofaqVO vo) {
		return stofaqDAO.getReplyResult(vo);
	}

	@Override
	public StofaqVO getStid(String sid) {
		return stofaqDAO.getStid(sid);
	}

	@Override
	public boolean getFaqDelete(String st_id) {
		int value = stofaqDAO.getFaqDelete(st_id);
		
		boolean result = false;
		if(value != 0) result = true;
		
		return result;
	}

	
	
	
	
}
