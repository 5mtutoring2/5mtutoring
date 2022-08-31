package org.zerock.fmt.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.QuestionBardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class MypageServiceTests {
	
	@Setter(onMethod_= @Autowired)
	private MypageService service;
	
	
	//1. 기본정보 테스트
	@Test
	@Order(1)
	@DisplayName("1. testGetUserInfo")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetFaqList() throws ServiceException {
		log.trace("TestGetUserInfo(), 마이페이지 기본정보 조회 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		UserVO vo = this.service.getUserInfo(dto);
		log.info("\t+ 기본정보: {}", vo);
		
	}//TestGetUserInfo()
	
	//1-1. 기본정보 테스트
	@Test
	@Order(4)
	@DisplayName("4. testModifyUserInfo")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testUpdateUserInfo() throws ServiceException {
		log.trace("testModifyUserInfo(), 마이페이지 기본정보 수정 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		dto.setUser_pw("pw123456");
		
		boolean result = this.service.modifyUserInfo(dto);
		log.info("\t+ 수정 결과: {}", result);
		
	}//testModifyUserInfo()
	
	
	//2-1. 나의 질문글 목록 조회 페이징 처리(내림차순으로)
	@Test
	@Order(2)
	@DisplayName("2. testGetAllMyQuestionList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetAllMyQuestionList() throws ServiceException {
		log.trace("TestGetUserInfo(), 나의 질문글 목록 조회 테스트");
		
//		String user_email = "test@gmail.com";
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<QuestionBardVO> list = this.service.getAllMyQuestionList(cri);
		list.forEach(e -> log.info(e));
		
	}//testGetAllMyQuestionList()
	
	//2-2. 나의 질문글 목록 총 개수 획득
	@Test
	@Order(3)
	@DisplayName("3. testGetMyQuestionTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyQuestionTotalAmount() throws ServiceException {
		log.trace("TestGetUserInfo(), 나의 질문글 목록 총 개수 획득 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		int result = this.service.getMyQuestionTotalAmount(dto.getUser_email());
		log.info("\t+ 나의 질문글 총 개수: {}", result);
		
	}//testGetMyQuestionTotalAmount()
	
	
	//4-1. 나의 댓글 목록 조회 페이징 처리(내림차순으로)
	@Test
	@Order(7)
	@DisplayName("7. testGetAllMyCommentList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetAllMyCommentList() throws ServiceException {
		log.trace("TestGetUserInfo(), 나의 댓글 목록 조회 테스트");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<CommentVO> list = this.service.getAllMyCommentList(cri);
		list.forEach(e -> log.info(e));
		
	}//testGetAllMyCommentList()
	
	//4-2. 나의 댓글 목록 총 개수 획득
	@Test
	@Order(8)
	@DisplayName("8. testGetMyCommentTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyCommentTotalAmount() throws ServiceException {
		log.trace("TestGetUserInfo(), 나의 댓글 목록 총 개수 획득 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		int result = this.service.getMyCommentTotalAmount(dto.getUser_email());
		log.info("\t+ 나의 댓글 총 개수: {}", result);
		
	}//testGetMyCommentTotalAmount()

}// end class
























