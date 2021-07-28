package com.myspring.myjeju;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myjeju.service.MypageService;
import com.myjeju.service.StofaqService;
import com.myjeju.service.StoreService;
import com.myjeju.vo.BasketVO;
import com.myjeju.vo.MemberVO;
import com.myjeju.vo.StofaqVO;
import com.myjeju.vo.StoreVO;
import com.myjeju.vo.StorevVO;

@Controller

public class StoreController {
	
	@Autowired
	private StoreService storeService;
	@Autowired	
	private StofaqService stofaqService;	
	@Autowired
	private MypageService mypageService;	

	
	/**
	 * store.do : 스토어 메인 상품 출력
	 */
	@RequestMapping(value="/store.do", method=RequestMethod.GET)
	public ModelAndView store() {
		ModelAndView mv = new ModelAndView();
		
		//StoreDAO dao = new StoreDAO();
		ArrayList<StoreVO> bestlist = storeService.getBestList();
		ArrayList<StoreVO> eatlist = storeService.getEatList();
		ArrayList<StoreVO> souvelist = storeService.getSouveList();
		ArrayList<StoreVO> etclist = storeService.getEtcList();
		
		// 상품평 
		
		
		mv.setViewName("store/store");
		
		mv.addObject("bestlist", bestlist);
		mv.addObject("eatlist", eatlist);
		mv.addObject("souvelist", souvelist);
		mv.addObject("etclist", etclist);
		
		
		return mv;
	}
	
	/**
	 * store_eat.do : 스토어 식품 화면
	 */
	@RequestMapping(value = "/store_eat.do", method = RequestMethod.GET)
	public ModelAndView store_eat() {
		ModelAndView mv = new ModelAndView();
		//StoreDAO dao = new StoreDAO();
		ArrayList<StoreVO> list = storeService.getEatList2();
		
		mv.setViewName("store/storeeat");
		mv.addObject("eatlist", list);
		
		return mv;
	}
	
	/**
	 * store_souve.do : 스토어 기념품 화면
	 */
	@RequestMapping(value = "/store_souve.do", method = RequestMethod.GET)
	public ModelAndView store_souve() {
		ModelAndView mv = new ModelAndView();
		//StoreDAO dao = new StoreDAO();
		ArrayList<StoreVO> list = storeService.getSouveList2();
		
		mv.setViewName("store/storesouve");
		mv.addObject("souvelist", list);
		
		return mv;
	}
	
	/**
	 * store_etc.do : 스토어 잡화 화면
	 */
	@RequestMapping(value = "/store_etc.do", method = RequestMethod.GET)
	public ModelAndView store_etc() {
		ModelAndView mv = new ModelAndView();
		//StoreDAO dao = new StoreDAO();
		ArrayList<StoreVO> list = storeService.getEtcList2();
		
		mv.setViewName("store/storeetc");
		mv.addObject("etclist", list);
		
		return mv;
	}
	
	
	/**
	 * store_content.do : 스토어 상품 상세화면
	 */
	@RequestMapping(value = "/store_content.do", method = RequestMethod.GET)
	public ModelAndView store_content(String sid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		String user_id = (String) session.getAttribute("session_id");
		
		//상품 상세정보 가져오기
		StoreVO vo = storeService.getContent(sid);
		
		//문의/답변 리스트
		ArrayList<StofaqVO> flist = stofaqService.getList(sid);
		
		//상품평 리스트
		ArrayList<StorevVO> rlist = storeService.getStoreReview(sid);
		boolean result = false;
		//회원 주문여부확인
		if (user_id!= null) {
			BasketVO bvo = new BasketVO();
			bvo.setId(user_id);
			bvo.setSid(sid);
			result = storeService.getOrderResult(bvo);
		}
		
		mv.setViewName("store/store_content");
		
		mv.addObject("vo", vo);
		mv.addObject("flist", flist);
		mv.addObject("rlist", rlist);
		mv.addObject("result", result);
		
		mv.addObject("sid", sid);			
		mv.addObject("id", user_id);

		
		return mv;
	}
	

	/**
	 * store_buy.do : 스토어 구매 화면 -- 장바구니에서
	 */
	@RequestMapping(value = "/store_buy.do", method = RequestMethod.GET)
	public ModelAndView store_buy(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		 String id = (String) session.getAttribute("session_id");
		 String type = request.getParameter("type");
		 
		 ArrayList<List<BasketVO>> basket_list = null;

			 String list = request.getParameter("list");
			 String sids[] = null;
			 
			 if ( list.contains(",")) {
				 sids = list.split(",");			 
				 basket_list = storeService.getBuyContent(sids, id);		
				 for(int i = 0; i <basket_list.size(); i++){ 
					 System.out.print(basket_list.get(i).get(0).getS_name()); 
					 mv.addObject("type", "many");
					 mv.addObject("option", "mypage");
					 mv.addObject("list", basket_list);					
					 mv.addObject("size", basket_list.size());
				 }		 
			 } else {
				 ArrayList<BasketVO> basket_one_list = storeService.getBuyContent(list, id);
				 System.out.print(basket_one_list.get(0).getS_name());
				 mv.addObject("type", "one");
				 mv.addObject("list", basket_one_list);
				 mv.addObject("option", "mypage");
				 mv.addObject("size", basket_one_list.size());
			 }
		 
		 
		MemberVO vo = mypageService.getMemberContent(id);	
		
		mv.addObject("vo", vo);
		mv.setViewName("store/storebuy");
		
		return mv;
	}
	
	/**
	 * store_buy.do : 스토어 구매 화면 -- 상품페이지에서
	 */
	@RequestMapping(value = "/store_buy2.do", method = RequestMethod.POST)
	public ModelAndView store_buy2(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		 String id = (String) session.getAttribute("session_id");
		
		 ArrayList<BasketVO> basket_one_list = new ArrayList<BasketVO>();
		 BasketVO bvo = new BasketVO();
		 bvo.setB_count(Integer.parseInt(request.getParameter("b_count")));
		 bvo.setS_image(request.getParameter("s_image"));
		 bvo.setS_name(request.getParameter("s_name"));
		 bvo.setS_price(Integer.parseInt(request.getParameter("s_price")));
		 bvo.setS_sfile(request.getParameter("s_sfile"));
		 bvo.setSid(request.getParameter("sid"));		 
		 basket_one_list.add(bvo);
		 
		 mv.addObject("size", 1);
		 mv.addObject("type", "one");
		 mv.addObject("option", "store");
		 mv.addObject("list", basket_one_list);

		 
		MemberVO vo = mypageService.getMemberContent(id);		
		mv.addObject("vo", vo);
		mv.setViewName("store/storebuy");
		
		return mv;
	}
	
	
	/**
	 * store_review_proc1.do : 스토어 상품평 등록 처리 (ajax)
	 */
	@ResponseBody
	@RequestMapping(value = "/store_review_proc1.do", method = RequestMethod.POST)
	public boolean store_review_proc1(HttpSession session, HttpServletRequest request) {

		String id = (String) session.getAttribute("session_id");
		String sid = request.getParameter("sid");
		String sr_review = request.getParameter("sr_review");
		String star = request.getParameter("sr_star");
		if(star == null) {
			star = "0";
		}
		int sr_star = Integer.parseInt(star);
		
		StorevVO vo = new StorevVO();

		vo.setId(id);
		vo.setSid(sid);
		vo.setSr_review(sr_review);
		vo.setSr_star(sr_star);
		
		boolean result = storeService.getInsertResult(vo);
		if(result) {
			// 별점 -> myjeju_store.table에 update
			boolean star2 = storeService.getReviewAvgUpdate(sid);
		}
	
		return result;
	}
	
	
	
	
	/**
	 * review_delete.do : 상품평 삭제
	 */
	@ResponseBody
	@RequestMapping(value = "/review_delete.do", method=RequestMethod.POST)
	public boolean review_delete(HttpServletRequest request) {
		boolean result = storeService.getReviewDelete(request.getParameter("srid"));
		
		return result;
	}

	
	/**
	 * review_update.do : 상품평 수정
	 */
	@RequestMapping(value = "/review_update.do", method=RequestMethod.GET)
	public ModelAndView review_update(String srid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		StorevVO rvo = storeService.getStoreReviewOne(srid);
		String review = rvo.getSr_review();
		
		mv.setViewName("redirect:/store_content");
		mv.addObject("rvo", rvo);
		mv.addObject("real_review", review);
		
		return mv;
	}
	
	/**
	 * reviewUpdate_proc.do : 상품평 수정 처리
	 */
	@RequestMapping(value = "/reviewUpdate_proc.do", method = RequestMethod.GET)
	public ModelAndView reviewUpdate_proc(StorevVO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String srid = request.getParameter("srid2");
		int sr_star = (Integer.parseInt(request.getParameter("sr_star2")));
		String sr_review = request.getParameter("sr_review2");
		
		vo.setSrid(srid);
		vo.setSr_star(sr_star);
		vo.setSr_review(sr_review);
		
		boolean result = storeService.getReviewUpdate(vo);
		
		if(result) {
			mv.setViewName("redirect:/store_content.do");
			mv.addObject("sid", vo.getSid());
		}
		return mv;
	}
	
	
	/**
	 * store_faq_proc.do : 스토어 문의 -> 문의 처리
	 */
	@RequestMapping(value = "/store_faq_proc.do", method = RequestMethod.GET)
	public ModelAndView store_faq_proc(StofaqVO vo) {
		ModelAndView mv = new ModelAndView();
		boolean result = stofaqService.getInsertResult(vo);
		if(result) {
			mv.setViewName("redirect:/store_content.do");
			mv.addObject("sid", vo.getSid());
		}
		return mv;
	}
	
	
	/**
	 * store_faq_reply_proc.do : 스토어 문의 -> 답변 처리
	 */
	@RequestMapping(value = "/store_faq_reply_proc.do", method = RequestMethod.GET)
	public ModelAndView store_faq_reply_proc(StofaqVO vo) {
		ModelAndView mv = new ModelAndView();
		boolean result = stofaqService.getReplyResult(vo);
		
		if(result) {
			mv.setViewName("redirect:/store_content.do");
			mv.addObject("sid", vo.getSid());
		}
		
		return mv;
	}
	
	/**
	 * faq_delete.do : 스토어 상품문의 삭제
	 */
	@ResponseBody
	@RequestMapping(value = "/faq_delete.do", method=RequestMethod.POST)
	public boolean faq_delete(String st_id, HttpServletRequest request) {
		boolean result = stofaqService.getFaqDelete(st_id);
		
		return result;
	}
	
}
