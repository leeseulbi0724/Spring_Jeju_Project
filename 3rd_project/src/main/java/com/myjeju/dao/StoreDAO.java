package com.myjeju.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.BasketVO;
import com.myjeju.vo.StoreVO;

@Repository
public class StoreDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private static String namespace = "mapper.store";
	
	//SELECT.BEST --> BEST MENU (베스트 메뉴 상위 세개) [메인화면]
	public ArrayList<StoreVO> getBestList() {
		List<StoreVO> list =  sqlSession.selectList(namespace + ".best3list");
		return (ArrayList<StoreVO>) list;
	}
	
	//SELECT.EAT --> 카테고리-식품 (상위 세개) [메인화면]
	public ArrayList<StoreVO> getEatList() {
		List<StoreVO> list = sqlSession.selectList(namespace + ".eat3list");
		return (ArrayList<StoreVO>) list;
	}
	
	//SELECT.EAT --> 카테고리-식품 [식품 상세화면]
	public ArrayList<StoreVO> getEatList2() {
		List<StoreVO> list = sqlSession.selectList(namespace + ".eatlist");
		return (ArrayList<StoreVO>) list;
	}
	
	//SELECT.SOUVE --> 카테고리-기념품 (상위 세개) [메인화면]
	public ArrayList<StoreVO> getSouveList() {
		List<StoreVO> list = sqlSession.selectList(namespace + ".souve3list");
		return (ArrayList<StoreVO>) list;
	}
	
	//SELECT.SOUVE --> 카테고리-기념품 [기념품 상세화면]
	public ArrayList<StoreVO> getSouveList2() {
		List<StoreVO> list = sqlSession.selectList(namespace + ".souvelist");
		return (ArrayList<StoreVO>) list;
	}
		
	//SELECT.ETC --> 카테고리-잡화 (상위 세개) [메인화면]
	public ArrayList<StoreVO> getEtcList() {
		List<StoreVO> list = sqlSession.selectList(namespace + ".etc3list");
		return (ArrayList<StoreVO>) list;
	}
	
	//SELECT.ETC --> 카테고리-잡화 [잡화 상세화면]
	public ArrayList<StoreVO> getEtcList2() {
		List<StoreVO> list = sqlSession.selectList(namespace + ".etclist");
		return (ArrayList<StoreVO>) list;
	}
			
	//SELECT --> 스토어 상품 상세 화면
	public StoreVO getContent(String sid) {
		return sqlSession.selectOne(namespace + ".content", sid);
	}
	
	//주문 여부확인
	public int getOrderResult(BasketVO vo) {
		return sqlSession.selectOne(namespace+".order_result", vo);
	}
		
	
}
