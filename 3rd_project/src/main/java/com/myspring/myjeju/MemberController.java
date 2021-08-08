package com.myspring.myjeju;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myjeju.service.MemberService;
import com.myjeju.service.MessageService;
import com.myjeju.service.MypageService;
import com.myjeju.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MypageService mypageService;

	/**
	 * main.do : 시작페이지
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "member/login";
	}
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		return "member/join";
	}
	@RequestMapping(value="/idsearch.do", method=RequestMethod.GET)
	public String idsearch() {
		return "member/idsearch";
	}
	@RequestMapping(value="/passsearch.do", method=RequestMethod.GET)
	public String passsearch() {
		return "member/passsearch";
	}
	
	/**
	 * sms 인증
	 */
	@ResponseBody
    @RequestMapping(value = "/sendSms.do", method = RequestMethod.POST )
     public void sendSms(HttpServletRequest request) throws Exception {
		// 파라미터로 받은 휴대폰번호, 난수로 생성된 인증번호를 전달
		messageService.sendMessage((String)request.getParameter("to"), (String)request.getParameter("text"));
	}
	
	/**
	 * 회원가입
	 */
	@RequestMapping(value = "/join_proc.do", method=RequestMethod.POST ) 
	public String join_proc(MemberVO vo) {
		
		String result = "";
		
		boolean join_result = memberService.getJoinResult(vo);
    	
    	if (join_result == true ) {
    		result = "member/join_success";
    	} else {
			/* result = "errorPage"; */
    	}
    	return result;
	}
	
	/**
	 * 로그인
	 */
	@ResponseBody
	@RequestMapping(value = "/login_proc.do", method=RequestMethod.POST ) 
	public boolean login_proc (HttpServletRequest request) {
		HttpSession session = request.getSession();
		boolean result = false; 	
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo.setPass(request.getParameter("pass"));
		
		
		result = memberService.getLoginResult(vo);
		
		session.setAttribute("session_id", request.getParameter("id"));
		MemberVO mvo = mypageService.getMemberContent(request.getParameter("id"));
		session.setAttribute("session_name", mvo.getName());
		
	
		return result;
	}
	
	 /**
	  * 로그아웃 세션 종료
	  */
		
		 @RequestMapping(value = "/logout.do", method=RequestMethod.GET) 
		 public String logout(HttpServletRequest request) { 
			 HttpSession session = request.getSession(); session.invalidate();		 
		 return "index";
		 
	}

	
	/**
	 * 아이디 찾기
	 */
	@RequestMapping(value = "/id_search_proc.do", method=RequestMethod.POST ) 
	public ModelAndView id_search_proc (MemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		System.out.print(vo.getName());
		System.out.print(vo.getHp());
		System.out.print(vo.getEmail());
		
		String id = memberService.getIdSearchResult(vo);  	
		
		mv.setViewName("member/search_success");
		mv.addObject("type", "id");
		mv.addObject("id", id);
		
		return mv;
	}
	
	/**
	 * 비밀번호 찾기
	 */
	@RequestMapping(value = "/pass_search_proc.do", method=RequestMethod.POST ) 
	public ModelAndView pass_search_proc (MemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		System.out.print(vo.getName());
		System.out.print(vo.getHp());
		System.out.print(vo.getEmail());
		
		String id = memberService.getPassSearchResult(vo);  	
		
		mv.setViewName("member/search_success");
		mv.addObject("type", "pass");
		mv.addObject("id", id);
		
		return mv;
	}
			
	/**
	 * 비밀번호 변경
	 */
	@RequestMapping(value = "/pass_update_proc.do", method=RequestMethod.POST)
	public ModelAndView pass_update_proc(MemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		boolean result = memberService.getPassUpdateResult(vo);
		
		if ( result ) {
			mv.setViewName("member/pass_success");
		}
		
		return mv;
	}
	
	/**
	 * 아이디 중복확인
	 */
	@RequestMapping(value = "/id_check_proc.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean id_check(HttpServletRequest request) throws Exception {
		System.out.print(request.getParameter("id"));
		int count = memberService.getIdCheckResult(request.getParameter("id"));
		
		boolean result = false;
		
		if ( count == 0 ) {
			result = true;
		} 
		
		return result;
	}
}
