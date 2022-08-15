package org.zerock.fmt.mapper;

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
import org.zerock.fmt.domain.UserVO;
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
public class UserMapperTests {

	@Setter(onMethod_= {@Autowired})
	private UserMapper mapper;

	@Test
	@Order(1)
	@DisplayName("  selectAllUser  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void selectAllUser() throws UserException {
		log.trace("selectAllUser. 전체 회원 조회");
		
		List<UserVO> list = this.mapper.selectAllUser();
		list.forEach(log::info);
	}//selectAllUser
	
	@Test
	@Order(2)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void userInfo() throws UserException {
		log.trace("userInfo:특정 회원 조회");
		
		UserVO user = this.mapper.userInfo("email_5");
		log.info("\t + user : {}", user);
		
	}//userInfo
	
	@Test
	@Order(3)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void joinStudent() throws UserException {
		UserVO newStudent = new UserVO("test","p","n","n","20220815","여자","01000","고등","1학년",null,null,null,null,
							"Student",null,null,null,null,null);
		if(this.mapper.joinStudent(newStudent)) {
			log.info("학생회원가입완료");
		}//if
		
	}//joinStudent
	
	@Test
	@Order(4)
	@Timeout(value = 5, unit =TimeUnit.SECONDS)
	void joinTutor() throws UserException {
		
		UserVO newTutor = new UserVO("t3", "t1", "t1", "t1", "20220000", "남자", "010",
				null, null, "재학", "국어", "file:c", null, null, null, null, null, null, null);
		Boolean insertResult = this.mapper.joinTutor(newTutor);
		if(insertResult) {
			log.info("\t + insertResult : {}", insertResult);
			log.info("튜터 가입 완료 -> 메일 송신 필요");
		}//if
		
	}//joinTutor
	
	@Test
	@Order(5)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateUser() throws UserException {
		
		UserVO user = new UserVO("n2","1111","1111","11111","20220815","여자","01000","고등","1학년",null,null,null,null,
				null,null,null,null,null,null);
		Boolean updateResult = this.mapper.updateUser(user);
		if(updateResult) {
			log.info("\t + updateResult : {}", updateResult);
			log.info("회원정보 수정완료");
		}//if
	}//updateUser
	
	@Test
	@Order(6)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void approveTutor() throws UserException {
		
		UserVO tutor = new UserVO("t1", "t1", "t1", "t1", "20220000", "남자", "010",
				null, null, "재학", "국어", "file:c", null, null, null, null, null, null, null);
		boolean updateResult = this.mapper.approveTutor(tutor);
		if(updateResult) {
			log.info("\t + updateResult : {}", updateResult);
			log.info("튜터 승인 완료");
		}//if
	}//approveTutor
	
	@Test
	@Order(7)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void stopUser() throws UserException {

		UserVO user = new UserVO("t1", "t1", "t1", "t1", "20220000", "남자", "010",
				null, null, "재학", "국어", "file:c", null, null, null, null, null, null, null);
		Boolean updateResult = this.mapper.stopUser(user);
		
		if(updateResult) {
			log.info("\t updateResult : {}", updateResult);
			log.info("활동정지 완료");
		};//if
		
	}//stopUser
}//end class
