package org.zerock.fmt.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
		})
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UserMapperTests {

	@Setter(onMethod_= {@Autowired})
	private UserMapper mapper;
	
	@Test
	@Order(1)
	@DisplayName("selectAllUser")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void selectAllUser() throws DAOException {
		log.info("selectStudent 학생 조회");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setCurrPage(1);
		cri.setAmount(10);
		cri.setPagesPerPage(5);
		cri.setKeyword(null);
		cri.setType("E");
		cri.setUser_group("Student");
		List<UserVO> list = this.mapper.selectAllUser(cri);
		list.forEach(log::info);
	}//selectAllUser
	
	@Test
	void selectTutor() throws DAOException {
		log.info("selectTutor 튜터 조회");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setCurrPage(1);
		cri.setAmount(5);
		cri.setPagesPerPage(5);
		cri.setUser_group("Tutor");
		List<UserVO> list = this.mapper.selectAllUser(cri);
		list.forEach(log::info);
	}//selectAllUser
	@Test
	void selectStop() throws DAOException {
		log.info("selectStop 정지 회원 조회");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setCurrPage(1);
		cri.setAmount(5);
		cri.setPagesPerPage(5);
		cri.setUser_status("STOP");
		List<UserVO> list = this.mapper.selectAllUser(cri);
		list.forEach(log::info);
	}//selectAllUser
	
	@Test
	@DisplayName(" AllUserCount ")
	void testAllUserCount() throws DAOException {
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(10);
		cri.setCurrPage(1);
		cri.setPagesPerPage(1);
		cri.setKeyword("고구마");
		cri.setType("N");
		cri.setUser_group("Tutor");
		cri.setUser_status(null);
		int result = this.mapper.userCount(cri);
		log.info("\t+ result: {}", result );
	}
	@Test
	@Order(2)
	@DisplayName("  selectUser  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void selectUser() throws DAOException {
		log.trace("selectUser:특정 회원 조회");
		
		UserVO user = this.mapper.selectUser("sd456@gmail.com");
		log.info("\t + user : {}", user);
		
	}//userInfo
	
	@Test
	@Order(3)
	@DisplayName("insertStudent 학생회원가입 ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void insertStudent() throws DAOException {
		
		UserDTO newStudent = new UserDTO("학생테스트1", "pass1","nick1","name1","19900430","여자","01000000000",
										"고등학생","3학년",null,null,null);
		if(this.mapper.insertStudent(newStudent) == 1) {
			log.info("학생회원가입완료");
		} else log.info("회원가입안됨");
		
	}//joinStudent
	
	@Test
	@Order(4)
	@DisplayName("insertTutor 튜터회원가입")
	@Timeout(value = 5, unit =TimeUnit.SECONDS)
	void insertTutor() throws DAOException {
		
		UserDTO newTutor = new UserDTO("totor@han.net","1111111","튜터2","김한글","11111111","남자","0100000000",
						null,null,"재학","국어","백수전공");
		Integer insertResult = this.mapper.insertTutor(newTutor);
		if(insertResult==1) {
			log.info("\t + insertResult : {}", insertResult);
			log.info("튜터 가입 완료 -> 메일 송신 필요");
		} else log.info("회원가입안됨");
		
	}//joinTutor
	
	@Test
	@Order(5)
	@DisplayName("updateUser 마이페이지 정보수정")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateUser() throws DAOException {
		
		UserDTO user = new UserDTO("STemail_5","pw변경","nick변경","name변경",null,null,
				null,null, null, null, null ,null);
		Integer updateResult = this.mapper.updateUser(user);
		if(updateResult==1) {
			log.info("\t + updateResult : {}", updateResult);
			log.info("회원정보 수정완료");
		}//if
	}//updateUser
	
	@Test
	@Order(6)
	@DisplayName("updateTutorPass 튜터 가입 승인")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateTutorPass() throws DAOException {
		String user_email = "TTemail_4";
		Integer updateResult = this.mapper.updateTutorPass(user_email);
		if(updateResult==1) {
			log.info("\t + updateResult : {}", updateResult);
			log.info("튜터 승인 완료");
		} else log.info("테스트실패");
	}//approveTutor
	
	@Test
	@Order(7)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void stopUser() throws DAOException {

		String user_email = "STemail_4";
		Integer updateResult = this.mapper.updateUserStop(user_email);
		
		if(updateResult==1) {
			log.info("\t updateResult : {}", updateResult);
			log.info("활동정지 완료");
		} else log.info("테스트실패");
		
	}//stopUser
	
	@Test
	@Order(8)
	@Timeout(value = 10, unit = TimeUnit.SECONDS)
	void updateHandGet() throws DAOException {
		
		Integer h_count = 30;
		String user_email = "STemail_4";
		
		int updateResult = this.mapper.updateHandGet(h_count, user_email);
		
		if(updateResult==1) {
			log.info("\t updateResult : {}", updateResult);
			log.info("테스트 성공");
		} else log.info("테스트실패");
		
	}//updateHandGet
	
	@Test
	@Order(9)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateHandUse() throws DAOException {
		
		Integer h_count = 3;
		String user_email = "STemail_3";
		Integer updateResult = this.mapper.updateHandUse(h_count, user_email);
		if(updateResult==1) {
			log.info("\t updateResult : {}", updateResult);
			log.info("테스트 성공");
		} else log.info("테스트실패");
	}//updateHandUse
	
	@Test
	@Order(10)
	@DisplayName("회원가입 닉네임 중복검사")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void testselectNicCheck() throws DAOException {
		log.trace("selectNicCheck");
		
		String nick1 = "나는학생";
		int Result1 = this.mapper.selectNicCheck(nick1);
		log.info("\t + Result1 : {}", Result1);
		
		String nick2 = "닉네";
		int Result2 = this.mapper.selectNicCheck(nick2);
		log.info("\t + Result2 : {}", Result2);
		
	}//selectNicCheck

	@Test
	@Order(11)
	@DisplayName("selectLogin 로그인하기 ")
	void testselectLogin() throws DAOException{
		log.trace("selectLogin() invoked.");
		String user_email = "test@email.net";
		String user_email1 = "now@han.net";
		UserVO vo = this.mapper.loginEmail(user_email1);
		log.info("\t + vo : {}", vo);
	}//selectLogin
	
	@Test
	@Order(12)
	@DisplayName("updatePW 비밀번호변경")
	void testupdatePW() throws DAOException {
		log.trace("updatePW()invoked.");
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		dto.setUser_pw("1111111q");
		int result = this.mapper.updatePW(dto);
		log.info("\t + result : {}", result);
		
	}//updatePW

	@Test
	@DisplayName("findKakao 카카오톡 유저찾기")
	void findKakao() {
		
		HashMap<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("userEmail", "stu0830@han.net");
		
//		UserDTO dto = this.mapper.findKakao(userInfo);
//		log.info("\t + dto : {}", dto);
	}
	
	@Test
	@DisplayName("승인 대기 튜터 리스트")
	void selectWaitTutor() throws DAOException {
		log.trace("selectWaitTutor");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(20);
		cri.setCurrPage(3);
		List<UserVO> list = this.mapper.selectWaitTutor(cri);
		list.forEach(log::info);
	}//selectWaitTutor
	
	@Test
	@DisplayName("승인 대기 튜터 인원수")
	void waitTutorCount() throws DAOException {
		log.trace("waitTutorCount 승인대기 회원수");
		int result = this.mapper.waitTutorCount();
		log.info("\t + result : {}", result);
	}//waitTutorCount
	
	@Test
	@DisplayName("이메일찾기")
	void selectFindEmail() throws DAOException {
		log.trace("selectFindEmail 이메일 찾기");
		String user_phone = "01089814300";
		String result1 = this.mapper.FindEmailreturnString(user_phone);
		log.info("\t+ result1 : {}", result1);
//		int result2 = this.mapper.FindEmailreturnInt(user_phone);
//		log.info("\t + result2 : {}", result2);
		
	}//selectFindEmail
	
	@Test
	@DisplayName("findEmailCheck")
	void findEmailCheckTest() throws DAOException {
		log.trace("findEmailCheck 이메일 중복체크");
		String userEmail = "tutor2@gmail.com";
		int result = this.mapper.findEmailCheck(userEmail);
		log.info("\t + result : {}", result);
	}//findEmailCheck
	
	@Test
	@DisplayName("kakaoCheck")
	void kakaoCheckTest() {
//		String email = "tutor2@gmail.com";
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", "tutor2@gmail.com");
		
		HashMap<String,Object> kakao = this.mapper.kakaoCheck(paramMap);
		log.info("\t + kakao: {}", kakao);
	}//kakaoCheck
	
	@Test
	@DisplayName("kakaoLogin")
	void kakaoLoginTest () {
//		String email ="tutor2@gmail.com";
//		String id = "12134";
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", "childhopp@hanmail.net");
		paramMap.put("id", "2412927328");
		UserVO vo= this.mapper.kakaoLogin(paramMap);
		log.info("\t + kakaoLoginVO : {}", vo);
	}//kakaoLogin
	
	@Test
	@DisplayName("updateKakao")
	void updateKakaoTest() {
//		String email = "tutor2@gmail.com";
//		String id = "12345";
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", "tutor2@gmail.com");
		paramMap.put("id", "12333");
		int result = this.mapper.updateKakao(paramMap);
		log.info("\t + result : {}", result);
	}//updateKakao
	
	@Test
	@DisplayName("kakaoRegister")
	void kakaoRegister() {
		log.trace("kakaoRegister : 카카오 회원가입");
		
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("email", "111");
		paramMap.put("user_pw", "111");
		paramMap.put("user_nick", "111");
		paramMap.put("user_name", "111");
		paramMap.put("user_birth", "111");
		paramMap.put("user_gender", "111");
		paramMap.put("user_phone", "111");
		paramMap.put("st_school", "111");
		paramMap.put("st_grade", "111");
		paramMap.put("kakaologin", "111111");
		
		
		int result = this.mapper.kakaoRegister(paramMap);
		log.info("\r + result : {}", result);
	}//kakaoRegister
	
	@Test
	@DisplayName("kakaoRegisterTu")
	void kakaoRegisterTu() {
		log.trace("kakaoRegisterTu : 튜터회원가입");
		
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("email", "111");
		paramMap.put("user_pw", "111");
		paramMap.put("user_nick", "111");
		paramMap.put("user_name", "111");
		paramMap.put("user_birth", "111");
		paramMap.put("user_gender", "111");
		paramMap.put("user_phone", "111");
		paramMap.put("tt_school", "111");
		paramMap.put("tt_subject", "111");
		paramMap.put("tt_depart", "111");
		paramMap.put("kakaologin", "111111");
		
		
		int result = this.mapper.kakaoRegister(paramMap);
		log.info("\r + result : {}", result);
	}//kakaoRegisterTu
}//end class
