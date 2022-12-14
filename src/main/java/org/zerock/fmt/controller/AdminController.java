package org.zerock.fmt.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.fmt.domain.AdminPageDTO;
import org.zerock.fmt.domain.AdminVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.FaqDTO;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.domain.FileVO;
import org.zerock.fmt.domain.InquiryAnswerDTO;
import org.zerock.fmt.domain.InquiryAnswerVO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.PageMyPageDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.domain.WithdrawalVO2;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.AdminService;
import org.zerock.fmt.service.BuyService;
import org.zerock.fmt.service.FaqService;
import org.zerock.fmt.service.FileService;
import org.zerock.fmt.service.InquiryAnswerService;
import org.zerock.fmt.service.InquiryQuestionService;
import org.zerock.fmt.service.TutorService;
import org.zerock.fmt.service.UserService;
import org.zerock.fmt.service.WithdrawalService;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Setter(onMethod_= @Autowired)
	private FaqService faqService;

	@Setter(onMethod_ =@Autowired)
	private AdminService adminService;
		
	@Setter(onMethod_ = @Autowired)
	private UserService userService;
	
	@Setter(onMethod_ = @Autowired)
	private FileService fileService;
	
	@Setter(onMethod_ = @Autowired)
	private BuyService buyService;
	
	@Setter(onMethod_ = @Autowired)
	private InquiryQuestionService questionService;
	
	@Setter(onMethod_= @Autowired)
	private InquiryAnswerService answerService;
	
	@Setter(onMethod_ = @Autowired)
	private WithdrawalService withdrawalService;
	
	@Setter(onMethod_ = @Autowired)
	private TutorService tutorService;
	//--------------------------------------------- ??????????????????
	@GetMapping
	public String adminLogin() {
		log.info("????????? Home");
		return "admin/8-00_adminLogin";
	}
	@PostMapping("/login")
	public String adminLogin(String ad_id,String ad_pw, Model model,RedirectAttributes rttrs) throws ControllerException {
		log.info("????????? ?????????");
		try {
			
			AdminVO admin = adminService.Login(ad_id, ad_pw);
			if(admin == null) {
				rttrs.addFlashAttribute("_RESULT_","????????? ????????? ????????????.");
				return "redirect:/admin";
			} else {
				model.addAttribute("_ADMIN_", admin);
				model.addAttribute("_ADMIN_RESULT_","???, ????????? ???????????????.");
				return "admin/Loginpost";
			}
		} catch (Exception e) { throw new ControllerException(e); }//try-catch
	}//adminLogin
	
	//--------------------------------------------- ????????????
	@GetMapping("/student")
	public String adminMemberStrudent(Model model, CriteriaAdmin cri) throws ControllerException {
		log.info("??????????????? - ?????? ?????????");
		try {
			List<UserVO> list = this.userService.getStudent(cri);
			model.addAttribute("_USERLIST_",list);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.userCount(cri));
			log.info("\t+ Adpage : {}", Adpage);
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		} catch (Exception e) {throw new ControllerException(e); }//try-catch
		return "admin/8-01_managerST";
	}
	
	@GetMapping("/tutor")
	public String adminMemberTutor(Model model,CriteriaAdmin cri) throws ControllerException {
		log.info("??????????????? - ?????? ????????? ");
		try {
			List<UserVO> list = this.userService.getTutor(cri);
			model.addAttribute("_USERLIST_",list);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.userCount(cri));
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		}catch(Exception e) {throw new ControllerException(e); }//try-catch
		return "admin/8-01_managerTT";
	}
	
	@GetMapping("/humenMember")
	public String adminMemberTZ(Model model,CriteriaAdmin cri) throws ControllerException {
		log.info("??????????????? - ????????????");
		try {
			List<UserVO> list = this.userService.getStopUser(cri);
			model.addAttribute("_USERLIST_",list);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.userCount(cri));
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		}catch(Exception e) {throw new ControllerException(e); }//try-catch
		return "admin/8-01_managerTZ";
	}
	
	@RequestMapping("/UserInfo")
	public String UserInfo(String user_email, Model model) throws ControllerException {
		log.info("??????????????????");
		try {
			UserVO userinfo = this.userService.getUserInfo(user_email);
			model.addAttribute("_USERINFO_",userinfo);
			return "admin/8-01_userInfo";
		} catch( Exception e) {throw new ControllerException(e); }//try-catch
		
	}//UserInfo
	
	@RequestMapping("/tutorInfo")
	public String tutorInfo(String user_email, Model model) throws ControllerException {
		log.info("????????????????????????");
		
		try {
			UserVO userInfo = this.userService.getUserInfo(user_email);
			model.addAttribute("_USERINFO_", userInfo);
			FileVO ttFile = this.fileService.getFile(user_email);
			model.addAttribute("_TUTOR_FILE_",ttFile);
			return "admin/8-01_tutorInfo";
		} catch(Exception e) {throw new ControllerException(e); }//try-catch
	}//tutorInfo
	//--------------------------------------------- ???????????????
	@GetMapping("/stator")
	public String adminStator(CriteriaAdmin cri, Model model) throws ControllerException {
		log.info("??????????????????");
		
		try {
			List<AdminVO> list = this.adminService.adminList(cri);
			model.addAttribute("_USERLIST_",list);
			list.forEach(log::info);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.adminService.adminCount());
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		}catch(Exception e) {throw new ControllerException(e); }//try-catch
		return "admin/8-02_administrator";
	}
	
	//--------------------------------------------- ???????????????
	@GetMapping("/answerBoard_OK")
	public String adminAnswerBoardOk(CriteriaAdmin cri, Model model) throws ControllerException {
		log.info("???????????????(????????????)");
		
		try {
			List<InquiryQuestionVO> list = this.questionService.getAllInquiryYList(cri);
			model.addAttribute("_RESULT_",list);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.questionService.countList("Y"));
			model.addAttribute("_ADMINPAGINATION_",Adpage);
			return "admin/8-03_answerBoard_OK";
		}catch(Exception e) {throw new ControllerException(e); }
	}//adminAnswerBoardOk
	
	
	@GetMapping("/answerBoard_NO")
	public String adminAnswerBoardNo(CriteriaAdmin cri, Model model) throws ControllerException {
		log.info("???????????????(?????????)");
		try {
			List<InquiryQuestionVO> list = this.questionService.getAllInquiryNList(cri);
			model.addAttribute("_RESULT_",list);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.questionService.countList("N"));
			model.addAttribute("_ADMINPAGINATION_",Adpage);
			return "admin/8-03_answerBoard_NO";
		}catch( Exception e) {throw new ControllerException(e); }
	}//adminAnswerBoardNo
	
	
	@PostMapping("/answerBoard/NOcomment") // ?????? ?????? ???
	public String adminAnswerBoard_NOcomment(@RequestParam Integer iq_number,
			InquiryAnswerDTO dto, Model model, CriteriaAdmin cri, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		log.info("???????????????(?????????)");
		
		try { 
			 InquiryQuestionVO Questionvo = this.questionService.getInquiry(iq_number);				

			dto.setIq_number(iq_number);
			this.answerService.createIA(dto);
			// ?????? ?????? ????????????
			this.questionService.updateInquiryState(dto.getIq_number());
			
			rttrs.addFlashAttribute("_ANSWER_"); 
			
			return "redirect:/admin/answerBoard_OK";
				
		} catch(Exception e) {throw new ControllerException(e); }
		
	} // ????????? ?????? ??????
	
	@GetMapping("/answerBoard/comment")
	public String adminAnswerBoard_comment(@RequestParam Integer iq_number,  Model model)throws ControllerException {
		
		try {
			InquiryQuestionVO Questionvo = this.questionService.getInquiry(iq_number);
			
			if(answerService.getIA(iq_number) == null ) {	// ????????? ?????????
				model.addAttribute("_QUESTION_", Questionvo);	
				
			} else {
				model.addAttribute("_QUESTION_", Questionvo);	
				
				InquiryAnswerVO Answervo = this.answerService.getIA(iq_number);
				model.addAttribute("_ANSWER_", Answervo);	
			}
			return "admin/8-03_answerBoard_comment";
		}catch(Exception e) {throw new ControllerException(e); }

	} // ???????????? ?????? ??????
	
	
	
//	????????? FAQ ========================================================================================
	@GetMapping("/adminFAQ")
	public String adminFAQ(CriteriaMyPage cri, Model model) throws ControllerException {
		log.info("?????? ?????? ??????(????????????)");
		
		try {
			List<FaqVO> list = this.faqService.getFaqList(cri);
			model.addAttribute("_FAQLIST_",list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.faqService.getFaqTotal());
			model.addAttribute("_FAQPAGENATION_",pageDto);
			
			return "admin/8-04_admin_faq";
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}// try-catch
	}// adminFAQ(??????)
	
	@PostMapping("/adminFAQCreate")
	public String adminFAQCreate(CriteriaMyPage cri, FaqDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.info("?????? ?????? ??????(????????????) ??????");
		
		try {
			if(this.faqService.createFaq(dto)) { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ?????? ??????"); } 
			else { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ?????? ??????"); } //if - else
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
		return "redirect:/admin/adminFAQ?currPage="+ cri.getCurrPage();
	}// adminFAQ(??????)
	
	@PostMapping("/adminFAQModify")
	public String adminFAQModify(CriteriaMyPage cri, FaqDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.info("?????? ?????? ??????(????????????) ??????");
		
		try {
			if(this.faqService.updateFaq(dto)) { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ?????? ??????"); } 
			else { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ?????? ??????"); } //if - else
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
		return "redirect:/admin/adminFAQ?currPage="+ cri.getCurrPage();
	}// adminFAQ(??????)
	
	@PostMapping("/adminFAQRemove")
	public String adminFAQRemove(CriteriaMyPage cri, FaqDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.info("?????? ?????? ??????(????????????) ??????");
		
		try {
			if(this.faqService.removeFaq(dto)) { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ?????? ??????"); } 
			else { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ?????? ??????"); } //if - else
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
		return "redirect:/admin/adminFAQ?currPage="+ cri.getCurrPage();
	}// adminFAQ(??????)
//	===================================================================================================

	@RequestMapping("/sale/sell")
	public String adminSale(CriteriaAdmin cri, Model model) throws ControllerException {
		log.info("???????????? ?????????");
		try {
			List<BuyVO> buyList = this.buyService.selectAllBuy(cri);
			model.addAttribute("_BUYLIST_",buyList);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.buyService.countBuy());
			model.addAttribute("_ADMINPAGINATION_",Adpage);
			int countSale = this.buyService.countSale();
			model.addAttribute("countSale",countSale);
			return "admin/8-05_sale_sell";			
		} catch (Exception e) {throw new ControllerException(e); }//try-catch
	}//adminSale
	
	@GetMapping("/sale/withdrow")
	public String adminWithDrow(CriteriaAdmin cri, Model model) throws ControllerException {
		log.info("?????? ?????????");
		try {
			List<WithdrawalVO> list = this.withdrawalService.getAllWithdrawalList(cri);
			model.addAttribute("_DRAWLIST_", list);
			
			AdminPageDTO adpage = new AdminPageDTO(cri, this.withdrawalService.countList(cri));
			model.addAttribute("_ADMINPAGINATION_",adpage);
			
			model.addAttribute("totalDrawal",this.withdrawalService.totalDrawal(cri));
			return "admin/8-05_sale_withdrow";
		}catch(Exception e) {throw new ControllerException(e); }
	}

	@GetMapping("/sale/withdrow/detail")
	public String adminWithDrowDetail(@RequestParam Integer w_num, Model model) throws ControllerException {
		log.info("?????? ?????? ?????? ?????????");
		try {
			log.info(w_num);
			WithdrawalVO2 vo = this.withdrawalService.getWithdrawal(w_num);
			
			model.addAttribute("_WITHDRAWALINFO_", vo);
			
			return "admin/8-06_sale_withdrowdetail";
		}catch(Exception e) {throw new ControllerException(e); }
	} // ?????? ?????? ?????? ?????? ?????????
	
	@PostMapping("/sale/withdrowOK")
	public String adminWithDrowOK(@RequestParam Integer w_num, WithdrawalDTO dto, CriteriaAdmin cri, Model model) throws ControllerException {
		log.info("?????? ?????? ?????? ?????????");
		try {
		
			dto.setW_num(w_num);
			
			withdrawalService.updateState(dto);
			log.info("dto: {}", dto);
			
			AdminPageDTO adpage = new AdminPageDTO(cri, this.withdrawalService.countList(cri));
			model.addAttribute("_ADMINPAGINATION_",adpage);
			
			model.addAttribute("totalDrawal",this.withdrawalService.totalDrawal(cri));
			
		
			return "redirect:/admin/sale/withdrow?currPage="+ cri.getCurrPage();
		}catch(Exception e) {throw new ControllerException(e); }
	} // ?????? ?????? ?????? ?????? ?????????
	
	//--------------------------------------------- ??????????????????
	@GetMapping("/signUp_comfim")
	public String signUp_comfim(CriteriaAdmin cri,Model model) throws ControllerException {
		log.trace("?????????????????? ????????? ");
		try{
			List<UserVO> list = this.userService.getWaitTutor(cri);
			model.addAttribute("_USERLIST_",list);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.waitTutorCount());
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		}catch(Exception e) {throw new ControllerException(e);}//try-catch
		return "admin/8-06_singUpConfim";
	}

	@PostMapping("/signUpOK")
	public String signUp_comfim(@RequestParam HashMap<String, Object> commandMap, RedirectAttributes rttrs) 
								throws ControllerException {
		log.trace("?????????????????? ?????????");

		try {
			String getEmails = commandMap.get("arrayParam").toString();
			String[] emails = null;
			emails = getEmails.split(",");
			
			int result = 0;
			for(String user_email : emails) {
				if( this.userService.tutorPass(user_email) == 1) {
					//???????????? ????????? true??????
					this.tutorService.createIntroInfo(user_email);
					//?????????????????? ??????
					result += this.userService.tutorPass(user_email);
					//??????????????? result??? ??????
				}//if
			}//for
			if(result==emails.length) {
				//?????????????????? ???????????? ????????? ????????? 
				rttrs.addFlashAttribute("TutorResult", "???????????????????????????.");
			} else {
				rttrs.addFlashAttribute("tutorResult", "??????????????????. ??????????????????.");
			}//if-else
			log.info("\t + ???????????? List : {}, result : {}", Arrays.toString(emails), result);
			
			return "redirect:/admin/signUp_comfim";
		}catch(Exception e) { throw new ControllerException(e); }//try-catch
	}//signUp_comfim
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage (String fileName) {
		log.trace("???????????????");
		File file = new File(fileName); //*** ?????? ????????? ?????? ??????????????? ***
		
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return result;
	}//uploadFile
	
	
}//end class
