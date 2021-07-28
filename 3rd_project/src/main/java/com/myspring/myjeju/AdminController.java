package com.myspring.myjeju;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myjeju.service.AdminService;
import com.myjeju.service.CommunityService;
import com.myjeju.vo.CafeVO;
import com.myjeju.vo.CarSpotVO;
import com.myjeju.vo.CommunityVO;
import com.myjeju.vo.FoodVO;
import com.myjeju.vo.HDetailVO;
import com.myjeju.vo.HouseVO;
import com.myjeju.vo.MemberVO;
import com.myjeju.vo.NoticeVO;
import com.myjeju.vo.PhotoSpotVO;
import com.myjeju.vo.StoreVO;
import com.myjeju.vo.TravelVO;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;	
	@Autowired
	private CommunityService communityService;
	

	//메인 화면 가기
	@RequestMapping(value="/adminindex.do", method=RequestMethod.GET)
	public String main(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.setAttribute("session_id", "admin");
		
		return "/admin/adminindex";
	}
	//회원관리 리스트
	@RequestMapping(value="/admember.do",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView tomember(String pnum, String search, String search_text) {
		ModelAndView mv = new ModelAndView();
		
		int pageNumber = 1;
		 
		if(pnum != null) {
	  		pageNumber = Integer.parseInt(pnum);
	  	}
		
		int startnum = ((pageNumber-1)*10) +1;
		int endnum = pageNumber*10; 
		int pagenum = (pageNumber -1) * 10;
		int target = 0;
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		if(search_text == null || search_text.equals("") || search_text.equals("null")) {
	  		list = adminService.getlist(startnum,endnum);
	  		target = adminService.targetPage(pagenum);
	  	} else {
	  		list = adminService.getlist(startnum,endnum,search,search_text);
	  		target = adminService.targetPage(pagenum,search,search_text);
	  	}
			
		int targetpage = 0;
		if(pageNumber != 1 ) {
			targetpage = (target-2) / 10 ;
			} else {
			targetpage = (target-1) / 10 ;
			}
		mv.setViewName("admin/admember");
		mv.addObject("list", list);
		mv.addObject("targetpage", String.valueOf(targetpage));
		mv.addObject("pageNumber", String.valueOf(pageNumber));
		mv.addObject("search", search);
		mv.addObject("search_text", search_text);
		
		return mv;
	}
	//숙소관리 리스트
		@RequestMapping(value="/adhouse.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView tohouse(String pnum, String search, String search_text) {
			ModelAndView mv = new ModelAndView();
			
			int pageNumber = 1;
			 
			if(pnum != null) {
		  		pageNumber = Integer.parseInt(pnum);
		  	}
			
			int startnum = ((pageNumber-1)*10) +1;
			int endnum = pageNumber*10; 
			int pagenum = (pageNumber -1) * 10;
			int target = 0;
			ArrayList<HouseVO> list = new ArrayList<HouseVO>();
			if(search_text == null || search_text.equals("") || search_text.equals("null")) {
		  		list = adminService.getlisthouse(startnum,endnum);
		  		target = adminService.targethousePage(pagenum);
		  	} else {
		  		list = adminService.getlisthouse(startnum,endnum,search,search_text);
		  		target = adminService.targethousePage(pagenum,search,search_text);
		  	}
				
			int targetpage = 0;
			if(pageNumber != 1 ) {
				targetpage = (target-2) / 10 ;
				} else {
				targetpage = (target-1) / 10 ;
				}
			mv.setViewName("admin/adhouse");
			mv.addObject("list", list);
			mv.addObject("targetpage", String.valueOf(targetpage));
			mv.addObject("pageNumber", String.valueOf(pageNumber));
			mv.addObject("search", search);
			mv.addObject("search_text", search_text);
			
			return mv;
		}
		//숙소관리 상세페이지
		@RequestMapping(value="/adhouse_content.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView tohouse(String hid) {
			ModelAndView mv = new ModelAndView();
			
			HouseVO vo = new HouseVO();
			vo = adminService.gethouse(hid);
			System.out.println(vo.getH_file());
			System.out.println(vo.getH_img());
			
			mv.setViewName("admin/adhouse_content");
			mv.addObject("vo", vo);
			mv.addObject("hid", hid);
			return mv;
		}
		
		//숙소 -> 객실 등록하기
		@RequestMapping(value="/adhouse_de_write.do", method=RequestMethod.GET)
		public ModelAndView adhouse_de_write(String hid) {
			ModelAndView mv = new ModelAndView();
			
			mv.addObject("hid", hid);
			mv.setViewName("admin/adhouse_de_write");
			
			return mv;
		}
		
		//숙소 --> 객실 등록 DB
		@RequestMapping(value = "/adhouse_de_write_proc.do", method=RequestMethod.POST)
		public ModelAndView adhouse_de_write(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();			
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			System.out.print(root_path);
			String attach_path = "\\resources\\images\\house\\house_detail\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUplodaName= "";
			
			UUID uuid = UUID.randomUUID();
			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				System.out.println("기존 파일명 : "+fileOriginName); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if(i==0) { 
					fileMultiName += fileOriginName; 
					fileMultiUplodaName += uuid +"_"+fileOriginName;
				} else { 
					fileMultiName += ","+fileOriginName; 
					fileMultiUplodaName += "," + uuid +"_"+fileOriginName;
					} 
			}
			HDetailVO vo = new HDetailVO();
			vo.setHd_img(fileMultiName);
			vo.setHd_file(fileMultiUplodaName);
			vo.setHid(request.getParameter("hid"));
			vo.setHd_name(request.getParameter("hd_name"));
			vo.setHd_price(Integer.parseInt(request.getParameter("hd_price")));
			vo.setHd_people(Integer.parseInt(request.getParameter("hd_people")));
			
			boolean result = adminService.getHdetailUpload(vo);
			
			mv.setViewName("redirect:/adhouse_de.do?hid="+request.getParameter("hid"));
			return mv;
			
		}
		
		//숙소 -> 객실 수정하기
		@RequestMapping(value="/adhouse_de_update.do", method=RequestMethod.GET)
		public ModelAndView adhouse_de_update(String hdid) {
			ModelAndView mv = new ModelAndView();
			HDetailVO vo = adminService.gethousedecontent(hdid);				
			String img[] = vo.getHd_img().split(",");	
			String file[] = vo.getHd_file().split(",");
			ArrayList<HDetailVO> list = new ArrayList<HDetailVO>();
			for (int i=0; i<img.length; i++) {
				HDetailVO hvo = new HDetailVO();
				hvo.setHd_img(img[i]);
				hvo.setHd_file(file[i]);
				
				list.add(hvo);
				
			}
			
			mv.setViewName("admin/adhouse_de_update");
			mv.addObject("vo", vo);
			mv.addObject("list", list);
			return mv;
		}
		
		//숙소 --> 객실 수정하기 DB
		@RequestMapping(value="/adhouse_de_update_proc.do", method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adhouse_de_update_proc(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();
			
			System.out.println("파일이름" + request.getParameter("hd_img"));
			System.out.print("파일경로" + request.getParameter("hd_file"));
			
			String fileOldName=request.getParameter("hd_img");
			String fileOldRoot = request.getParameter("hd_file");
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			System.out.print(root_path);
			String attach_path = "\\resources\\images\\house\\house_detail\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUplodaName= "";
			
			UUID uuid = UUID.randomUUID();
			
			System.out.print(file.length);

			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				System.out.println("기존 파일명 : "+fileOriginName); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if (fileOriginName != "") {
					if(i==0) { 
						fileMultiName += fileOriginName; 
						fileMultiUplodaName += uuid +"_"+fileOriginName;
					} else { 
						fileMultiName += ","+fileOriginName; 
						fileMultiUplodaName += "," + uuid +"_"+fileOriginName;
					} 
				}
			}
			
			String old_name = request.getParameter("old_name");
			String old[] = old_name.split(",");
			for (int i=0; i<old.length; i++) {
				File old_file = new File(root_path+attach_path+old[i]);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}

			HDetailVO vo = new HDetailVO();
			vo.setHd_img(fileOldName+fileMultiName);
			vo.setHd_file(fileOldRoot+fileMultiUplodaName);
			vo.setHid(request.getParameter("hid"));
			vo.setHdid(request.getParameter("hdid"));
			vo.setHd_name(request.getParameter("hd_name"));
			vo.setHd_price(Integer.parseInt(request.getParameter("hd_price")));
			vo.setHd_people(Integer.parseInt(request.getParameter("hd_people")));
			
			boolean result = adminService.getHdetailUpdate(vo);
			
			mv.setViewName("redirect:/adhouse_de_content.do?hdid="+vo.getHdid());
			return mv;
		}
		
		//객실 삭제
		@ResponseBody
		@RequestMapping(value = "/adhouse_de_delete.do", method=RequestMethod.POST)
		public boolean adhous_de_delete(HttpServletRequest request) {
			String hdid = request.getParameter("hdid");			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\house\\house_detail\\";
			
			HDetailVO vo = adminService.gethousedecontent(hdid);
			
			String old_name = vo.getHd_file();
			String old[] = old_name.split(",");
			for (int i=0; i<old.length; i++) {
				File old_file = new File(root_path+attach_path+old[i]);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}
			
			boolean result = adminService.getHdetailDelete(hdid);
			
			return result;
		}
		
		
//음식점관리 리스트
		@RequestMapping(value="/adfood.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView tofood(String pnum, String search, String search_text) {
			ModelAndView mv = new ModelAndView();
			
			int pageNumber = 1;
			 
			if(pnum != null) {
		  		pageNumber = Integer.parseInt(pnum);
		  	}
			
			int startnum = ((pageNumber-1)*10) +1;
			int endnum = pageNumber*10; 
			int pagenum = (pageNumber -1) * 10;
			int target = 0;
			ArrayList<FoodVO> list = new ArrayList<FoodVO>();
			if(search_text == null || search_text.equals("") || search_text.equals("null")) {
		  		list = adminService.getlistfood(startnum,endnum);
		  		target = adminService.targetfoodPage(pagenum);
		  	} else {
		  		list = adminService.getlistfood(startnum,endnum,search,search_text);
		  		target = adminService.targetfoodPage(pagenum,search,search_text);
		  	}
				
			int targetpage = 0;
			if(pageNumber != 1 ) {
				targetpage = (target-2) / 10 ;
				} else {
				targetpage = (target-1) / 10 ;
				}
			mv.setViewName("admin/adfood");
			mv.addObject("list", list);
			mv.addObject("targetpage", String.valueOf(targetpage));
			mv.addObject("pageNumber", String.valueOf(pageNumber));
			mv.addObject("search", search);
			mv.addObject("search_text", search_text);
			
			return mv;
		}
//음식점 등록
		@RequestMapping(value="/adfood_write.do", method=RequestMethod.GET)
		public String adfood_write() {
			return "admin/adfood_write";
		}
//음식점 등록 DB
		@RequestMapping(value="/adfood_write_proc.do", method={RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adfood_write_proc(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();			
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\food\\food_detail\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUplodaName= "";
			
			UUID uuid = UUID.randomUUID();
			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				System.out.println("기존 파일명 : "+fileOriginName); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if(i==0) { 
					fileMultiName += fileOriginName; 
					fileMultiUplodaName += uuid +"_"+fileOriginName;
				} else { 
					fileMultiName += ","+fileOriginName; 
					fileMultiUplodaName += "," + uuid +"_"+fileOriginName;
					} 
			}
			FoodVO vo = new FoodVO();
			vo.setF_file(fileMultiName);
			vo.setF_sfile(fileMultiUplodaName);
			vo.setF_name(request.getParameter("f_name"));
			vo.setF_tag(request.getParameter("f_tag"));
			vo.setF_infor(request.getParameter("f_infor"));
			vo.setF_infor2(request.getParameter("f_infor2"));
			vo.setF_addr1(request.getParameter("f_addr1"));
			vo.setF_addr2(request.getParameter("f_addr2"));
			vo.setF_addr(vo.getF_addr1()+" "+vo.getF_addr2());
			vo.setF_vpoint(request.getParameter("f_vpoint"));
			vo.setF_hpoint(request.getParameter("f_hpoint"));
			vo.setF_hp(request.getParameter("f_hp"));
			
			boolean result = adminService.getFoodUpload(vo);
			
			mv.setViewName("redirect:/adfood.do");
			return mv;
			
		}
		
//음식점 상세
		@RequestMapping(value="/adfood_content.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adfood_content(String fid) {
			ModelAndView mv = new ModelAndView();
			FoodVO vo = adminService.getFoodcontent(fid);
			String img[] = vo.getF_sfile().split(",");		
			
			mv.addObject("vo", vo);
			mv.addObject("img", img);
			mv.setViewName("admin/adfood_content");
			return mv;
		}
		
//음식점수정
		@RequestMapping(value="/adfood_update.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adfood_update(String fid) {
			ModelAndView mv = new ModelAndView();
			FoodVO vo = adminService.getFoodcontent(fid);
			String file[] = vo.getF_file().split(",");
			String sfile[] = vo.getF_sfile().split(",");	
			ArrayList<FoodVO> list = new ArrayList<FoodVO>();
			for (int i=0; i<sfile.length; i++) {
				FoodVO fvo = new FoodVO();
				fvo.setF_file(file[i]);
				fvo.setF_sfile(sfile[i]);				
				list.add(fvo);				
			}			
			mv.addObject("vo", vo);
			mv.addObject("list", list);
			mv.setViewName("admin/adfood_update");
			return mv;
		}
		
//음식점 수정하기 DB
		@RequestMapping(value="/adfood_update_proc.do", method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adfood_update_proc(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();
			
			System.out.println("파일이름" + request.getParameter("f_file"));
			System.out.print("파일경로" + request.getParameter("f_sfile"));
			
			String fileOldName=request.getParameter("f_file");
			String fileOldRoot = request.getParameter("f_sfile");
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			System.out.print(root_path);
			String attach_path = "\\resources\\images\\food\\food_detail\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUplodaName= "";
			
			UUID uuid = UUID.randomUUID();
			
			System.out.print(file.length);

			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				System.out.println("기존 파일명 : "+fileOriginName); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if (fileOriginName != "") {
					if(i==0) { 
						fileMultiName += fileOriginName; 
						fileMultiUplodaName += uuid +"_"+fileOriginName;
					} else { 
						fileMultiName += ","+fileOriginName; 
						fileMultiUplodaName += "," + uuid +"_"+fileOriginName;
					} 
				}
			}
			
			String old_name = request.getParameter("old_name");
			String old[] = old_name.split(",");
			for (int i=0; i<old.length; i++) {
				File old_file = new File(root_path+attach_path+old[i]);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}

			FoodVO vo = new FoodVO();
			vo.setF_file(fileOldName+fileMultiName);
			vo.setF_sfile(fileOldRoot+fileMultiUplodaName);
			vo.setF_name(request.getParameter("f_name"));
			vo.setF_tag(request.getParameter("f_tag"));
			vo.setF_infor(request.getParameter("f_infor"));
			vo.setF_infor2(request.getParameter("f_infor2"));
			vo.setF_addr(request.getParameter("f_addr"));
			vo.setF_vpoint(request.getParameter("f_vpoint"));
			vo.setF_hpoint(request.getParameter("f_hpoint"));
			vo.setF_hp(request.getParameter("f_hp"));
			vo.setFid(request.getParameter("fid"));
			
			boolean result = adminService.getFoodUpdate(vo);
			
			mv.setViewName("redirect:/adfood_content.do?fid="+vo.getFid());
			return mv;
		}
		
//음식점 삭제
		@ResponseBody
		@RequestMapping(value = "/adfood_delete.do", method=RequestMethod.POST)
		public boolean adfood_delete(HttpServletRequest request) {
			String fid = request.getParameter("fid");			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\food\\food_detail\\";
			
			FoodVO vo = adminService.getFoodcontent(fid);
			
			String old_name = vo.getF_sfile();
			String old[] = old_name.split(",");
			for (int i=0; i<old.length; i++) {
				File old_file = new File(root_path+attach_path+old[i]);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}
			
			boolean result = adminService.getFoodDelete(fid);
			
			return result;
		}
		
		
//여행지관리 리스트
		@RequestMapping(value="/adtravel.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView totravel(String pnum, String search, String search_text) {
			ModelAndView mv = new ModelAndView();
			
			int pageNumber = 1;
			 
			if(pnum != null) {
		  		pageNumber = Integer.parseInt(pnum);
		  	}
			
			int startnum = ((pageNumber-1)*10) +1;
			int endnum = pageNumber*10; 
			int pagenum = (pageNumber -1) * 10;
			int target = 0;
			ArrayList<TravelVO> list = new ArrayList<TravelVO>();
			if(search_text == null || search_text.equals("") || search_text.equals("null")) {
		  		list = adminService.getlisttravel(startnum,endnum);
		  		target = adminService.targettravelPage(pagenum);
		  	} else {
		  		list = adminService.getlisttravel(startnum,endnum,search,search_text);
		  		target = adminService.targettravelPage(pagenum,search,search_text);
		  	}
				
			int targetpage = 0;
			if(pageNumber != 1 ) {
				targetpage = (target-2) / 10 ;
				} else {
				targetpage = (target-1) / 10 ;
				}
			mv.setViewName("admin/adtravel");
			mv.addObject("list", list);
			mv.addObject("targetpage", String.valueOf(targetpage));
			mv.addObject("pageNumber", String.valueOf(pageNumber));
			mv.addObject("search", search);
			mv.addObject("search_text", search_text);
			
			return mv;
		}
		
		//여행지관리 상세페이지
		@RequestMapping(value="/adtravel_content.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView totravel(String tid) {
			ModelAndView mv = new ModelAndView();
			
			
			PhotoSpotVO photovo = new PhotoSpotVO();
			CarSpotVO carvo = new CarSpotVO();
			
			if(photovo.getPs_psfile() == null || carvo.getCs_csfile() == null) {
				photovo = adminService.getPhotoSpot(tid);
				carvo = adminService.getCarSpot(tid);
			}
			
			TravelVO vo = adminService.gettravel(tid);
	
			String img[] = vo.getT_sfile().split(",");	

					
			mv.setViewName("admin/adtravel_content");
			mv.addObject("img", img);
			mv.addObject("vo", vo);
			mv.addObject("photovo", photovo);
			mv.addObject("carvo", carvo);
			mv.addObject("tid", tid);

			return mv;
		}
	
//여행지 등록
		@RequestMapping(value="/adtravel_write.do", method=RequestMethod.GET)
		public String adtravel_write() {
			return "admin/adtravel_write";
		}
		
		
//여행지 등록 DB
		@RequestMapping(value="/adtravel_write_proc.do", method={RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adtravel_write_proc(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();			
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\travel\\travel_detail\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUplodaName= "";
			
			UUID uuid = UUID.randomUUID();
			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if(i==0) { 
					fileMultiName += fileOriginName; 
					fileMultiUplodaName += uuid +"_"+fileOriginName;
				} else { 
					fileMultiName += ","+fileOriginName; 
					fileMultiUplodaName += "," + uuid +"_"+fileOriginName;
					} 
			}
			
			TravelVO vo = new TravelVO();
			vo.setT_file(fileMultiName);
			vo.setT_sfile(fileMultiUplodaName);
			vo.setT_name(request.getParameter("t_name"));
			vo.setT_tag(request.getParameter("t_tag"));
			vo.setT_infor(request.getParameter("t_infor"));
			vo.setT_infor2(request.getParameter("t_infor2"));
			vo.setT_addr1(request.getParameter("t_addr1"));
			vo.setT_addr2(request.getParameter("t_addr2"));
			vo.setT_addr(vo.getT_addr1()+" "+vo.getT_addr2());
			vo.setT_vpoint(request.getParameter("t_vpoint"));
			vo.setT_hpoint(request.getParameter("t_hpoint"));
			vo.setT_hp(request.getParameter("t_hp"));
			
			boolean result = adminService.getTravelUpload(vo);
			
			mv.setViewName("redirect:/adtravel.do");
			return mv;
			
		}
		
		
		//여행지 수정
		@RequestMapping(value="/adtravel_update.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adtravel_update(String tid) {
			ModelAndView mv = new ModelAndView();
			TravelVO vo = adminService.gettravel(tid);
			String file[] = vo.getT_file().split(",");
			String sfile[] = vo.getT_sfile().split(",");	
			ArrayList<TravelVO> list = new ArrayList<TravelVO>();
			for (int i=0; i<sfile.length; i++) {
				TravelVO fvo = new TravelVO();
				fvo.setT_file(file[i]);
				fvo.setT_sfile(sfile[i]);				
				list.add(fvo);				
			}			
			mv.addObject("vo", vo);
			mv.addObject("list", list);
			mv.setViewName("admin/adtravel_update");
			return mv;
		}
		
		//여행지 수정하기 DB
		@RequestMapping(value="/adtravel_update_proc.do", method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adtravel_update_proc(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();
			
			String fileOldName = request.getParameter("t_file");
			String fileOldRoot = request.getParameter("t_sfile");
			
			System.out.println(request.getParameter("t_sfile"));
			System.out.println(request.getParameter("t_sfile"));
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\travel\\travel_detail\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUplodaName= "";
			
			UUID uuid = UUID.randomUUID();
			

			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				System.out.println("기존 파일명 : "+fileOriginName); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if (fileOriginName != "") {
					if(i==0) { 
						fileMultiName += fileOriginName; 
						fileMultiUplodaName += uuid +"_"+fileOriginName;
					} else { 
						fileMultiName += ","+fileOriginName; 
						fileMultiUplodaName += "," + uuid +"_"+fileOriginName;
					} 
				}
			}
			
			String old_name = request.getParameter("old_name");
			String old[] = old_name.split(",");
			for (int i=0; i<old.length; i++) {
				File old_file = new File(root_path+attach_path+old[i]);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}

			TravelVO vo = new TravelVO();
			vo.setT_file(fileOldName+fileMultiName);
			vo.setT_sfile(fileOldRoot+fileMultiUplodaName);
			vo.setT_name(request.getParameter("t_name"));
			vo.setT_tag(request.getParameter("t_tag"));
			vo.setT_infor(request.getParameter("t_infor"));
			vo.setT_infor2(request.getParameter("t_infor2"));
			vo.setT_addr(request.getParameter("t_addr"));
			vo.setT_vpoint(request.getParameter("t_vpoint"));
			vo.setT_hpoint(request.getParameter("t_hpoint"));
			vo.setT_hp(request.getParameter("t_hp"));
			vo.setTid(request.getParameter("tid"));
			
			boolean result = adminService.getTravelUpdate(vo);
			
			mv.setViewName("redirect:/adtravel_content.do?tid="+vo.getTid());
			return mv;
		}
		
//여행지 삭제
		@ResponseBody
		@RequestMapping(value = "/adtravel_delete.do", method=RequestMethod.POST)
		public boolean adtravel_delete(HttpServletRequest request) {
			String tid = request.getParameter("tid");			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\travel\\travel_detail\\";
			
			TravelVO vo = adminService.gettravel(tid);
			
			String old_name = vo.getT_file();
			String old[] = old_name.split(",");
			for (int i=0; i<old.length; i++) {
				File old_file = new File(root_path+attach_path+old[i]);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}
			
			boolean result = adminService.getTravelDelete(tid);
			
			return result;
		}
		
		
//여행지 스팟 등록
		@RequestMapping(value="/adtravel_spot_write.do", method=RequestMethod.GET)
		public ModelAndView adtravel_spot_write(String tid) {
			ModelAndView mv = new ModelAndView();
			
			mv.setViewName("admin/adtravel_spot_write");
			mv.addObject("tid",tid);
			return mv;
		}
		
//여행지 스팟 등록 DB
		@RequestMapping(value="/adtravel_spot_write_proc.do", method={RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adtravel_spot_write_proc(String spot_choice, MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();			
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\spot\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUplodaName= "";
			
			String tid = request.getParameter("tid");
			String ps_name = request.getParameter("ps_name");
			String cs_name = request.getParameter("cs_name");
			String ps_infor = request.getParameter("ps_infor");
			String cs_infor = request.getParameter("cs_infor");
			
			UUID uuid = UUID.randomUUID();
			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if(i==0) { 
					fileMultiName += fileOriginName; 
					fileMultiUplodaName += uuid +"_"+fileOriginName;
				} else { 
					fileMultiName += ","+fileOriginName; 
					fileMultiUplodaName += "," + uuid +"_"+fileOriginName;
					} 
			}
			
			PhotoSpotVO photovo = new PhotoSpotVO();
			CarSpotVO carvo = new CarSpotVO();
			
			if(spot_choice.equals("photo")) {
				photovo.setPs_psfile(fileMultiName);
				photovo.setPs_pssfile(fileMultiUplodaName);
				photovo.setTid(tid);
				photovo.setPs_name(ps_name);
				photovo.setPs_infor(ps_infor);
				boolean result = adminService.getTravelPhotoSpotUpload(photovo);
			}else {
				carvo.setCs_csfile(fileMultiName);
				carvo.setCs_cssfile(fileMultiUplodaName);
				carvo.setTid(tid);
				carvo.setCs_name(cs_name);
				carvo.setCs_infor(cs_infor);
				boolean result = adminService.getTravelCarSpotUpload(carvo);
			}

			mv.setViewName("redirect:/adtravel_content.do");
			mv.addObject("tid",tid);
			
			return mv;
			
		}
		
//여행지 포토스팟 수정
		@RequestMapping(value="/adtravel_photospot_update.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adtravel_photospot_update(String tid) {
			ModelAndView mv = new ModelAndView();
			
			PhotoSpotVO photovo = adminService.getPhotoSpot(tid);
			
			String file = photovo.getPs_psfile();
			String sfile = photovo.getPs_pssfile();
			
			ArrayList<PhotoSpotVO> list = new ArrayList<PhotoSpotVO>();
			
			PhotoSpotVO pvo = new PhotoSpotVO();
			pvo.setPs_psfile(file);
			pvo.setPs_pssfile(sfile);			
			list.add(pvo);				
					
			mv.setViewName("admin/adtravel_photospot_update");
			mv.addObject("photovo", photovo);
			mv.addObject("list", list);
			return mv;
		}	
		
//여행지 포토스팟 수정 DB
		@RequestMapping(value="/adtravel_photospot_update_proc.do", method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adtravel_photospot_update_proc(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();
			
			System.out.println("파일이름" + request.getParameter("ps_psfile"));
			System.out.print("파일경로" + request.getParameter("ps_pssfile"));
			
			String fileOldName = request.getParameter("ps_psfile");
			String fileOldRoot = request.getParameter("ps_pssfile");
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\spot\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUplodaName= "";
			
			UUID uuid = UUID.randomUUID();
			

			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				System.out.println("기존 파일명 : "+fileOriginName); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if (fileOriginName != "") {
					if(i==0) { 
						fileMultiName += fileOriginName; 
						fileMultiUplodaName += uuid +"_"+fileOriginName;
					} else { 
						fileMultiName += ","+fileOriginName; 
						fileMultiUplodaName += "," + uuid +"_"+fileOriginName;
					} 
				}
			}
			
			String old_name = request.getParameter("old_name");
			String old[] = old_name.split(",");
			for (int i=0; i<old.length; i++) {
				File old_file = new File(root_path+attach_path+old[i]);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}

			PhotoSpotVO photovo = new PhotoSpotVO();
			
			photovo.setPs_psfile(fileOldName+fileMultiName);
			photovo.setPs_pssfile(fileOldRoot+fileMultiUplodaName);
			photovo.setTid(request.getParameter("tid"));
			photovo.setPs_name(request.getParameter("ps_name"));
			photovo.setPs_infor(request.getParameter("ps_infor"));
			
			boolean result = adminService.getPhotoSpotUpdate(photovo);
			
			mv.setViewName("redirect:/adtravel_content.do?tid="+photovo.getTid());
			return mv;
		}
		
//여행지 차박스팟 수정
		@RequestMapping(value="/adtravel_carspot_update.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adtravel_carspot_update(String tid) {
			ModelAndView mv = new ModelAndView();
			
			CarSpotVO carvo = adminService.getCarSpot(tid);
			
			String file = carvo.getCs_csfile();
			String sfile = carvo.getCs_cssfile();
			
			ArrayList<CarSpotVO> list = new ArrayList<CarSpotVO>();
			
			CarSpotVO cvo = new CarSpotVO();
			cvo.setCs_csfile(file);
			cvo.setCs_cssfile(sfile);			
			list.add(cvo);				
					
			mv.setViewName("admin/adtravel_carspot_update");
			mv.addObject("carvo", carvo);
			mv.addObject("list", list);
			return mv;
		}		
//여행지 차박스팟 수정 DB
		@RequestMapping(value="/adtravel_carspot_update_proc.do", method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adtravel_carspot_update_proc(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();
			
			System.out.println("파일이름" + request.getParameter("cs_csfile"));
			System.out.print("파일경로" + request.getParameter("cs_cssfile"));
			
			String fileOldName = request.getParameter("cs_csfile");
			String fileOldRoot = request.getParameter("cs_cssfile");
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\spot\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUplodaName= "";
			
			UUID uuid = UUID.randomUUID();
			

			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				System.out.println("기존 파일명 : "+fileOriginName); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if (fileOriginName != "") {
					if(i==0) { 
						fileMultiName += fileOriginName; 
						fileMultiUplodaName += uuid +"_"+fileOriginName;
					} else { 
						fileMultiName += ","+fileOriginName; 
						fileMultiUplodaName += "," + uuid +"_"+fileOriginName;
					} 
				}
			}
			
			String old_name = request.getParameter("old_name");
			String old[] = old_name.split(",");
			for (int i=0; i<old.length; i++) {
				File old_file = new File(root_path+attach_path+old[i]);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}

			CarSpotVO carvo = new CarSpotVO();
			
			carvo.setCs_csfile(fileOldName+fileMultiName);
			carvo.setCs_cssfile(fileOldRoot+fileMultiUplodaName);
			carvo.setTid(request.getParameter("tid"));
			carvo.setCs_name(request.getParameter("cs_name"));
			carvo.setCs_infor(request.getParameter("cs_infor"));
			
			boolean result = adminService.getCarSpotUpdate(carvo);
			
			mv.setViewName("redirect:/adtravel_content.do");
			mv.addObject("tid",request.getParameter("tid"));
			return mv;
		}
				
//여행지 스팟 삭제
		@ResponseBody
		@RequestMapping(value = "/adtravel_photospot_delete.do", method=RequestMethod.POST)
		public boolean adtravel_photospot_delete(HttpServletRequest request) {
			String tid = request.getParameter("tid");			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\spot\\";
			
			PhotoSpotVO photovo = adminService.getPhotoSpot(tid);
			
			String old_psfile = photovo.getPs_psfile();
			
			File old_file = new File(root_path + attach_path + old_psfile);
			if ( old_file.exists()) {
				old_file.delete();
			}
			
			boolean result = adminService.getPhotoSpotDelete(tid);
			
			return result;
		}		
		@ResponseBody
		@RequestMapping(value = "/adtravel_carspot_delete.do", method=RequestMethod.POST)
		public boolean adtravel_carspot_delete(HttpServletRequest request) {
			String tid = request.getParameter("tid");			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\spot\\";
			
			CarSpotVO carvo = adminService.getCarSpot(tid);
			
			String old_csfile = carvo.getCs_csfile();
			
			File old_file = new File(root_path + attach_path + old_csfile);
			if ( old_file.exists()) {
				old_file.delete();
			}
			
			boolean result = adminService.getCarSpotDelete(tid);
			
			return result;
		}		
		
		
		
		
		
		
		
		
		
		
		
		
		//카페 관리 리스트
		@RequestMapping(value="/adcafe.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView tocafe(String pnum, String search, String search_text) {
			ModelAndView mv = new ModelAndView();
			
			int pageNumber = 1;
			 
			if(pnum != null) {
		  		pageNumber = Integer.parseInt(pnum);
		  	}
			
			int startnum = ((pageNumber-1)*10) +1;
			int endnum = pageNumber*10; 
			int pagenum = (pageNumber -1) * 10;
			int target = 0;
			ArrayList<CafeVO> list = new ArrayList<CafeVO>();
			if(search_text == null || search_text.equals("") || search_text.equals("null")) {
				list = adminService.getlistcafe(startnum, endnum);
				target = adminService.targetcafePage(pageNumber);
		  	} else {
		  		list = adminService.getlistcafe(startnum, endnum, search, search_text);
		  		target = adminService.targetcafePage(pageNumber, search, search_text);
		  	}
				
			int targetpage = 0;
			if(pageNumber != 1 ) {
				targetpage = (target-2) / 10 ;
				} else {
				targetpage = (target-1) / 10 ;
				}
			mv.setViewName("admin/adcafe");
			mv.addObject("list", list);
			mv.addObject("targetpage", String.valueOf(targetpage));
			mv.addObject("pageNumber", String.valueOf(pageNumber));
			mv.addObject("search", search);
			mv.addObject("search_text", search_text);
			
			return mv;
		}
		//카페 등록
		@RequestMapping(value="/adcafe_write.do", method=RequestMethod.GET)
		public String adcafe_write() {
			return "admin/adcafe_write";
		}
		//카페 등록 DB
		@RequestMapping(value="/adcafe_write_proc.do", method={RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adcafe_write_proc(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();			
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\cafe\\cafe_detail\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUplodaName= "";
			
			UUID uuid = UUID.randomUUID();
			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				System.out.println("기존 파일명 : "+fileOriginName); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if(i==0) { 
					fileMultiName += fileOriginName; 
					fileMultiUplodaName += uuid +"_"+fileOriginName;
				} else { 
					fileMultiName += ","+fileOriginName; 
					fileMultiUplodaName += "," + uuid +"_"+fileOriginName;
					} 
			}
			CafeVO vo = new CafeVO();
			
			vo.setCa_file(fileMultiName);
			vo.setCa_sfile(fileMultiUplodaName);
			vo.setCa_name(request.getParameter("ca_name"));
			vo.setCa_tag(request.getParameter("ca_tag"));
			vo.setCa_infor(request.getParameter("ca_infor"));
			vo.setCa_infor2(request.getParameter("ca_infor2"));
			vo.setCa_addr1(request.getParameter("ca_addr1"));
			vo.setCa_addr2(request.getParameter("ca_addr2"));
			vo.setCa_addr(vo.getCa_addr1() + " " + vo.getCa_addr2());
			vo.setCa_vpoint(request.getParameter("ca_vpoint"));
			vo.setCa_hpoint(request.getParameter("ca_hpoint"));
			vo.setCa_hp(request.getParameter("ca_hp"));
			
			boolean result = adminService.getCafeUpload(vo);
			
			mv.setViewName("redirect:/adcafe.do");
			return mv;
			
		}
		
		//카페 상세
		@RequestMapping(value="/adcafe_content.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adcafe_content(String caid) {
			ModelAndView mv = new ModelAndView();
			CafeVO vo = adminService.getCafecontent(caid);
			String img[] = vo.getCa_sfile().split(",");
			
			mv.addObject("vo", vo);
			mv.addObject("img", img);
			mv.setViewName("admin/adcafe_content");
			return mv;
		}
		
		//카페 수정
		@RequestMapping(value="/adcafe_update.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adcafe_update(String caid) {
			ModelAndView mv = new ModelAndView();
			CafeVO vo = adminService.getCafecontent(caid);
			String file[] = vo.getCa_file().split(",");
			String sfile[] = vo.getCa_sfile().split(",");
			
			ArrayList<CafeVO> list = new ArrayList<CafeVO>();
			for (int i=0; i<sfile.length; i++) {
				CafeVO cvo = new CafeVO();
				cvo.setCa_file(file[i]);
				cvo.setCa_sfile(sfile[i]);
				list.add(cvo);
			}
			
			mv.addObject("vo", vo);
			mv.addObject("list", list);
			mv.setViewName("admin/adcafe_update");
			return mv;
		}
		
		//카페 수정하기 DB
		@RequestMapping(value="/adcafe_update_proc.do", method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView adcafe_update_proc(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] file) throws Exception {
			ModelAndView mv = new ModelAndView();
			
			System.out.println("파일이름" + request.getParameter("ca_file"));
			System.out.print("파일경로" + request.getParameter("ca_sfile"));
			
			String fileOldName = request.getParameter("ca_file");
			String fileOldRoot = request.getParameter("ca_sfile");
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			System.out.print(root_path);
			String attach_path = "\\resources\\images\\cafe\\cafe_detail\\";
			String fileOriginName = ""; 
			String fileMultiName = "";
			String fileMultiUploadName= "";
			
			UUID uuid = UUID.randomUUID();
			
			System.out.print(file.length);

			for(int i=0; i<file.length; i++) { 
				fileOriginName = file[i].getOriginalFilename(); 
				System.out.println("기존 파일명 : "+fileOriginName); 
				File f = new File(root_path + attach_path + uuid +"_"+ fileOriginName); 
				file[i].transferTo(f);
				if (fileOriginName != "") {
					if(i==0) { 
						fileMultiName += fileOriginName; 
						fileMultiUploadName += uuid +"_"+fileOriginName;
					} else { 
						fileMultiName += ","+fileOriginName; 
						fileMultiUploadName += "," + uuid +"_"+fileOriginName;
					} 
				}
			}
			
			String old_name = request.getParameter("old_name");
			String old[] = old_name.split(",");
			for (int i=0; i<old.length; i++) {
				File old_file = new File(root_path+attach_path+old[i]);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}

			CafeVO vo = new CafeVO();
			vo.setCa_file(fileOldName + fileMultiName);
			vo.setCa_sfile(fileOldRoot + fileMultiUploadName);
			vo.setCa_name(request.getParameter("ca_name"));
			vo.setCa_tag(request.getParameter("ca_tag"));
			vo.setCa_infor(request.getParameter("ca_infor"));
			vo.setCa_infor2(request.getParameter("ca_infor2"));
			vo.setCa_addr(request.getParameter("ca_addr"));
			vo.setCa_vpoint(request.getParameter("ca_vpoint"));
			vo.setCa_hpoint(request.getParameter("ca_hpoint"));
			vo.setCa_hp(request.getParameter("ca_hp"));
			vo.setCaid(request.getParameter("caid"));
			
			boolean result = adminService.getCafeUpdate(vo);
			
			mv.setViewName("redirect:/adcafe_content.do?caid=" + vo.getCaid());
			
			return mv;
		}
		//카페 삭제
		@ResponseBody
		@RequestMapping(value = "/adcafe_delete.do", method=RequestMethod.POST)
		public boolean adcafe_delete(HttpServletRequest request) {
			String caid = request.getParameter("caid");
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\cafe\\cafe_detail\\";
			
			CafeVO vo = adminService.getCafecontent(caid);
			
			String old_name = vo.getCa_sfile();
			String old[] = old_name.split(",");
			for (int i=0; i<old.length; i++) {
				File old_file = new File(root_path+attach_path+old[i]);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}
			
			boolean result = adminService.getCafeDelete(caid);
			
			return result;
		}	
		
		
	// 관리자 게시판
	@RequestMapping(value="/adboard.do",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView toboard(String pnum, String search, String search_text) {
		ModelAndView mv = new ModelAndView();
		
		int pageNumber = 1;
		 
		if(pnum != null) {
	  		pageNumber = Integer.parseInt(pnum);
	  	}
		
		int startnum = ((pageNumber-1)*10) +1;
		int endnum = pageNumber*10; 
		int pagenum = (pageNumber -1) * 10;
		int target = 0;
		ArrayList<CommunityVO> list = new ArrayList<CommunityVO>();
		if(search_text == null || search_text.equals("") || search_text.equals("null")) {
	  		list = adminService.getBoardList(startnum,endnum);
	  		target = adminService.BoardPage(pagenum);
	  	} else {
	  		list = adminService.getBoardList(startnum,endnum,search,search_text);
	  		target = adminService.BoardPage(pagenum,search,search_text);
	  	}
			
		int targetpage = 0;
		if(pageNumber != 1 ) {
			targetpage = (target-2) / 10 ;
			} else {
			targetpage = (target-1) / 10 ;
			}
		mv.setViewName("admin/adboard");
		mv.addObject("list", list);
		mv.addObject("targetpage", String.valueOf(targetpage));
		mv.addObject("pageNumber", String.valueOf(pageNumber));
		mv.addObject("search", search);
		mv.addObject("search_text", search_text);
		
		return mv;
	}
	
	//관리자 게시판 상세보기 
	@RequestMapping(value="/adboard_content.do",method= RequestMethod.GET)
	public ModelAndView adboard_content(String fid) {		
		ModelAndView mv = new ModelAndView();
		
		//게시물 내용
		CommunityVO vo = communityService.getFreeContent(fid);		
		//댓글 정보
		ArrayList<CommunityVO> list = communityService.getFreeComment(fid);		
		
		mv.addObject("vo", vo);
		mv.addObject("list", list);
		mv.setViewName("admin/adboard_content");
		
		return mv;
				
	}
	
	//관리자 게시글 삭제
	@ResponseBody
	@RequestMapping(value = "/admin_board_delete.do", method=RequestMethod.POST)
	public boolean admin_board_delete(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		
		String fid = request.getParameter("fid");
		
		String old_bsfile = communityService.getFileResult(fid);
		
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "\\resources\\upload\\";
		
		File old_file = new File(root_path+attach_path+old_bsfile);
		if ( old_file.exists()) {
			old_file.delete();
		}
		
		boolean result = communityService.getFreeBoardDelete(fid);
	
		
		return result;
	}
	
	// 관리자 공지사항
		@RequestMapping(value="/adnotice.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView tonotice(String pnum, String search, String search_text) {
			ModelAndView mv = new ModelAndView();
			
			int pageNumber = 1;
			 
			if(pnum != null) {
		  		pageNumber = Integer.parseInt(pnum);
		  	}
			
			int startnum = ((pageNumber-1)*10) +1;
			int endnum = pageNumber*10; 
			int pagenum = (pageNumber -1) * 10;
			int target = 0;
			ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
			if(search_text == null || search_text.equals("") || search_text.equals("null")) {
		  		list = adminService.getNoticeList(startnum,endnum);
		  		target = adminService.NoticePage(pagenum);
		  	} else {
		  		list = adminService.getNoticeList(startnum,endnum,search,search_text);
		  		target = adminService.NoticePage(pagenum,search,search_text);
		  	}
				
			int targetpage = 0;
			if(pageNumber != 1 ) {
				targetpage = (target-2) / 10 ;
				} else {
				targetpage = (target-1) / 10 ;
				}
			mv.setViewName("admin/adnotice");
			mv.addObject("list", list);
			mv.addObject("targetpage", String.valueOf(targetpage));
			mv.addObject("pageNumber", String.valueOf(pageNumber));
			mv.addObject("search", search);
			mv.addObject("search_text", search_text);
			
			return mv;
		}
		
		//관리자 공지사항 글쓰기
		@RequestMapping(value = "/adnotice_write.do", method=RequestMethod.GET)
		public String adnotice_write() {
			return "admin/adnotice_write";
		}
		
		//관리자 공지사항 글쓰기 DB
		@RequestMapping(value = "/adnotice_write_proc.do", method=RequestMethod.POST)
		public ModelAndView admin_notice_write_process(HttpServletRequest request, NoticeVO vo, HttpSession session) throws Exception {
			ModelAndView mv = new ModelAndView();
			System.out.print(vo.getNtitle());
			System.out.print(vo.getNcontent());
			//파일 존재
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\upload\\";
			
			UUID uuid = UUID.randomUUID();
			//rfname 중복방지 처리			
			System.out.println((vo.getFile1().getOriginalFilename()));
			System.out.println((uuid +"_"+vo.getFile1().getOriginalFilename()));	
			
			if (vo.getFile1().getOriginalFilename() != "") {
				//DB저장
				vo.setNfile(vo.getFile1().getOriginalFilename());
				vo.setNsfile(uuid+ "_"+vo.getFile1().getOriginalFilename());
			}
			
			boolean result = adminService.getNoticeWrite(vo);
			
			//DB저장 완료 후 폴더에 저장하기
			if (result) {
				System.out.println(root_path + attach_path + uuid +"_"+vo.getFile1().getOriginalFilename());
				
				if (vo.getFile1().getOriginalFilename() != "") {
					File file = new File(root_path + attach_path + uuid
							+"_"+vo.getFile1().getOriginalFilename()); vo.getFile1().transferTo(file);
				}
			}			
			
			mv.setViewName("redirect:/adnotice.do");
			return mv;
			
		}
		
		//공지사항 상세보기
		@RequestMapping(value = "/adnotice_content.do", method=RequestMethod.GET)
		public ModelAndView adnotice_content(String nid) {
			ModelAndView mv = new ModelAndView();
			
			NoticeVO vo = adminService.getNoticeContent(nid);
			mv.addObject("vo", vo);
			mv.setViewName("admin/adnotice_content");
			
			return mv;
		}
		
		//공지사항 수정하기
		@RequestMapping(value = "/adnotice_update.do", method=RequestMethod.GET)
		public ModelAndView adnotice_update(String nid) {
			ModelAndView mv = new ModelAndView();
			
			NoticeVO vo = adminService.getNoticeContent(nid);
			mv.addObject("vo", vo);
			mv.setViewName("admin/adnotice_update");
			
			return mv;
		}
		
		//공지사항 수정 DB
		@RequestMapping(value = "/adnotice_update_proc.do", method=RequestMethod.POST)
		public ModelAndView adnotice_update_proc(HttpServletRequest request, NoticeVO vo) throws Exception {
			ModelAndView mv = new ModelAndView();
			String nid=vo.getNid();
			boolean result = false;
			if (vo.getFile1().getOriginalFilename() != "") {
				//파일 존재
				String root_path = request.getSession().getServletContext().getRealPath("/");
				String attach_path = "\\resources\\upload\\";
				System.out.print(root_path);
				
				//rfname 중복방지 처리			
				UUID uuid = UUID.randomUUID();
				System.out.println((vo.getFile1().getOriginalFilename()));
				System.out.println((uuid +"_"+vo.getFile1().getOriginalFilename()));	
					
				//DB저장
				vo.setNfile(vo.getFile1().getOriginalFilename());
				vo.setNsfile(uuid+ "_"+vo.getFile1().getOriginalFilename());
		
				//BoardDAO dao = new BoardDAO();
				String old_bsfile = adminService.getOldFile(vo.getNid());
				result = adminService.getFileYesUpdate(vo);
				
				//DB저장 완료 후 폴더에 저장하기
				if (result) {
					File file = new File(root_path+attach_path+vo.getNsfile());
					vo.getFile1().transferTo(file);
					
					//기존 upload 폴더에 존재하는 파일 삭제
					File old_file = new File(root_path+attach_path+old_bsfile);
					if ( old_file.exists()) {
						old_file.delete();
					}
				}
				
			} else {
				//파일 미포함 업데이트
				result = adminService.getFileNoUpdate(vo);
			}	
			
			
			mv.setViewName("redirect:/adnotice_content.do?nid="+nid);
			return mv;
		
	}
		
	//관리자 공지사항 삭제
	@RequestMapping(value = "/adnotice_delete_proc.do", method=RequestMethod.GET)
	public ModelAndView free_board_delete(HttpServletRequest request, String nid) {
		
		ModelAndView mv = new ModelAndView();
		
		String old_bsfile = adminService.getOldFile(nid);
		
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "\\resources\\upload\\";
		
		File old_file = new File(root_path+attach_path+old_bsfile);
		if ( old_file.exists()) {
			old_file.delete();
		}
		
		boolean result = adminService.getNoticeDelete(nid);
		
		mv.setViewName("redirect:/adnotice.do");
		
		return mv;
	}
		
		
	// 관리자 요청
	@RequestMapping(value="/adrequest.do",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView torequest(String pnum, String search, String search_text) {
		ModelAndView mv = new ModelAndView();
		
		int pageNumber = 1;
		 
		if(pnum != null) {
	  		pageNumber = Integer.parseInt(pnum);
	  	}
		
		int startnum = ((pageNumber-1)*10) +1;
		int endnum = pageNumber*10; 
		int pagenum = (pageNumber -1) * 10;
		int target = 0;
		ArrayList<CommunityVO> list = new ArrayList<CommunityVO>();
		if(search_text == null || search_text.equals("") || search_text.equals("null")) {
	  		list = adminService.getRequestList(startnum,endnum);
	  		target = adminService.RequestPage(pagenum);
	  	} else {
	  		list = adminService.getRequestList(startnum,endnum,search,search_text);
	  		target = adminService.RequestPage(pagenum,search,search_text);
	  	}
			
		int targetpage = 0;
		if(pageNumber != 1 ) {
			targetpage = (target-2) / 10 ;
			} else {
			targetpage = (target-1) / 10 ;
			}
		mv.setViewName("admin/adrequest");
		mv.addObject("list", list);
		mv.addObject("targetpage", String.valueOf(targetpage));
		mv.addObject("pageNumber", String.valueOf(pageNumber));
		mv.addObject("search", search);
		mv.addObject("search_text", search_text);
		
		return mv;
	}
	
	//관리자 답변하기
	@RequestMapping (value = "/adrequest_re.do", method=RequestMethod.GET)
	public ModelAndView adrequest_re(String rid) {
		ModelAndView mv = new ModelAndView();
		
		CommunityVO vo = adminService.getRequestContent(rid);
		//답변내용 가져오기
		CommunityVO rvo = adminService.getRequestCommentResult(rid);
		
		mv.addObject("vo", vo);
		mv.addObject("rvo", rvo);
		mv.setViewName("admin/adrequest_re");
		return mv;
	}
	
	//관리자 답변하기
	@ResponseBody
	@RequestMapping(value="/adrequest_comment.do", method=RequestMethod.POST)
	public boolean adrequest_comment(HttpServletRequest request) {
		CommunityVO vo = new CommunityVO();
		vo.setRid(request.getParameter("rid"));
		vo.setRcontent(request.getParameter("textarea"));
		
		boolean result = adminService.getRequestComment(vo);
		
		return result;
		
	}
	
	//관리자 요청삭제하기
	@ResponseBody
	@RequestMapping(value="/admin_request_delete.do", method=RequestMethod.POST)
	public boolean admin_request_delete(HttpServletRequest request) {
		boolean result = adminService.getRequestDelete(request.getParameter("rid"));
		
		String old_bsfile = adminService.getOldFile(request.getParameter("rid"));
		
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "\\resources\\upload\\";
		
		File old_file = new File(root_path+attach_path+old_bsfile);
		if ( old_file.exists()) {
			old_file.delete();
		}
		
		return result;
	}
	
	// 스토어 스토어 스토어
	
	// 관리자 스토어창
	@RequestMapping(value="/adstore.do",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView tostore(String pnum, String search, String search_text) {
		ModelAndView mv = new ModelAndView();
		
		int pageNumber = 1;
		 
		if(pnum != null) {
	  		pageNumber = Integer.parseInt(pnum);
	  	}
		int startnum = ((pageNumber-1)*10) +1;
		int endnum = pageNumber*10; 
		int pagenum = (pageNumber -1) * 10;
		int target = 0;
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		if(search_text == null || search_text.equals("") || search_text.equals("null")) {
	  		list = adminService.getStoreList(startnum, endnum);
	  		target = adminService.StorePage(pagenum);
	  	} else {
	  		list = adminService.getStoreList(startnum,endnum,search,search_text);
	  		target = adminService.StorePage(pagenum,search,search_text);
	  	}
			
		int targetpage = 0;
		if(pageNumber != 1 ) {
			targetpage = (target-2) / 10 ;
			} else {
			targetpage = (target-1) / 10 ;
			}
		mv.setViewName("admin/adstore");
		mv.addObject("list", list);
		mv.addObject("targetpage", String.valueOf(targetpage));
		mv.addObject("pageNumber", String.valueOf(pageNumber));
		mv.addObject("search", search);
		mv.addObject("search_text", search_text);
		
		return mv;
	}
	
	//스토어 상세보기
	@RequestMapping(value = "/adstore_content.do", method=RequestMethod.GET)
	public ModelAndView adstore_content(String sid) {
		ModelAndView mv = new ModelAndView();
		
		StoreVO vo = adminService.getStoreContent(sid);
		
		mv.setViewName("admin/adstore_content");
		mv.addObject("vo", vo);
		
		return mv;
	}
	
	//스토어 수정 화면
	@RequestMapping(value = "/adstore_update.do", method=RequestMethod.GET)
	public ModelAndView adstore_update(String sid) {
		ModelAndView mv = new ModelAndView();
		
		StoreVO vo = adminService.getStoreContent(sid);
		
		mv.addObject("vo", vo);
		mv.setViewName("admin/adstore_update");
		
		return mv;
	}
	
	//스토어 수정 처리
	@RequestMapping(value = "/adstore_update_proc.do", method=RequestMethod.POST)
	public ModelAndView adstore_update_proc(HttpServletRequest request, StoreVO vo) throws Exception {
		ModelAndView mv = new ModelAndView();
		String sid = vo.getSid();
		
		boolean result = false;
		
		if (vo.getSfile1().getOriginalFilename() != "") {
			//파일 존재
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\images\\store\\store_detail\\";
			//System.out.print(root_path);
			
			//rfname 중복방지 처리			
			UUID uuid = UUID.randomUUID();
			//System.out.println((vo.getSfile1().getOriginalFilename()));
			//System.out.println((uuid +"_"+vo.getSfile1().getOriginalFilename()));	
				
			//DB저장
			vo.setS_image(vo.getSfile1().getOriginalFilename());
			vo.setS_sfile(uuid + "_" + vo.getSfile1().getOriginalFilename());
			
			vo.setS_content(vo.getSfile2().getOriginalFilename());
			vo.setS_ssfile(uuid + "_" + vo.getSfile2().getOriginalFilename());
	
			//BoardDAO dao = new BoardDAO();
			//String old_bsfile = adminService.getOldFile(vo.getSid());
			String old_sfile1 = adminService.getStoreOldFile(vo.getSid());
			
			result = adminService.getStoreUpdateFile(vo);
			
			//DB저장 완료 후 폴더에 저장하기
			if (result) {
				File file1 = new File(root_path + attach_path + vo.getS_sfile());
				vo.getSfile1().transferTo(file1);
				
				File file2 = new File(root_path + attach_path + vo.getS_ssfile());
				vo.getSfile2().transferTo(file2);
				
				//기존 upload 폴더에 존재하는 파일 삭제
				File old_file = new File(root_path + attach_path + old_sfile1);
				if ( old_file.exists()) {
					old_file.delete();
				}
			}
			
		} else {
			//파일 미포함 업데이트
			result = adminService.getStoreUpdateNoFile(vo);
		}
		mv.setViewName("redirect:/adstore_content.do?sid=" + sid);
		return mv;
	}
	
	

	//스토어 상품등록 화면
	@RequestMapping(value = "/adstore_write.do", method = RequestMethod.GET)
	public String adstore_write() {
		return "admin/adstore_write";
	}
	

	//store_write_proc.do : 스토어 상품 등록 처리
	@RequestMapping(value = "/adstore_write_proc.do", method = RequestMethod.POST)
	public ModelAndView adstore_write_proc(StoreVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		String root_path = "";
		String attach_path = "";
		
		if(vo.getSfile1().getSize() != 0) {
			// 1. 파일 저장 위치
			root_path = request.getSession().getServletContext().getRealPath("/");
			attach_path = "\\resources\\images\\store\\store_detail\\";
			
			// 2. 파일 이름 --> vo에 저장
			//rfname 중복방지 처리
			UUID uuid = UUID.randomUUID();
			
			//DB저장
			vo.setS_image(vo.getSfile1().getOriginalFilename());
			vo.setS_sfile(uuid + "_" + vo.getSfile1().getOriginalFilename());
			
			vo.setS_content(vo.getSfile2().getOriginalFilename());
			vo.setS_ssfile(uuid + "_" + vo.getSfile2().getOriginalFilename());
		
		}
			
		// 3. DB연동
		boolean result = adminService.getStoreInsertResult(vo);
		
		if(result) {
			
			// 4. DB 연동 성공 --> upload 폴더에 저장			//DB저장 완료 후 폴더에 저장하기
			File file = new File(root_path + attach_path + vo.getS_sfile());
			vo.getSfile1().transferTo(file);
			
			File file2 = new File(root_path + attach_path + vo.getS_ssfile());
			vo.getSfile2().transferTo(file2);
			
			mv.setViewName("redirect:/adstore.do");
		}
			
		return mv;
	}
	
	
	//관리자 상품 삭제
	@RequestMapping(value = "/adstore_delete_proc.do", method=RequestMethod.GET)
	public ModelAndView adstore_delete_proc(HttpServletRequest request, String sid) {
		ModelAndView mv = new ModelAndView();
		
		//String old_bsfile = adminService.getOldFile(nid);
		String old_sfile = adminService.getStoreOldFile(sid);
		
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "\\resources\\images\\store\\store_detail\\";
		
		File old_file = new File(root_path + attach_path + old_sfile);
		
		if ( old_file.exists()) {
			old_file.delete();
		}
		
		//boolean result = adminService.getNoticeDelete(nid);
		boolean result = adminService.getStoreDelete(sid);
		
		if(result) {
			mv.setViewName("redirect:/adstore.do");
		}
		
		return mv;
	}
	
	//숙소 숙소등록 화면
		@RequestMapping(value = "/adhouse_write.do", method = RequestMethod.GET)
		public String adhouse_write() {
			return "admin/adhouse_write";
		}
		
		//숙소 등록 DB적용
		@RequestMapping(value="/adhouse_write_proc.do", method = RequestMethod.POST)
		public ModelAndView adhouse_write_proc(HouseVO vo, HttpServletRequest request) throws Exception {
			ModelAndView mv = new ModelAndView();			
			
			String root_path = "";
			String attach_path = "";
			
			if(vo.getHfile1().getSize() != 0) {
				// 1. 파일 저장 위치
				root_path = request.getSession().getServletContext().getRealPath("/");
				attach_path = "\\resources\\images\\house\\";
				
				// 2. 파일 이름 --> vo에 저장
				//rfname 중복방지 처리
				UUID uuid = UUID.randomUUID();
				vo.setH_img(vo.getHfile1().getOriginalFilename());
				vo.setH_file(uuid + "_" + vo.getHfile1().getOriginalFilename());
				System.out.println("himg--->" + vo.getH_img());
				System.out.println("hfile--->" + vo.getH_file());
					
			}
				
			// 3. DB연동
			boolean result = adminService.getHouseInsertResult(vo);
			
			if(result) {
				System.out.println("path=" + root_path + attach_path + vo.getH_file());
				// 4. DB 연동 성공 --> upload 폴더에 저장			//DB저장 완료 후 폴더에 저장하기
				File file = new File(root_path + attach_path + vo.getH_file());
				vo.getHfile1().transferTo(file);
				
				mv.setViewName("redirect:/adhouse.do");
			}
				
			return mv;
		}
		
		//숙소 수정 화면
		@RequestMapping(value = "/adhouse_update.do", method=RequestMethod.GET)
		public ModelAndView adhouse_update(String hid) {
			ModelAndView mv = new ModelAndView();
			
			HouseVO vo = adminService.getHouseContent(hid);
			
			mv.addObject("vo", vo);
			mv.addObject("hid", hid);
			mv.setViewName("admin/adhouse_update");
			
			return mv;
		}
		
		//숙소 수정 처리
		@RequestMapping(value = "/adhouse_update_proc.do", method=RequestMethod.POST)
		public ModelAndView adhouse_update_proc(HouseVO vo, HttpServletRequest request) throws Exception {
			ModelAndView mv = new ModelAndView();
			String hid = vo.getHid();
			String root_path;
			String attach_path;
			
			System.out.println(hid);
			System.out.println(vo.getH_name());
			
			if (vo.getHfile1().getOriginalFilename() != "") {
				//파일 존재
				root_path = request.getSession().getServletContext().getRealPath("/");
				attach_path = "\\resources\\images\\house\\";
				System.out.println(root_path);
				
				//rfname 중복방지 처리			
				UUID uuid = UUID.randomUUID();
				System.out.println((vo.getHfile1().getOriginalFilename()));
				System.out.println((uuid +"_"+vo.getHfile1().getOriginalFilename()));	
					
				//DB저장
				vo.setH_img(vo.getHfile1().getOriginalFilename());
				vo.setH_file(uuid + "_" + vo.getHfile1().getOriginalFilename());
				
				String old_hfile1 = adminService.getHouseOldFile(vo.getHid());
				boolean result = adminService.getHouseUpdateFile(vo);
				
				
				//DB저장 완료 후 폴더에 저장하기
				if (result) {
					File file1 = new File(root_path + attach_path + vo.getH_file());
					vo.getHfile1().transferTo(file1);
					
					//기존 upload 폴더에 존재하는 파일 삭제
					File old_file = new File(root_path + attach_path + old_hfile1);
					if (old_file.exists()) {
						old_file.delete();
					}
				}
				
			} else {
				//파일 미포함 업데이트
				boolean result = adminService.getHouseUpdateNoFile(vo);
			}
			mv.setViewName("redirect:/adhouse_content.do?hid=" + hid);
			return mv;
		}
	
		//관리자 상품 삭제
		@RequestMapping(value = "/adhouse_delete_proc.do", method=RequestMethod.GET)
		public ModelAndView adhouse_delete_proc(HttpServletRequest request, String hid) {
			ModelAndView mv = new ModelAndView();
			
			//String old_bsfile = adminService.getOldFile(nid);
			String old_sfile = adminService.getHouseOldFile(hid);
			
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\\\resources\\\\images\\\\house\\\\";
			System.out.println("올드 패스=" + root_path + attach_path + old_sfile);
			File old_file = new File(root_path + attach_path + old_sfile);
			
			if ( old_file.exists()) {
				old_file.delete();
			}
			
			boolean result = adminService.getHouseDelete(hid);
			
			if(result) {
				mv.setViewName("redirect:/adhouse.do");
			}
			
			return mv;
		}
	
	
	
	
	
	
	
	
}


