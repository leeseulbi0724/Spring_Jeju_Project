package com.myjeju.service;

import java.util.ArrayList;

import com.myjeju.vo.HDetailVO;
import com.myjeju.vo.HouseVO;
import com.myjeju.vo.MReservationVO;
import com.myjeju.vo.RoomImgVO;
import com.myjeju.vo.RoomVO;

public interface ReservationService {
	
	String getCurrentName(String hid);
	HouseVO gethouse(String hid);
	HDetailVO getdetail(String hdid);
	ArrayList<RoomImgVO> gethimg(String hdid);
	boolean setreservation(MReservationVO vo);
	boolean updateavail(String roomid,String f_dated,String s_dated);
	ArrayList<RoomVO> searchroom(String start, String end ,String hdid);
	ArrayList<RoomVO> searchroom_each(String start, String end ,String roomid);
	ArrayList<RoomVO> notavails(String start, String end ,String hdid);
}
