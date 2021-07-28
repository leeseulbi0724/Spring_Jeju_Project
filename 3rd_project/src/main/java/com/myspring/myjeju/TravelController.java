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
import com.myjeju.service.TravelService;
import com.myjeju.vo.CarSpotVO;
import com.myjeju.vo.HeartVO;
import com.myjeju.vo.PhotoSpotVO;
import com.myjeju.vo.TravelReviewVO;
import com.myjeju.vo.TravelVO;

@Controller
public class TravelController {
	
	@Autowired
	private TravelService travelService;
	
	/**
	 * travel.do : 여행지 메인페이지
	 */
	@RequestMapping(value="/travel.do", method=RequestMethod.GET)
	public ModelAndView travel(HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");
		
		ArrayList<TravelVO> list = travelService.getTravelList(1, 5);
		for (int i=0; i<list.size(); i++) {
			String img[] = list.get(i).getT_sfile().split(",");
			list.get(i).setT_sfile(img[0]);
		}
		
		ArrayList<TravelVO> toplist = travelService.getTravelListTop3();
		for (int i=0; i<toplist.size(); i++) {
			String img[] = toplist.get(i).getT_sfile().split(",");
			toplist.get(i).setT_sfile(img[0]);
		}
		
		if (id != null) {
			for (int i=0; i<toplist.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setTid(toplist.get(i).getTid());
				int status = travelService.getHeartInfoResult(vo);
				toplist.get(i).setStatus(status);				 
			}
			for (int i=0; i<list.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setTid(list.get(i).getTid());
				int status = travelService.getHeartInfoResult(vo);
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
		
		
		
		mv.setViewName("travel/travel");
		mv.addObject("list",list);
		mv.addObject("toplist",toplist);
		
		return mv;
	}
	
	/**
	 * 여행지 리스트 ajax 처리
	 */
	@ResponseBody
	@RequestMapping(value="/travel_proc.do", produces = "application/text; charset=utf8", method=RequestMethod.GET)
	public String travel_proc(String pnum, String search, String search_text, HttpSession session) {
		
		ArrayList<TravelVO> list = travelService.getTravelList();

		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");
		int pageNumber = 1;

		if(!pnum.equals("")) { 
			pageNumber = Integer.parseInt(pnum) +1;
		}
		
		int startnum = ((pageNumber-1)*5) +1;
		int endnum = pageNumber*5; 

		if(search_text == null || search_text.equals("") || search_text.equals("null")) {
			list = travelService.getTravelList(startnum, endnum);
		}else {
			list = travelService.getTravelList(startnum, endnum, search, search_text);
		}
		
		if (id != null) {
			for (int i=0; i<list.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setTid(list.get(i).getTid());
				int status = travelService.getHeartInfoResult(vo);
				list.get(i).setStatus(status);				 
			}
			for (int i=0; i<list.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setTid(list.get(i).getTid());
				int status = travelService.getHeartInfoResult(vo);
				list.get(i).setStatus(status);				 
			}
		} 		
		

		JsonObject jdata = new JsonObject();
		JsonArray jlist = new JsonArray();
		Gson gson = new Gson();
		
		for(TravelVO vo : list) {
			String img[] = vo.getT_sfile().split(",");
			JsonObject jobj = new JsonObject();
			jobj.addProperty("tid", vo.getTid());
			jobj.addProperty("t_name", vo.getT_name());
			jobj.addProperty("t_tag", vo.getT_tag());
			jobj.addProperty("t_infor", vo.getT_infor());
			jobj.addProperty("t_addr", vo.getT_addr());
			jobj.addProperty("t_like", vo.getT_like());
			jobj.addProperty("status", vo.getStatus());
			jobj.addProperty("t_image", img[0]);
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
	 * 여행지 리스트 추가분 ajax 처리
	 */
	@ResponseBody
	@RequestMapping(value="/travel_proc_add.do", produces = "application/text; charset=utf8", method=RequestMethod.GET)
	public String travel_proc(String pnum) {
		
		int pageNumber = 1;
		
		if(!pnum.equals("")) { 
			pageNumber = Integer.parseInt(pnum) +1;
		}
		
		int startnum = ((pageNumber-1)*5) +1;
		int endnum = pageNumber*5; 
		
		
		ArrayList<TravelVO> list = travelService.getTravelList(startnum, endnum);
		
		JsonObject jdata = new JsonObject();
		JsonArray jlist = new JsonArray();
		Gson gson = new Gson();
		
		for(TravelVO vo : list) {
			String img[] = vo.getT_sfile().split(",");
			JsonObject jobj = new JsonObject();
			jobj.addProperty("tid", vo.getTid());
			jobj.addProperty("t_name", vo.getT_name());
			jobj.addProperty("t_addr", vo.getT_addr());
			jobj.addProperty("t_hp", vo.getT_hp());
			jobj.addProperty("t_image", img[0]);
			jobj.addProperty("t_sfile", vo.getT_sfile());
			jobj.addProperty("t_vpoint", vo.getT_vpoint());
			jobj.addProperty("t_hpoint", vo.getT_hpoint());
			
			jlist.add(jobj);
		}
		
		jdata.add("jlist", jlist);

		return gson.toJson(jdata);
	}
	
	/**
	 * travel_detail.do : 여행지 상세페이지
	 */
	@RequestMapping(value="/travel_detail.do", method=RequestMethod.GET)
	public ModelAndView travel_detail(String tid, HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		
		int pnum = 1;
		
		TravelVO vo = travelService.getTravelDetail(tid);
		PhotoSpotVO photovo = travelService.getPhotoSpot(tid);
		CarSpotVO carvo = travelService.getCarSpot(tid); 
		String infor2 = vo.getT_infor2().replace("\r\n", "<br>");
		String user_id = (String) session.getAttribute("session_id");
		String img[] = vo.getT_sfile().split(",");
		
		ArrayList<TravelReviewVO> revo = travelService.getTravelReview(tid);
		
		mv.setViewName("travel/travel_detail");
		mv.addObject("pnum",pnum);
		mv.addObject("vo",vo);
		mv.addObject("photovo",photovo);
		mv.addObject("carvo",carvo);
		mv.addObject("infor2",infor2);
		mv.addObject("revo", revo);
		mv.addObject("user_id", user_id);
		mv.addObject("tid", tid);
		mv.addObject("img",img);
		
		return mv;
	}
	
	/**
	 * travel_review_proc.do : 여행지 리뷰 등록 처리
	 */
	@RequestMapping(value="/travel_review_proc.do", method=RequestMethod.POST)
	public ModelAndView travel_review_proc(TravelReviewVO vo, HttpSession session, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		
		String user_id = (String) session.getAttribute("session_id");
		String tid = request.getParameter("tid");
		String t_review = request.getParameter("t_review");
		String star = request.getParameter("t_star");
		
		if(star == null) {
			star = "0";
		}
		
		int t_star = Integer.parseInt(star);
		
		vo.setId(user_id);
		vo.setTid(tid);
		vo.setT_review(t_review);
		vo.setT_star(t_star);

		boolean result = travelService.getInsertResult(vo);
		boolean star_avg = travelService.getStarAvgUpdate(tid);
		boolean review_count = travelService.getReviewCountUpdate(tid);
		
		if(result) {
			mv.setViewName("redirect:/travel_detail.do");
			mv.addObject("tid", vo.getTid());
			mv.addObject("star_avg",star_avg);
			mv.addObject("review_count",review_count);
			//mv.setViewName("redirect:/travel_review_list_proc.do");
			//mv.addObject("id", vo.getId());
			//mv.addObject("t_review", vo.getT_review());
			//mv.addObject("t_star", vo.getT_star());
		}
		return mv;
	}
	
	
	/**
	 * 여행지 리뷰 리스트 ajax 처리
	 */
	
	@ResponseBody
	@RequestMapping(value="/travel_review_list_proc.do", produces = "application/text; charset=utf8", method=RequestMethod.POST)
	public String travel_review_list_proc(String pnum, String tid, HttpSession session) {
		
		ArrayList<TravelReviewVO> list = travelService.getTravelReview(tid);
		String user_id = (String) session.getAttribute("session_id");
		int pageNumber = 1;

		if(!pnum.equals("")) { 
			pageNumber = Integer.parseInt(pnum) +1;
		}
		int startnum = ((pageNumber-1)*5) +1;
		int endnum = pageNumber*5; 
		list = travelService.getTravelReview(tid, startnum, endnum);
		
		JsonObject jdata = new JsonObject();
		JsonArray jlist = new JsonArray();
		Gson gson = new Gson();
		
		for(TravelReviewVO vo : list) {
			JsonObject jobj = new JsonObject();
			jobj.addProperty("reid", vo.getReid());
			jobj.addProperty("tid", vo.getTid());
			jobj.addProperty("id", vo.getId());
			jobj.addProperty("t_review", vo.getT_review());
			jobj.addProperty("t_star", vo.getT_star());
			jobj.addProperty("t_time", vo.getT_time());
			jobj.addProperty("pnum", String.valueOf(pageNumber));
			jobj.addProperty("user_id", user_id);
			
			jlist.add(jobj);
		}
		
		jdata.add("jlist", jlist);
		
		return gson.toJson(jdata);
	}

	
	/**
	 * travel_review_delete.do : 여행지 리뷰 삭제
	 */
	@ResponseBody
	@RequestMapping(value = "/travel_review_delete.do", method=RequestMethod.POST)
	public boolean travel_review_delete(HttpServletRequest request) {
		boolean result = travelService.getTravelReviewDelete(request.getParameter("reid"));
		
		return result;
	}
	
	
	
	/**
	 * 좋아요 +
	 */
	@ResponseBody
	@RequestMapping(value = "/heart_travel_plus.do", method=RequestMethod.POST)
	public boolean heart_plus(HttpSession session, HttpServletRequest request) {
		boolean total = false;
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");
		String tid = request.getParameter("tid");
		HeartVO vo = new HeartVO();
		vo.setId(id);
		vo.setTid(tid);		
		//heart 테이블 추가
		total = travelService.getHeartPlus(vo);
		//house테이블 하트+
		if (total) {
			boolean result = travelService.getUpdateHeart(tid);
		}
		return total;
	}

	/**
	 * 좋아요 -
	 */
	@ResponseBody
	@RequestMapping(value = "/heart_travel_minus.do", method=RequestMethod.POST)
	public boolean heart_minus(HttpSession session, HttpServletRequest request) {
		boolean total = false;
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");
		String tid = request.getParameter("tid");
		HeartVO vo = new HeartVO();
		vo.setId(id);
		vo.setTid(tid);		
		//heart 테이블 삭제 
		total = travelService.getHeartMinus(vo);
		//house테이블 하트-
		if (total) {
			boolean result = travelService.getUpdateMinusHeart(tid);
		}

		return total;
	}
}
