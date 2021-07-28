package com.myspring.myjeju;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.myjeju.service.AdminService;
import com.myjeju.vo.DateVO;
import com.myjeju.vo.HDetailVO;
import com.myjeju.vo.RoomVO;
import com.myjeju.vo.RoomdeVO;

@Controller
public class Adminreservation {
	
	//년도 월별 날짜수 계산
	public static int monthDay(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				return 29;
			} else {
				return 28;
			}
		}
	}
	//달력 날짜 계산
	public static ArrayList<DateVO> calprint(int year, int month, int maxrow) {

		int sum = 0;

		for (int i = 1583; i < year; i++) {
			if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
				// 윤년이라면
				sum += 2;
			} else {
				// 평년이라면
				sum += 1;
			}
		}
		int first = (sum + 6) % 7; // 입력한 year의 1월 1일의 요일
		
		for (int j = 1; j < month; j++) {
			first += monthDay(year, j) % 7;
		}
		
		int day = first % 7; // 입력한 month의 1일의 요일
		
		
		ArrayList<DateVO> calv = new ArrayList<DateVO>();
		
		
		
		int num = 1; // month의 일 표시
		int max = monthDay(year, month); // 해당 month가 가지는 최대 일 수
		int pre_max = monthDay(year, month-1);
		int pre_day = pre_max-(day-1);
		int next_day = 1;
		
		boolean start = false;

		loop: for (int row = 0; row < maxrow; row++) {
			for (int column = 0; column <= 6; column++) {
				DateVO date = new DateVO();
				if (row == 0 && !start) {
					// 달력의 첫 행
					if (column == day) {
						// 시작 일에 도달하면
						start = true;
					} else {
						// 시작 일에 도달 전에는 공백
						date.setDay(pre_day);
						date.setMonth(month-1);
						
						calv.add(date);
						pre_day++;
						continue;
					}
				}
				if(num <= max) {
					date.setDay(num);
					date.setMonth(month);
					
					calv.add(date);
					num++;
				}else {
					date.setDay(next_day);
					date.setMonth(month+1);
					
					calv.add(date);
					next_day++;
				}
				
				if (num > max && column==6) {
					// 최대 일 수 + 마지막칸에 도달하면 break loop
					break loop;
				}
			}
		}
		return calv;
	}
	//날짜와 날짜 빼기 펑션
	public static int comparedate(String curdate, String nextdate) {
		int cur_year = Integer.parseInt(curdate.substring(0,4));
		int next_year = Integer.parseInt(nextdate.substring(0,4));
		
		int cur_month = Integer.parseInt(curdate.substring(5,7));
		int naxt_month = Integer.parseInt(nextdate.substring(5,7));
		
		int cur_day = Integer.parseInt(curdate.substring(8,10));
		int next_day = Integer.parseInt(nextdate.substring(8,10));
		
		int month = naxt_month - cur_month;
		if(month < 0) {
			month = 1;
		}
		int day = next_day - cur_day;
		
		int value = (month*monthDay(cur_year,cur_month)) + day;
		
		return value;
	}
	//날짜 일수 빼기 펑선
	public static String comparedateminus(String curdate, int minus) {
		int cur_year = Integer.parseInt(curdate.substring(0,4));
		
		int cur_month = Integer.parseInt(curdate.substring(5,7));
		
		int cur_day = Integer.parseInt(curdate.substring(8,10));
		
		int day = cur_day - minus;
		
		if(day <= 0) {
			cur_month -= 1;
			if(cur_month == 12) {
				day += monthDay(cur_year-1,cur_month);
			}else {
				day += monthDay(cur_year,cur_month);
			}
		}
		
		String month = String.valueOf(cur_month);
		if(month.length()==1) {
			month = "0" + month;
		}
		String day1 = String.valueOf(day);
		if(day1.length()==1) {
			day1 = "0" + day1;
		}
		
		
		String value = String.valueOf(cur_year) + "-" + month + "-" + day1 + " 00:00:00"; 
		return value;
	}
	//날짜 일수 더하기 펑션
	public static int comparedateplus(String curdate, int max) {
		
		int cur_year = Integer.parseInt(curdate.substring(0,4));
		
		int cur_month = Integer.parseInt(curdate.substring(5,7));
		
		int cur_day = Integer.parseInt(curdate.substring(8,10));
		
		int day = cur_day+max;
		int month = cur_month;
		if(day>monthDay(cur_year,cur_month)){
			day -= monthDay(cur_year,cur_month);
			month += 1;
			if(month>12) {
				month -=12;
			}
		}
		
		int value = (month*100)+day;
		return value;
	}
	
	
	
	@Autowired
	private AdminService adminService;
	

	//객실 리스트
	@RequestMapping(value="/adhouse_de.do",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView tomember(String hid) {
		ModelAndView mv = new ModelAndView();
		ArrayList<HDetailVO> list = adminService.gethousede(hid);
		
		mv.setViewName("admin/adhouse_de");
		mv.addObject("hid", hid);
		mv.addObject("list", list);
		return mv;
	}
	//객실 상세
	@RequestMapping(value="/adhouse_de_content.do",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView tode(String hdid) {
		ModelAndView mv = new ModelAndView();
		HDetailVO vo = adminService.gethousedecontent(hdid);		
		
		String img[] = vo.getHd_file().split(",");		
		
		mv.setViewName("admin/adhouse_de_content");
		mv.addObject("vo", vo);
		mv.addObject("img", img);
		mv.addObject("hid", vo.getHid());
		return mv;
	}
	//객실 룸 리스트
	@RequestMapping(value="/adhouse_de_room.do",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView toroom(String hdid,String result) {
		ModelAndView mv = new ModelAndView();
		ArrayList<RoomdeVO> list = adminService.gethousederoom(hdid);
		int result1 = 0;
		if(result != null) {
			result1 = Integer.parseInt(result);
		}
		mv.setViewName("admin/adhouse_de_room");
		mv.addObject("list", list);
		mv.addObject("hdid", hdid);
		mv.addObject("result", result1);
		return mv;
	}
	//객실 룸 일괄예약
	@RequestMapping(value="/adhouse_res_all.do",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView resroomall(String hdid, String year, String month) {
		ModelAndView mv = new ModelAndView();
		ArrayList<RoomdeVO> list = adminService.gethousederoom(hdid);
		int sum = 0;
		int maxrow = 0;

		int yeari = Integer.parseInt(year);
		int monthi = Integer.parseInt(month);
			
		for (int i = 1583; i < yeari; i++) {
			if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
				// 윤년이라면
				sum += 2;
			} else {
				// 평년이라면
				sum += 1;
			}
		}
		int first = (sum + 6) % 7; // 입력한 year의 1월 1일의 요일
		
		int day = first % 7;
		int maxday = day + monthDay(yeari, monthi);
		if(maxday >= 36) {
					maxrow = 6;
				} else {
					maxrow = 5;
				}
		
		ArrayList<DateVO> value = calprint(yeari,monthi,maxrow);
		String months = "";
		if(month.length()==1) {
			months = "0" + month;
		}else {
			months = month;
		}
		String years = year.substring(2,4);
		String checkmonth = years +"/"+ months;
		ArrayList<RoomdeVO> list1 = adminService.gethousederoom(hdid);
		
		int checksize = 0;
		for(int i =0;i<list1.size();i++) {
		ArrayList<RoomVO> room = adminService.getmonthcheck(checkmonth , list1.get(i).getRoomid());
		checksize += room.size();
		}
		
		int result = 0;
		if(checksize == 0) {
			ArrayList<RoomVO> monthdate = new ArrayList<RoomVO>(); 
			for(int i=0;i<value.size();i++) {	
				for(int j =0; j<list.size();j++) {
					RoomVO vo = new RoomVO();
					if(value.get(i).getMonth() == monthi) {
						String dayi = String.valueOf(value.get(i).getDay());
						String days = "";
						if(dayi.length() == 1) {
							days = "0"+dayi;
						} else {
							days = dayi;
						}
						String date = year + "/" + months + "/" +days;
						vo.setRoomid(list.get(j).getRoomid());
						vo.setRdate(date);
						vo.setAvailable("0");
						monthdate.add(vo);
					}
				}
			}
			for(int i=0;i<monthdate.size();i++) {
				boolean result1 = adminService.insertres(monthdate.get(i));
			}
			result = 1;
		} else {
			result = 2;
		}
		
		mv.setViewName("admin/adhouse_de_room");
		mv.addObject("list", list);
		mv.addObject("result", result);
		mv.addObject("hdid", hdid);
		return mv;
	}
	//객실 개별 룸 일괄예약
		@RequestMapping(value="/adhouse_res_each.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView resroomeach(String hdid, String year, String month,String roomid) {
			ModelAndView mv = new ModelAndView();
			ArrayList<RoomdeVO> list = adminService.gethousederoom(hdid);
			int sum = 0;
			int maxrow = 0;

			int yeari = Integer.parseInt(year);
			int monthi = Integer.parseInt(month);
				
			for (int i = 1583; i < yeari; i++) {
				if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
					// 윤년이라면
					sum += 2;
				} else {
					// 평년이라면
					sum += 1;
				}
			}
			int first = (sum + 6) % 7; // 입력한 year의 1월 1일의 요일
			
			int day = first % 7;
			int maxday = day + monthDay(yeari, monthi);
			if(maxday >= 36) {
						maxrow = 6;
					} else {
						maxrow = 5;
					}
			
			ArrayList<DateVO> value = calprint(yeari,monthi,maxrow);
			String months = "";
			if(month.length()==1) {
				months = "0" + month;
			}
			String years = year.substring(3,4);
			String checkmonth = years +"/"+ months;
			
			
			ArrayList<RoomVO> room = adminService.getmonthcheck(checkmonth , roomid);
			
			int result = 0;
			if(room.size() == 0) {
				ArrayList<RoomVO> monthdate = new ArrayList<RoomVO>(); 
				for(int i=0;i<value.size();i++) {	
						RoomVO vo = new RoomVO();
						if(value.get(i).getMonth() == monthi) {
							String dayi = String.valueOf(value.get(i).getDay());
							String days = "";
							if(dayi.length() == 1) {
								days = "0"+dayi;
							} else {
								days = dayi;
							}
							String date = year + "/" + months + "/" +days;
							vo.setRoomid(roomid);
							vo.setRdate(date);
							vo.setAvailable("0");
							monthdate.add(vo);
						}
				}
				for(int i=0;i<monthdate.size();i++) {
					boolean result1 = adminService.insertres(monthdate.get(i));
				}
				result = 1;
			} else {
				result = 2;
			}
			
			mv.setViewName("admin/adhouse_de_room");
			mv.addObject("list", list);
			mv.addObject("result", result);
			mv.addObject("hdid", hdid);
			return mv;
		}
		
		//룸 write
		@RequestMapping(value="/adhouse_de_room_write.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView toroomwrite(String hdid) {
			ModelAndView mv = new ModelAndView();
			
			mv.setViewName("admin/adhouse_de_room_write");
			mv.addObject("hdid", hdid);
			return mv;
		}
		//룸 DB write
		@RequestMapping(value="/adhouse_de_room_write_proc.do",method= {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView toroomwritedb(String hdid,String room_name) {
			ModelAndView mv = new ModelAndView();
			boolean result = adminService.uploadroom(hdid,room_name);
			mv.setViewName("redirect:/adhouse_de_room.do");
			mv.addObject("hdid", hdid);
			return mv;
		}
		// 룸 DB delete 
		@RequestMapping(value="/adhouse_de_room_delete_proc.do",method=RequestMethod.GET)
		public ModelAndView toroomdeletedb(String hdid,String roomid) {
			ModelAndView mv = new ModelAndView();
			boolean result = adminService.deleteroom(roomid);
			int result1 = 0;
			if(result) {
				result1 = 3;
			}else {
				result1 = 4;
			}
			mv.setView(new RedirectView("adhouse_de_room.do?hdid="+hdid));
			mv.addObject("result", result1);
			return mv;
		}
		

}


