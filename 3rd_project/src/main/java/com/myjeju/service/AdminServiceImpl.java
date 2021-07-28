package com.myjeju.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjeju.dao.AdminDAO;
import com.myjeju.vo.CafeVO;
import com.myjeju.vo.CarSpotVO;
import com.myjeju.vo.CommunityVO;
import com.myjeju.vo.FoodVO;
import com.myjeju.vo.HDetailVO;
import com.myjeju.vo.HouseVO;
import com.myjeju.vo.MemberVO;
import com.myjeju.vo.NoticeVO;
import com.myjeju.vo.PhotoSpotVO;
import com.myjeju.vo.RoomVO;
import com.myjeju.vo.RoomdeVO;
import com.myjeju.vo.StoreVO;
import com.myjeju.vo.TravelVO;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDAO;
	//관리자 멤버
	@Override
	public ArrayList<MemberVO> getlist(int startnum,int endnum){
		return adminDAO.getlist(startnum,endnum);
	}
	@Override
	public ArrayList<MemberVO> getlist(int startnum,int endnum,String search,String search_text){
		return adminDAO.getlist(startnum,endnum,search,search_text);
	}
	@Override
	public int targetPage(int pageNumber){
		return adminDAO.targetPage(pageNumber);
	}
	@Override
	public int targetPage(int pageNumber,String search,String search_text){
		return adminDAO.targetPage(pageNumber,search,search_text);
	}
	@Override
	public MemberVO getmember(String id) {
		return adminDAO.getmember(id);
	}
	//관리자 숙소
	@Override
	public ArrayList<HouseVO> getlisthouse(int startnum,int endnum){
		return adminDAO.getlisthouse(startnum,endnum);
	}
	@Override
	public ArrayList<HouseVO> getlisthouse(int startnum,int endnum,String search,String search_text){
		return adminDAO.getlisthouse(startnum,endnum,search,search_text);
	}
	@Override
	public int targethousePage(int pageNumber){
		return adminDAO.targethousePage(pageNumber);
	}
	@Override
	public int targethousePage(int pageNumber,String search,String search_text){
		return adminDAO.targethousePage(pageNumber,search,search_text);
	}
	@Override
	public HouseVO gethouse(String hid){
		return adminDAO.gethouse(hid);
	}
	@Override
	public boolean getHouseInsertResult(HouseVO vo) {
		return adminDAO.getHouseInsertResult(vo);
	}
	@Override
	public HouseVO getHouseContent(String hid) {
		return adminDAO.getHouseContent(hid);
	}
	@Override
	public boolean getHouseUpdateFile(HouseVO vo) {
		boolean result = false;
		int value = adminDAO.getHouseUpdateFile(vo);
		if (value != 0) {
			result = true;
		}
		return result;
	}
	@Override
	public boolean getHouseUpdateNoFile(HouseVO vo) {
		boolean result = false;
		int value = adminDAO.getHouseUpdateNoFile(vo);
		if (value != 0) {
			result = true;
		}
		return result;
	}
	@Override
	public String getHouseOldFile(String hid) {
		return adminDAO.getHouseOldFile(hid);
	}
	@Override
	public boolean getHouseDelete(String hid) {
		return adminDAO.getHouseDelete(hid);
	}
	//관리자 룸
	public ArrayList<RoomdeVO> gethousederoom(String hdid){
		return adminDAO.gethousederoom(hdid);
	}
	public ArrayList<RoomVO> getmonthcheck(String month,String roomid) {
		return adminDAO.getmonthcheck(month,roomid);
	}
	public boolean insertres(RoomVO vo) {
		int val = adminDAO.insertres(vo);
		boolean result = false;
		if (val!=0) {
			result = true;
		}
		return result;
	}
	public boolean deleteroom(String roomid) {
		int val = adminDAO.deleteroom(roomid);
		boolean result = false;
		if (val!=0) {
			result = true;
		}
		return result;
	}
	@Override
	public boolean uploadroom(String hdid,String room_name) {
		int val = adminDAO.uploadroom(hdid,room_name);
		boolean result = false;
		if (val!=0) {
			result = true;
		}
		return result;
	}
	@Override
	public RoomdeVO getroom(String roomid) {
		return adminDAO.getroom(roomid);
	}
	@Override
	public boolean notavail(String roomid,String date) {
		int val = adminDAO.notavail(roomid,date);
		boolean result = false;
		if (val!=0) {
			result = true;
		}
		return result;
	}
	@Override
	public boolean toavail(String roomid,String date) {
		int val = adminDAO.toavail(roomid,date);
		boolean result = false;
		if (val!=0) {
			result = true;
		}
		return result;
	}
	//관리자 객실
	public ArrayList<HDetailVO> gethousede(String hid){
		return adminDAO.gethousede(hid);
	}
	public HDetailVO gethousedecontent(String hdid) {
		return adminDAO.gethousedecontent(hdid);
	}
	public boolean getHdetailUpload(HDetailVO vo) {
		int val = adminDAO.getHdetailUpload(vo);
		boolean result = false;
		if (val!=0) {
			result = true;
		}
		return result;
	}
	public boolean getHdetailUpdate(HDetailVO vo) {
		int val = adminDAO.getHdetailUpdate(vo);
		boolean result = false;
		if (val!=0) {
			result = true;
		}
		return result;
	}
	public boolean getHdetailDelete(String hdid) {
		int val = adminDAO.getHdetailDelete(hdid);
		boolean result = false;
		if (val!=0) {
			result = true;
		}
		return result;
	}
	//관리자 음식점
	@Override
	public ArrayList<FoodVO> getlistfood(int startnum,int endnum){
		return adminDAO.getlistfood(startnum,endnum);
	}
	@Override
	public ArrayList<FoodVO> getlistfood(int startnum,int endnum,String search,String search_text){
		return adminDAO.getlistfood(startnum,endnum,search,search_text);
	}
	@Override
	public int targetfoodPage(int pageNumber){
		return adminDAO.targetfoodPage(pageNumber);
	}
	@Override
	public int targetfoodPage(int pageNumber,String search,String search_text){
		return adminDAO.targetfoodPage(pageNumber,search,search_text);
	}
	@Override
	public boolean getFoodUpload(FoodVO vo) {
		int val = adminDAO.getFoodUpload(vo);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
	}
	@Override
	public FoodVO getFoodcontent(String fid) {
		return adminDAO.getFoodcontent(fid);
	}
	@Override
	public boolean getFoodUpdate(FoodVO vo) {
		int val = adminDAO.getFoodUpdate(vo);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
		
	}
	@Override
	public boolean getFoodDelete(String fid) {
		int val = adminDAO.getFoodDelete(fid);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
	}
	//관리자 여행지
	@Override
	public ArrayList<TravelVO> getlisttravel(int startnum,int endnum){
		return adminDAO.getlisttravel(startnum,endnum);
	}
	@Override
	public ArrayList<TravelVO> getlisttravel(int startnum,int endnum,String search,String search_text){
		return adminDAO.getlisttravel(startnum,endnum,search,search_text);
	}
	@Override
	public int targettravelPage(int pageNumber){
		return adminDAO.targettravelPage(pageNumber);
	}
	@Override
	public int targettravelPage(int pageNumber,String search,String search_text){
		return adminDAO.targettravelPage(pageNumber,search,search_text);
	}
	@Override
	public TravelVO gettravel(String tid){
		return adminDAO.gettravel(tid);
	}
	@Override
	public boolean getTravelUpload(TravelVO vo) {
		int val = adminDAO.getTravelUpload(vo);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
	}
	@Override
	public boolean getTravelUpdate(TravelVO vo) {
		int val = adminDAO.getTravelUpdate(vo);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
		
	}
	@Override
	public boolean getTravelDelete(String tid) {
		int val = adminDAO.getTravelDelete(tid);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
	}
	
	//여행지 스팟
	@Override
	public boolean getTravelPhotoSpotUpload(PhotoSpotVO photovo) {
		int val = adminDAO.getTravelPhotoSpotUpload(photovo);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean getTravelCarSpotUpload(CarSpotVO carvo) {
		int val = adminDAO.getTravelCarSpotUpload(carvo);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public PhotoSpotVO getPhotoSpot(String tid){
		return adminDAO.getPhotoSpot(tid);
	}
	
	@Override
	public CarSpotVO getCarSpot(String tid){
		return adminDAO.getCarSpot(tid);
	}
	@Override
	public boolean getPhotoSpotUpdate(PhotoSpotVO photovo) {
		int val = adminDAO.getPhotoSpotUpdate(photovo);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean getCarSpotUpdate(CarSpotVO carvo) {
		int val = adminDAO.getCarSpotUpdate(carvo);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean getPhotoSpotDelete(String tid) {
		int val = adminDAO.getPhotoSpotDelete(tid);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
	}
	@Override
	public boolean getCarSpotDelete(String tid) {
		int val = adminDAO.getCarSpotDelete(tid);
		boolean result = false;
		if (val != 0) {
			result = true;
		}
		return result;
	}
	
	
	
	// 관리자 카페
	@Override
	public ArrayList<CafeVO> getlistcafe(int startnum, int endnum) {
		return adminDAO.getlistcafe(startnum, endnum);
	}
	@Override
	public ArrayList<CafeVO> getlistcafe(int startnum, int end, String search, String search_text) {
		return adminDAO.getlistcafe(startnum, end, search, search_text);
	}
	@Override
	public int targetcafePage(int pageNumber) {
		return adminDAO.targetcafePage(pageNumber);
	}
	@Override
	public int targetcafePage(int pageNumber, String search, String search_text) {
		return adminDAO.targetcafePage(pageNumber, search, search_text);
	}
	@Override
	public CafeVO getCafecontent(String fid) {
		return adminDAO.getCafecontent(fid);
	}
	@Override
	public boolean getCafeUpload(CafeVO vo) {
		int val = adminDAO.getCafeUpload(vo);
		boolean result = false;
		if(val != 0) {
			result = true;
		}
		return result;
	}
	@Override
	public boolean getCafeUpdate(CafeVO vo) {
		int val = adminDAO.getCafeUpdate(vo);
		boolean result = false;
		if(val != 0) {
			result = true;
		}
		return result;
	}
	@Override
	public boolean getCafeDelete(String fid) {
		int val = adminDAO.getCafeDelete(fid);
		boolean result = false;
		if(val != 0) {
			result = true;
		}
		return result;
	}
	
	
	
	//관리자 게시판
	@Override
	public ArrayList<CommunityVO> getBoardList(int startnum,int endnum) {
		return adminDAO.getBoardList(startnum, endnum);
	}
	@Override
	public int BoardPage(int pageNumber) {
		return adminDAO.getBoardPage(pageNumber);
	}
	@Override
	public int BoardPage(int pageNumber,String search,String search_text){
		return adminDAO.getBoardPage(pageNumber,search,search_text);
	}
	@Override
	public ArrayList<CommunityVO> getBoardList(int startnum,int endnum,String search,String search_text){
		return adminDAO.getBoardList(startnum,endnum,search,search_text);
	}
	
	//관리자 공지사항
	@Override
	public boolean getNoticeWrite(NoticeVO vo) {
		boolean result = false;
		int val = adminDAO.getNoticeWrite(vo);
		if (val != 0) {
			result = true;
		}
		return result;
	}
	@Override
	public  ArrayList<NoticeVO> getNoticeList(int startnum, int endnum) {
		return adminDAO.getNoticeList(startnum, endnum);
	}
	@Override
	public int NoticePage(int pageNumber) {
		return adminDAO.getNoticePage(pageNumber);
	}
	@Override
	public int NoticePage(int pageNumber,String search,String search_text) {
		return adminDAO.getNoticePage(pageNumber, search, search_text);
	}
	@Override
	public	ArrayList<NoticeVO> getNoticeList(int startnum,int endnum,String search,String search_text) {
		return adminDAO.getNoticeList(startnum, endnum, search, search_text);
	}
	@Override
	public  NoticeVO getNoticeContent(String nid) {
		return adminDAO.getNoticeContent(nid);
	}
	@Override
	public boolean getFileYesUpdate(NoticeVO vo) {
		int val = adminDAO.getFileYesUpdate(vo);
		boolean result = false;
		if ( val !=0) {
			result =true;
		}
		return result;
	}
	@Override
	public boolean getFileNoUpdate(NoticeVO vo) {
		int val = adminDAO.getFileNoUpdate(vo);
		boolean result = false;
		if ( val !=0) {
			result =true;
		}
		return result;
	}
	@Override
	public  String getOldFile(String nid) {
		return adminDAO.getOldFile(nid);
	}
	@Override
	public boolean getNoticeDelete(String nid) {
		int val = adminDAO.getNoticeDelete(nid);
		boolean result = false;
		if ( val !=0) {
			result =true;
		}
		return result;
	}
	
	//관리자 요청
	@Override
	public ArrayList<CommunityVO> getRequestList(int startnum,int endnum) {
		return adminDAO.getRequestList(startnum, endnum);
	}
	@Override
	public int RequestPage(int pageNumber) {
		return adminDAO.getRequestPage(pageNumber);
	}
	@Override
	public int RequestPage(int pageNumber,String search,String search_text){
		return adminDAO.getRequestPage(pageNumber,search,search_text);
	}
	@Override
	public ArrayList<CommunityVO> getRequestList(int startnum,int endnum,String search,String search_text){
		return adminDAO.getRequestList(startnum,endnum,search,search_text);
	}
	@Override
	public CommunityVO getRequestContent(String rid) {
		return adminDAO.getRequestContent(rid);
	}
	@Override
	public boolean getRequestComment(CommunityVO vo) {
		int val = adminDAO.getRequestComment(vo);
		boolean result = false;
		if (val!=0) {
			result = true;
		}
		return result;
	}
	@Override
	public CommunityVO getRequestCommentResult(String rid) {
		return adminDAO.getRequestCommentResult(rid);
	}
	@Override
	public boolean getRequestDelete(String rid) {
		int val = adminDAO.getRequestDelete(rid);
		boolean result = false;
		if (val!=0) {
			result = true;
		}
		return result;
	}
	
	
	
	//관리자 - 스토어
	@Override
	public ArrayList<StoreVO> getStoreList(int startnum, int endnum) {
		return adminDAO.getStoreList(startnum, endnum);
	}
	@Override
	public ArrayList<StoreVO> getStoreList(int startnum, int endnum, String search, String search_text) {
		return adminDAO.getStoreList(startnum, endnum, search, search_text);
	}
	@Override
	public int StorePage(int pageNumber) {
		return adminDAO.getStorePage(pageNumber);
	}
	@Override
	public int StorePage(int pageNumber, String search, String search_text) {
		return adminDAO.getStorePage(pageNumber, search, search_text);
	}
	@Override
	public StoreVO getStoreContent(String sid) {
		return adminDAO.getStoreContent(sid);
	}
	@Override
	public boolean getStoreUpdateFile(StoreVO vo) {
		boolean result = false;
		int value = adminDAO.getStoreUpdateFile(vo);
		if (value != 0) {
			result = true;
		}
		return result;
	}
	@Override
	public boolean getStoreUpdateNoFile(StoreVO vo) {
		boolean result = false;
		int value = adminDAO.getStoreUpdateNoFile(vo);
		if (value != 0) {
			result = true;
		}
		return result;
	}
	@Override
	public String getStoreOldFile(String sid) {
		return adminDAO.getStoreOldFile(sid);
	}
	@Override
	public boolean getStoreInsertResult(StoreVO vo) {
		return adminDAO.getStoreInsertResult(vo);
	}
	@Override
	public boolean getStoreDelete(String sid) {
		return adminDAO.getStoreDelete(sid);
	}
	

}
