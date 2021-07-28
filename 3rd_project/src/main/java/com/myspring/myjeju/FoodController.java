package com.myspring.myjeju;

import java.io.Console;
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
import com.myjeju.service.FoodService;
import com.myjeju.vo.FoodReviewVO;
import com.myjeju.vo.FoodVO;
import com.myjeju.vo.HeartVO;

@Controller
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	/**
	 * food.do : 음식점 메인페이지
	 */
	@RequestMapping(value="/food.do", method=RequestMethod.GET)
	public ModelAndView food(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");	
		
		ArrayList<FoodVO> toplist = foodService.getFoodListTop3();
		for (int i=0; i<toplist.size(); i++) {
			String img[] = toplist.get(i).getF_sfile().split(",");
			toplist.get(i).setF_sfile(img[0]);
		}
		
		ArrayList<FoodVO> list = foodService.getFoodList(1,5); 
		for (int i=0; i<list.size(); i++) {
			String img[] = list.get(i).getF_sfile().split(",");
			list.get(i).setF_sfile(img[0]);
		}		
		
		//System.out.print(toplist.get(0).getF_sfile());
		//System.out.print(toplist.get(1).getF_sfile());
		//System.out.print(toplist.get(2).getF_sfile());
		
		
		if (id != null) {
			for (int i=0; i<toplist.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setFid(toplist.get(i).getFid());
				int status = foodService.getHeartInfoResult(vo);
				toplist.get(i).setStatus(status);				 
			}
			for (int i=0; i<list.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setFid(list.get(i).getFid());
				int status = foodService.getHeartInfoResult(vo);
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
		
		mv.setViewName("food/food");
		mv.addObject("list",list);
		mv.addObject("toplist",toplist);
		
		
		return mv;
	}
	
	/**
	 * 음식점 리스트 ajax 처리
	 */
	@ResponseBody
	@RequestMapping(value="/food_proc.do", produces = "application/text; charset=utf8", method=RequestMethod.GET)
	public String travel_proc(String pnum, String search, String search_text, HttpSession session) {
		ArrayList<FoodVO> list = foodService.getFoodList();
		
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");	
		
		int pageNumber = 1;

		if(!pnum.equals("")) { 
			pageNumber = Integer.parseInt(pnum) +1;
		}
		
		int startnum = ((pageNumber-1)*5) +1;
		int endnum = pageNumber*5; 

		if(search_text == null || search_text.equals("") || search_text.equals("null")) {
			list = foodService.getFoodList(startnum, endnum);
		}else {
			list = foodService.getFoodList(startnum, endnum, search, search_text);
		}
		
		if (id != null) {
			for (int i=0; i<list.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setFid(list.get(i).getFid());
				int status = foodService.getHeartInfoResult(vo);
				list.get(i).setStatus(status);				 
			}
			for (int i=0; i<list.size(); i++) {
				HeartVO vo = new HeartVO();		
				vo.setId(id); vo.setFid(list.get(i).getFid());
				int status = foodService.getHeartInfoResult(vo);
				list.get(i).setStatus(status);				 
			}
		} 		

		JsonObject jdata = new JsonObject();
		JsonArray jlist = new JsonArray();
		Gson gson = new Gson();
		
		
		
		
		for(FoodVO vo : list) {
			String img[] = vo.getF_sfile().split(",");
			JsonObject jobj = new JsonObject();
			jobj.addProperty("fid", vo.getFid());
			jobj.addProperty("f_name", vo.getF_name());
			jobj.addProperty("f_tag", vo.getF_tag());
			jobj.addProperty("f_infor", vo.getF_infor());
			jobj.addProperty("f_addr", vo.getF_addr());
			jobj.addProperty("f_like", vo.getF_like());
			jobj.addProperty("status", vo.getStatus());
			jobj.addProperty("f_image", img[0]);
			jobj.addProperty("star_avg", vo.getStar_avg()); 
			jobj.addProperty("review_count", vo.getReview_count());
			jobj.addProperty("pnum", String.valueOf(pageNumber));
			jobj.addProperty("search", search);
			jobj.addProperty("search_text", search_text);
			jobj.addProperty("endnum", endnum);
			
			jlist.add(jobj);
		}
		
		jdata.add("jlist", jlist);

		return gson.toJson(jdata);
	}
	
	/**
	 * 음식점 리스트 추가분 ajax 처리
	 */
	@ResponseBody
	@RequestMapping(value="/food_proc_add.do", produces = "application/text; charset=utf8", method=RequestMethod.GET)
	public String food_proc(String pnum) {
		
		int pageNumber = 1;
		
		if(!pnum.equals("")) { 
			pageNumber = Integer.parseInt(pnum) +1;
		}
		
		int startnum = ((pageNumber-1)*5) +1;
		int endnum = pageNumber*5; 
		
		
		ArrayList<FoodVO> list = foodService.getFoodList(startnum, endnum);
		
		JsonObject jdata = new JsonObject();
		JsonArray jlist = new JsonArray();
		Gson gson = new Gson();
		
		for(FoodVO vo : list) {
			String img[] = vo.getF_sfile().split(",");
			JsonObject jobj = new JsonObject();
			jobj.addProperty("fid", vo.getFid());
			jobj.addProperty("f_name", vo.getF_name());
			jobj.addProperty("f_addr", vo.getF_addr());
			jobj.addProperty("f_hp", vo.getF_hp());
			jobj.addProperty("f_image", img[0]);
			jobj.addProperty("f_vpoint", vo.getF_vpoint());
			jobj.addProperty("f_hpoint", vo.getF_hpoint());
			
			System.out.print(img[0]);
			
			jlist.add(jobj);
		}
		
		jdata.add("jlist", jlist);

		return gson.toJson(jdata);
	}
	
	/**
	 * food_detail.do : 음식점 상세페이지
	 */
	@RequestMapping(value="/food_detail.do", method=RequestMethod.GET)
	public ModelAndView food_detail(String fid, HttpSession session) {
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");
		
		ModelAndView mv = new ModelAndView();
		
		int pnum = 1;
		
		FoodVO vo = foodService.getFoodDetail(fid);
		if (id!= null) {
			HeartVO hvo = new HeartVO();
			hvo.setId(id);  hvo.setFid(fid);
			int status = foodService.getHeartInfoResult(hvo);		
			vo.setStatus(status);
		} else {
			vo.setStatus(0);
		}
		String infor2 = vo.getF_infor2().replace("\r\n", "<br>");
		String user_id = (String) session.getAttribute("session_id");
		String img[] = vo.getF_sfile().split(",");
		
		
		ArrayList<FoodReviewVO> fvo = foodService.getFoodReview(fid);
		
		mv.setViewName("food/food_detail");
		mv.addObject("pnum",pnum);
		mv.addObject("vo",vo);
		mv.addObject("img",img);
		mv.addObject("infor2",infor2);
		mv.addObject("fvo", fvo);
		mv.addObject("user_id", user_id);
		mv.addObject("fid", fid);
		
		return mv;
	}
	
	
	
	/**
	 * food_review_proc.do : 음식점 리뷰 등록 처리
	 */
	@RequestMapping(value="/food_review_proc.do", method=RequestMethod.POST)
	public ModelAndView food_review_proc(FoodReviewVO vo, HttpSession session, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		
		String user_id = (String) session.getAttribute("session_id");
		String fid = request.getParameter("fid");
		String f_review = request.getParameter("f_review");
		String star = request.getParameter("f_star");
		
		if(star == null) {
			star = "0";
		}
		
		int f_star = Integer.parseInt(star);
		
		vo.setId(user_id);
		vo.setFid(fid);
		vo.setF_review(f_review);
		vo.setF_star(f_star);

		boolean result = foodService.getInsertResult(vo);
		boolean star_avg = foodService.getStarAvgUpdate(fid);
		boolean review_count = foodService.getReviewCountUpdate(fid);
		
		if(result) {
			mv.setViewName("redirect:/food_detail.do");
			mv.addObject("fid", vo.getFid());
			mv.addObject("star_avg",star_avg);
			mv.addObject("review_count",review_count);
		}
		return mv;
	}
	
	
	/**
	 * 음식점 리뷰 리스트 ajax 처리
	 */
	
	@ResponseBody
	@RequestMapping(value="/food_review_list_proc.do", produces = "application/text; charset=utf8", method=RequestMethod.POST)
	public String food_review_list_proc(String pnum, String fid, HttpSession session) {
		
		ArrayList<FoodReviewVO> list = foodService.getFoodReview(fid);
		String user_id = (String) session.getAttribute("session_id");
		int pageNumber = 1;

		if(!pnum.equals("")) { 
			pageNumber = Integer.parseInt(pnum) +1;
		}
		int startnum = ((pageNumber-1)*5) +1;
		int endnum = pageNumber*5; 
		list = foodService.getFoodReview(fid, startnum, endnum);
		
		JsonObject jdata = new JsonObject();
		JsonArray jlist = new JsonArray();
		Gson gson = new Gson();
		
		for(FoodReviewVO vo : list) {
			JsonObject jobj = new JsonObject();
			jobj.addProperty("reid", vo.getReid());
			jobj.addProperty("fid", vo.getFid());
			jobj.addProperty("id", vo.getId());
			jobj.addProperty("f_review", vo.getF_review());
			jobj.addProperty("f_star", vo.getF_star());
			jobj.addProperty("f_time", vo.getF_time());
			jobj.addProperty("pnum", String.valueOf(pageNumber));
			jobj.addProperty("user_id", user_id);
			
			jlist.add(jobj);
		}
		
		jdata.add("jlist", jlist);
		
		return gson.toJson(jdata);
	}

	
	/**
	 * food_review_delete.do : 음식점 리뷰 삭제
	 */
	@ResponseBody
	@RequestMapping(value = "/food_review_delete.do", method=RequestMethod.POST)
	public boolean food_review_delete(HttpServletRequest request) {
		boolean result = foodService.getFoodReviewDelete(request.getParameter("reid"));
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 좋아요 +
	 */
	@ResponseBody
	@RequestMapping(value = "/heart_food_plus.do", method=RequestMethod.POST)
	public boolean heart_plus(HttpSession session, HttpServletRequest request) {
		boolean total = false;
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");
		String fid = request.getParameter("fid");
		HeartVO vo = new HeartVO();
		vo.setId(id);
		vo.setFid(fid);		
		//heart 테이블 추가
		total = foodService.getHeartPlus(vo);
		//hous테이블 하트+
		if (total) {
			boolean result = foodService.getUpdateHeart(fid);
		}
		return total;
	}

	/**
	 * 좋아요 -
	 */
	@ResponseBody
	@RequestMapping(value = "/heart_food_minus.do", method=RequestMethod.POST)
	public boolean heart_minus(HttpSession session, HttpServletRequest request) {
		boolean total = false;
		//로그인 회원정보 가져오기
		String id = (String) session.getAttribute("session_id");
		String fid = request.getParameter("fid");
		HeartVO vo = new HeartVO();
		vo.setId(id);
		vo.setFid(fid);		
		//heart 테이블 삭제 
		total = foodService.getHeartMinus(vo);
		//hous테이블 하트-
		if (total) {
			boolean result = foodService.getUpdateMinusHeart(fid);
		}

		return total;
	}
	
}
