package com.myjeju.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.BasketVO;
import com.myjeju.vo.CafeReviewVO;
import com.myjeju.vo.CommunityVO;
import com.myjeju.vo.FoodReviewVO;
import com.myjeju.vo.HeartVO;
import com.myjeju.vo.HouseReviewVO;
import com.myjeju.vo.MemberVO;
import com.myjeju.vo.OrderVO;
import com.myjeju.vo.PointVO;
import com.myjeju.vo.StorevVO;
import com.myjeju.vo.TravelReviewVO;

@Repository
public class MypageDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.mypage";
	private static String namespace2 = "mapper.member";
	
	/** �н����� üũ **/
	public int getPassCheckResult(MemberVO vo) {
		return sqlSession.selectOne(namespace+".pass_check", vo);
	}
	
	/** ���̵�� ȸ������ �������� **/
	public MemberVO getMemberContent(String id) {
		return sqlSession.selectOne(namespace+".content", id);
	}
	
	/** ȸ������ �����ϱ� **/
	public int getInfoUpdate(MemberVO vo) {
		return sqlSession.update(namespace+".info_update", vo);
	}
	
	/** ȸ��Ż�� **/
	public int getInfoOut(String id) {
		return sqlSession.delete(namespace+".info_out", id);
	}
	
	/** �����Խ��� �Խñ� �ҷ����� **/
	public ArrayList<CommunityVO> getFreeBoardResult(String id) {
		List<CommunityVO> list = sqlSession.selectList(namespace+".user_free_list",id);		
		return (ArrayList<CommunityVO>)list;
	}
	
	/** �����Խ��� �Խñ� �ҷ����� **/
	public ArrayList<CommunityVO> getRequestBoardResult(String id) {
		List<CommunityVO> list = sqlSession.selectList(namespace+".user_request_list",id);		
		return (ArrayList<CommunityVO>)list;
	}
	
	/** �����Խ��� ��� �ҷ����� **/
	public ArrayList<CommunityVO> getCommentResult(String id) {
		List<CommunityVO> list = sqlSession.selectList(namespace+".user_comment_list",id);		
		return (ArrayList<CommunityVO>)list;
	}
	
	/** ����Ʈ **/
	public int getPoint(String id) {
		return sqlSession.selectOne(namespace2+".point", id);
	}
	
	/** �ֹ����� �������� **/
	public ArrayList<OrderVO> getOrderList(String id) {
		List<OrderVO> list = sqlSession.selectList(namespace+".order_list",id);		
		return (ArrayList<OrderVO>)list;
	}
	
	/** ����Ʈ ���� **/
	public ArrayList<PointVO> getPointList(String id) {
		List<PointVO> list = sqlSession.selectList(namespace+".point_list",id);		
		return (ArrayList<PointVO>)list;
	}
	
	/** ���ҿ��೻�� - �������̺� **/
	public ArrayList<OrderVO> getMReservation(String id) {
		List<OrderVO> list = sqlSession.selectList(namespace+".mreservation_list",id);		
		return (ArrayList<OrderVO>)list;
	}
	
	/** �����̸� + �̹��� �������� **/
	public OrderVO getHouseName(String hid) {
		return sqlSession.selectOne(namespace+".housename", hid);
	}
	
	/**���̸� �������� **/
	public OrderVO getRommName(String hdid) {
		return sqlSession.selectOne(namespace+".roomname", hdid);
	}
	
	/** �ֹ� ������ �ֱ� **/
	public int getOrderSequ(BasketVO vo) {
		return sqlSession.insert(namespace+".order_sequ", vo);
	}
	
	
	/** My�ı� - ����� ���� **/
	public ArrayList<StorevVO> getStoreReview(String id) {
		List<StorevVO> list = sqlSession.selectList(namespace + ".store_review", id);
		return (ArrayList<StorevVO>) list;
	}
	
	/** My�ı� - ������ ���� **/
	public ArrayList<TravelReviewVO> getTravelReview(String id) {
		List<TravelReviewVO> list = sqlSession.selectList(namespace + ".travel_review", id);
		return (ArrayList<TravelReviewVO>) list;
	}
	
	/** My�ı� - ������ ���� **/
	public ArrayList<FoodReviewVO> getFoodReview(String id) { 
		List<FoodReviewVO> list = sqlSession.selectList(namespace + ".food_review", id); 
		return (ArrayList<FoodReviewVO>) list; 
	}
	
	/** My�ı� - ī�� ���� **/
	public ArrayList<CafeReviewVO> getCafeReview(String id) { 
		List<CafeReviewVO> list = sqlSession.selectList(namespace + ".cafe_review", id); 
		return (ArrayList<CafeReviewVO>) list; 
	}
	
	/** My�ı� - ���� ���� **/
	public ArrayList<HouseReviewVO> getHouseReview(String id) { 
		List<HouseReviewVO> list = sqlSession.selectList(namespace + ".house_review", id); 
		return (ArrayList<HouseReviewVO>) list; 
	}
	
	/** My�ı� - ����� - ���� ���� ���� **/
	public int getMyreviewStoreDelete(String reid) {
		return sqlSession.delete(namespace + ".myreview_store_delete", reid);
	}
	
	/** My�ı� - ������ - ���� ���� ���� **/
	public int getMyreviewTravelDelete(String reid) {
		return sqlSession.delete(namespace + ".myreview_travel_delete", reid);
	}
	
	/** My�ı� - ������ - ���� ���� ���� **/
	public int getMyreviewFoodDelete(String reid) {
		return sqlSession.delete(namespace + ".myreview_food_delete", reid);
	}
	
	/** My�ı� - ī�� - ���� ���� ���� **/
	public int getMyreviewCafeDelete(String reid) {
		return sqlSession.delete(namespace + ".myreview_cafe_delete", reid);
	}
	
	/** My�ı� - ���� - ���� ���� ���� **/
	public int getMyreviewHouseDelete(String reid) {
		return sqlSession.delete(namespace + ".myreview_house_delete", reid);
	}
	
	/**
	 * ���೻����������
	 */
	public OrderVO getOrderContent(String rid) {
		return sqlSession.selectOne(namespace+".order_content", rid);
	}
	
	/**
	 * ��¥���̿� ��¥���ϱ�
	 */
	public ArrayList<OrderVO> getDayResult(OrderVO vo) {
		List<OrderVO> list = sqlSession.selectList(namespace+".day_result", vo);
		return (ArrayList<OrderVO>) list;
	}
	
	/**
	 * reservation 0�����
	 */
	public int getReservationResult(OrderVO vo) {
		return sqlSession.update(namespace+".reservation_result", vo);
	}
	
	/**
	 * ���೻������
	 */
	public int getOrderDelete(String rid) {
		return sqlSession.delete(namespace+".order_delete", rid);
	}
	
	/** ���ƿ� ���� - house **/
	public ArrayList<HeartVO> getHouseHeartList(String id) {
		List<HeartVO> list = sqlSession.selectList(namespace+".house_heart", id);
		return (ArrayList<HeartVO>) list;
	}
	
	/** ���ƿ� ���� - food **/
	public ArrayList<HeartVO> getFoodHeartList(String id) {
		List<HeartVO> list = sqlSession.selectList(namespace+".food_heart", id);
		return (ArrayList<HeartVO>) list;
	}
	
	/** ���ƿ� ���� - cafe **/
	public ArrayList<HeartVO> getCafeHeartList(String id) {
		List<HeartVO> list = sqlSession.selectList(namespace+".cafe_heart", id);
		return (ArrayList<HeartVO>) list;
	}
	
	/** ���ƿ� ���� - travel **/
	public ArrayList<HeartVO> getTravelHeartList(String id) {
		List<HeartVO> list = sqlSession.selectList(namespace+".travel_heart", id);
		return (ArrayList<HeartVO>) list;
	}
	
	
}