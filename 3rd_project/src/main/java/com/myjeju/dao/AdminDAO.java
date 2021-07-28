package com.myjeju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

@Repository
public class AdminDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.admin";
	
	//멤버 리스트 가져오기
	public ArrayList<MemberVO> getlist(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<MemberVO> list = sqlSession.selectList(namespace+".listnum",se);
		return (ArrayList<MemberVO>)list;
	}
	public ArrayList<MemberVO> getlist(int startnum, int end, String search, String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(end));
		se.put("search", search);
		se.put("search_text", search_text);
		
		List<MemberVO> list = sqlSession.selectList(namespace+".listsearch",se);
		return (ArrayList<MemberVO>)list;
	}
	public int targetPage(int pageNumber) {
		return sqlSession.selectOne(namespace+".target", pageNumber);
	}
	public int targetPage(int pageNumber,String search,String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("pageNumber", String.valueOf(pageNumber));
		se.put("search", search);
		se.put("search_text", search_text);
		return sqlSession.selectOne(namespace+".searchtarget1", se);
	}
	public MemberVO getmember(String id) {
		return sqlSession.selectOne(namespace+".getmember", id);
	}
	//숙소 리스트 가져오기
	public ArrayList<HouseVO> getlisthouse(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<HouseVO> list = sqlSession.selectList(namespace+".listnumhouse",se);
		return (ArrayList<HouseVO>)list;
	}
	public ArrayList<HouseVO> getlisthouse(int startnum, int end, String search, String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(end));
		se.put("search", search);
		se.put("search_text", search_text);
		
		List<HouseVO> list = sqlSession.selectList(namespace+".listsearchhouse",se);
		return (ArrayList<HouseVO>)list;
	}
	public int targethousePage(int pageNumber) {
		return sqlSession.selectOne(namespace+".targethouse", pageNumber);
	}
	public int targethousePage(int pageNumber,String search,String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("pageNumber", String.valueOf(pageNumber));
		se.put("search", search);
		se.put("search_text", search_text);
		return sqlSession.selectOne(namespace+".searchtargethouse", se);
	}
	public HouseVO gethouse(String hid) {
		return sqlSession.selectOne(namespace+".gethouse", hid);
	}
	//INSERT --> 숙소 숙소 등록
	public boolean getHouseInsertResult(HouseVO vo) {
		boolean result = false;
		int value = sqlSession.insert(namespace + ".houseInsert", vo);
		if(value != 0) result = true;
		return result;
	}
	
	//SELECT --> 숙소 수정 화면
	public HouseVO getHouseContent(String hid) {
		return sqlSession.selectOne(namespace + ".house_content", hid);
	}
	//관리자 - 숙소 UPDATE 파일 포함
	public int getHouseUpdateFile(HouseVO vo) {
		return sqlSession.update(namespace + ".house_update_file", vo);
	}	
	
	//관리자 - 숙소 UPDATE 파일 미 포함
	public int getHouseUpdateNoFile(HouseVO vo) {
		return sqlSession.update(namespace + ".house_update_no_file", vo);
	}
	//관리자 - 숙소 오래된 이미지 찾기
	public String getHouseOldFile(String hid) {
		return sqlSession.selectOne(namespace+".house_oldfile", hid);
	}
	//숙소 객실 리스트 가져오기
	public ArrayList<HDetailVO> gethousede(String hid) {
		List<HDetailVO> list = sqlSession.selectList(namespace+".listdehouse",hid);
		return (ArrayList<HDetailVO>)list;
	}
	
	//관리자 숙소 삭제
	public boolean getHouseDelete(String hid) {
		boolean result = false;
		int value = sqlSession.delete(namespace+".house_delete", hid);
		if(value != 0) result = true;
		return result;
	}
	
	public HDetailVO gethousedecontent(String hdid) {
		return sqlSession.selectOne(namespace+".getdehousecontent", hdid);
	}
	//객실 룸 리스트 가져오기
	public ArrayList<RoomdeVO> gethousederoom(String hdid) {
		List<RoomdeVO> list = sqlSession.selectList(namespace+".listdehouseroom",hdid);
		return (ArrayList<RoomdeVO>)list;
	}
	public ArrayList<RoomVO> getmonthcheck(String month,String roomid) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("month", month);
		se.put("roomid", roomid);
		List<RoomVO> list = sqlSession.selectList(namespace+".getmonthcheck",se);
		return (ArrayList<RoomVO>)list;
	}
	public int insertres(RoomVO vo) {
		return sqlSession.insert(namespace+".insertres", vo);
	}
	public int uploadroom(String hdid,String room_name) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("hdid", String.valueOf(hdid));
		se.put("room_name", room_name);
		return sqlSession.insert(namespace+".uploadroom", se);
	}
	public RoomdeVO getroom(String roomid) {
		return sqlSession.selectOne(namespace+".getroom", roomid);
	}
	public int deleteroom(String roomid) {
		return sqlSession.delete(namespace+".deleteroom", roomid);
	}
	//룸 -- 예약불가
	public int notavail(String roomid,String date) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("roomid",roomid);
		se.put("date", date);
		return sqlSession.update(namespace+".notavail",se);
		}
	//룸 -- 예약가능
	public int toavail(String roomid,String date) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("roomid",roomid);
		se.put("date", date);
		return sqlSession.update(namespace+".toavail",se);
	}
	
	//숙소 -- 객실추가
	public int getHdetailUpload(HDetailVO vo) {
		return sqlSession.insert(namespace+".hdetail_upload", vo);
	}
	//숙소 -- 객실수정
	public int getHdetailUpdate(HDetailVO vo) {
		return sqlSession.update(namespace+".hdetail_update", vo);
	}
	//숙소 -- 객실삭제
	public int getHdetailDelete(String hdid) {
		return sqlSession.delete(namespace+".hdetail_delete", hdid);
	}
	
	//음식점 리스트 가져오기
	public ArrayList<FoodVO> getlistfood(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<FoodVO> list = sqlSession.selectList(namespace+".listnumfood",se);
		return (ArrayList<FoodVO>)list;
	}
	public ArrayList<FoodVO> getlistfood(int startnum, int end, String search, String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(end));
		se.put("search", search);
		se.put("search_text", search_text);
		
		List<FoodVO> list = sqlSession.selectList(namespace+".listsearchfood",se);
		return (ArrayList<FoodVO>)list;
	}
	public int targetfoodPage(int pageNumber) {
		return sqlSession.selectOne(namespace+".targetfood", pageNumber);
	}
	public int targetfoodPage(int pageNumber,String search,String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("pageNumber", String.valueOf(pageNumber));
		se.put("search", search);
		se.put("search_text", search_text);
		return sqlSession.selectOne(namespace+".searchtargetfood", se);
	}
	//음식점 -- 추가
	public int getFoodUpload(FoodVO vo) {
		return sqlSession.insert(namespace+".food_upload", vo);
	}
	//음식점 상세보기
	public FoodVO getFoodcontent(String fid) {
		return sqlSession.selectOne(namespace+".food_content", fid);
	}
	//음식점 - 업데이트
	public int getFoodUpdate(FoodVO vo) {
		return sqlSession.update(namespace+".food_update", vo);
	}
	//음식점 - 삭제
	public int getFoodDelete(String fid) {
		return sqlSession.delete(namespace+".food_delete", fid);
	}
	
	//여행지 리스트 가져오기
	public ArrayList<TravelVO> getlisttravel(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<TravelVO> list = sqlSession.selectList(namespace+".listnumtravel",se);
		return (ArrayList<TravelVO>)list;
	}
	public ArrayList<TravelVO> getlisttravel(int startnum, int end, String search, String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(end));
		se.put("search", search);
		se.put("search_text", search_text);
		
		List<TravelVO> list = sqlSession.selectList(namespace+".listsearchtravel",se);
		return (ArrayList<TravelVO>)list;
	}
	public int targettravelPage(int pageNumber) {
		return sqlSession.selectOne(namespace+".targettravel", pageNumber);
	}
	public int targettravelPage(int pageNumber,String search,String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("pageNumber", String.valueOf(pageNumber));
		se.put("search", search);
		se.put("search_text", search_text);
		return sqlSession.selectOne(namespace+".searchtargettravel", se);
	}
	
	public TravelVO gettravel(String tid) {
		return sqlSession.selectOne(namespace+".gettravel", tid);
	}
	//여행지 -- 추가
	public int getTravelUpload(TravelVO vo) {
		return sqlSession.insert(namespace+".travel_upload", vo);
	}
	//여행지 - 업데이트
	public int getTravelUpdate(TravelVO vo) {
		return sqlSession.update(namespace+".travel_update", vo);
	}
	//여행지 - 삭제
	public int getTravelDelete(String tid) {
		return sqlSession.delete(namespace+".travel_delete", tid);
	}
	
	//여행지 포토스팟 -- 추가
	public int getTravelPhotoSpotUpload(PhotoSpotVO photovo) {
		return sqlSession.insert(namespace+".travel_photospot_upload", photovo);
	}
	//여행지 차박스팟 -- 추가
	public int getTravelCarSpotUpload(CarSpotVO carvo) {
		return sqlSession.insert(namespace+".travel_carspot_upload", carvo);
	}
	//여행지 포토스팟 가져오기
	public PhotoSpotVO getPhotoSpot(String tid) {
		return sqlSession.selectOne(namespace+".photo_spot", tid);
	}
	//여행지 차박스팟 가져오기
	public CarSpotVO getCarSpot(String tid) {
		return sqlSession.selectOne(namespace+".car_spot", tid);
	}
	//여행지 포토스팟 수정
	public int getPhotoSpotUpdate(PhotoSpotVO photovo) {
		return sqlSession.update(namespace+".photospot_update", photovo);
	}
	//여행지 차박스팟 수정
	public int getCarSpotUpdate(CarSpotVO carvo) {
		return sqlSession.update(namespace+".carspot_update", carvo);
	}
	//여행지 포토스팟 삭제
	public int getPhotoSpotDelete(String tid) {
		return sqlSession.delete(namespace+".photospot_delete", tid);
	}
	//여행지 차박스팟 삭제
	public int getCarSpotDelete(String tid) {
		return sqlSession.delete(namespace+".carspot_delete", tid);
	}
	
	//카페 리스트 가져오기
	public ArrayList<CafeVO> getlistcafe(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<CafeVO> list = sqlSession.selectList(namespace+".listnumcafe",se);
		return (ArrayList<CafeVO>)list;
	}
	public ArrayList<CafeVO> getlistcafe(int startnum, int end, String search, String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(end));
		se.put("search", search);
		se.put("search_text", search_text);
		
		List<CafeVO> list = sqlSession.selectList(namespace+".listsearchcafe",se);
		return (ArrayList<CafeVO>)list;
	}
	public int targetcafePage(int pageNumber) {
		return sqlSession.selectOne(namespace+".targetcafe", pageNumber);
	}
	public int targetcafePage(int pageNumber,String search,String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("pageNumber", String.valueOf(pageNumber));
		se.put("search", search);
		se.put("search_text", search_text);
		return sqlSession.selectOne(namespace+".searchtargetcafe", se);
	}
	//카페 -- 추가
	public int getCafeUpload(CafeVO vo) {
		return sqlSession.insert(namespace+".cafe_upload", vo);
	}
	//카페 상세보기
	public CafeVO getCafecontent(String fid) {
		return sqlSession.selectOne(namespace+".cafe_content", fid);
	}
	//카페 - 업데이트
	public int getCafeUpdate(CafeVO vo) {
		return sqlSession.update(namespace+".cafe_update", vo);
	}
	//카페 - 삭제
	public int getCafeDelete(String fid) {
		return sqlSession.delete(namespace+".cafe_delete", fid);
	}
	
	
	
	
	//관리자 게시판 리스트
	public ArrayList<CommunityVO> getBoardList(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<CommunityVO> list = sqlSession.selectList(namespace+".board_list",se);
		return (ArrayList<CommunityVO>)list;
	}
	//게시판 총 갯수
	public int getBoardPage(int pageNumber) {
		return sqlSession.selectOne(namespace+".board_count", pageNumber);
	}
	//
	public ArrayList<CommunityVO> getBoardList(int startnum, int end, String search, String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(end));
		se.put("search", search);
		se.put("search_text", search_text);
		
		List<CommunityVO> list = sqlSession.selectList(namespace+".board_search",se);
		return (ArrayList<CommunityVO>)list;
	}
	public int getBoardPage(int pageNumber,String search,String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("pageNumber", String.valueOf(pageNumber));
		se.put("search", search);
		se.put("search_text", search_text);
		return sqlSession.selectOne(namespace+".board_searchpage", se);
	}
	
	//관리자 공지사항 글쓰기
	public int getNoticeWrite(NoticeVO vo) {
		return sqlSession.insert(namespace+".notice_write", vo);
	}
	//관리자 리스트
	public ArrayList<NoticeVO> getNoticeList(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<NoticeVO> list = sqlSession.selectList(namespace+".notice_list",se);
		return (ArrayList<NoticeVO>)list;
	}
	//공지사항 총 갯수
	public int getNoticePage(int pageNumber) {
		return sqlSession.selectOne(namespace+".notice_count", pageNumber);
	}
	public ArrayList<NoticeVO> getNoticeList(int startnum, int end, String search, String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(end));
		se.put("search", search);
		se.put("search_text", search_text);
		
		List<NoticeVO> list = sqlSession.selectList(namespace+".notice_search",se);
		return (ArrayList<NoticeVO>)list;
	}
	public int getNoticePage(int pageNumber,String search,String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("pageNumber", String.valueOf(pageNumber));
		se.put("search", search);
		se.put("search_text", search_text);
		return sqlSession.selectOne(namespace+".notice_searchpage", se);
	}
	//공지사항 상세보기
	public NoticeVO getNoticeContent(String nid) {
		return sqlSession.selectOne(namespace+".notice_content", nid);
	}
	//파일포함 --> 업데이트
	public int getFileYesUpdate(NoticeVO vo) {
		return sqlSession.update(namespace+".yes_update", vo);
	}	
	//파일미포함 --> 업데이트
	public int getFileNoUpdate(NoticeVO vo) {
		return sqlSession.update(namespace+".no_update", vo);
	}
	//공지사항 이미지 찾기
	public String getOldFile(String nid) {
		return sqlSession.selectOne(namespace+".oldfile", nid);
	}
	//공지사항 삭제
	public int getNoticeDelete(String nid) {
		return sqlSession.delete(namespace+".notice_delete", nid);
	}
	
	//관리자 요청 리스트
	public ArrayList<CommunityVO> getRequestList(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<CommunityVO> list = sqlSession.selectList(namespace+".request_list",se);
		return (ArrayList<CommunityVO>)list;
	}
	//요청 총 갯수
	public int getRequestPage(int pageNumber) {
		return sqlSession.selectOne(namespace+".request_count", pageNumber);
	}
	public ArrayList<CommunityVO> getRequestList(int startnum, int end, String search, String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(end));
		se.put("search", search);
		se.put("search_text", search_text);
		
		List<CommunityVO> list = sqlSession.selectList(namespace+".request_search",se);
		return (ArrayList<CommunityVO>)list;
	}
	public int getRequestPage(int pageNumber,String search,String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("pageNumber", String.valueOf(pageNumber));
		se.put("search", search);
		se.put("search_text", search_text);
		return sqlSession.selectOne(namespace+".request_searchpage", se);
	}
	//상세보기
	public CommunityVO getRequestContent(String rid) {
		return sqlSession.selectOne(namespace+".request_content", rid);
	}
	//댓글달기
	public int getRequestComment(CommunityVO vo) {
		return sqlSession.insert(namespace+".request_comment", vo);
	}
	//답변내용 가져오기
	public CommunityVO getRequestCommentResult(String rid) {
		return sqlSession.selectOne(namespace+".request_comment_result", rid);
	}
	//삭제하기
	public int getRequestDelete(String rid) {
		return sqlSession.delete(namespace+".request_delete", rid);
	}
	
	
	//관리자 스토어
	//관리자 - 스토어 상품 전체 리스트
	public ArrayList<StoreVO> getStoreList(int startnum, int endnum) {
		Map<String, String> se = new HashMap<String, String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<StoreVO> list = sqlSession.selectList(namespace + ".store_list", se);
		return (ArrayList<StoreVO>) list;
	}
	
	//관리자 - 스토어 상품 전체 리스트
	public ArrayList<StoreVO> getStoreList(int startnum, int end, String search, String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(end));
		se.put("search", search);
		se.put("search_text", search_text);
		
		List<StoreVO> list = sqlSession.selectList(namespace+".store_search",se);
		return (ArrayList<StoreVO>) list;
	}
	
	//관리자 - 스토어 총 개수
	public int getStorePage(int pageNumber) {
		return sqlSession.selectOne(namespace+".store_count", pageNumber);
	}
	public int getStorePage(int pageNumber,String search,String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("pageNumber", String.valueOf(pageNumber));
		se.put("search", search);
		se.put("search_text", search_text);
		return sqlSession.selectOne(namespace+".store_searchpage", se);
	}
	
	//SELECT --> 스토어 상품 상세 화면
	public StoreVO getStoreContent(String sid) {
		return sqlSession.selectOne(namespace + ".store_content", sid);
	}
	
	//관리자 - 스토어 UPDATE 파일 포함
	public int getStoreUpdateFile(StoreVO vo) {
		return sqlSession.update(namespace + ".store_update_file", vo);
	}	
	
	//관리자 - 스토어 UPDATE 파일 미 포함
	public int getStoreUpdateNoFile(StoreVO vo) {
		return sqlSession.update(namespace + ".store_update_no_file", vo);
	}
	
	//관리자 - 스토어 오래된 이미지 찾기
	public String getStoreOldFile(String sid) {
		return sqlSession.selectOne(namespace+".store_oldfile", sid);
	}
	
	//INSERT --> 스토어 상품 등록
	public boolean getStoreInsertResult(StoreVO vo) {
		boolean result = false;
		int value = sqlSession.insert(namespace + ".storeInsert", vo);
		if(value != 0) result = true;
		return result;
	}
	
	//스토어 상품 삭제
	public boolean getStoreDelete(String sid) {
		boolean result = false;
		int value = sqlSession.delete(namespace+".store_delete", sid);
		if(value != 0) result = true;
		return result;
	}
	
	
	


}
