package org.zerock.fmt.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.fmt.common.SharedScopeKeys;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.PageMyPageDTO;
import org.zerock.fmt.domain.ProfileDTO;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.MypageService;
import org.zerock.fmt.service.ProfileService;
import org.zerock.fmt.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/mypage")	//기본URI(Base URI)
@Controller
public class MypageController {
	
	@Setter(onMethod_= @Autowired)
	private UserService userService;
	
	@Setter(onMethod_= @Autowired)
	private MypageService mypageService;
	
	@Setter(onMethod_= @Autowired)
	private ProfileLoad profileUpload;
	
	@Setter(onMethod_= @Autowired)
	private ProfileService profileService;
	
//============================================================	
	
//=====기본정보===============================================	
	@GetMapping("/studentPage")
	public String studentPage(Model model, HttpSession session) throws ControllerException{
		log.trace("마이페이지 기본정보 조회(학생)");
		
		try {
			//1. 기본정보 조회
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			log.info("1. session scope 정보: {}", vo);
			
			UserVO userInfo = this.userService.getUserInfo(vo.getUser_email());
			model.addAttribute("_USERINFO_", userInfo);
			
			//2. 프로필 사진 유무 조회
			List<ProfileVO> profileVo = this.profileService.getProfile(vo.getUser_email());
			if(profileVo.size() == 0 ) {
				model.addAttribute("_ISPROFILE_", "false");
			} else {
				model.addAttribute("_ISPROFILE_", "true");
			}// if-else
			
			return "mypage/7-01_StudentPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 기본정보조회(학생)
	
	@PostMapping("/pwCheck")
	@ResponseBody
	public int pwCheck(@RequestParam String user_pw, HttpSession session) throws ControllerException {
		
		UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		try {
			String dbPw = this.mypageService.getUserDbPw(vo.getUser_email());
			log.info("\t+ user_pw: {}", user_pw);
			int result;
					
			if(encoder.matches(user_pw, dbPw)) { 
				log.info("비밀번호 일치");
				result = 1;
			} else { log.info("비밀번호 불일치"); result = 0; }// if-else

			return result;
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
	}// 비밀번호 유효성 체크(ajax로 처리)
	
	@PostMapping("/studentPageModify")
	public String studentPage(UserDTO dto, @RequestParam String user_newPw, MultipartFile file_name,
			RedirectAttributes rttrs, HttpSession session) throws ControllerException{
		log.trace("마이페이지 기본정보 수정(학생)");
		log.info("\t+ 1. file_name: {}", file_name);
		
		try {
			log.info("\t+ user_newPw: {}", user_newPw);
			dto.setUser_pw(user_newPw);
			
			if(file_name.getSize() != 0) {	//input file에 파일 값이 있다면
				//1-1. 프로필 사진 유무 조회
				List<ProfileVO> profileVo = this.profileService.getProfile(dto.getUser_email());
				
				ProfileDTO profileDto = new ProfileDTO();
				profileDto.setUser_email(dto.getUser_email());
				profileDto.setFile_name(dto.getUser_nick() + "_profile");
				
				//1-2. DB에 정보 저장 또는 수정
				if(profileVo.size() == 0) { //DB에 프로필 정보가 없다면, 
					this.profileService.createProfile(profileDto); 
					log.info("프로필 정보 DB 생성");
				} else { 
					this.profileService.modifyProfile(profileDto);
					log.info("프로필 정보 DB 수정");
				}// if- else
				
				//1-3. 로컬에 저장
				profileUpload.uploadProfile(file_name, dto);
				log.info("프로필 사진 로컬 저장소 업로드 완료");
			}//if
			
			//2. 회원정보 수정
			if(this.mypageService.modifyUserInfo(dto)) {
				UserVO vo = this.userService.getUserInfo(dto.getUser_email());
				session.setAttribute(SharedScopeKeys.LOGIN_USER, vo);
				
				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "회원정보 수정 성공");
			}// if
			return "redirect:/mypage/studentPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 기본정보 수정(학생)
	
	
	@GetMapping("/tutorPage")
	public String tutorPage(Model model, HttpSession session) throws ControllerException{
		log.trace("마이페이지 기본정보 조회(튜터)");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			
			UserVO userInfo = this.userService.getUserInfo(vo.getUser_email());
			model.addAttribute("_USERINFO_", userInfo);
			
			return "mypage/7-02_TutorPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
	}// 기본정보 조회(튜터)
	
	@PostMapping("/tutorPageModify")
	public String tutorPageModify(UserDTO dto, RedirectAttributes rttrs) throws ControllerException{
		log.trace("마이페이지 기본정보 수정(튜터)");
		
		return "redirect:/mypage/tutorPage";
		
//		try {
//			if(this.userService.updateUser(dto)) {
//				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "회원정보 수정 성공");
//			} else {
//				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "회원정보 수정 오류");
//			}//if-else
//		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 기본정보 수정(튜터)
	
//=====나의 질문글===============================================
	@GetMapping("/myQuestion")
	public String myQuestion(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("마이페이지 나의 질문글 목록 조회");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<QuestionBoardVO> list = this.mypageService.getAllMyQuestionList(cri);
			model.addAttribute("_MYQLIST_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyQuestionTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYQLISTPAGENATION_", pageDto);
						
			return "mypage/7-03_MyQuestionList";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 나의 질문글 조회

//=====나의 커뮤니티===============================================
	@GetMapping("/community/write")		//GET	
	public String communityWrite(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("마이페이지 나의 커뮤니티 작성글 목록 조회");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<CommunityVO> list = this.mypageService.getAllMyCommunityList(cri);
			model.addAttribute("_MYCOMMUNITY_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyCommunityTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYCOMMUNITYPAGENATION_", pageDto);
						
			return "mypage/7-04_CommunityW";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 나의 작성글
	
	@GetMapping("/community/comments")		//GET	
	public String communityComments(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("마이페이지 나의 댓글 목록 조회");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<CommentVO> list = this.mypageService.getAllMyCommentList(cri);
			model.addAttribute("_MYCOMMENT_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyCommentTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYCOMMENTPAGENATION_", pageDto);
						
			return "mypage/7-05_CommunityC";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 나의 댓글 조회
	
	@GetMapping("/qList")	// GET
	public String qList() {
		log.trace("7-06_QList");
		
		return "mypage/7-06_QList";
	}// qList
	
	@GetMapping("/question") // GET
	public String question() {
		log.trace("7-07_Q");
		
		return "mypage/7-07_Q";
	}// question
	
	@GetMapping("/qAndA")	// GET
	public String qAndA() {
		log.trace("7-07_QandA");
		
		return "mypage/7-07_QandA";
	}// qAndA
	
	@RequestMapping("/unregister")	// POST
	public String unregister() {
		log.trace("7-08_Unregister");
		
		return "mypage/7-08_Unregister";
	}// unregister
	
	@GetMapping("/unregister/completed")	// GET
	public String unregisterCompleted() {
		log.trace("7-09_UnregisterCompleted");
		
		return "mypage/7-09_UnregisterCompleted";
	}// unregisterCompleted
	
	@GetMapping("/studentHands/use")		//Get
	public String studentHandsUse() {
		log.trace("7-10_StudentHandsListUse");
		
		return "mypage/7-10_StudentHandsListUse";
	}// studentHandsUse
	
	@GetMapping("/studentHands/buy")		//GET
	public String studentHandsBuy() {
		log.trace("7-11_StudentHandsListBuy");
		
		return "mypage/7-11_StudentHandsListBuy";
	}// studentHandsBuy
	
	@GetMapping("/studentHands/buy/detail")		//GET
	public String studentHandsBuyDetail() {
		log.trace("7-12_StudentHandsListBuyD");
		
		return "mypage/7-12_StudentHandsListBuyD";
	}// studentHandsBuyDetail
	
	@GetMapping("/tutorHands/get")		//GET
	public String tutorHandsGet() {
		log.trace("7-13_TutorHandsListGet");
		
		return "mypage/7-13_TutorHandsListGet";
	}// tutorHandsGet
	
	@GetMapping("/tutorHands/withdraw")		//GET
	public String tutorHandsWithdraw() {
		log.trace("7-14_TutorHandsListWithdraw");
		
		return "mypage/7-14_TutorHandsListWithdraw";
	}// tutorHandsWithdraw
	
	@RequestMapping("/withdraw")	// POST
	public String withdraw() {
		log.trace("7-15_Withdraw");
		
		return "mypage/7-15_Withdraw";
	}// withdraw
	
	@GetMapping("/withdraw/completed")	// GET
	public String withdrawCompleted() {
		log.trace("7-16_WithdrawCompleted");
		
		return "mypage/7-16_WithdrawCompleted";
	}// withdrawCompleted

}//end class