package com.myjeju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.myjeju.vo.HDetailVO;
import com.myjeju.vo.HouseVO;
import com.myjeju.vo.MReservationVO;
import com.myjeju.vo.RoomImgVO;
import com.myjeju.vo.RoomVO;

public class ReservationDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace="mapper.reservation";
	
	public String getCurrentName(String hid) {
		return sqlSession.selectOne(namespace+".currentname", hid);
	}
	
	public ArrayList<RoomVO> searchroom(String start, String end,String hdid) {
		
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", start);
		se.put("end", end);
		se.put("hdid", hdid);
		
		List<RoomVO> list = sqlSession.selectList(namespace+".searchroom", se);
		return (ArrayList<RoomVO>)list;
	}
	public ArrayList<RoomVO> searchroom_each(String start, String end,String roomid) {
		
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", start);
		se.put("end", end);
		se.put("roomid", roomid);
		System.out.println(start);
		System.out.println(end);
		System.out.println(roomid);
		List<RoomVO> list = sqlSession.selectList(namespace+".searchroomeach", se);
		return (ArrayList<RoomVO>)list;
	}
	
	public ArrayList<RoomVO> notavails(String start, String end,String hdid) {
		
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", start);
		se.put("end", end);
		se.put("hdid", hdid);
		
		List<RoomVO> list = sqlSession.selectList(namespace+".notavails", se);
		return (ArrayList<RoomVO>)list;
	}
	
	public HouseVO gethouse(String hid) {
		return sqlSession.selectOne(namespace+".gethouse", hid);
	}
	
	public HDetailVO getdetail(String hdid) {
		return sqlSession.selectOne(namespace+".gethdetail", hdid);
	}
	
	public ArrayList<RoomImgVO> gethimg(String hdid) {
		List<RoomImgVO> list = sqlSession.selectList(namespace+".searchimg", hdid);
		return (ArrayList<RoomImgVO>)list;
	}
	
	public boolean setreservation(MReservationVO vo) {
		 int numresult = sqlSession.insert(namespace+".inserreservation",vo);
		 boolean result = false;
		 if(numresult !=0) {
			 result = true;
		 }
		 return result;
	}
	public boolean updateavail(String roomid,String f_dated,String s_dated) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("roomid", roomid);
		se.put("f_dated", f_dated);
		se.put("s_dated", s_dated);
		
		int numresult = sqlSession.update(namespace+".updateavail",se);
		boolean result = false;
		if(numresult !=0) {
			result = true;
		}
		return result;
	}
}
