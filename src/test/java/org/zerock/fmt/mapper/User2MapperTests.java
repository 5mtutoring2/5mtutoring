package org.zerock.fmt.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
import org.zerock.fmt.domain.UserVO_tutor;
import org.zerock.fmt.domain.StudentVO;
import org.zerock.fmt.domain.UserVO2;
import org.zerock.fmt.domain.UserVO_Stop;
import org.zerock.fmt.exception.UserException;

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
public class User2MapperTests {

	@Setter(onMethod_= {@Autowired})
	private User2Mapper mapper;
	
	@BeforeAll
	void beforeAll() {
		log.info("beforeAll() invoked.");
		assertNotNull(this.mapper);
		log.info("\t + this.mapper : {}", this.mapper);
		
	}//beforeAll
	
	
	@Test
	@Order(1)
	@DisplayName("  selectAllUser  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void selectAllUser() throws UserException {
		log.trace("selectAllUser. 전체 회원 조회");
		
		List<UserVO2> list = this.mapper.selectAllUser();
		list.forEach(log::info);
		
	}//selectAllUser
	
	@Test
	@Order(2)
	@DisplayName("  userInfo  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void userInfo() throws UserException {
		log.trace("userInfo. 회원 상세 정보 조회");
		
		String user_email = "Temail_10";
		UserVO2 userInformation = this.mapper.userInfo(user_email);
		log.info("\t+ userInformation : {}", userInformation);
		
	}//userInfo
	
	@Disabled
	@Test
	@Order(3)
	@DisplayName("  insertUser  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void insertUser() throws UserException {
		log.trace("insertUser. 회원가입 회원 추가" );
		
		
//		UserVO2 newUser = new UserVO2("5e", "1q", "1n", "1n", "1g", new Date(11111111), 
//										"000", "Student", "고등학교", "3학년", null, null, null, null);
//        
//		boolean insertResult =this.mapper.insertUser(newUser);
	
//		log.info("\t + New User: {} " + newUser);
//		log.info("\t + insertResult : {}", insertResult);
		
	}//insertUser 
	
	
	@Test
	@DisplayName(" updateUser ")
	@Order(4)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateUser() throws UserException {
		log.trace("updateUser. 회원정보 수정 테스트");
		
//		UserVO2 userVO = new UserVO2("Temail_4", "22222", "nick", null, null, null, null, null, null, null, null, null, null, "C:file");
//		boolean updateResult = this.mapper.updateUser(userVO);
//		
//		log.info("\t + userVO : {}", userVO);
//		log.info("\t + updateResult : " + updateResult);
		
	}//updateUser
	
	
	@Test
	@Order(5)
	@DisplayName(" approveTutor ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void approveTutor() throws UserException {
		log.trace("approveTutor. 튜터 승인 테스트");
		
		String user_email = "Temail_2";
		String pass = "nooooo";
		
		UserVO_tutor tutor = new UserVO_tutor(user_email, pass);
		boolean approveResult = this.mapper.approveTutor(tutor);
		log.info("\t + approveResult :" + approveResult);
		
	}//approveTutor
	
	@Test
	@Order(6)
	@DisplayName("  stopUser  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void stopUser() throws UserException {
		log.trace("stopUser. 회원정지테스트" );
		
		String user_email = "email_8";
		String user_status="정지";
		UserVO_Stop user = new UserVO_Stop(user_email, user_status);
		
		boolean sqlResult = this.mapper.stopUser(user);
		log.info("\t sqlResult : " + sqlResult);
		
		
	}//stopUser
	
	@Test
	@Order(7)
	@DisplayName(" joinStudent ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void joinStudent() throws UserException {
		log.trace("joinStudent. 학생 회원가입 ");
		
		StudentVO student = new StudentVO("e14", "e14", "e14", "e14", "남자", new Date(11111111), "010" , null , "고등", "1학년");
		Boolean result = this.mapper.joinStudent(student);
		
		log.info("\t + result : {}", result);
		
	}//joinStudent
}//end class
