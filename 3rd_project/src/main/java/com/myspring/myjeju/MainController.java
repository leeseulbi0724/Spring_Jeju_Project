package com.myspring.myjeju;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	/**
	 * main.do : 시작페이지
	 */
	@RequestMapping(value="/main.do", method=RequestMethod.GET)
	public String main() {
		return "main";
	}
}
