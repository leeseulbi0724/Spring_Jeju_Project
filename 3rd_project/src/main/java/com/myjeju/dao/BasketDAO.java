package com.myjeju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.BasketVO;
import com.myjeju.vo.StoreVO;

@Repository
public class BasketDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.mypage";
	
	// 상품 장바구니에 추가
	public boolean getInsertResult(BasketVO vo) {
		boolean result = false;
		int value = sqlSession.insert(namespace + ".basket", vo);
		if(value != 0) result = true;
		return result;
	}
	
	// id 비교해서 상품 정보 불러오기
	public ArrayList<BasketVO> getBasketContent(String id) {
		List<BasketVO> list = sqlSession.selectList(namespace + ".getsid" , id);
		return (ArrayList<BasketVO>) list;
	}
	
	public BasketVO getSid2(String id) {
		return sqlSession.selectOne(namespace + ".getVOsid" , id);
	}
	
	//상품이 이미 등록이 되어있는 없는지 확인하기
	public int getAlready(BasketVO vo) {
		return sqlSession.selectOne(namespace+".already", vo);
	}
	
	//상품이 있으면 -> 수량 업데이트
	public int getAlreadyCount(BasketVO vo) {
		return sqlSession.update(namespace+".already_count", vo);
	}
	
	//장바구니 삭제
	public int getBasketDelete(String sid) {
		return sqlSession.delete(namespace+".basket_delete", sid);
	}
	
	//장바구니 컬럼수
	public int getColumn(String id) {
		return sqlSession.selectOne(namespace+".column", id);
	}
	
	//주문내역에 필요한 내용 -- 여러개
	public ArrayList<List<BasketVO>> getBuyContent(String[] list, String id) {
		/* HashMap map1= new HashMap<String, List<BasketVO>>(); */
		ArrayList<List<BasketVO>> result_list = new ArrayList<List<BasketVO>>();
		
		for (int i=0; i<list.length; i++) {
			Map<String, String> param = new HashMap<String, String>();
			param.put("sid",list[i]);
			param.put("id", id);
			
			List<BasketVO> value_list = sqlSession.selectList(namespace+".buy_content",param);	
			result_list.add(value_list);
		}
		return result_list;
	}
	
	//주문내역에 필요한 내용 -- 한개
	public ArrayList<BasketVO> getBuyContent(String sid, String id) {	
		
			Map<String, String> param = new HashMap<String, String>();
			param.put("sid",sid);
			param.put("id", id);
			
			List<BasketVO> list = sqlSession.selectList(namespace+".buy_content",param);	

		return (ArrayList<BasketVO>)list;
	}

}

