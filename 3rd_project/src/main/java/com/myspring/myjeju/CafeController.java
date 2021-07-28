package com.myspring.myjeju;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.myjeju.service.CafeService;
import com.myjeju.vo.CafeReviewVO;
import com.myjeju.vo.CafeVO;
import com.myjeju.vo.HeartVO;

@Controller
public class CafeController {
	
	@Autowired
	private CafeService cafeService;
	
	/**
	 * cafe.do : 카페 메인페이지
	 */
	@RequestMapping(value="/cafe.do", method=RequestMethod.GET)
	public ModelAndView cafe(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");	
		
		
		ArrayList<CafeVO> list = cafeService.getCafeList(1,5);
		for (int i=0; i<list.size(); i++) {
			String img[] = list.get(i).getCa_sfile().split(",");
			list.get(i).setCa_sfile(img[0]);
		}
		ArrayList<CafeVO> toplist = cafeService.getCafeListTop3();
		for (int i=0; i<toplist.size(); i++) {
			String img[] = toplist.get(i).getCa_sfile().split(",");
			toplist.get(i).setCa_sfile(img[0]);
		}
		
		if (id != null) {
			for (int i=0; i<toplist.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setCaid(toplist.get(i).getCaid());
				int status = cafeService.getHeartInfoResult(vo);
				toplist.get(i).setStatus(status);				 
			}
			for (int i=0; i<list.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setCaid(list.get(i).getCaid());
				int status = cafeService.getHeartInfoResult(vo);
				list.get(i).setStatus(status);				 
			}
		} else {
			for (int i=0; i<toplist.size(); i++) {
				toplist.get(i).setStatus(0);
			}
			for (int i=0; i<list.size(); i++) {
				list.get(i).setStatus(0);
			}
		}
		
		mv.setViewName("food/cafe");
		mv.addObject("list",list);
		mv.addObject("toplist",toplist);
		
		return mv;
	}
	
	/**
	 * 카페 리스트 ajax 처리
	 */
	@ResponseBody
	@RequestMapping(value="/cafe_proc.do", produces = "application/text; charset=utf8", method=RequestMethod.GET)
	public String cafe_proc(String pnum, String search, String search_text, HttpSession session) {
		ArrayList<CafeVO> list = cafeService.getCafeList();
		
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");	
		int pageNumber = 1;

		if(!pnum.equals("")) { 
			pageNumber = Integer.parseInt(pnum) +1;
		}
		
		int startnum = ((pageNumber-1)*5) +1;
		int endnum = pageNumber*5; 

		if(search_text == null || search_text.equals("") || search_text.equals("null")) {
			list = cafeService.getCafeList(startnum, endnum);
		}else {
			list = cafeService.getCafeList(startnum, endnum, search, search_text);
		}
		
		if (id != null) {
			for (int i=0; i<list.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setCaid(list.get(i).getCaid());
				int status = cafeService.getHeartInfoResult(vo);
				list.get(i).setStatus(status);				 
			}
			for (int i=0; i<list.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setCaid(list.get(i).getCaid());
				int status = cafeService.getHeartInfoResult(vo);
				list.get(i).setStatus(status);				 
			}
		} 		
		

		JsonObject jdata = new JsonObject();
		JsonArray jlist = new JsonArray();
		Gson gson = new Gson();
		
		for(CafeVO vo : list) {
			String img[] = vo.getCa_sfile().split(",");
			JsonObject jobj = new JsonObject();
			jobj.addProperty("caid", vo.getCaid());
			jobj.addProperty("ca_name", vo.getCa_name());
			jobj.addProperty("ca_tag", vo.getCa_tag());
			jobj.addProperty("ca_infor", vo.getCa_infor());
			jobj.addProperty("ca_addr", vo.getCa_addr());
			jobj.addProperty("ca_like", vo.getCa_like());
			jobj.addProperty("status", vo.getStatus());
			jobj.addProperty("ca_image", img[0]);
			jobj.addProperty("star_avg", vo.getStar_avg()); 
			jobj.addProperty("review_count", vo.getReview_count());
			jobj.addProperty("pnum", String.valueOf(pageNumber));
			jobj.addProperty("search", search);
			jobj.addProperty("search_text", search_text);
			
			jlist.add(jobj);
		}
		
		jdata.add("jlist", jlist);

		return gson.toJson(jdata);
	}
	
	/**
	 * 카페 리스트 추가분 ajax 처리
	 */
	@ResponseBody
	@RequestMapping(value="/cafe_proc_add.do", produces = "application/text; charset=utf8", method=RequestMethod.GET)
	public String cafe_proc(String pnum) {
		
		int pageNumber = 1;
		
		if(!pnum.equals("")) { 
			pageNumber = Integer.parseInt(pnum) +1;
		}
		System.out.println(pageNumber);
		int startnum = ((pageNumber-1)*5) +1;
		int endnum = pageNumber*5; 
		System.out.println(startnum);
		System.out.println(endnum);
		
		
		ArrayList<CafeVO> list = cafeService.getCafeList(startnum, endnum);
		
		JsonObject jdata = new JsonObject();
		JsonArray jlist = new JsonArray();
		Gson gson = new Gson();
		
		for(CafeVO vo : list) {
			String img[] = vo.getCa_sfile().split(",");
			JsonObject jobj = new JsonObject();
			jobj.addProperty("caid", vo.getCaid());
			jobj.addProperty("ca_name", vo.getCa_name());
			jobj.addProperty("ca_addr", vo.getCa_addr());
			jobj.addProperty("ca_hp", vo.getCa_hp());
			jobj.addProperty("ca_image", img[0]);
			jobj.addProperty("ca_vpoint", vo.getCa_vpoint());
			jobj.addProperty("ca_hpoint", vo.getCa_hpoint());
			
			jlist.add(jobj);
		}
		
		jdata.add("jlist", jlist);

		return gson.toJson(jdata);
	}
	
	
	/**
	 * cafe_detail.do : 카페 상세페이지
	 */
	@RequestMapping(value="/cafe_detail.do", method=RequestMethod.GET)
	public ModelAndView cafe_detail(String caid, HttpSession session) {
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");	
				
		ModelAndView mv = new ModelAndView();
		
		int pnum = 1;
		
		CafeVO vo = cafeService.getCafeDetail(caid);
		if (id!= null) {
			HeartVO hvo = new HeartVO();
			hvo.setId(id);  hvo.setCaid(caid);
			int status = cafeService.getHeartInfoResult(hvo);		
			vo.setStatus(status);
		} else {
			vo.setStatus(0);
		}
		
		String infor2 = vo.getCa_infor2().replace("\r\n", "<br>");
		String user_id = (String) session.getAttribute("session_id");
		String img[] = vo.getCa_sfile().split(",");
		
		ArrayList<CafeReviewVO> cavo = cafeService.getCafeReview(caid);
		
		mv.setViewName("food/cafe_detail");
		mv.addObject("pnum",pnum);
		mv.addObject("vo",vo);
		mv.addObject("infor2",infor2);
		mv.addObject("cavo", cavo);
		mv.addObject("img", img);
		mv.addObject("user_id", user_id);
		mv.addObject("caid", caid);
		
		return mv;
	}
	
	/**
	 * cafe_review_proc.do : 카페 리뷰 등록 처리
	 */
	@RequestMapping(value="/cafe_review_proc.do", method=RequestMethod.POST)
	public ModelAndView cafe_review_proc(CafeReviewVO vo, HttpSession session, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		
		String user_id = (String) session.getAttribute("session_id");
		String caid = request.getParameter("caid");
		String ca_review = request.getParameter("ca_review");
		String star = request.getParameter("ca_star");
		
		if(star == null) {
			star = "0";
		}
		
		int ca_star = Integer.parseInt(star);
		
		vo.setId(user_id);
		vo.setCaid(caid);
		vo.setCa_review(ca_review);
		vo.setCa_star(ca_star);

		boolean result = cafeService.getInsertResult(vo);
		boolean star_avg = cafeService.getStarAvgUpdate(caid);
		boolean review_count = cafeService.getReviewCountUpdate(caid);
		
		if(result) {
			mv.setViewName("redirect:/cafe_detail.do");
			mv.addObject("caid", vo.getCaid());
			mv.addObject("star_avg",star_avg);
			mv.addObject("review_count",review_count);
		}
		return mv;
	}
	
	
	/**
	 * 카페 리뷰 리스트 ajax 처리
	 */
	
	@ResponseBody
	@RequestMapping(value="/cafe_review_list_proc.do", produces = "application/text; charset=utf8", method=RequestMethod.POST)
	public String cafe_review_list_proc(String pnum, String caid, HttpSession session) {
		
		ArrayList<CafeReviewVO> list = cafeService.getCafeReview(caid);
		String user_id = (String) session.getAttribute("session_id");
		int pageNumber = 1;

		if(!pnum.equals("")) { 
			pageNumber = Integer.parseInt(pnum) +1;
		}
		int startnum = ((pageNumber-1)*5) +1;
		int endnum = pageNumber*5; 
		list = cafeService.getCafeReview(caid, startnum, endnum);
		
		JsonObject jdata = new JsonObject();
		JsonArray jlist = new JsonArray();
		Gson gson = new Gson();
		
		for(CafeReviewVO vo : list) {
			JsonObject jobj = new JsonObject();
			jobj.addProperty("reid", vo.getReid());
			jobj.addProperty("caid", vo.getCaid());
			jobj.addProperty("id", vo.getId());
			jobj.addProperty("ca_review", vo.getCa_review());
			jobj.addProperty("ca_star", vo.getCa_star());
			jobj.addProperty("ca_time", vo.getCa_time());
			jobj.addProperty("pnum", String.valueOf(pageNumber));
			jobj.addProperty("user_id", user_id);
			
			jlist.add(jobj);
		}
		
		jdata.add("jlist", jlist);
		
		return gson.toJson(jdata);
	}

	
	/**
	 * cafe_review_delete.do : 카페 리뷰 삭제
	 */
	@ResponseBody
	@RequestMapping(value = "/cafe_review_delete.do", method=RequestMethod.POST)
	public boolean cafe_review_delete(HttpServletRequest request) {
		boolean result = cafeService.getCafeReviewDelete(request.getParameter("reid"));
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 좋아요 +
	 */
	@ResponseBody
	@RequestMapping(value = "/heart_cafe_plus.do", method=RequestMethod.POST)
	public boolean heart_plus(HttpSession session, HttpServletRequest request) {
		boolean total = false;
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");
		String caid = request.getParameter("caid");
		HeartVO vo = new HeartVO();
		vo.setId(id);
		vo.setCaid(caid);		
		//heart 테이블 추가
		total = cafeService.getHeartPlus(vo);
		//hous테이블 하트+
		if (total) {
			boolean result = cafeService.getUpdateHeart(caid);
		}
		return total;
	}

	/**
	 * 좋아요 -
	 */
	@ResponseBody
	@RequestMapping(value = "/heart_cafe_minus.do", method=RequestMethod.POST)
	public boolean heart_minus(HttpSession session, HttpServletRequest request) {
		boolean total = false;
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");
		String caid = request.getParameter("caid");
		HeartVO vo = new HeartVO();
		vo.setId(id);
		vo.setCaid(caid);		
		//heart 테이블 삭제 
		total = cafeService.getHeartMinus(vo);
		//hous테이블 하트-
		if (total) {
			boolean result = cafeService.getUpdateMinusHeart(caid);
		}

		return total;
	}
}
