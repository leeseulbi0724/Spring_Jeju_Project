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
	
	/** 패스워드 체크 **/
	public int getPassCheckResult(MemberVO vo) {
		return sqlSession.selectOne(namespace+".pass_check", vo);
	}
	
	/** 아이디로 회원정보 가져오기 **/
	public MemberVO getMemberContent(String id) {
		return sqlSession.selectOne(namespace+".content", id);
	}
	
	/** 회원정보 수정하기 **/
	public int getInfoUpdate(MemberVO vo) {
		return sqlSession.update(namespace+".info_update", vo);
	}
	
	/** 회원탈퇴 **/
	public int getInfoOut(String id) {
		return sqlSession.delete(namespace+".info_out", id);
	}
	
	/** 자유게시판 게시글 불러오기 **/
	public ArrayList<CommunityVO> getFreeBoardResult(String id) {
		List<CommunityVO> list = sqlSession.selectList(namespace+".user_free_list",id);		
		return (ArrayList<CommunityVO>)list;
	}
	
	/** 자유게시판 게시글 불러오기 **/
	public ArrayList<CommunityVO> getRequestBoardResult(String id) {
		List<CommunityVO> list = sqlSession.selectList(namespace+".user_request_list",id);		
		return (ArrayList<CommunityVO>)list;
	}
	
	/** 자유게시판 댓글 불러오기 **/
	public ArrayList<CommunityVO> getCommentResult(String id) {
		List<CommunityVO> list = sqlSession.selectList(namespace+".user_comment_list",id);		
		return (ArrayList<CommunityVO>)list;
	}
	
	/** 포인트 **/
	public int getPoint(String id) {
		return sqlSession.selectOne(namespace2+".point", id);
	}
	
	/** 주문내역 가져오기 **/
	public ArrayList<OrderVO> getOrderList(String id) {
		List<OrderVO> list = sqlSession.selectList(namespace+".order_list",id);		
		return (ArrayList<OrderVO>)list;
	}
	
	/** 포인트 내역 **/
	public ArrayList<PointVO> getPointList(String id) {
		List<PointVO> list = sqlSession.selectList(namespace+".point_list",id);		
		return (ArrayList<PointVO>)list;
	}
	
	/** 숙소예약내역 - 시퀀테이블 **/
	public ArrayList<OrderVO> getMReservation(String id) {
		List<OrderVO> list = sqlSession.selectList(namespace+".mreservation_list",id);		
		return (ArrayList<OrderVO>)list;
	}
	
	/** 숙소이름 + 이미지 가져오기 **/
	public OrderVO getHouseName(String hid) {
		return sqlSession.selectOne(namespace+".housename", hid);
	}
	
	/**방이름 가져오기 **/
	public OrderVO getRommName(String hdid) {
		return sqlSession.selectOne(namespace+".roomname", hdid);
	}
	
	/** 주문 시퀀스 넣기 **/
	public int getOrderSequ(BasketVO vo) {
		return sqlSession.insert(namespace+".order_sequ", vo);
	}
	
	
	/** My후기 - 스토어 리뷰 **/
	public ArrayList<StorevVO> getStoreReview(String id) {
		List<StorevVO> list = sqlSession.selectList(namespace + ".store_review", id);
		return (ArrayList<StorevVO>) list;
	}
	
	/** My후기 - 여행지 리뷰 **/
	public ArrayList<TravelReviewVO> getTravelReview(String id) {
		List<TravelReviewVO> list = sqlSession.selectList(namespace + ".travel_review", id);
		return (ArrayList<TravelReviewVO>) list;
	}
	
	/** My후기 - 음식점 리뷰 **/
	public ArrayList<FoodReviewVO> getFoodReview(String id) { 
		List<FoodReviewVO> list = sqlSession.selectList(namespace + ".food_review", id); 
		return (ArrayList<FoodReviewVO>) list; 
	}
	
	/** My후기 - 카페 리뷰 **/
	public ArrayList<CafeReviewVO> getCafeReview(String id) { 
		List<CafeReviewVO> list = sqlSession.selectList(namespace + ".cafe_review", id); 
		return (ArrayList<CafeReviewVO>) list; 
	}
	
	/** My후기 - 숙소 리뷰 **/
	public ArrayList<HouseReviewVO> getHouseReview(String id) { 
		List<HouseReviewVO> list = sqlSession.selectList(namespace + ".house_review", id); 
		return (ArrayList<HouseReviewVO>) list; 
	}
	
	/** My후기 - 스토어 - 나의 리뷰 삭제 **/
	public int getMyreviewStoreDelete(String reid) {
		return sqlSession.delete(namespace + ".myreview_store_delete", reid);
	}
	
	/** My후기 - 여행지 - 나의 리뷰 삭제 **/
	public int getMyreviewTravelDelete(String reid) {
		return sqlSession.delete(namespace + ".myreview_travel_delete", reid);
	}
	
	/** My후기 - 음식점 - 나의 리뷰 삭제 **/
	public int getMyreviewFoodDelete(String reid) {
		return sqlSession.delete(namespace + ".myreview_food_delete", reid);
	}
	
	/** My후기 - 카페 - 나의 리뷰 삭제 **/
	public int getMyreviewCafeDelete(String reid) {
		return sqlSession.delete(namespace + ".myreview_cafe_delete", reid);
	}
	
	/** My후기 - 숙소 - 나의 리뷰 삭제 **/
	public int getMyreviewHouseDelete(String reid) {
		return sqlSession.delete(namespace + ".myreview_house_delete", reid);
	}
	
	/**
	 * 예약내역가져오기
	 */
	public OrderVO getOrderContent(String rid) {
		return sqlSession.selectOne(namespace+".order_content", rid);
	}
	
	/**
	 * 날짜사이에 날짜구하기
	 */
	public ArrayList<OrderVO> getDayResult(OrderVO vo) {
		List<OrderVO> list = sqlSession.selectList(namespace+".day_result", vo);
		return (ArrayList<OrderVO>) list;
	}
	
	/**
	 * reservation 0만들기
	 */
	public int getReservationResult(OrderVO vo) {
		return sqlSession.update(namespace+".reservation_result", vo);
	}
	
	/**
	 * 예약내역삭제
	 */
	public int getOrderDelete(String rid) {
		return sqlSession.delete(namespace+".order_delete", rid);
	}
	
	/** 좋아요 내역 - house **/
	public ArrayList<HeartVO> getHouseHeartList(String id) {
		List<HeartVO> list = sqlSession.selectList(namespace+".house_heart", id);
		return (ArrayList<HeartVO>) list;
	}
	
	/** 좋아요 내역 - food **/
	public ArrayList<HeartVO> getFoodHeartList(String id) {
		List<HeartVO> list = sqlSession.selectList(namespace+".food_heart", id);
		return (ArrayList<HeartVO>) list;
	}
	
	/** 좋아요 내역 - cafe **/
	public ArrayList<HeartVO> getCafeHeartList(String id) {
		List<HeartVO> list = sqlSession.selectList(namespace+".cafe_heart", id);
		return (ArrayList<HeartVO>) list;
	}
	
	/** 좋아요 내역 - travel **/
	public ArrayList<HeartVO> getTravelHeartList(String id) {
		List<HeartVO> list = sqlSession.selectList(namespace+".travel_heart", id);
		return (ArrayList<HeartVO>) list;
	}
	
	
}
