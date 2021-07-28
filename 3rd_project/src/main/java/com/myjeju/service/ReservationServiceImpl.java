package com.myjeju.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjeju.dao.ReservationDAO;
import com.myjeju.vo.HDetailVO;
import com.myjeju.vo.HouseVO;
import com.myjeju.vo.MReservationVO;
import com.myjeju.vo.RoomImgVO;
import com.myjeju.vo.RoomVO;

@Service("ReservationService")
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationDAO reservationDAO;
	
	@Override
	public String getCurrentName(String hid) {
		return reservationDAO.getCurrentName(hid); 
	}
	@Override
	public ArrayList<RoomVO> searchroom(String start, String end,String hdid){
		return reservationDAO.searchroom(start,end,hdid); 
	}
	@Override
	public ArrayList<RoomVO> searchroom_each(String start, String end,String roomid){
		return reservationDAO.searchroom_each(start,end,roomid); 
	}
	@Override
	public ArrayList<RoomVO> notavails(String start, String end,String hdid){
		return reservationDAO.notavails(start,end,hdid); 
	}
	
	@Override
	public HouseVO gethouse(String hid) {
		return reservationDAO.gethouse(hid);
	}
	@Override
	public HDetailVO getdetail(String hdid) {
		return reservationDAO.getdetail(hdid);
	}
	@Override
	public ArrayList<RoomImgVO> gethimg(String hdid) {
		return reservationDAO.gethimg(hdid);
	}
	@Override
	public boolean setreservation(MReservationVO vo) {
		return reservationDAO.setreservation(vo);
	}
	@Override
	public boolean updateavail(String roomid,String f_dated,String s_dated) {
		return reservationDAO.updateavail(roomid,f_dated,s_dated);
	}
}
