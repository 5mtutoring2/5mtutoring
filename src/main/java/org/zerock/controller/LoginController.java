package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/login")	//기본URI(Base URI)
@Controller
public class LoginController {
	
	//로그인화면
	@GetMapping
	public String login() {
		log.trace("login() invoked.");
		
		return "login/1-02_login";
	}
	
	//회원가입 선택 
	@GetMapping("/selectAccount")
	public String selectAccount() {
		log.trace("selectAccount() invoked.");
		
		return "login/1-03_selectAccount";
	}
	
	//회원가입폼 - 학생
	@RequestMapping("/signUp_student")
	public String signUp_student() {
		log.trace("signUp_student() invoked.");
		
		return "login/1-04_signUpStudent";
	}
	
	//회원가입폼 - 튜터
	@RequestMapping("/signUp_tutor")
	public String signUp_tutor() {
		log.trace("signUp_tutor() invoked.");
		
		return "login/1-04_signUpTutor";
	}
	
	//로그인 후 메인화면
	@RequestMapping("/home")
	public String loginHome() {
		log.trace("loginHome() invoked.");
		
		return "login/1-01_homeLogin";
	}
	
	
	@GetMapping("/signupReq")
	public String signupReq() {
		log.trace("signupReq() invoked.");
		
		return "login/1-08_signupReq";
	}
	

}//end class