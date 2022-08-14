package org.zerock.fmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.fmt.domain.UserVO_tutor;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/admin")
@Controller
public class AdminController {

	@RequestMapping
	public String adminLogin() {
		log.info("어드민 로그인 페이지eeeeeeee");
		
		return "admin/8-00_adminLogin";
	}
//	@GetMapping("/student")
//	@PostMapping("/student")
	@RequestMapping("/student")
	public String adminMemberStrudent() {
		log.info("회원리스트 - 학생 페이지");
		
		return "admin/8-01_managerST";
	}
	
	@RequestMapping("/tutor")
	public String adminMemberTutor() {
		log.info("회원리스트 - 튜터 페이지 ");
		
		return "admin/8-01_managerTT";
	}
	
	@RequestMapping("/humenMember")
	public String adminMemberTZ() {
		log.info("회원리스트 - 탈퇴회원");
		
		return "admin/8-01_managerTZ";
	}
	
	@RequestMapping("/UserInfo")
	public String UserInfo() {
		log.info("회원정보조회");
		
		return "admin/8-01_userInfo";
	}
	
	@RequestMapping("/tutorInfo")
	public String tutorInfo(Model model) {
		log.info("회원정보조회");
		
//		TutorVO tutor = 
//				new TutorVO("1234@email", "튜터닉네임", "000000", "11월11일", "대핟생", "수학", "이거는첨부파일");
		
//		model.addAttribute("tutor",tutor);
		
		
		return "admin/8-01_tutorInfo";
	}
	
	@RequestMapping("/stator")
	public String adminStator() {
		log.info("관리자리스트");
		
		return "admin/8-02_administrator";
	}
	
	@RequestMapping("/answerBoard_OK")
	public String adminAnswerBoardOk() {
		log.info("문의게시판(답변완료)");
		
		return "admin/8-03_answerBoard_OK";
	}
	
	@RequestMapping("/answerBoard_NO")
	public String adminAnswerBoardNo() {
		log.info("문의게시판(미답변)");
		
		return "admin/8-03_answerBoard_NO";
	}
	
	@RequestMapping("/answerBoard/comment")
	public String adminAnswerBoard_comment() {
		log.info("문의게시판(미답변)");
		
		return "admin/8-03_answerBoard_comment";
	}
	
	@RequestMapping("/adminFAQ")
	public String adminFAQ() {
		log.info("자주 묻는 질문(관리자용)");
		
		return "admin/8-04_admin_faq";
	}
	
	@RequestMapping("/sale/sell")
	public String adminSale() {
		log.info("매출관리 페이지");
		
		return "admin/8-05_sale_sell";
	}
	
	@RequestMapping("/sale/withdrow")
	public String adminWithDrow() {
		log.info("출금 페이지");
		
		return "admin/8-05_sale_withdrow";
	}
	
	@RequestMapping("/signUp_comfim")
	public String signUp_comfim() {
		log.info("튜터가입승인 페이지 ");
		
		return "admin/8-06_singUpConfim";
	}
	
	
}//end class
