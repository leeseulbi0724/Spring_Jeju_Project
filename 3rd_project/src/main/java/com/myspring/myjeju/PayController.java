package com.myspring.myjeju;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myjeju.service.MypageService;
import com.myjeju.service.PayService;
import com.myjeju.service.StoreService;
import com.myjeju.vo.BasketVO;
import com.myjeju.vo.MemberVO;
import com.myjeju.vo.StoreVO;

@Controller
public class PayController {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private MypageService mypageService;
	
	
	/**
	 * 직접 카드결제
	 */
	@RequestMapping(value = "/payment.do", method=RequestMethod.GET)
	public ModelAndView payment(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();		
		 String id = (String) session.getAttribute("session_id");
		 
		 mv.addObject("id", id);
		 mv.addObject("total", request.getParameter("total"));
		 mv.addObject("list", request.getParameter("list"));
		 mv.addObject("option", request.getParameter("option"));
		 mv.addObject("c", request.getParameter("c"));
		 mv.addObject("point", request.getParameter("point"));
		 mv.addObject("dis", request.getParameter("dis"));
		 mv.setViewName("pay/payment");
		 
		return mv;
		
	}
	
	/**
	 * 카드결제 proc
	 */
	@ResponseBody
	@RequestMapping(value = "/payment_proc.do", method=RequestMethod.POST)
		public boolean payment_proc(HttpServletRequest request, HttpSession session) {
			boolean result = false;
		
			String id = (String) session.getAttribute("session_id");
			String list = request.getParameter("list");
			String total = request.getParameter("total");
			String count = request.getParameter("count");
			String point = request.getParameter("point");
			String dis = request.getParameter("dis");
			
			System.out.print(total+dis+point);
			
			String sids[] = null;
			ArrayList<List<BasketVO>> basket_list = null;
			 BasketVO vo = new BasketVO();
			System.out.print(count);
			
			 if ( list.contains(",")) {
				 //다중 결제 시 list에 ","를 통해 상품들이 구분되어있음
				 //","가 들어있으면 다중결제 로직
				 sids = list.split(",");			 
				 basket_list = storeService.getBuyContent(sids, id);		 
				 
				 vo.setS_price(Integer.parseInt(total));
				 vo.setId(id);
				 vo.setS_name("");
				 vo.setS_image(basket_list.get(0).get(0).getS_image());
				 vo.setS_sfile(basket_list.get(0).get(0).getS_sfile());
				 for(int i = 0; i <basket_list.size(); i++){ 
					 vo.setS_name(vo.getS_name() + basket_list.get(i).get(0).getS_name() + " ");
					 vo.setB_count(vo.getB_count() + basket_list.get(i).get(0).getB_count());
				 }		 
				 System.out.print(vo.getS_name()); 
				 System.out.print(vo.getB_count());			 
				
			 } else {	
				 //","가 들어있지 않으면 단일결제 로직
				 ArrayList<BasketVO> basket_one_list = new ArrayList<BasketVO>();
				 if (request.getParameter("option").equals("mypage")) {
					 //option 파라미터를 통해 장바구니 -> 결제 인 것을 확인!
					 //장바구니에서 주문 시 해당 상품을 삭제하기 위한 용도
					 basket_one_list = storeService.getBuyContent(list, id);					 
					 vo.setId(id);
					 vo.setS_name(basket_one_list.get(0).getS_name());
					 vo.setS_price(Integer.parseInt(total));
					 vo.setB_count(basket_one_list.get(0).getB_count());
					 vo.setS_sfile(basket_one_list.get(0).getS_sfile());
					 vo.setS_image(basket_one_list.get(0).getS_image());
				 } 
				 
				 
				 else {
					 //option 파라미터를 통해 상품페이지 -> 바로결제 인 것을 확인!
					 StoreVO svo = storeService.getContent(list);
					 vo.setId(id);
					 vo.setS_name(svo.getS_name());
					 vo.setB_count(Integer.parseInt(count));
					 if (dis == "") {
						 vo.setS_price(svo.getS_price()*vo.getB_count());						 
					 } else {
						 vo.setS_price(svo.getS_price()*vo.getB_count()-Integer.parseInt(dis));
					 }
					 vo.setS_sfile(svo.getS_sfile());
					 vo.setS_image(svo.getS_image());
				 }
			 } 			
			 
			 
			 result = payService.getOrderResult(vo);
			 //주문 시퀀스 테이블 저장
			 if (result) {
				 if ( list.contains(",")) {
					 BasketVO bvo = new BasketVO();
					 sids = list.split(",");
					 bvo.setId(id);	
					 for (int i=0; i<sids.length; i++) {
						bvo.setSid(sids[i]);
						mypageService.getOrderSequ(bvo);
					 }
				 } else {
					 BasketVO bvo = new BasketVO();
					 bvo.setId(id);	bvo.setSid(list);
					 mypageService.getOrderSequ(bvo);
				 }
			 }
			 
			 //포인트 적립
			 MemberVO mvo = new MemberVO();
			 mvo.setId(id);
			 mvo.setPoint(Integer.parseInt(point));
			 boolean point_result = payService.getPointInsert(mvo);
			 if (point_result) {
				 mvo.setType("plus");
				 boolean point_plus = payService.getPointPlus(mvo);
			 }
			 
			 //사용 포인트삭제
			 if (!dis.equals("")) {
				 mvo.setPoint(Integer.parseInt(dis));
				 boolean point_delete = payService.getPointDelete(mvo);
				 if (point_delete) {
					 mvo.setType("minus");
					 boolean point_minus = payService.getPointMinus(mvo);
				 }
			 }
			 
			 //장바구니 삭제
			 if (result) {
				 if (request.getParameter("option").equals("mypage")) {
					 //옵션을 통해 mypage일 경우 -> (장바구니->결제) 경로이므로 장바구니에서 상품 삭제
					 if ( list.contains(",")) {
						 sids = list.split(",");	
						 for (int i = 0; i<sids.length; i++) {
							 mypageService.getBasketDelete(sids[i]);
						 }
					 } else {
						  mypageService.getBasketDelete(list);						 
					 }
				 }
			 }
			 
			 return result;
	
	}
	
	/**
	 * 카카오페이 결제
	 */
	@RequestMapping(value="/kakaopay.do", method=RequestMethod.GET)
	public ModelAndView kakaopay(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();		
		String id = (String) session.getAttribute("session_id");
		
		 mv.addObject("id", id);
		 mv.addObject("total", request.getParameter("total"));
		 mv.addObject("list", request.getParameter("list"));
		 mv.addObject("option", request.getParameter("option"));
		 mv.addObject("c", request.getParameter("c"));
		 mv.addObject("point", request.getParameter("point"));
		 mv.addObject("dis", request.getParameter("dis"));
		mv.setViewName("pay/kakaopay");
		 
		return mv;
	
	}

}
