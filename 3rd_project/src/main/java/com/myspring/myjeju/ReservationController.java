package com.myspring.myjeju;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.myjeju.service.AdminService;
import com.myjeju.service.ReservationService;
import com.myjeju.vo.DateVO;
import com.myjeju.vo.HDetailVO;
import com.myjeju.vo.HouseVO;
import com.myjeju.vo.MReservationVO;
import com.myjeju.vo.MemberVO;
import com.myjeju.vo.RoomImgVO;
import com.myjeju.vo.RoomVO;
import com.myjeju.vo.RoomdeVO;

import util.Code;
import util.Gmail;

@Controller
public class ReservationController {
	
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
		
		int montha = month-1;
		int preyeara = year;
		if(montha == 0) {
			montha = montha + 12;
			preyeara -= 1;
		}
		int pre_max = monthDay(preyeara, montha);
		int pre_day = pre_max-(day-1);
		int next_day = 1;
		
		boolean start = false;

		loop: for (int row = 0; row < maxrow; row++) {
			for (int column = 0; column <= 6; column++) {
				if (row == 0 && !start) {
					// 달력의 첫 행
					if (column == day) {
						// 시작 일에 도달하면
						start = true;
					} else {
						// 시작 일에 도달 전에는 공백
						DateVO date = new DateVO();
						date.setDay(pre_day);
						
						int setmonth = month-1;
						int syear = year;
						if(setmonth <= 0) {
							setmonth +=12;
							syear = year -1;
						}else if(setmonth >12) {
							setmonth -=12;
							syear = year + 1;
						}
						date.setMonth(setmonth);
						date.setYear(syear);
						calv.add(date);
						pre_day++;
						continue;
					}
				}
				if(num <= max) {
					DateVO date = new DateVO();
					date.setDay(num);
					date.setMonth(month);
					date.setYear(year);
					calv.add(date);
					num++;
				}else {
					DateVO date = new DateVO();
					date.setDay(next_day);
					int setmonth1 = month+1;
					int syear1 = year;
					if(setmonth1 <= 0) {
						setmonth1 +=12;
						syear1 = year - 1;
					}else if(setmonth1 >12) {
						setmonth1 -=12;
						syear1 = year + 1;
					}
					date.setMonth(setmonth1);
					date.setYear(syear1);
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
	public static ArrayList<DateVO> calprintforderes(int year, int month, int maxrow) {
		
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
		
		int montha = month-1;
		int preyeara = year;
		if(montha == 0) {
			montha = montha + 12;
			preyeara -= 1;
		}
		int pre_max = monthDay(preyeara, montha);
		int pre_day = pre_max-(day-1);
		int next_day = 1;
		
		boolean start = false;
		
		loop: for (int row = 0; row < maxrow; row++) {
			for (int column = 0; column <= 6; column++) {
				if (row == 0 && !start) {
					// 달력의 첫 행
					if (column == day) {
						// 시작 일에 도달하면
						start = true;
					} else {
						// 시작 일에 도달 전에는 공백
						DateVO date = new DateVO();
						date.setDay(pre_day);
						
						int setmonth = month-1;
						int syear = year;
						if(setmonth <= 0) {
							setmonth +=12;
							syear = year -1;
						}else if(setmonth >12) {
							setmonth -=12;
							syear = year + 1;
						}
						date.setMonth(setmonth);
						date.setYear(syear);
						calv.add(date);
						pre_day++;
						continue;
					}
				}
				if(num <= max) {
					DateVO date = new DateVO();
					date.setDay(num);
					date.setMonth(month);
					date.setYear(year);
					calv.add(date);
					num++;
				}else {
					DateVO date = new DateVO();
					date.setDay(next_day);
					int setmonth1 = month+1;
					int syear1 = year;
					if(setmonth1 <= 0) {
						setmonth1 +=12;
						syear1 = year - 1;
					}else if(setmonth1 >12) {
						setmonth1 -=12;
						syear1 = year + 1;
					}
					date.setMonth(setmonth1);
					date.setYear(syear1);
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
	private ReservationService ReservationService;
	@Autowired
	private AdminService adminService;	
	/**
	 * main.do : 시작페이지
	 */
	@RequestMapping(value="/calendar.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView calendar(String preyear, String premonth, String hid, String hdid) {
		
		String currentname = ReservationService.getCurrentName(hid);
		
		ModelAndView mv = new ModelAndView();
		Calendar cal = Calendar.getInstance();
		
		int today = (cal.get(Calendar.MONTH)+1)*100 + cal.get(Calendar.DAY_OF_MONTH);
		int toyear = cal.get(Calendar.YEAR); 
		int year = 0;
		int month = 0;
		if(preyear == null && premonth == null) {
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}else {
			year = Integer.parseInt(preyear);
			month = Integer.parseInt(premonth);
		}
		
		if(month == 13) {
			year += 1;
			month -= 12;
		}
		if(month == 0) {
			year -=1;
			month += 12;
		}
		
		
		int maxrow = 0;
		int maxday = 0;
		
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
		
		int day = first % 7;

		maxday = day + monthDay(year, month);
		if(maxday >= 36) {
					maxrow = 6;
				} else {
					maxrow = 5;
				}
		ArrayList<DateVO> value = calprint(year,month,maxrow);
		int presmonth = value.get(0).getMonth();
		int syear = value.get(0).getYear();
		if(presmonth>12) {
			presmonth -= 12;
			syear = year + 1;
		}else if(presmonth<=0) {
			presmonth += 12;
			syear = year - 1;
		}
		String sMonth = String.valueOf(presmonth);
		if(sMonth.length() == 1) {
			sMonth = "0"+ sMonth;
		}
		String sDay = String.valueOf(value.get(0).getDay());
		if(sDay.length() == 1) {
			sDay = "0"+ sDay;
		}
		
		String start = syear + sMonth + sDay;
		
		int preemonth = value.get(value.size()-1).getMonth();
		int eyear = value.get(value.size()-1).getYear();
		if(preemonth>12) {
			preemonth -= 12;
			eyear += 1;
		}else if(preemonth<=0) {
			preemonth += 12;
			eyear -= 1;
			
		}
		String eMonth = String.valueOf(preemonth);
		if(eMonth.length() == 1) {
			eMonth = "0"+ eMonth;
		}
		
		String eDay = String.valueOf(value.get(value.size()-1).getDay());
		if(eDay.length() == 1) {
			eDay = "0"+ eDay;
		}
		
		String end = eyear + eMonth + eDay;
		
		ArrayList<RoomVO> searchroom = ReservationService.searchroom(start,end,hdid);
		
		String[] availdate = new String[searchroom.size()];		
		for(int i=0; i<searchroom.size(); i++) {
			availdate[i] = searchroom.get(i).getRdate();
		}
		
		
		ArrayList<String> arrayList = new ArrayList<String>();
		for(String item : availdate){
            if(!arrayList.contains(item))
                arrayList.add(item);
        }
		ArrayList<String> conver = new ArrayList<String>();
		for(int i =0; i<arrayList.size();i++) {
			String[] a = arrayList.get(i).split("-");
			String aa = a[0] + a[1] + a[2];
			conver.add(aa.substring(0,8));
		}
		ArrayList<String> availlast1 = new ArrayList<String>();
		
		for(int i = 0;i<value.size();i++) {
			int smonth2 = value.get(i).getMonth();
			int syear1 = value.get(i).getYear();
			if(smonth2<=0) {
				smonth2 +=12;
				syear1 -=1;
			}else if (smonth2>12) {
				smonth2 -=12;
				syear1 +=1;
			}
			
			String sMonth1 = String.valueOf(smonth2);
			if(sMonth1.length() == 1) {
				sMonth1 = "0"+ sMonth1;
			}
			String sDay1 = String.valueOf(value.get(i).getDay());
			if(sDay1.length() == 1) {
				sDay1 = "0"+ sDay1;
			}
			String start1 = syear1 + sMonth1 + sDay1;
			availlast1.add(start1);
		}
		
		
	
		
		ArrayList<Integer> availlast = new ArrayList<Integer>();
		for(int i = 0;i<value.size();i++) {
			boolean check = false;
			for(int j = 0 ;j<conver.size();j++) {
				if(availlast1.get(i).equals(conver.get(j))) {
					availlast.add(0);
					check = true;
				}
			}
			if(!check) {
				availlast.add(-1);
			}
		}
		
		mv.setViewName("reservation/calendar");
		mv.addObject("calvalue",value);
		mv.addObject("availlast",availlast);
		mv.addObject("hdid",hdid);
		mv.addObject("hid",hid);
		mv.addObject("maxrow",maxrow);
		mv.addObject("year",year);
		mv.addObject("month",month);
		mv.addObject("today",today);
		mv.addObject("toyear",toyear);
		mv.addObject("currentname",currentname);
		return mv;
	}
	
	@RequestMapping(value="/availcheck", method=RequestMethod.POST)
	@ResponseBody
	public String availcheck(int startvalue, int year, int month ,String hdid) {
		String val1 = String.valueOf(startvalue);
		String b = "";
		if(val1.length()==7) {
			StringBuffer a = new StringBuffer();
			a.append(val1);
			a.insert(6, "0");
			b = a.toString();
			startvalue = Integer.parseInt(b);
		}
		int start = startvalue;
		int end = 0;
		int monthday = monthDay(year,month);
		
		
		
		String compareval = String.valueOf(startvalue);
		String compareval1 = compareval.substring(6,8);
		int compare = Integer.parseInt(compareval1);
		
		if(compare+6 > monthday) {
			end = ((startvalue+6) - monthday) +100;
			if(month==12) {
				end = end - 1200;
				end = end + 10000;
			}
		}else {
			end = startvalue+6;
		}
		ArrayList<RoomVO> searchroom = ReservationService.searchroom(String.valueOf(start),String.valueOf(end),hdid);
		ArrayList<String> roomname = new ArrayList<String>(); 
		for(int i=0;i<searchroom.size();i++) {
			roomname.add(searchroom.get(i).getRoomid());
		}
		ArrayList<String> arrayList = new ArrayList<String>();
		for(String name : roomname){
	            if(!arrayList.contains(name))
	                arrayList.add(name);
	    }
		
		ArrayList<Integer> sequenceday = new ArrayList<Integer>();
		ArrayList<String> sequenceroom = new ArrayList<String>();
		StringBuffer f_compare = new StringBuffer(); 
		f_compare.append(String.valueOf(startvalue));
		f_compare.insert(4, "-");	
		f_compare.insert(7, "-");	
		String ff_compare = f_compare.toString();
		
		
		
		for(int i = 0;i<arrayList.size();i++) {
			int val = 0;
			for(int j = 0; j<searchroom.size(); j++) {
				if(arrayList.get(i).equals(searchroom.get(j).getRoomid())){
					if(comparedate(ff_compare, searchroom.get(j).getRdate())>1) {
						j = searchroom.size();
					}else {
						val +=1;
						ff_compare = searchroom.get(j).getRdate();
					}
				}
			}
			sequenceday.add(val);
			sequenceroom.add(arrayList.get(i));
			ff_compare = f_compare.toString();
		}
		
		int max=Collections.max(sequenceday);
		String targetroom = "";
		
		for(int i = 0;i<sequenceroom.size();i++) {
			if(sequenceday.get(i) == max) {
				targetroom = sequenceroom.get(i);
				i = sequenceroom.size();
			}
		}
		int curday = comparedateplus(f_compare.toString(),1);
		int targetday = comparedateplus(f_compare.toString(),max);
		int curday2 = comparedateplus(f_compare.toString(),0);
		int targetday2 = comparedateplus(f_compare.toString(),max+1);
		String maxs=curday+"/"+targetday+"/"+curday2+"/"+targetday2+"/" + targetroom;
		return maxs;
	}
	
	
	

	@RequestMapping(value="/reservationDetail.do", method=RequestMethod.POST)
	public ModelAndView reservationDetail(String hid, String hdid, String targetroom, String preday,String presday,String preyear,String roomid) {
		ModelAndView mv = new ModelAndView();
		
		HouseVO house = ReservationService.gethouse(hid);
		HDetailVO detail = ReservationService.getdetail(hdid);
		ArrayList<RoomImgVO> himg = ReservationService.gethimg(hdid);
		String f_value = preday;
		String s_value = presday;
		String year = preyear;
		
		String[] f_array = f_value.split("/");
		String[] s_array = s_value.split("/");
		
		String f_month = "";
		String f_day = "";
		
		if(f_array[0].length()==1){
			f_month = "0" + f_array[0];
		}else{
			f_month = f_array[0];
		}
		if(f_array[1].length()==1){
			f_day = "0" + f_array[1];
		}else{
			f_day = f_array[1];
		}
		
		String s_month = "";
		String s_day = "";
		
		if(s_array[0].length()==1){
			s_month = "0" + s_array[0];
		}else {
			s_month = s_array[0];
		}
		if(s_array[1].length()==1){
			s_day = "0" + s_array[1];
		}else {
			s_day = s_array[1];
		}
		
		f_day = year + "-" + f_month + "-" + f_day;
		s_day = year + "-" + s_month + "-" + s_day;
		
		String img[] = detail.getHd_file().split(",");		
		
		
		int night = comparedate(f_day,s_day);
		int fullprice = night * detail.getHd_price();
		mv.setViewName("reservation/reservationDetail");
		mv.addObject("houseVO",house);
		mv.addObject("hdetailVO",detail);
		mv.addObject("f_day",f_day);
		mv.addObject("s_day",s_day);
		mv.addObject("year",year);
		mv.addObject("roomid",roomid);
		mv.addObject("night",night);
		mv.addObject("fullprice",fullprice);
		mv.addObject("preyear",preyear);
		mv.addObject("premonth",f_month);
		mv.addObject("hid",hid);
		mv.addObject("hdid",hdid);
		mv.addObject("himg",himg);
		mv.addObject("img", img);
		return mv;
	}
	@RequestMapping(value="/resersuccess.do", method=RequestMethod.POST)
	public String resersuccess(HttpServletRequest request, HttpServletResponse response,String hid, String hdid, String roomid, String sessionid, String f_day, String s_day) {
		
		MReservationVO vo = new MReservationVO();
		vo.setId(sessionid);
		vo.setHid(hid);
		vo.setHdid(hdid);
		vo.setRoomid(roomid);
		String f_dated = f_day + " 00:00:00";
		String s_dated = s_day + " 00:00:00";
		vo.setFirstday(f_dated);
		vo.setLastday(s_dated);
		
		boolean updateresult = ReservationService.updateavail(roomid,f_dated,comparedateminus(s_day,1)); 
		
		if(updateresult) {
			boolean result = ReservationService.setreservation(vo);
			
			if(result) {
				sendemail(request,response);
				return "reservation/success";
			} else {
				return "reservation/fail";
			}
		}else {
			return "reservation/fail";
		}
		
		
	}
	@RequestMapping(value="/sendemail.do", method=RequestMethod.POST)
	public void sendemail(HttpServletRequest request, HttpServletResponse response) {
		
		String hid = request.getParameter("hid");
		String hdid = request.getParameter("hdid");
		String roomid = request.getParameter("roomid");
		String id = request.getParameter("sessionid");
		String f_day = request.getParameter("f_day");
		String s_day = request.getParameter("s_day");
		HouseVO hvo = adminService.gethouse(hid);
		HDetailVO hdvo = adminService.gethousedecontent(hdid);
		RoomdeVO roomvo = adminService.getroom(roomid);
		MemberVO membervo = adminService.getmember(id);
		
		
		response.setCharacterEncoding("UTF-8");
		String userID = id;
		String name = membervo.getName();
		String toEmail =hvo.getEmail();
		
		Code code = new Code();
		String checkCode =Code.getCode();
		
		// 사용자에게 보낼 메시지를 기입합니다.
		String host = "http://localhost:9000/myjeju/";
		String from = "jsggo2001@gmail.com";
		String to = toEmail;
		String subject = "고객님의 새 예약 정보 입니다";
		String content = "업체 이름 : " + hvo.getH_name() + "<br>" + "객실이름 : " + hdvo.getHd_name() + "<br>";
		content += "호실 : " + roomvo.getRoom_name() + "<br>" + "고객님 성함 : " + membervo.getName() + "<br>";
		content += "기간 : " + f_day + " ~ " + s_day; 

		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "456");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");

		try {
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(false);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=UTF8");
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 어드민 캘린더
	 */
	@RequestMapping(value="/adcalendar.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView adcalendar(String deyear, String demonth, String hdid, String room_name, String roomid) {
		
		ModelAndView mv = new ModelAndView();
		Calendar cal = Calendar.getInstance();
		
		boolean activateresult = true;
		
		HDetailVO hdvo = adminService.gethousedecontent(hdid);
		String hdname = hdvo.getHd_name();
		String demonth1 = "";
		String deyear1 = deyear.substring(2,4);
		if(demonth.length() == 1) {
			demonth1 = "0"+demonth; 
		}
		demonth1 = deyear1+"/"+demonth1;
		ArrayList<RoomVO> room = adminService.getmonthcheck(demonth1 ,roomid);
		
		if(room.size() == 0) {
			activateresult = false;
		}
		
		int today = (cal.get(Calendar.MONTH)+1)*100 + cal.get(Calendar.DAY_OF_MONTH);
		int toyear = cal.get(Calendar.YEAR); 
		int year = Integer.parseInt(deyear);
		int month = Integer.parseInt(demonth);
		
		if(month == 13) {
			year += 1;
			month -= 12;
		}
		if(month == 0) {
			year -=1;
			month += 12;
		}
		
		
		int maxrow = 0;
		int maxday = 0;
		
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
		
		int day = first % 7;

		maxday = day + monthDay(year, month);
		if(maxday >= 36) {
					maxrow = 6;
				} else {
					maxrow = 5;
				}
		ArrayList<DateVO> value = calprintforderes(year,month,maxrow);
		
		int presmonth = value.get(0).getMonth();
		int syear = value.get(0).getYear();
		if(presmonth>12) {
			presmonth -= 12;
			syear = year + 1;
		}else if(presmonth<=0) {
			presmonth += 12;
			syear = year - 1;
		}
		String sMonth = String.valueOf(presmonth);
		if(sMonth.length() == 1) {
			sMonth = "0"+ sMonth;
		}
		String sDay = String.valueOf(value.get(0).getDay());
		if(sDay.length() == 1) {
			sDay = "0"+ sDay;
		}
		
		String start = syear + sMonth + sDay;
		
		int preemonth = value.get(value.size()-1).getMonth();
		int eyear = value.get(value.size()-1).getYear();
		if(preemonth>12) {
			preemonth -= 12;
			eyear += 1;
		}else if(preemonth<=0) {
			preemonth += 12;
			eyear -= 1;
			
		}
		String eMonth = String.valueOf(preemonth);
		if(eMonth.length() == 1) {
			eMonth = "0"+ eMonth;
		}
		
		String eDay = String.valueOf(value.get(value.size()-1).getDay());
		if(eDay.length() == 1) {
			eDay = "0"+ eDay;
		}
		
		String end = eyear + eMonth + eDay;
		
		ArrayList<RoomVO> searchroom = ReservationService.searchroom_each(start,end,roomid);
		ArrayList<RoomVO> notavails = ReservationService.notavails(start,end,roomid);
		
		String[] availdate = new String[searchroom.size()];		
		for(int i=0; i<searchroom.size(); i++) {
			availdate[i] = searchroom.get(i).getRdate();
		}
		
		ArrayList<String> arrayList = new ArrayList<String>();
		for(String item : availdate){
            if(!arrayList.contains(item))
                arrayList.add(item);
        }
		ArrayList<String> conver = new ArrayList<String>();
		for(int i =0; i<arrayList.size();i++) {
			String[] a = arrayList.get(i).split("-");
			String aa = a[0] + a[1] + a[2];
			conver.add(aa.substring(0,8));
		}
		
		String[] availdate1 = new String[notavails.size()];		
		for(int i=0; i<notavails.size(); i++) {
			availdate1[i] = notavails.get(i).getRdate();
		}
		
		ArrayList<String> arrayList1 = new ArrayList<String>();
		for(String item : availdate1){
			if(!arrayList1.contains(item))
				arrayList1.add(item);
		}
		ArrayList<String> conver2 = new ArrayList<String>();
		for(int i =0; i<arrayList1.size();i++) {
			String[] a = arrayList1.get(i).split("-");
			String aa = a[0] + a[1] + a[2];
			conver2.add(aa.substring(0,8));
		}
		
		ArrayList<String> availlast1 = new ArrayList<String>();
		
		for(int i = 0;i<value.size();i++) {
			int smonth2 = value.get(i).getMonth();
			int syear1 = value.get(i).getYear();
			if(smonth2<=0) {
				smonth2 +=12;
				syear1 -=1;
			}else if (smonth2>12) {
				smonth2 -=12;
				syear1 +=1;
			}
			
			String sMonth1 = String.valueOf(smonth2);
			if(sMonth1.length() == 1) {
				sMonth1 = "0"+ sMonth1;
			}
			String sDay1 = String.valueOf(value.get(i).getDay());
			if(sDay1.length() == 1) {
				sDay1 = "0"+ sDay1;
			}
			String start1 = syear1 + sMonth1 + sDay1;
			availlast1.add(start1);
		}
		ArrayList<Integer> availlast = new ArrayList<Integer>();
		for(int i = 0;i<value.size();i++) {
			int check = 0;
			for(int j = 0 ;j<conver.size();j++) {
				if(availlast1.get(i).equals(conver.get(j))) {
					availlast.add(0);
					check = 1;
				}
			}
			for(int h = 0;h < conver2.size();h++) {
				if(availlast1.get(i).equals(conver2.get(h))) {
					availlast.add(2);
					check = 2;
				}
			}
			if(check == 0) {
				availlast.add(-1);
			}
		}
		
		if(activateresult) {
			mv.setViewName("admin/adcalendar");
			mv.addObject("calvalue",value);
			mv.addObject("availlast",availlast);
			mv.addObject("hdid",hdid);
			mv.addObject("roomid",roomid);
			mv.addObject("maxrow",maxrow);
			mv.addObject("year",year);
			mv.addObject("month",month);
			mv.addObject("today",today);
			mv.addObject("toyear",toyear);
			mv.addObject("hdname",hdname);
			mv.addObject("roomname",room_name);
		return mv;
		} else {
			mv.setView(new RedirectView("adhouse_de_room.do?hdid="+hdid));
			mv.addObject("result","5");
		return mv;
		}
	}
	
	@RequestMapping(value="/notavail", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String notavail(String year, String month, String roomid, String day) {
		
		String date ="";
		
		date += year.substring(2,4) + "/";
		if(month.length()==1) {
			month = "0"+month;
		}
		if(day.length()==1) {
			day = "0"+day;
		}
		System.out.println("예약 안됨 =" + roomid);
		System.out.println("예약 안됨 =" +date);
		date += month + "/";
		date += day;
		boolean result = adminService.notavail(roomid,date);
		if(result) {
		return "예약 불가 상태로 바뀌었습니다.";
		}else {
		return "실패";
		}
	}
	@RequestMapping(value="/toavail", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String toavail(String year, String month, String roomid, String day) {
		
		String date ="";
		
		date += year.substring(2,4) + "/";
		if(month.length()==1) {
			month = "0"+month;
		}
		if(day.length()==1) {
			day = "0"+day;
		}
		date += month + "/";
		date += day;
		System.out.println("예약 됨 =" +roomid);
		System.out.println("예약 됨 =" +date);
		boolean result = adminService.toavail(roomid,date);
		if(result) {
			return "예약 가능 상태로 바뀌었습니다.";
		}else {
			return "실패";
		}
	}
}
