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
	
	//��� ����Ʈ ��������
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
	//���� ����Ʈ ��������
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
	//INSERT --> ���� ���� ���
	public boolean getHouseInsertResult(HouseVO vo) {
		boolean result = false;
		int value = sqlSession.insert(namespace + ".houseInsert", vo);
		if(value != 0) result = true;
		return result;
	}
	
	//SELECT --> ���� ���� ȭ��
	public HouseVO getHouseContent(String hid) {
		return sqlSession.selectOne(namespace + ".house_content", hid);
	}
	//������ - ���� UPDATE ���� ����
	public int getHouseUpdateFile(HouseVO vo) {
		return sqlSession.update(namespace + ".house_update_file", vo);
	}	
	
	//������ - ���� UPDATE ���� �� ����
	public int getHouseUpdateNoFile(HouseVO vo) {
		return sqlSession.update(namespace + ".house_update_no_file", vo);
	}
	//������ - ���� ������ �̹��� ã��
	public String getHouseOldFile(String hid) {
		return sqlSession.selectOne(namespace+".house_oldfile", hid);
	}
	//���� ���� ����Ʈ ��������
	public ArrayList<HDetailVO> gethousede(String hid) {
		List<HDetailVO> list = sqlSession.selectList(namespace+".listdehouse",hid);
		return (ArrayList<HDetailVO>)list;
	}
	
	//������ ���� ����
	public boolean getHouseDelete(String hid) {
		boolean result = false;
		int value = sqlSession.delete(namespace+".house_delete", hid);
		if(value != 0) result = true;
		return result;
	}
	
	public HDetailVO gethousedecontent(String hdid) {
		return sqlSession.selectOne(namespace+".getdehousecontent", hdid);
	}
	//���� �� ����Ʈ ��������
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
	//�� -- ����Ұ�
	public int notavail(String roomid,String date) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("roomid",roomid);
		se.put("date", date);
		return sqlSession.update(namespace+".notavail",se);
		}
	//�� -- ���డ��
	public int toavail(String roomid,String date) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("roomid",roomid);
		se.put("date", date);
		return sqlSession.update(namespace+".toavail",se);
	}
	
	//���� -- �����߰�
	public int getHdetailUpload(HDetailVO vo) {
		return sqlSession.insert(namespace+".hdetail_upload", vo);
	}
	//���� -- ���Ǽ���
	public int getHdetailUpdate(HDetailVO vo) {
		return sqlSession.update(namespace+".hdetail_update", vo);
	}
	//���� -- ���ǻ���
	public int getHdetailDelete(String hdid) {
		return sqlSession.delete(namespace+".hdetail_delete", hdid);
	}
	
	//������ ����Ʈ ��������
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
	//������ -- �߰�
	public int getFoodUpload(FoodVO vo) {
		return sqlSession.insert(namespace+".food_upload", vo);
	}
	//������ �󼼺���
	public FoodVO getFoodcontent(String fid) {
		return sqlSession.selectOne(namespace+".food_content", fid);
	}
	//������ - ������Ʈ
	public int getFoodUpdate(FoodVO vo) {
		return sqlSession.update(namespace+".food_update", vo);
	}
	//������ - ����
	public int getFoodDelete(String fid) {
		return sqlSession.delete(namespace+".food_delete", fid);
	}
	
	//������ ����Ʈ ��������
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
	//������ -- �߰�
	public int getTravelUpload(TravelVO vo) {
		return sqlSession.insert(namespace+".travel_upload", vo);
	}
	//������ - ������Ʈ
	public int getTravelUpdate(TravelVO vo) {
		return sqlSession.update(namespace+".travel_update", vo);
	}
	//������ - ����
	public int getTravelDelete(String tid) {
		return sqlSession.delete(namespace+".travel_delete", tid);
	}
	
	//������ ���佺�� -- �߰�
	public int getTravelPhotoSpotUpload(PhotoSpotVO photovo) {
		return sqlSession.insert(namespace+".travel_photospot_upload", photovo);
	}
	//������ ���ڽ��� -- �߰�
	public int getTravelCarSpotUpload(CarSpotVO carvo) {
		return sqlSession.insert(namespace+".travel_carspot_upload", carvo);
	}
	//������ ���佺�� ��������
	public PhotoSpotVO getPhotoSpot(String tid) {
		return sqlSession.selectOne(namespace+".photo_spot", tid);
	}
	//������ ���ڽ��� ��������
	public CarSpotVO getCarSpot(String tid) {
		return sqlSession.selectOne(namespace+".car_spot", tid);
	}
	//������ ���佺�� ����
	public int getPhotoSpotUpdate(PhotoSpotVO photovo) {
		return sqlSession.update(namespace+".photospot_update", photovo);
	}
	//������ ���ڽ��� ����
	public int getCarSpotUpdate(CarSpotVO carvo) {
		return sqlSession.update(namespace+".carspot_update", carvo);
	}
	//������ ���佺�� ����
	public int getPhotoSpotDelete(String tid) {
		return sqlSession.delete(namespace+".photospot_delete", tid);
	}
	//������ ���ڽ��� ����
	public int getCarSpotDelete(String tid) {
		return sqlSession.delete(namespace+".carspot_delete", tid);
	}
	
	//ī�� ����Ʈ ��������
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
	//ī�� -- �߰�
	public int getCafeUpload(CafeVO vo) {
		return sqlSession.insert(namespace+".cafe_upload", vo);
	}
	//ī�� �󼼺���
	public CafeVO getCafecontent(String fid) {
		return sqlSession.selectOne(namespace+".cafe_content", fid);
	}
	//ī�� - ������Ʈ
	public int getCafeUpdate(CafeVO vo) {
		return sqlSession.update(namespace+".cafe_update", vo);
	}
	//ī�� - ����
	public int getCafeDelete(String fid) {
		return sqlSession.delete(namespace+".cafe_delete", fid);
	}
	
	
	
	
	//������ �Խ��� ����Ʈ
	public ArrayList<CommunityVO> getBoardList(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<CommunityVO> list = sqlSession.selectList(namespace+".board_list",se);
		return (ArrayList<CommunityVO>)list;
	}
	//�Խ��� �� ����
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
	
	//������ �������� �۾���
	public int getNoticeWrite(NoticeVO vo) {
		return sqlSession.insert(namespace+".notice_write", vo);
	}
	//������ ����Ʈ
	public ArrayList<NoticeVO> getNoticeList(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<NoticeVO> list = sqlSession.selectList(namespace+".notice_list",se);
		return (ArrayList<NoticeVO>)list;
	}
	//�������� �� ����
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
	//�������� �󼼺���
	public NoticeVO getNoticeContent(String nid) {
		return sqlSession.selectOne(namespace+".notice_content", nid);
	}
	//�������� --> ������Ʈ
	public int getFileYesUpdate(NoticeVO vo) {
		return sqlSession.update(namespace+".yes_update", vo);
	}	
	//���Ϲ����� --> ������Ʈ
	public int getFileNoUpdate(NoticeVO vo) {
		return sqlSession.update(namespace+".no_update", vo);
	}
	//�������� �̹��� ã��
	public String getOldFile(String nid) {
		return sqlSession.selectOne(namespace+".oldfile", nid);
	}
	//�������� ����
	public int getNoticeDelete(String nid) {
		return sqlSession.delete(namespace+".notice_delete", nid);
	}
	
	//������ ��û ����Ʈ
	public ArrayList<CommunityVO> getRequestList(int startnum, int endnum) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<CommunityVO> list = sqlSession.selectList(namespace+".request_list",se);
		return (ArrayList<CommunityVO>)list;
	}
	//��û �� ����
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
	//�󼼺���
	public CommunityVO getRequestContent(String rid) {
		return sqlSession.selectOne(namespace+".request_content", rid);
	}
	//��۴ޱ�
	public int getRequestComment(CommunityVO vo) {
		return sqlSession.insert(namespace+".request_comment", vo);
	}
	//�亯���� ��������
	public CommunityVO getRequestCommentResult(String rid) {
		return sqlSession.selectOne(namespace+".request_comment_result", rid);
	}
	//�����ϱ�
	public int getRequestDelete(String rid) {
		return sqlSession.delete(namespace+".request_delete", rid);
	}
	
	
	//������ �����
	//������ - ����� ��ǰ ��ü ����Ʈ
	public ArrayList<StoreVO> getStoreList(int startnum, int endnum) {
		Map<String, String> se = new HashMap<String, String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(endnum));
		
		List<StoreVO> list = sqlSession.selectList(namespace + ".store_list", se);
		return (ArrayList<StoreVO>) list;
	}
	
	//������ - ����� ��ǰ ��ü ����Ʈ
	public ArrayList<StoreVO> getStoreList(int startnum, int end, String search, String search_text) {
		Map<String,String> se = new HashMap<String,String>();
		se.put("start", String.valueOf(startnum));
		se.put("end", String.valueOf(end));
		se.put("search", search);
		se.put("search_text", search_text);
		
		List<StoreVO> list = sqlSession.selectList(namespace+".store_search",se);
		return (ArrayList<StoreVO>) list;
	}
	
	//������ - ����� �� ����
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
	
	//SELECT --> ����� ��ǰ �� ȭ��
	public StoreVO getStoreContent(String sid) {
		return sqlSession.selectOne(namespace + ".store_content", sid);
	}
	
	//������ - ����� UPDATE ���� ����
	public int getStoreUpdateFile(StoreVO vo) {
		return sqlSession.update(namespace + ".store_update_file", vo);
	}	
	
	//������ - ����� UPDATE ���� �� ����
	public int getStoreUpdateNoFile(StoreVO vo) {
		return sqlSession.update(namespace + ".store_update_no_file", vo);
	}
	
	//������ - ����� ������ �̹��� ã��
	public String getStoreOldFile(String sid) {
		return sqlSession.selectOne(namespace+".store_oldfile", sid);
	}
	
	//INSERT --> ����� ��ǰ ���
	public boolean getStoreInsertResult(StoreVO vo) {
		boolean result = false;
		int value = sqlSession.insert(namespace + ".storeInsert", vo);
		if(value != 0) result = true;
		return result;
	}
	
	//����� ��ǰ ����
	public boolean getStoreDelete(String sid) {
		boolean result = false;
		int value = sqlSession.delete(namespace+".store_delete", sid);
		if(value != 0) result = true;
		return result;
	}
	
	
	


}